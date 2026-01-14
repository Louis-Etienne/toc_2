package server_initialization;

import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.*;

public class ServerExtension implements BeforeAllCallback, BeforeEachCallback, AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        ServerExtensionHelper.stopServer();
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        ServerExtensionHelper.startServer();
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        ServerExtensionHelper.compileProject();
        RestAssured.baseURI = ServerExtensionHelper.URL;
    }
}
