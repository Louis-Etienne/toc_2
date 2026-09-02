package ca.ulaval.lemes3.domain.actions;

import ca.ulaval.lemes3.MarbleId;
import ca.ulaval.lemes3.domain.Board;
import ca.ulaval.lemes3.domain.PlayerId;

import java.util.UUID;

public class PlayActionSwitch extends PlayAction {
    public PlayActionSwitch(PlayerId playerID, MarbleId marbleID) {
        super(playerID, marbleID);
    }

    @Override
    public void play(Board board) {

    }
}
