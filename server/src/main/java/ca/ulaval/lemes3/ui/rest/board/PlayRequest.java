package ca.ulaval.lemes3.ui.rest.board;

import ca.ulaval.lemes3.domain.Card;

import java.util.Map;
import java.util.UUID;

public record PlayRequest(UUID boardId, UUID playerId, UUID marbleId, Card card, Map<String, String> args) {

}
