package server_initialization;

import io.restassured.RestAssured;

import java.io.File;

public class ServerExtensionHelper {
    private static final String PORT = "8181";
    public static final String URL = "http://localhost:" + PORT;
    private static final String HEARTBEAT_PATH = "/api/heartbeat";
    private static final long SERVER_START_TIMEOUT = 30_000;
    private static Process process;

    public static void compileProject() throws Exception {
        ProcessBuilder processBuilder = new ProcessBuilder("mvn", "compile");
        processBuilder.directory(new File(".."));
        processBuilder.inheritIO();
        Process process = processBuilder.start();
        process.waitFor();
    }

    public static void startServer() throws Exception {
        ProcessBuilder server = new ProcessBuilder("mvn", "exec:java", "-pl", "server", String.format("-Dport=%s", PORT));
        server.directory(new File(".."));
        server.inheritIO();
        process = server.start();
        waitUntilServerIsReady(URL);
    }

    public static void stopServer() throws Exception {
        if (process != null) {
            process.destroy();
            process.waitFor();
        }
    }

    private static void waitUntilServerIsReady(String url) throws Exception {
        long start = System.currentTimeMillis();

        while (System.currentTimeMillis() - start < SERVER_START_TIMEOUT) {
            try {
                if (RestAssured.get(url + HEARTBEAT_PATH).statusCode() == 200) {
                    return;
                }
            } catch (Exception ignored) {
            }
            Thread.sleep(200);
        }

        throw new RuntimeException("Server at " + URL + " failed to start within timeout");
    }

}
