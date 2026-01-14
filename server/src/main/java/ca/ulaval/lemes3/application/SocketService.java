package ca.ulaval.lemes3.application;

import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.PartyId;

public interface SocketService {
    void create(PartyId partyId, ListenerId listenerId);

    void join(PartyId partyId, ListenerId listenerId);
}
