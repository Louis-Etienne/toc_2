package ca.ulaval.lemes3.infra.audio_sharing;

import ca.ulaval.lemes3.application.PartyRepository;
import ca.ulaval.lemes3.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryPartyRepository implements PartyRepository {
    private final static Logger logger = LoggerFactory.getLogger(InMemoryPartyRepository.class);

    private Map<PartyId, Party> parties = new HashMap<>();

    @Override
    public Listener findListenerById(ListenerId id) {
        return null;
    }

    @Override
    public Party findPartyById(PartyId id) {
        if (parties.containsKey(id)) {
            return parties.get(id);
        } else {
            throw new PartyNotFoundException("Party not found with id : " + id);
        }
    }

    @Override
    public void save(Party party) {
        parties.put(party.getId(), party);
    }

    @Override
    public Party findPartyByListenerId(ListenerId listenerId) {
        List<Party> listParties = new ArrayList<>(parties.values());
        for (Party party : listParties) {
            if (party.hasListener(listenerId)) {
                return party;
            }
        }

        throw new ListenerNotFoundException("Party not found with listener id : " + listenerId);
    }
}
