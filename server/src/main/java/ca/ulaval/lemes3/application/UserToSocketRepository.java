package ca.ulaval.lemes3.application;

import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.ListenerNotFoundException;
import ca.ulaval.lemes3.domain.PartyId;
import ca.ulaval.lemes3.domain.PartyNotFoundException;
import io.socket.socketio.server.SocketIoSocket;
import javassist.NotFoundException;

public interface UserToSocketRepository {
    void put(ListenerId listenerId, SocketIoSocket socket);
    void put(PartyId partyId, Object[] offer);
    SocketIoSocket get(ListenerId listenerId) throws ListenerNotFoundException;
    ListenerId get(SocketIoSocket socket) throws ListenerNotFoundException;
    Object[] getOffer(PartyId partyId) throws PartyNotFoundException;
}
