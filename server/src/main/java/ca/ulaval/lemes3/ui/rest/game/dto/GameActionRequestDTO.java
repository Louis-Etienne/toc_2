package ca.ulaval.lemes3.ui.rest.game.dto;

import java.util.List;

public record GameActionRequestDTO(String gameId, String playerId, String actionName, List<ParameterRequestDTO> parameters) {
}
