package ca.ulaval.lemes3.ui.rest.board;

import ca.ulaval.lemes3.domain.Board;
import ca.ulaval.lemes3.domain.actions.PlayAction;
import ca.ulaval.lemes3.domain.actions.PlayActionFactory;
import ca.ulaval.lemes3.infra.repository.BoardRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("")
public class BoardResource {
    private final BoardRepository boardRepository;
    private final PlayActionFactory playActionFactory;

    public BoardResource(BoardRepository boardRepository, PlayActionFactory playActionFactory) {
        this.boardRepository = boardRepository;
        this.playActionFactory = playActionFactory;
    }

    @POST
    @Path("/play")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response play(PlayRequest playRequest) {
        Board board = boardRepository.get(playRequest.boardId());

        PlayAction actionToPlay = playActionFactory.createPlayAction(playRequest);
        actionToPlay.play(board);

        boardRepository.save(board);

        return Response.status(Response.Status.OK).build();
    }

}
