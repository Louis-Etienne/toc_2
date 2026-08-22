package ca.ulaval.lemes3.domain.actions;

import ca.ulaval.lemes3.domain.Board;

import java.util.UUID;

public class PlayActionSplit extends PlayAction {

    public PlayActionSplit(UUID playerID, UUID marbleID) {
        super(playerID, marbleID);
    }

    @Override
    public void play(Board board) {

    }
}
