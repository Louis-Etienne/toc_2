package ca.ulaval.lemes3.helper;

import ca.ulaval.lemes3.MarbleId;
import ca.ulaval.lemes3.domain.*;

import java.util.*;

public class BoardBuilder {
    private UUID id = UUID.randomUUID();
    private Deck deck = new Deck(new ArrayList<>());
    private List<Player> players = new ArrayList<>();
    private List<Marble> marbles = new ArrayList<>();
    private MarbleId[] track = new MarbleId[1];
    private Map<PlayerId, List<MarbleId>> homes = new HashMap<>();
    private Map<PlayerId, MarbleId[]> heavens = new HashMap<>();
    private int heavenSize = 4;

    public static BoardBuilder aBoard() {
        return new BoardBuilder();
    }

    public BoardBuilder withPlayer(PlayerId playerId) {
        players.add(new Player(playerId));
        return this;
    }

    public BoardBuilder withMarble(MarbleId marbleId) {
        marbles.add(new Marble(marbleId));
        return this;
    }

    public BoardBuilder withNoMarbles() {
        marbles.clear();
        return this;
    }

    public BoardBuilder withMarbleInHome(PlayerId playerId, MarbleId marbleId) {
        withMarble(marbleId);
        homes.computeIfAbsent(playerId, id -> new ArrayList<>()).add(marbleId);
        return this;
    }

    public BoardBuilder withHeavenSize(int size) {
        heavenSize = size;
        return this;
    }

    public BoardBuilder withMarbleInHeaven(PlayerId playerId, MarbleId marbleId, int tileIndex) {
        withMarble(marbleId);
        MarbleId[] heaven = heavens.computeIfAbsent(playerId, id -> new MarbleId[heavenSize]);
        heaven[tileIndex] = marbleId;
        return this;
    }

    public BoardBuilder withMarbleOnTrack(MarbleId marbleId, int tileIndex) {
        withMarble(marbleId);
        track[tileIndex] = marbleId;
        return this;
    }

    public Board build() {
        BoardLayout layout = new BoardLayout(track, homes, heavens);
        return new Board(id, deck, players, marbles, layout);
    }

}
