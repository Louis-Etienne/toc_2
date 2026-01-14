package ca.ulaval.lemes3.application;

import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.PartyId;

public interface IdGenerator {
    PartyId generatePartyId();
    ListenerId generateListenerId();
}
