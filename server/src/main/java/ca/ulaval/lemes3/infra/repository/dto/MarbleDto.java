package ca.ulaval.lemes3.infra.repository.dto;

import ca.ulaval.lemes3.domain.Marble;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dizitart.no2.repository.annotations.Entity;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MarbleDto {
    private UUID id;

    public Marble toDomain() {
        return new Marble(id);
    }

    public MarbleDto(Marble marble) {
        this(marble.getId());
    }
}
