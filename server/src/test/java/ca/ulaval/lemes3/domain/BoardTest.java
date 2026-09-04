package ca.ulaval.lemes3.domain;

import ca.ulaval.lemes3.MarbleId;
import ca.ulaval.lemes3.domain.exceptions.InvalidMoveException;
import ca.ulaval.lemes3.helper.BoardBuilder;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private PlayerId playerId = new PlayerId(UUID.randomUUID());
    private MarbleId marbleId = new MarbleId(UUID.randomUUID());
    private int step = 1;

    @Test
    public void givenNoMarble_whenMoveMarble_thenThrow() {
        Board board = BoardBuilder.aBoard().withPlayer(playerId).withNoMarbles().build();

        assertThrows(InvalidMoveException.class, () -> board.moveMarble(playerId, marbleId, step));
    }

    @Test
    public void givenInvalidPlayer_whenMoveMarble_thenThrow() {
        Board board = BoardBuilder.aBoard().withMarble(marbleId).build();

        PlayerId invalidPlayerId = new PlayerId(UUID.randomUUID());

        assertThrows(InvalidMoveException.class, () -> board.moveMarble(invalidPlayerId, marbleId, step));
    }

    @Test
    public void givenInvalidMarble_whenMoveMarble_thenThrow() {
        Board board = BoardBuilder.aBoard().withPlayer(playerId).build();

        MarbleId invalidMarbleID = new MarbleId(UUID.randomUUID());

        assertThrows(InvalidMoveException.class, () -> board.moveMarble(playerId, invalidMarbleID, step));
    }

    @Test
    public void givenMarbleInHome_whenMoveMarble_thenThrow() {
        Board board = BoardBuilder.aBoard().withPlayer(playerId).withMarbleInHome(playerId, marbleId).build();

        assertThrows(InvalidMoveException.class, () -> board.moveMarble(playerId, marbleId, step));
    }

    @Test
    public void giveMarbleInHeaven_WhenMoveMarbleOutOfRange_thenThrow() {
        Board board = BoardBuilder.aBoard().withHeavenSize(4).withPlayer(playerId).withMarbleInHeaven(playerId, marbleId, 3).build();

        assertThrows(InvalidMoveException.class, () -> board.moveMarble(playerId, marbleId, 5));

    }

    @Test
    public void givenMarbleBlockingHeaven_whenMoveMarble_thenThrow() {
        MarbleId blockingMarbleId = new MarbleId(UUID.randomUUID());
        Board board = BoardBuilder.aBoard().withHeavenSize(4).withPlayer(playerId).withMarbleInHeaven(playerId, marbleId, 0)
                .withMarbleInHeaven(playerId, blockingMarbleId, 1).build();

        assertThrows(InvalidMoveException.class, () -> board.moveMarble(playerId, marbleId, 2));
    }

}