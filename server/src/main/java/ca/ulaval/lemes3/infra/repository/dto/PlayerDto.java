package ca.ulaval.lemes3.infra.repository.dto;

import ca.ulaval.lemes3.domain.Player;
import ca.ulaval.lemes3.domain.PlayerId;
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
        PlayerId playerId = new PlayerId(id);
        return new Player(playerId);
    }

    public PlayerDto(Player player) {
        this.id = player.getId().id();
    }

}
