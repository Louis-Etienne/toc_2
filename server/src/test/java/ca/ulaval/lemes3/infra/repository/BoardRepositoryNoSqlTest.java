package ca.ulaval.lemes3.infra.repository;

import ca.ulaval.lemes3.domain.Board;
import ca.ulaval.lemes3.helper.BoardHelper;
import ca.ulaval.lemes3.infra.repository.dto.BoardDto;
import org.dizitart.no2.mvstore.MVStoreModule;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BoardRepositoryNoSqlTest {

    @Test
    void givenEmptydb_whenGet_thenThrowException() throws IOException {
        Path tempFile = Files.createTempFile("test", ".db");
        MVStoreModule store = MVStoreModule.withConfig().filePath(tempFile.toFile()).build();
        BoardRepositoryNoSql repository = new BoardRepositoryNoSql(store);
        UUID uuid = UUID.randomUUID();

        assertThrows(Exception.class, () -> repository.get(uuid));
    }

    @Test
    void givenABoard_whenGetBoard_thenBoardIsReturned() throws IOException {
        Path tempFile = Files.createTempFile("test", ".db");
        MVStoreModule store = MVStoreModule.withConfig().filePath(tempFile.toFile()).build();
        BoardRepositoryNoSql repository = new BoardRepositoryNoSql(store);

        Board board = BoardHelper.createBasicBoard();
        repository.save(board);
        Board boardResult = repository.get(board.getId());
        BoardDto boardDto = new BoardDto(board);
        BoardDto boardResultDto = new BoardDto(boardResult);

        assertEquals(boardDto, boardResultDto);
    }

}