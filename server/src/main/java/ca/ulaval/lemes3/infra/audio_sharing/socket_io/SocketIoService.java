package ca.ulaval.lemes3.infra.audio_sharing.socket_io;

import ca.ulaval.lemes3.application.UserToSocketRepository;
import ca.ulaval.lemes3.application.SocketService;
import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.PartyId;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketIoService implements SocketService {

    private final static Logger logger = LoggerFactory.getLogger(SocketIoService.class);

    private SocketIoServer socketIoServer;
    private UserToSocketRepository userToSocketRepository;
    private WebRTCConnectionHandler connectionHandler;

    @Inject
    public SocketIoService(SocketIoServer socketIoServer, UserToSocketRepository userToSocketRepository, WebRTCConnectionHandler connectionHandler) {
        this.socketIoServer = socketIoServer;
        this.userToSocketRepository = userToSocketRepository;
        this.connectionHandler = connectionHandler;
    }

    @PostConstruct
    public void init() {
        SocketIoNamespace ns = socketIoServer.namespace("/");

        ns.on("connection", emitter -> {
            SocketIoSocket socket = (SocketIoSocket) emitter[0];

            initialConnection(socket);
            registerListeners(socket);
        });
    }

    @Override
    public void create(PartyId partyId, ListenerId listenerId) {
        SocketIoSocket socket = userToSocketRepository.get(listenerId);

        String roomId = partyId.toString();
        socket.joinRoom(roomId);

        logger.info("socket :" + socket.getId() + " created the room : " + roomId);
    }

    @Override
    public void join(PartyId partyId, ListenerId listenerId) {
        SocketIoSocket socket = userToSocketRepository.get(listenerId);

        String roomId = partyId.toString();
        socket.joinRoom(roomId);

        Object[] offer = userToSocketRepository.getOffer(partyId);

        logger.info("Offer : " + offer);

        socketIoServer.namespace("/").broadcast(roomId, "offer", offer);

        logger.info("socket :" + socket.getId() + " joined the room : " + roomId);
    }

    private void initialConnection(SocketIoSocket socket) {
        connectionHandler.handleInitialConnection(socket);
    }

    private void registerListeners(SocketIoSocket socket) {
        socket.on("offer", objects -> connectionHandler.handleOffer(socket, objects));
        socket.on("answer", objects -> connectionHandler.handleAnswer(socket, objects));
        socket.on("ice_candidate", objects -> connectionHandler.handleIceCandidate(socket, objects));
    }
}
