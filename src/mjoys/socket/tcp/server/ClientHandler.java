package mjoys.socket.tcp.server;

public class ClientHandler<T> implements Runnable {
	private boolean isHandling = true;
    private ClientConnection<T> connection;
    private ClientConnectionHandler<T> handler;
    
    public ClientHandler(ClientConnection<T> connection, ClientConnectionHandler<T> handler) {
        this.connection = connection;
        this.handler = handler;
    }
    
    @Override
    public void run() {
        while (isHandling) {
            int result = handler.handle(connection);
            if (result < 0) {
            	if (isHandling == true) {
            		connection.disconnect();
            		connection.remove();
            		isHandling = false;;
            	}
            }
        }
    }
    
    public void stop() {
    	isHandling = false;
    }
}
