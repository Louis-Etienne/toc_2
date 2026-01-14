package ca.ulaval.lemes3.application;

import ca.ulaval.lemes3.application.dto.PartyCreationInformations;
import ca.ulaval.lemes3.application.dto.Token;
import ca.ulaval.lemes3.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AudioSharingServiceTest {

    private AudioSharingService audioSharingService;

    private PartyRepository partyRepository;

    private PartyFactory partyFactory;

    private IdGenerator idGenerator;

    private TokenGenerator tokenGenerator;

    private SocketService socketService;

    private ListenerId listenerId;

    private Listener listener;

    private PartyId partyId;

    private Party party;

    private Token token;

    @BeforeEach
    public void setUp() {
        partyRepository = mock(PartyRepository.class);
        partyFactory = mock(PartyFactory.class);
        idGenerator = mock(IdGenerator.class);
        tokenGenerator = mock(TokenGenerator.class);
        socketService = mock(SocketService.class);

        audioSharingService = new AudioSharingService(partyRepository, partyFactory, idGenerator, tokenGenerator, socketService);
    }

    @Test
    void given_whenCreateParty_thenPartyFactoryCreatesListener() {
        wireCreatePartyMock();

        audioSharingService.createParty(listenerId);

        verify(partyFactory).createListener(listenerId);
    }

    @Test
    void given_whenCreateParty_thenPartyFactoryCreatesParty() {
        wireCreatePartyMock();

        audioSharingService.createParty(listenerId);

        verify(partyFactory).create(eq(partyId), anyList());
    }

    @Test
    void given_whenCreateParty_thenSaveParty() {
        wireCreatePartyMock();

        audioSharingService.createParty(listenerId);

        verify(partyRepository).save(party);
    }

    @Test
    void given_whenCreateParty_thenGenerateToken() {
        wireCreatePartyMock();

        audioSharingService.createParty(listenerId);

        verify(tokenGenerator).generate(partyId, listenerId);
    }

    @Test
    void given_whenCreateParty_thenCreateSocketConnection() {
        wireCreatePartyMock();

        audioSharingService.createParty(listenerId);

        verify(socketService).create(partyId, listenerId);
    }

    @Test
    void given_whenCreateParty_thenReturnsPartyCreationInformations() {
        wireCreatePartyMock();

        PartyCreationInformations result = audioSharingService.createParty(listenerId);

        assertEquals(new PartyCreationInformations(token, partyId, listenerId), result);
    }

    @Test
    void given_whenJoinParty_thenFindPartyFromId() {
        wireJoinPartyMock();

        audioSharingService.joinParty(partyId, listenerId);

        verify(partyRepository).findPartyById(partyId);
    }

    @Test
    void given_whenJoinParty_thenCreateListener() {
        wireJoinPartyMock();

        audioSharingService.joinParty(partyId, listenerId);

        verify(partyFactory).createListener(listenerId);
    }

    @Test
    void given_whenJoinParty_thenListenerJoinsParty() {
        wireJoinPartyMock();

        audioSharingService.joinParty(partyId, listenerId);

        verify(party).join(listener);
    }

    @Test
    void given_whenJoinParty_thenSaveParty() {
        wireJoinPartyMock();

        audioSharingService.joinParty(partyId, listenerId);

        verify(partyRepository).save(party);
    }

    @Test
    void given_whenJoinParty_thenGenerateToken() {
        wireJoinPartyMock();

        audioSharingService.joinParty(partyId, listenerId);

        verify(tokenGenerator).generate(partyId, listenerId);
    }

    @Test
    void given_whenJoinParty_thenJoinSocketConnection() {
        wireJoinPartyMock();

        audioSharingService.joinParty(partyId, listenerId);

        verify(socketService).join(partyId, listenerId);
    }

    @Test
    void given_whenJoinParty_thenReturnPartyCreationInformations() {
        wireJoinPartyMock();

        PartyCreationInformations partyCreationInformations = audioSharingService.joinParty(partyId, listenerId);

        assertEquals(new PartyCreationInformations(token, partyId, listenerId), partyCreationInformations);
    }

    @Test
    void given_whenCreateListener_thenReturnListenerId() {
        ListenerId listenerId = mock(ListenerId.class);
        when(idGenerator.generateListenerId()).thenReturn(listenerId);

        ListenerId result = audioSharingService.createListener();

        assertEquals(listenerId, result);
    }

    private void wireCreatePartyMock() {
        listenerId = mock(ListenerId.class);
        listener = mock(Listener.class);
        partyId = mock(PartyId.class);
        party = mock(Party.class);
        token = mock(Token.class);

        when(partyFactory.createListener(listenerId)).thenReturn(listener);
        when(idGenerator.generatePartyId()).thenReturn(partyId);
        when(partyFactory.create(partyId, new ArrayList<>())).thenReturn(party);
        when(tokenGenerator.generate(partyId, listenerId)).thenReturn(token);
    }

    private void wireJoinPartyMock() {
        partyId = mock(PartyId.class);
        listenerId = mock(ListenerId.class);
        party = mock(Party.class);
        listener = mock(Listener.class);
        token = mock(Token.class);

        when(partyRepository.findPartyById(partyId)).thenReturn(party);
        when(partyFactory.createListener(listenerId)).thenReturn(listener);
        when(tokenGenerator.generate(partyId, listenerId)).thenReturn(token);
    }

}