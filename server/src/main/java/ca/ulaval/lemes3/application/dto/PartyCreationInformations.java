package ca.ulaval.lemes3.application.dto;

import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.PartyId;

public record PartyCreationInformations(Token token, PartyId partyId, ListenerId listenerId) {
}
