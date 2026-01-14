package ca.ulaval.lemes3.ui.rest.audio_sharing.mapper;

import ca.ulaval.lemes3.domain.PartyNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class PartyNotFoundExceptionMapper implements ExceptionMapper<PartyNotFoundException> {
    private static String BAD_REQUEST = "Bad Request";
    private final Logger logger;

    public PartyNotFoundExceptionMapper() {
        this.logger = LoggerFactory.getLogger(PartyNotFoundExceptionMapper.class);
    }

    @Override
    public Response toResponse(PartyNotFoundException exception) {
        logger.error(BAD_REQUEST, exception);
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
