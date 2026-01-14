package ca.ulaval.lemes3.infra.audio_sharing;

import ca.ulaval.lemes3.domain.Listener;
import ca.ulaval.lemes3.domain.ListenerId;
import ca.ulaval.lemes3.domain.Party;
import ca.ulaval.lemes3.domain.PartyId;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryPartyRepositoryTest {

    private InMemoryPartyRepository repository;

    private Party party;

    private Listener listener;

    private PartyId partyId;

    private ListenerId listenerId;

    @BeforeEach
    public void setUp() {

        repository = new InMemoryPartyRepository();
    }
}