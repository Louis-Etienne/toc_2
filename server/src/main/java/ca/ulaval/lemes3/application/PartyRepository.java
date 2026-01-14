package ca.ulaval.lemes3.application;

import ca.ulaval.lemes3.domain.*;

public interface PartyRepository {

    public Listener findListenerById(ListenerId id) throws ListenerNotFoundException;

    public Party findPartyById(PartyId id) throws PartyNotFoundException;

    public void save(Party party);

    Party findPartyByListenerId(ListenerId listenerId) throws PartyNotFoundException;
}
