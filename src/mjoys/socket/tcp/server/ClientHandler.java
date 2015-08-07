package mjoys.socket.tcp.server;

import java.io.IOException;

public class ClientHandler<T> implements Runnable {
    private ClientConnection<T> connection;
    private ClientConnectionHandler<T> handler;
    
    public ClientHandler(ClientConnection<T> connection, ClientConnectionHandler<T> handler) {
        this.connection = connection;
        this.handler = handler;
    }
    
    @Override
    public void run() {
        while (connection.isHandling()) {
            int result = handler.handle(connection);
            if (result < 0) {
                stop();
            }
        }
    }
    
    public void stop() {
        if (connection.isHandling() == false) {
            return;
        }
        
        connection.setHandling(false);
        
        try {
            connection.getSocket().close();
        } catch (IOException e) {
            connection.getLogger().log("close exception: server=%s, client=%s", 
                    e,
                    connection.getSocket().getLocalSocketAddress().toString(),
                    connection.getSocket().getRemoteSocketAddress().toString());
        }
    }
}
