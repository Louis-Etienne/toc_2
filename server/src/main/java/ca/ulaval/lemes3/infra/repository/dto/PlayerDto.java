package ca.ulaval.lemes3.infra.repository.dto;

import ca.ulaval.lemes3.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dizitart.no2.repository.annotations.Entity;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlayerDto {
    private UUID id;

    public Player toDomain() {
        return new Player(id);
    }

    public PlayerDto(Player player) {
        this.id = player.getId();
    }

}
