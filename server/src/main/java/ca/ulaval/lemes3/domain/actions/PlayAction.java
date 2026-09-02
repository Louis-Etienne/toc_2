package ca.ulaval.lemes3.domain.actions;

import ca.ulaval.lemes3.MarbleId;
import ca.ulaval.lemes3.domain.Board;
import ca.ulaval.lemes3.domain.PlayerId;

import java.util.UUID;

public abstract class PlayAction {
    protected PlayerId playerID;
    protected MarbleId marbleID;

    public abstract void play(Board board);

    public PlayAction(PlayerId playerID, MarbleId marbleID) {
        this.playerID = playerID;
        this.marbleID = marbleID;
    }

}
