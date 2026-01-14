package ca.ulaval.lemes3.ui.socket_io.server;

import ca.ulaval.lemes3.ui.socket_io.filter.CorsFilter;
import ca.ulaval.lemes3.ui.socket_io.filter.RemoteAddrFilter;
import io.socket.engineio.server.EngineIoServer;
import io.socket.engineio.server.EngineIoServerOptions;
import io.socket.engineio.server.JettyWebSocketHandler;
import io.socket.socketio.server.SocketIoNamespace;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoSocket;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.server.config.JettyWebSocketServletContainerInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.EnumSet;

public class SocketIoServerWrapper {

    private static final Logger logger = LoggerFactory.getLogger(SocketIoServerWrapper.class);

    private final EngineIoServerOptions engineIoServerOptions;
    private final EngineIoServer engineIoServer;
    private final SocketIoServer socketIoServer;

    public SocketIoServerWrapper() {
        engineIoServerOptions = EngineIoServerOptions.newFromDefault();
        engineIoServer = new EngineIoServer(engineIoServerOptions);
        socketIoServer = new SocketIoServer(engineIoServer);
    }

    public ServletContextHandler createContextHandler() {
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/socket.io");

        contextHandler.addFilter(CorsFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        contextHandler.addFilter(RemoteAddrFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        ServletHolder engineIoServlet = new ServletHolder(new HttpServlet() {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
                engineIoServer.handleRequest(request, response);
            }
        });
        contextHandler.addServlet(engineIoServlet, "/*");

        JettyWebSocketServletContainerInitializer.configure(contextHandler, (servletContext, container) -> {
            container.setMaxTextMessageSize(128 * 1024);
            container.addMapping("/", (req, resp) -> new JettyWebSocketHandler(engineIoServer));
        });

        return contextHandler;
    }

    public SocketIoServer getSocketIoServer() {
        return this.socketIoServer;
    }
}
