package ca.ulaval.lemes3.domain.actions;

import ca.ulaval.lemes3.MarbleId;
import ca.ulaval.lemes3.domain.Board;
import ca.ulaval.lemes3.domain.PlayerId;

import java.util.UUID;

public class PlayActionMove extends PlayAction {
    private int step;

    public PlayActionMove(PlayerId playerID, MarbleId marbleID, int step) {
        super(playerID, marbleID);
        this.step = step;
    }

    @Override
    public void play(Board board) {
        board.moveMarble(playerID, marbleID, step);
    }

}
