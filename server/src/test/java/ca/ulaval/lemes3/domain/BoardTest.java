package ca.ulaval.lemes3.domain;

import ca.ulaval.lemes3.MarbleId;
import ca.ulaval.lemes3.domain.exceptions.InvalidMoveException;
import ca.ulaval.lemes3.helper.BoardHelper;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    public void givenNoMarble_whenMoveMarble_thenThrow() {
        Board board = BoardHelper.createBoardWithNoMarbles();
        PlayerId playerID = new PlayerId(UUID.randomUUID());
        MarbleId marbleID = new MarbleId(UUID.randomUUID());
        int step = 1;

        assertThrows(InvalidMoveException.class, () -> board.moveMarble(playerID, marbleID, step));
    }

    @Test
    public void givenInvalidPlayer_whenMoveMarble_thenThrow() {
        Board board = BoardHelper.createBasicBoard();
        PlayerId invalidPlayerId = new PlayerId(UUID.randomUUID());
        MarbleId marbleID = new MarbleId(UUID.randomUUID());
        int step = 1;

        assertThrows(InvalidMoveException.class, () -> board.moveMarble(invalidPlayerId, marbleID, step));

    }

    @Test
    public void givenInvalidMarble_whenMoveMarble_thenThrow() {
        PlayerId validPlayerId = new PlayerId(UUID.randomUUID());
        Board board = BoardHelper.createBasicBoard(validPlayerId);
        MarbleId invalidMarbleID = new MarbleId(UUID.randomUUID());
        int step = 1;

        assertThrows(InvalidMoveException.class, () -> board.moveMarble(validPlayerId, invalidMarbleID, step));

    }

}