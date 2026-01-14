package ca.ulaval.lemes3.application;

import ca.ulaval.lemes3.application.dto.PartyCreationInformations;
import ca.ulaval.lemes3.application.dto.Token;
import ca.ulaval.lemes3.domain.*;
import jakarta.inject.Inject;

import java.util.ArrayList;

public class AudioSharingService {

    private PartyRepository partyRepository;
    private PartyFactory partyFactory;
    private IdGenerator idGenerator;
    private TokenGenerator tokenGenerator;
    private SocketService socketService;

    @Inject
    public AudioSharingService(PartyRepository partyRepository, PartyFactory partyFactory, IdGenerator idGenerator, TokenGenerator tokenGenerator,
            SocketService socketService) {
        this.partyRepository = partyRepository;
        this.partyFactory = partyFactory;
        this.idGenerator = idGenerator;
        this.tokenGenerator = tokenGenerator;
        this.socketService = socketService;
    }

    public PartyCreationInformations createParty(ListenerId listenerIdHost) {
        Listener listenerHost = partyFactory.createListener(listenerIdHost);

        PartyId partyId = idGenerator.generatePartyId();
        Party newParty = partyFactory.create(partyId, new ArrayList<Listener>());
        partyRepository.save(newParty);

        Token token = tokenGenerator.generate(partyId, listenerIdHost);

        socketService.create(partyId, listenerIdHost);

        return new PartyCreationInformations(token, partyId, listenerIdHost);
    }

    public PartyCreationInformations joinParty(PartyId partyId, ListenerId listenerId) {
        Party party = partyRepository.findPartyById(partyId);

        Listener listener = partyFactory.createListener(listenerId);

        party.join(listener);

        partyRepository.save(party);

        Token token = tokenGenerator.generate(partyId, listenerId);

        socketService.join(partyId, listenerId);

        return new PartyCreationInformations(token, partyId, listenerId);
    }

    public ListenerId createListener() {
        ListenerId listenerId = idGenerator.generateListenerId();
        return listenerId;
    }
}
