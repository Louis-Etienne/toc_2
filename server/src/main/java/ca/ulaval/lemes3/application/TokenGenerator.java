package ca.ulaval.lemes3.application;

import ca.ulaval.lemes3.application.dto.Token;
import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.PartyId;

public interface TokenGenerator {
    public Token generate(PartyId partyId, ListenerId listenerId);
}
