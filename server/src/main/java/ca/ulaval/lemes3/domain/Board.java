package ca.ulaval.lemes3.domain;

import java.util.List;
import java.util.UUID;

public class Board {

    private Deck deck;
    private List<Player> players;
    private List<Marble> marbles;
    private final UUID id;

    public Board(UUID id, Deck deck, List<Player> players, List<Marble> marbles) {
        this.deck = deck;
        this.players = players;
        this.marbles = marbles;
        this.id = id;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Marble> getMarbles() {
        return marbles;
    }

    public UUID getId() {
        return id;
    }
}
