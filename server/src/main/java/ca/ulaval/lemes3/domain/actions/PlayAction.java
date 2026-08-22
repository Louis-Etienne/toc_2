package ca.ulaval.lemes3.domain.actions;

import ca.ulaval.lemes3.domain.Board;

import java.util.UUID;

public abstract class PlayAction {
    private UUID playerID;
    private UUID marbleID;

    public abstract void play(Board board);

    public PlayAction(UUID playerID, UUID marbleID) {
        this.playerID = playerID;
        this.marbleID = marbleID;
    }

}
