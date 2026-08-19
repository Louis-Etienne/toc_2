package ca.ulaval.lemes3.infra.repository;

import ca.ulaval.lemes3.infra.repository.dto.BoardDto;
import org.dizitart.no2.Nitrite;

import ca.ulaval.lemes3.domain.Board;
import org.dizitart.no2.mvstore.MVStoreModule;
import org.dizitart.no2.repository.ObjectRepository;

import java.util.UUID;

import static org.dizitart.no2.filters.FluentFilter.where;

public class BoardRepositoryNoSql implements BoardRepository {

    private final ObjectRepository<BoardDto> repository;

    public BoardRepositoryNoSql() {
        MVStoreModule store = MVStoreModule.withConfig()
                .filePath("data/app.db")
                .build();

        Nitrite db = Nitrite.builder()
                .loadModule(store)
                .openOrCreate();

        repository = db.getRepository(BoardDto.class);
    }

    @Override
    public Board get(UUID id) {
        return repository.find(
                where("id").eq(id)).firstOrNull().toDomain();
    }

    @Override
    public void save(Board board) {
        BoardDto dto = new BoardDto(board);
        repository.update(dto, true);
    }
}
