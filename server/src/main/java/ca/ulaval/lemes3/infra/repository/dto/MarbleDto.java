package ca.ulaval.lemes3.infra.repository.dto;

import ca.ulaval.lemes3.domain.Marble;

import java.util.UUID;

public record MarbleDto(UUID id) {

    public Marble toDomain() {
        return new Marble(id);
    }

    public MarbleDto (Marble marble) {
        this(marble.getId());
    }
}
