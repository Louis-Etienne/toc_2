package ca.ulaval.lemes3.domain;

import ca.ulaval.lemes3.MarbleId;

import java.util.UUID;

public class Marble {
    private final MarbleId id;

    public Marble(MarbleId id) {
        this.id = id;
    }

    public MarbleId getId() {
        return id;
    }
}
