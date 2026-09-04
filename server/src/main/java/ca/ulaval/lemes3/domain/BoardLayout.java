package ca.ulaval.lemes3.domain;

import ca.ulaval.lemes3.MarbleId;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BoardLayout {
    private final int numberOfTilePerPlayer = 18;
    private final int numberOfPlayers = 4;
    private MarbleId[] track;
    private Map<PlayerId, List<MarbleId>> homes;
    private Map<PlayerId, MarbleId[]> heavens;

    public BoardLayout(MarbleId[] trackDomain, Map<PlayerId, List<MarbleId>> homesDomain, Map<PlayerId, MarbleId[]> heavensDomain) {
        this.track = trackDomain;
        this.homes = homesDomain;
        this.heavens = heavensDomain;
    }

    public MarbleId[] getTrack() {
        return track;
    }

    public Map<PlayerId, List<MarbleId>> getHomes() {
        return homes;
    }

    public Map<PlayerId, MarbleId[]> getHeavens() {
        return heavens;
    }

    public boolean isHome(MarbleId marbleId) {
        return homes.values().stream().anyMatch(marbleIds -> marbleIds.contains(marbleId));
    }

    public boolean isInHeaven(MarbleId marbleId) {
        return heavens.values().stream().anyMatch(heaven -> Arrays.asList(heaven).contains(marbleId));
    }

    public boolean isHeavenMoveOutOfRange(PlayerId playerId, MarbleId marbleId, int step) {
        MarbleId[] heaven = heavens.get(playerId);
        for (int i = 0; i < heaven.length; i++) {
            if (marbleId.equals(heaven[i])) {
                return i + step >= heaven.length;
            }
        }
        return true;
    }

    public boolean isHeavenBlocked(PlayerId playerId, MarbleId marbleId, int step) {
        MarbleId[] heaven = heavens.get(playerId);

        for (int i = 0; i < heaven.length; i++) {

            if (marbleId.equals(heaven[i])) {

                int destinationIndex = i + step;

                for (int j = i + 1; j < destinationIndex; j++) {
                    if (heaven[j] != null) {
                        return true;
                    }

                }
                return false;
            }
        }
        return true;
    }
}
