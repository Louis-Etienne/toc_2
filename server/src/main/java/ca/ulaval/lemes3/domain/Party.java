package ca.ulaval.lemes3.domain;

import java.util.List;

public class Party {

    private PartyId id;
    private List<Listener> listeners;

    public Party(PartyId partyId, List<Listener> listenerList) {
        this.id = partyId;
        this.listeners = listenerList;
    }

    public void join(Listener listener) {
        listeners.add(listener);
    }

    public PartyId getId() {
        return id;
    }

    public boolean hasListener(ListenerId listenerId) {
        for (Listener listener : listeners) {
            if (listener.getId().equals(listenerId)) {
                return true;
            }
        }
        return false;
    }
}
