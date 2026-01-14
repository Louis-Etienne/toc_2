package ca.ulaval.lemes3.ui.rest.audio_sharing.assembler;

import ca.ulaval.lemes3.application.dto.PartyCreationInformations;
import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.PartyId;
import ca.ulaval.lemes3.ui.rest.audio_sharing.dto.JoinPartyRequest;
import ca.ulaval.lemes3.ui.rest.audio_sharing.dto.ListenerCreationResponse;
import ca.ulaval.lemes3.ui.rest.audio_sharing.dto.ListenerRequest;
import ca.ulaval.lemes3.ui.rest.audio_sharing.dto.PartyCreationResponse;

public class PartyAssembler {

    public PartyCreationResponse fromDomain(PartyCreationInformations partyCreationInformations) {
        return new PartyCreationResponse(partyCreationInformations.token().value(), partyCreationInformations.partyId().value(),
                partyCreationInformations.listenerId().value());
    }

    public ListenerCreationResponse fromDomain(ListenerId listenerId) {
        return new ListenerCreationResponse(listenerId.value());
    }

    public ListenerId toDomainListenerId(ListenerRequest listenerRequest) {
        return new ListenerId(listenerRequest.listenerId());
    }

    public ListenerId toDomainListenerId(JoinPartyRequest joinPartyRequest) {
        return new ListenerId(joinPartyRequest.listenerId());
    }

    public ListenerId toDomainListenerId(String id) {
        return new ListenerId(id);
    }

    public PartyId toDomainPartyId(JoinPartyRequest joinPartyRequest) {
        return new PartyId(joinPartyRequest.partyId());
    }

    public PartyId toDomainPartyId(String id) {
        return new PartyId(id);
    }
}
