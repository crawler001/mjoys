package mjoys.socket.tcp.server;

import java.io.IOException;
import java.net.Socket;

import mjoys.util.Logger;

public class ClientConnection<T> {
    private T context;
    private Socket socket;
    private SocketServer<T> server;
    private ClientHandler<T> handler;
    private Logger logger = new Logger().addPrinter(System.out);
    
    public ClientConnection(SocketServer<T> server, Socket socket, ClientConnectionHandler<T> handler) {
    	this.server = server;
        this.socket = socket;
        this.handler = new ClientHandler<T>(this, handler);
        new Thread(new ClientHandler<T>(this, handler)).start();
    }
    
    public void disconnect() {
    	if (socket == null && this.socket.isClosed()) {
    		return;
    	}
    	
        try {
            this.socket.close();
            logger.log("close connection: %s, %s", 
            		this.socket.getLocalSocketAddress().toString(),
                    this.socket.getRemoteSocketAddress().toString());
        } catch (IOException e) {
            logger.log("close exception: server=%s, client=%s", 
                    e,
                    this.socket.getLocalSocketAddress().toString(),
                    this.socket.getRemoteSocketAddress().toString());
        }
    }
    
    public void remove() {
    	this.server.removeConnection(this);
    }
    
    public T getContext() {
        return context;
    }

    public void setContext(T context) {
        this.context = context;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public ClientHandler<T> getHandler() {
    	return this.handler;
    }
}
