package ca.ulaval.lemes3.ui.rest.game;

import ca.ulaval.lemes3.ui.rest.game.dto.GameActionRequestDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/game")
public class GameResource {

    @POST
    @Path("/actions")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handleGameAction(GameActionRequestDTO gameActionRequestDTO) {
        return Response.ok().build();
    }
}
