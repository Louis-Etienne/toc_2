package ca.ulaval.lemes3.infra.audio_sharing;

import ca.ulaval.lemes3.application.IdGenerator;
import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.PartyId;

import java.util.UUID;

public class UUIDIdGenerator implements IdGenerator {
    @Override
    public PartyId generatePartyId() {
        String uuid = UUID.randomUUID().toString();
        PartyId partyId = new PartyId(uuid);
        return partyId;
    }

    @Override
    public ListenerId generateListenerId() {
        String uuid = UUID.randomUUID().toString();
        ListenerId listenerId = new ListenerId(uuid);
        return listenerId;
    }
}
