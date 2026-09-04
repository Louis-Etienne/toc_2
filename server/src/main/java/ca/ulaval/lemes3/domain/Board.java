package ca.ulaval.lemes3.domain;

import ca.ulaval.lemes3.MarbleId;
import ca.ulaval.lemes3.domain.exceptions.InvalidMoveException;

import java.util.List;
import java.util.UUID;

public class Board {

    private Deck deck;
    private List<Player> players;
    private List<Marble> marbles;
    private final UUID id;
    private BoardLayout boardLayout;

    public Board(UUID id, Deck deck, List<Player> players, List<Marble> marbles, BoardLayout boardLayout) {
        this.deck = deck;
        this.players = players;
        this.marbles = marbles;
        this.id = id;
        this.boardLayout = boardLayout;
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

    public BoardLayout getBoardLayout() {
        return boardLayout;
    }

    public void moveMarble(PlayerId playerID, MarbleId marbleID, int step) {
        if (marbles.isEmpty()) {
            throw new InvalidMoveException("No marbles on the board!");
        }

        if (players.stream().noneMatch(player -> player.getId().equals(playerID))) {
            throw new InvalidMoveException("Player " + playerID + " is not on the board!");
        }

        if (marbles.stream().noneMatch(marble -> marble.getId().equals(marbleID))) {
            throw new InvalidMoveException("Marble " + marbleID + " is not on the board!");
        }

        if (boardLayout.isHome(marbleID)) {
            throw new InvalidMoveException("Marble " + marbleID + " is still in home!");
        }

        if (boardLayout.isInHeaven(marbleID)) {
            moveInHeaven(playerID, marbleID, step);
        }

    }

    private void moveInHeaven(PlayerId playerId, MarbleId marbleId, int step) {
        if (boardLayout.isHeavenMoveOutOfRange(playerId, marbleId, step)) {
            throw new InvalidMoveException("Marble " + marbleId + " is out of range in heaven!");
        }
        if (boardLayout.isHeavenBlocked(playerId, marbleId, step)) {
            throw new InvalidMoveException("Marble " + marbleId + " is blocked in heaven!");
        }

    }

    private void moveInTrack(PlayerId playerID, MarbleId marbleID, int step) {

    }

}
