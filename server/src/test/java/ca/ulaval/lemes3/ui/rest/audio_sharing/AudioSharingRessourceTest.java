package ca.ulaval.lemes3.ui.rest.audio_sharing;

import ca.ulaval.lemes3.application.AudioSharingService;
import ca.ulaval.lemes3.application.dto.PartyCreationInformations;
import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.PartyId;
import ca.ulaval.lemes3.ui.rest.audio_sharing.assembler.PartyAssembler;
import ca.ulaval.lemes3.ui.rest.audio_sharing.dto.JoinPartyRequest;
import ca.ulaval.lemes3.ui.rest.audio_sharing.dto.ListenerCreationResponse;
import ca.ulaval.lemes3.ui.rest.audio_sharing.dto.ListenerRequest;
import ca.ulaval.lemes3.ui.rest.audio_sharing.dto.PartyCreationResponse;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AudioSharingRessourceTest {

    private AudioSharingRessource audioSharingRessource;

    private AudioSharingService audioSharingService;

    private PartyAssembler partyAssembler;

    @BeforeEach
    public void setUp() {
        this.audioSharingService = mock(AudioSharingService.class);
        this.partyAssembler = mock(PartyAssembler.class);

        this.audioSharingRessource = new AudioSharingRessource(audioSharingService, partyAssembler);
    }

    @Test
    void given_whenCreateListener_thenResponseCreated(){
        Response response = audioSharingRessource.createListener();

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    void given_whenCreateListener_thenResponseBodyContainsListenerCreationResponse(){
        ListenerCreationResponse listenerCreationResponse = mock(ListenerCreationResponse.class);
        wireCreateListenerMock(listenerCreationResponse);

        Response response = audioSharingRessource.createListener();

        assertEquals(listenerCreationResponse, response.getEntity());
    }

    @Test
    void given_whenCreateParty_thenResponseCreated(){
        ListenerRequest  listenerRequest = mock(ListenerRequest.class);

        Response response = audioSharingRessource.createParty(listenerRequest);

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    void given_whenCreateParty_thenResponseBodyContainsPartyCreationInformation(){
        ListenerRequest  listenerRequest = mock(ListenerRequest.class);
        PartyCreationResponse partyCreationResponse = mock(PartyCreationResponse.class);
        wireCreatePartyMock(listenerRequest, partyCreationResponse);

        Response response = audioSharingRessource.createParty(listenerRequest);

        assertEquals(partyCreationResponse, response.getEntity());
    }

    @Test
    void given_whenJoinParty_thenResponseOk(){
        JoinPartyRequest  joinPartyRequest = mock(JoinPartyRequest.class);

        Response response = audioSharingRessource.joinParty(joinPartyRequest);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void given_whenJoinParty_thenResponseBodyContainsJoinPartyResponse(){
        JoinPartyRequest  joinPartyRequest = mock(JoinPartyRequest.class);
        PartyCreationResponse partyCreationResponse = mock(PartyCreationResponse.class);
        wireJoinPartyMock(joinPartyRequest, partyCreationResponse);

        Response response = audioSharingRessource.joinParty(joinPartyRequest);

        assertEquals(partyCreationResponse, response.getEntity());
    }

    private void wireCreateListenerMock(ListenerCreationResponse listenerCreationResponse){
        ListenerId listenerId = mock(ListenerId.class);
        when(audioSharingService.createListener()).thenReturn(listenerId);
        when(partyAssembler.fromDomain(listenerId)).thenReturn(listenerCreationResponse);
    }

    private void wireCreatePartyMock(ListenerRequest listenerRequest, PartyCreationResponse partyCreationResponse){
        ListenerId listenerId = mock(ListenerId.class);
        PartyCreationInformations partyCreationInformations = mock(PartyCreationInformations.class);
        when(partyAssembler.toDomainListenerId(listenerRequest)).thenReturn(listenerId);
        when(audioSharingService.createParty(listenerId)).thenReturn(partyCreationInformations);
        when(partyAssembler.fromDomain(partyCreationInformations)).thenReturn(partyCreationResponse);
    }

    private void wireJoinPartyMock(JoinPartyRequest joinPartyRequest, PartyCreationResponse partyCreationResponse){
        ListenerId listenerId = mock(ListenerId.class);
        PartyId partyId = mock(PartyId.class);
        PartyCreationInformations partyCreationInformations = mock(PartyCreationInformations.class);
        when(partyAssembler.toDomainListenerId(joinPartyRequest)).thenReturn(listenerId);
        when(partyAssembler.toDomainPartyId(joinPartyRequest)).thenReturn(partyId);
        when(audioSharingService.joinParty(partyId, listenerId)).thenReturn(partyCreationInformations);
        when(partyAssembler.fromDomain(partyCreationInformations)).thenReturn(partyCreationResponse);
    }
}