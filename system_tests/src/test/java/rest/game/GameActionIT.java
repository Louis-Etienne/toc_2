package rest.game;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import server_initialization.ServerExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static io.restassured.RestAssured.given;

@ExtendWith(ServerExtension.class)
public class GameActionIT {
    private final String PATH_GAME_ACTION_REQUEST = "src/test/resources/rest/game/GameActionRequest.json";

    @Test
    void given_whenPostGameAction_thenReturns200() throws IOException {
        String requestBody = Files.readString(Path.of(PATH_GAME_ACTION_REQUEST));

        given().header("Content-Type", "application/json").body(requestBody)

                .when().post("/api/game/actions")

                .then().statusCode(200);
    }

}
