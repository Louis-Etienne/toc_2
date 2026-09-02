package ca.ulaval.lemes3.domain;

import ca.ulaval.lemes3.MarbleId;

import java.util.List;
import java.util.Map;

public class BoardLayout {
    private final int numberOfTilePerPlayer = 18;
    private final int numberOfPlayers = 4;
    private MarbleId[] track;
    private Map<PlayerId, List<MarbleId>> homes;
    private Map<PlayerId, List<MarbleId>> heavens;

    public BoardLayout(MarbleId[] trackDomain, Map<PlayerId, List<MarbleId>> homesDomain, Map<PlayerId, List<MarbleId>> heavensDomain) {
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

    public Map<PlayerId, List<MarbleId>> getHeavens() {
        return heavens;
    }

}
