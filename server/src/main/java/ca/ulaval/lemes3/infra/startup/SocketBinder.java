package ca.ulaval.lemes3.infra.startup;

import io.socket.socketio.server.SocketIoServer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class SocketBinder extends AbstractBinder {

    private final SocketIoServer socketIoServer;

    public SocketBinder(SocketIoServer socketIoServer) {
        this.socketIoServer = socketIoServer;
    }

    @Override
    protected void configure() {
        bind(socketIoServer).to(SocketIoServer.class);
    }
}
