package mjoys.socket.tcp.server;

import java.io.IOException;
import java.net.Socket;

import mjoys.util.Logger;

public class Acceptor implements Runnable {
    private SocketServer<?> server;
    private boolean isAccepting = true;
    private final static Logger logger = new Logger().addPrinter(System.out);
    
    public Acceptor(SocketServer<?> server) {
    	this.server = server;
    }
    
    @Override
    public void run() {
        while (isAccepting) {
            try {
            	Socket socket = server.getSocket().accept();
            	server.addConnection(socket);
            	logger.log("new client connection:%s", socket.getRemoteSocketAddress().toString());
            } catch (IOException e) {
                logger.log("accept connection exception: bound address=%s", e, server.getSocket().getLocalSocketAddress().toString());
            }
        }
    }
    
    public void stop() {
        if (isAccepting == false) {
            return;
        }
        
        isAccepting = false;
        
        try {
            server.getSocket().close();
        } catch (IOException e) {
            logger.log("close server socket exception: address=%s", e, server.getSocket().getLocalSocketAddress().toString());
        }
    }
}
