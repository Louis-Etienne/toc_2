package rest.audio_sharing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import server_initialization.ServerExtension;

import static io.restassured.RestAssured.given;

@ExtendWith(ServerExtension.class)
public class AudioSharingIT {
    @Test
    void given_whenGetListener_thenListenerIdIsReturnAndCreated() {
        given()

                .when().get("/api/listener")

                .then().statusCode(201);
    }
}
