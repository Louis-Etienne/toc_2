package ca.ulaval.lemes3.infra.repository.dto;

import ca.ulaval.lemes3.domain.Board;
import ca.ulaval.lemes3.domain.Marble;
import ca.ulaval.lemes3.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dizitart.no2.repository.annotations.Entity;
import org.dizitart.no2.repository.annotations.Id;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BoardDto {
    @Id
    private UUID id;
    private DeckDto deck;
    private List<PlayerDto> players;
    private List<MarbleDto> marbles;

    public Board toDomain() {
        List<Marble> marblesDomain = marbles.stream().map(MarbleDto::toDomain).toList();
        List<Player> playersDomain = players.stream().map(PlayerDto::toDomain).toList();
        return new Board(id, deck.toDomain(), playersDomain, marblesDomain);
    }

    public BoardDto(Board board) {
        this(board.getId(), new DeckDto(board.getDeck()), board.getPlayers().stream().map(PlayerDto::new).toList(),
                board.getMarbles().stream().map(MarbleDto::new).toList());
    }
}
