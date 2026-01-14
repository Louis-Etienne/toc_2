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
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class AudioSharingRessource {

    private AudioSharingService audioSharingService;
    private PartyAssembler partyAssembler;

    @Inject
    public AudioSharingRessource(AudioSharingService audioSharingService, PartyAssembler partyAssembler) {
        this.audioSharingService = audioSharingService;
        this.partyAssembler = partyAssembler;
    }

    @GET
    @Path("/listener")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createListener() {
        ListenerId listenerId = audioSharingService.createListener();
        ListenerCreationResponse listenerCreationResponse = partyAssembler.fromDomain(listenerId);
        return Response.status(Response.Status.CREATED).entity(listenerCreationResponse).build();
    }

    @POST
    @Path("/party/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createParty(ListenerRequest listenerRequest) {
        ListenerId listenerId = partyAssembler.toDomainListenerId(listenerRequest);
        PartyCreationInformations partyCreationInformations = audioSharingService.createParty(listenerId);
        PartyCreationResponse partyCreationResponse = partyAssembler.fromDomain(partyCreationInformations);
        return Response.status(Response.Status.CREATED).entity(partyCreationResponse).build();
    }

    @POST
    @Path("/party/join")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response joinParty(JoinPartyRequest joinPartyRequest) {
        ListenerId listenerId = partyAssembler.toDomainListenerId(joinPartyRequest);
        PartyId partyId = partyAssembler.toDomainPartyId(joinPartyRequest);
        PartyCreationInformations partyCreationInformations = audioSharingService.joinParty(partyId, listenerId);
        PartyCreationResponse partyCreationResponse = partyAssembler.fromDomain(partyCreationInformations);
        return Response.status(Response.Status.OK).entity(partyCreationResponse).build();
    }

    @GET
    @Path("/party")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getParty() {
        return Response.ok().build();
    }
}
