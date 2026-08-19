package ca.ulaval.lemes3.infra.repository;

import ca.ulaval.lemes3.domain.Board;

import java.util.UUID;

public interface BoardRepository {
    public Board get(UUID id);
    public void save(Board board);
}
