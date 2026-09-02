package ca.ulaval.lemes3.domain.actions;

import ca.ulaval.lemes3.MarbleId;
import ca.ulaval.lemes3.domain.PlayerId;
import ca.ulaval.lemes3.domain.Rank;
import ca.ulaval.lemes3.ui.rest.board.PlayRequest;

import java.util.UUID;

public class PlayActionFactory {

    public PlayAction createPlayAction(PlayRequest playRequest) {
        PlayerId pId = new PlayerId(playRequest.playerId());
        MarbleId mId = new MarbleId(playRequest.marbleId());
        return switch (playRequest.card().rank()) {

            case Rank.ONE -> isStarter(playRequest) ? new PlayActionStarter(pId, mId) : new PlayActionMove(pId, mId, 1);
            case Rank.TWO -> new PlayActionMove(pId, mId, 2);
            case Rank.THREE -> new PlayActionMove(pId, mId, 3);
            case Rank.FOUR -> new PlayActionMove(pId, mId, -4);
            case Rank.FIVE -> new PlayActionMove(pId, mId, 5);
            case Rank.SIX -> new PlayActionMove(pId, mId, 6);
            case Rank.SEVEN -> isSplit(playRequest) ? new PlayActionSplit(pId, mId) : new PlayActionMove(pId, mId, 7);
            case Rank.EIGHT -> new PlayActionMove(pId, mId, 8);
            case Rank.NINE -> new PlayActionMove(pId, mId, 9);
            case Rank.TEN -> new PlayActionMove(pId, mId, 10);
            case Rank.JACK -> isSwitch(playRequest) ? new PlayActionSwitch(pId, mId) : new PlayActionMove(pId, mId, 11);
            case Rank.QUEEN -> new PlayActionMove(pId, mId, 12);
            case Rank.KING -> isStarter(playRequest) ? new PlayActionStarter(pId, mId) : new PlayActionMove(pId, mId, 13);

            default -> throw new UnsupportedOperationException("Invalid play request");
        };
    }

    private boolean isStarter(PlayRequest playRequest) {
        return playRequest.args() != null && "starter".equals(playRequest.args().get("type"));
    }

    private boolean isSplit(PlayRequest playRequest) {
        return playRequest.args() != null && "split".equals(playRequest.args().get("type"));
    }

    private boolean isSwitch(PlayRequest playRequest) {
        return playRequest.args() != null && "switch".equals(playRequest.args().get("type"));
    }
}
