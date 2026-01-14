package ca.ulaval.lemes3.ui.rest.heartbeat;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("")
public class HearbeatResource {

    @GET
    @Path("/heartbeat")
    public Response heartbeat() {
        return Response.noContent().status(Response.Status.OK).build();
    }
}
