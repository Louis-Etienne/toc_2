package ca.ulaval.lemes3.infra.audio_sharing.socket_io;

import ca.ulaval.lemes3.application.UserToSocketRepository;
import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.Party;
import ca.ulaval.lemes3.application.PartyRepository;
import ca.ulaval.lemes3.ui.rest.audio_sharing.assembler.PartyAssembler;
import io.socket.socketio.server.SocketIoSocket;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebRTCConnectionHandler {

    private final static Logger logger = LoggerFactory.getLogger(WebRTCConnectionHandler.class);

    private UserToSocketRepository userToSocketRepository;
    private PartyRepository partyRepository;
    private PartyAssembler partyAssembler;

    @Inject
    public WebRTCConnectionHandler(UserToSocketRepository userToSocketRepository, PartyRepository partyRepository,
                                   PartyAssembler partyAssembler) {
        this.userToSocketRepository = userToSocketRepository;
        this.partyRepository = partyRepository;
        this.partyAssembler = partyAssembler;
    }

    public void handleInitialConnection(SocketIoSocket socket) {
        String id = socket.getInitialQuery().get("listenerId");
        ListenerId listenerId = partyAssembler.toDomainListenerId(id);
        userToSocketRepository.put(listenerId, socket);

        logger.info("socket :" + socket.getId() + " mapped with id : " + id);
    }

    public void handleOffer(SocketIoSocket socket, Object[] objects){
        ListenerId listenerId = userToSocketRepository.get(socket);

        Party party = partyRepository.findPartyByListenerId(listenerId);

        userToSocketRepository.put(party.getId(), objects);

        socket.broadcast(party.getId().toString(), "offer", objects);

        logger.info("socket :" + socket.getId() + "  broadcast offer");
    }

    public void handleAnswer(SocketIoSocket socket, Object[] objects){
        ListenerId listenerId = userToSocketRepository.get(socket);

        Party party = partyRepository.findPartyByListenerId(listenerId);

        socket.broadcast(party.getId().toString(), "answer", objects);

        logger.info("socket :" + socket.getId() + " broadcast answer");
    }

    public void handleIceCandidate(SocketIoSocket socket, Object[] objects){
        ListenerId listenerId = userToSocketRepository.get(socket);

        Party party = partyRepository.findPartyByListenerId(listenerId);

        socket.broadcast(party.getId().toString(), "ice_candidate", objects);

        logger.info("socket :" + socket.getId() + " broadcast ice_candidate");
    }
}
