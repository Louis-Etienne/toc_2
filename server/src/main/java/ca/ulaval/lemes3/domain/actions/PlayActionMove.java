package ca.ulaval.lemes3.domain.actions;

import ca.ulaval.lemes3.domain.Board;

import java.util.UUID;

public class PlayActionMove extends PlayAction {
    private int step;

    public PlayActionMove(UUID playerID, UUID marbleID, int step) {
        super(playerID, marbleID);
        this.step = step;
    }

    @Override
    public void play(Board board) {

    }

}
