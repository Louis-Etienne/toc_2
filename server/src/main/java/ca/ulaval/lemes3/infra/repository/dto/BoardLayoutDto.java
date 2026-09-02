package ca.ulaval.lemes3.infra.repository.dto;

import ca.ulaval.lemes3.MarbleId;
import ca.ulaval.lemes3.domain.BoardLayout;
import ca.ulaval.lemes3.domain.PlayerId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dizitart.no2.repository.annotations.Entity;

import java.util.*;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BoardLayoutDto {
    private UUID[] track;
    private Map<UUID, List<UUID>> homes;
    private Map<UUID, List<UUID>> heavens;

    public BoardLayout toDomain() {
        MarbleId[] trackDomain = Arrays.stream(track).map(id -> id == null ? null : new MarbleId(id)).toArray(MarbleId[]::new);
        Map<PlayerId, List<MarbleId>> homesDomain = homes.entrySet().stream()
                .collect(Collectors.toMap(entry -> new PlayerId(entry.getKey()), entry -> entry.getValue().stream().map(MarbleId::new).toList()));
        Map<PlayerId, List<MarbleId>> heavensDomain = heavens.entrySet().stream()
                .collect(Collectors.toMap(entry -> new PlayerId(entry.getKey()), entry -> entry.getValue().stream().map(MarbleId::new).toList()));

        return new BoardLayout(trackDomain, homesDomain, heavensDomain);

    }

    public BoardLayoutDto(BoardLayout boardLayout) {
        track = Arrays.stream(boardLayout.getTrack()).map(marbleId -> marbleId == null ? null : marbleId.id()).toArray(UUID[]::new);

        homes = boardLayout.getHomes().entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey().id(),
                entry -> entry.getValue().stream().map(marbleId -> marbleId == null ? null : marbleId.id()).toList()));

        heavens = boardLayout.getHeavens().entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey().id(),
                entry -> entry.getValue().stream().map(marbleId -> marbleId == null ? null : marbleId.id()).toList()));
    }
}
