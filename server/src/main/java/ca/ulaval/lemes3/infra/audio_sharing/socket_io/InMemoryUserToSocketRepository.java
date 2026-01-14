package ca.ulaval.lemes3.infra.audio_sharing.socket_io;

import ca.ulaval.lemes3.application.UserToSocketRepository;
import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.ListenerNotFoundException;
import ca.ulaval.lemes3.domain.PartyId;
import ca.ulaval.lemes3.domain.PartyNotFoundException;
import io.socket.socketio.server.SocketIoSocket;
import javassist.NotFoundException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserToSocketRepository implements UserToSocketRepository {

    Map<ListenerId, SocketIoSocket> userToSocket = new HashMap<>();
    Map<String, ListenerId> socketToUser = new HashMap<>();
    Map<PartyId, Object[]> partyToOffer = new HashMap<>();

    @Override
    public void put(ListenerId listenerId, SocketIoSocket socket) {
        userToSocket.put(listenerId, socket);
        socketToUser.put(socket.getId(), listenerId);
    }

    @Override
    public SocketIoSocket get(ListenerId listenerId) throws ListenerNotFoundException {
        if(userToSocket.containsKey(listenerId)){
            return userToSocket.get(listenerId);

        }

        throw new ListenerNotFoundException("Socket not found with listenerId " + listenerId);
    }

    @Override
    public ListenerId get(SocketIoSocket socket) {
        if(socketToUser.containsKey(socket.getId())) {
            return socketToUser.get(socket.getId());
        }

        throw new ListenerNotFoundException("ListenerId not found with socket id " + socket.getId());
    }

    @Override
    public void put(PartyId partyId, Object offer[]) {
        partyToOffer.put(partyId, offer);
    }

    @Override
    public Object[] getOffer(PartyId partyId) throws PartyNotFoundException {
        if (partyToOffer.containsKey(partyId)) {
            return partyToOffer.get(partyId);
        }

        throw new PartyNotFoundException("Party offer not found");
    }

}
