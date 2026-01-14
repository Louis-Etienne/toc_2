package ca.ulaval.lemes3;

import ca.ulaval.lemes3.ui.rest.server.RestServerWrapper;
import ca.ulaval.lemes3.ui.socket_io.server.SocketIoServerWrapper;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private final static Logger logger = LoggerFactory.getLogger(Main.class);

    private static int port = 8181;

    public static void main(String[] args) {
        String rawPortValue = System.getenv("PORT");
        if (rawPortValue != null) {
            port = Integer.parseInt(rawPortValue);
            logger.info("Using port env port: " + port);
        }
        new Main().run();
    }

    public void run() {
        Server server = new Server(port);

        SocketIoServerWrapper socketIoServerWrapper = new SocketIoServerWrapper();
        ServletContextHandler socketIoHandler = socketIoServerWrapper.createContextHandler();

        RestServerWrapper restServerWrapper = new RestServerWrapper();
        ServletContextHandler restHandler = restServerWrapper.createContextHandler(socketIoServerWrapper);

        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        contextHandlerCollection.addHandler(restHandler);
        contextHandlerCollection.addHandler(socketIoHandler);
        server.setHandler(contextHandlerCollection);

        try {
            server.start();
            logger.info("Server started on port " + port);
            server.join();
        } catch (Exception e) {
            logger.error("Error starting server", e);
        } finally {
            if (server.isRunning()) {
                server.destroy();
            }
        }
    }
}