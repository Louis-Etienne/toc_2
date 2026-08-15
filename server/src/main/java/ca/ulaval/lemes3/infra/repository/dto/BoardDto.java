package ca.ulaval.lemes3.infra.repository.dto;

import ca.ulaval.lemes3.domain.Board;
import ca.ulaval.lemes3.domain.Marble;
import ca.ulaval.lemes3.domain.Player;

import java.util.List;
import java.util.UUID;

public record BoardDto(UUID id, DeckDto deck, List<PlayerDto> players, List<MarbleDto> marbles) {

    public Board toDomain() {
        List<Marble> marblesDomain = marbles.stream().map(MarbleDto::toDomain).toList();
        List<Player> playersDomain = players.stream().map(PlayerDto::toDomain).toList();
        return new Board(id, deck.toDomain(), playersDomain, marblesDomain );
    }
}
