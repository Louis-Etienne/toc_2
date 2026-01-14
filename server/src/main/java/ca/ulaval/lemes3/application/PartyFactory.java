package ca.ulaval.lemes3.application;

import ca.ulaval.lemes3.domain.Listener;
import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.Party;
import ca.ulaval.lemes3.domain.PartyId;

import java.util.ArrayList;
import java.util.List;

public class PartyFactory {
    public Party create(PartyId partyId, List<Listener> listeners) {
        return new Party(partyId, listeners);
    }

    public Listener createListener(ListenerId listenerIdHost) {
        return new Listener(listenerIdHost);
    }
}
