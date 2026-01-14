package rest.heartbeat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import server_initialization.ServerExtension;

import java.io.IOException;

import static io.restassured.RestAssured.given;

@ExtendWith(ServerExtension.class)
public class HeartbeatIT {

    @Test
    void given_whenGetHeartbeat_thenOKReturned() throws IOException {
        given()

                .when().get("/api/heartbeat")

                .then().statusCode(200);
    }
}
