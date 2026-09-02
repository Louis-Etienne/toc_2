package ca.ulaval.lemes3.domain;

import java.util.UUID;

public class Player {
    private final PlayerId id;

    public Player(PlayerId id) {
        this.id = id;
    }

    public PlayerId getId() {
        return id;
    }
}
