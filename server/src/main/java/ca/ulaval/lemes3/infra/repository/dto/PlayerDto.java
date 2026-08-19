package ca.ulaval.lemes3.infra.repository.dto;

import ca.ulaval.lemes3.domain.Player;

import java.util.UUID;

public record PlayerDto(UUID id) {

    public Player  toDomain() {
        return new Player(id);
    }

    public PlayerDto (Player player) {
        this(player.getId());
    }

}

