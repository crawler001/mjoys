package mjoys.socket.tcp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import mjoys.util.Address;
import mjoys.util.Logger;

public class SocketServer<T> {
    private ServerSocket socket;
    
    private Acceptor acceptor;
    private Thread acceptThread;
    private ClientConnectionHandler<T> handler;
    private Map<String, ClientConnection<T>> connections;
    
    private final Logger logger = new Logger().addPrinter(System.out);
    
    public boolean start(int port, ClientConnectionHandler<T> handler) {
        try {
            this.socket = new ServerSocket(port);
        } catch (IOException e) {
            logger.log("bind exception: address=%s", e, socket.getLocalSocketAddress().toString());
            return false;
        }
        
        this.handler = handler;
        this.connections = new HashMap<String, ClientConnection<T>>();
        
        this.acceptor = new Acceptor();
        this.acceptThread = new Thread(this.acceptor);
        this.acceptThread.start();
        
        return true;
    }
    
    public void stop() {
       for (ClientConnection<T> c : connections.values()) {
           c.stop();
       }
       
       this.acceptor.stop();
       
       this.socket = null;
       this.acceptor = null;
       this.connections.clear();
    }
    
    public void disconnect(String address) {
        ClientConnection<T> c = connections.get(address);
        if (c != null) {
            c.stop();
            connections.remove(socket);
        }
    }
    
    public void addConnection(Socket socket) {
        Address peerAddress = Address.fromSocketAddress(socket.getRemoteSocketAddress());
        ClientConnection<T> connection = new ClientConnection<T>(socket, handler);
        this.connections.put(peerAddress.toString(), connection);
    }
    
    public boolean setClientContext(String address, T ctx) {
        ClientConnection<T> c = connections.get(address);
        if (c != null) {
            if (c.getContext() == null) {
                c.setContext(ctx);
                return true;
            }
            logger.log("context has setted:%s", address);
            return false;
        } else {
            logger.log("can't find the connection:%s", address);
            return false;
        }
    }
    
    public T getContext(String address) {
        ClientConnection<T> c = connections.get(address);
        if (c != null) {
            return c.getContext();
        }
        return null;
    }
    
    public ServerSocket getSocket() {
        return socket;
    }
}
