package mjoys.socket.tcp.server;

public interface ClientConnectionHandler<T> {
    int handle(ClientConnection<T> connection);
}
