package ca.ulaval.lemes3.infra.audio_sharing.socket_io;

import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.ListenerNotFoundException;
import ca.ulaval.lemes3.domain.PartyId;
import ca.ulaval.lemes3.domain.PartyNotFoundException;
import io.socket.socketio.server.SocketIoSocket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class InMemoryUserToSocketRepositoryTest {

    private InMemoryUserToSocketRepository userToSocketRepository;

    private ListenerId listenerId;

    private SocketIoSocket socket;

    private Object[] offer;

    private PartyId partyId;

    @BeforeEach
    public void setUp() {
        listenerId = mock(ListenerId.class);
        socket = mock(SocketIoSocket.class);
        offer = new Object[0];
        partyId = mock(PartyId.class);

        userToSocketRepository = new InMemoryUserToSocketRepository();
    }

    @Test
    void givenSavedSocket_whenGetSocketWithId_thenReturnSocket() {
        userToSocketRepository.put(listenerId, socket);

        SocketIoSocket result =  userToSocketRepository.get(listenerId);

        assertEquals(socket, result);
    }

    @Test
    void givenNothing_whenGetSocketWithId_thenThrowListenerNotFoundException() {
        assertThrows(ListenerNotFoundException.class, () -> userToSocketRepository.get(listenerId));
    }

    @Test
    void givenSavedSocket_whenGetIdWithSocket_thenReturnId(){
        userToSocketRepository.put(listenerId, socket);

        ListenerId result = userToSocketRepository.get(socket);

        assertEquals(listenerId, result);
    }

    @Test
    void givenNothing_whenGetIdWithSocket_thenThrowListenerNotFoundException() {
        assertThrows(ListenerNotFoundException.class, () -> userToSocketRepository.get(socket));
    }

    @Test
    void givenSavedOffer_whenGetOfferWithId_thenReturnOffer(){
        userToSocketRepository.put(partyId, offer);

        Object[] result = userToSocketRepository.getOffer(partyId);

        assertEquals(offer, result);
    }

    @Test
    void givenNothing_whenGetOfferWithId_thenThrowPartyNotFoundException() {
        assertThrows(PartyNotFoundException.class, ()-> userToSocketRepository.getOffer(partyId));
    }
}