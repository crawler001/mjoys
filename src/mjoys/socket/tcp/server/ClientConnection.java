package mjoys.socket.tcp.server;

import java.net.Socket;

import mjoys.util.Logger;

public class ClientConnection<T> {
    private T context;
    private Socket socket;
    private boolean isHandling = true;
    private ClientHandler<T> handler;
    private Logger logger = new Logger().addPrinter(System.out);
    
    public ClientConnection(Socket socket, ClientConnectionHandler<T> handler) {
        this.socket = socket;
        this.handler = new ClientHandler<T>(this, handler);
        new Thread(new ClientHandler<T>(this, handler)).start();
    }
    
    public void stop() {
        this.handler.stop();
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

    public boolean isHandling() {
        return isHandling;
    }

    public void setHandling(boolean isHandling) {
        this.isHandling = isHandling;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
