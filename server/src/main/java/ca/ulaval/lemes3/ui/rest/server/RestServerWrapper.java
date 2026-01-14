package ca.ulaval.lemes3.ui.rest.server;

import ca.ulaval.lemes3.infra.startup.ServerBinder;
import ca.ulaval.lemes3.infra.startup.SocketBinder;
import ca.ulaval.lemes3.ui.rest.audio_sharing.mapper.PartyNotFoundExceptionMapper;
import ca.ulaval.lemes3.ui.socket_io.server.SocketIoServerWrapper;
import io.socket.socketio.server.SocketIoServer;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class RestServerWrapper {

    public ServletContextHandler createContextHandler(SocketIoServerWrapper socketIoServerWrapper) {
        ServletContextHandler restHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        restHandler.setContextPath("/api");
        ResourceConfig packageConfig = new ResourceConfig().packages("ca.ulaval.lemes3.ui.rest").register(PartyNotFoundExceptionMapper.class)
                .register(new ServerBinder()).register(new SocketBinder(socketIoServerWrapper.getSocketIoServer()));
        ServletContainer container = new ServletContainer(packageConfig);
        ServletHolder servletHolder = new ServletHolder(container);
        restHandler.addServlet(servletHolder, "/*");
        return restHandler;
    }
}
