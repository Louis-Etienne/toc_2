package socket_io;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.engineio.client.transports.WebSocket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import server_initialization.ServerExtension;
import server_initialization.ServerExtensionHelper;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ServerExtension.class)
public class SocketConnectionIT {

    Socket socketHost;

    URI serverUri;

    IO.Options optionsHost;

    CompletableFuture<Object[]> completableFuture;

    @BeforeEach
    void setup() {
        serverUri = URI.create(ServerExtensionHelper.URL);
        optionsHost = new IO.Options().builder().setPath("/socket.io").setTransports(new String[]{WebSocket.NAME}).setQuery("listenerId=host").setForceNew(true)
                .build();
        completableFuture = new CompletableFuture<>();
    }

    @AfterEach
    void tearDown() {
        if (socketHost != null) {
            socketHost.close();
        }
    }

    @Test
    void given_whenSocketsConnect_thenSocketsAreConnected() throws ExecutionException, InterruptedException, TimeoutException {
        socketHost = IO.socket(serverUri, optionsHost);
        socketHost.on(Socket.EVENT_CONNECT, completableFuture::complete);

        socketHost.connect();
        completableFuture.get(5, TimeUnit.SECONDS);

        assertTrue(socketHost.connected());
    }
}
