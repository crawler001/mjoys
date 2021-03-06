package mjoys.util;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class Address {
    public enum Protocol {
    	Unknown,
        Tcp,
        Udp,
        Pipe
    }
    
    private Protocol protocol;
    private String address;
    
    public Address() {
    	
    }
    
    public Address(Protocol p, String address) {
        this.protocol = p;
        this.address = address;
    }
    
    public static Address newAddress(Protocol p, String address) {
    	return new Address(p, address);
    }
    
    public static Protocol parseProtocol(String protocol) {
    	if (protocol.equalsIgnoreCase(Protocol.Tcp.name())) {
    		return Protocol.Tcp;
    	} else if (protocol.equalsIgnoreCase(Protocol.Udp.name())) {
    		return Protocol.Udp;
    	} else if (protocol.equalsIgnoreCase(Protocol.Udp.name())) {
    		return Protocol.Pipe;
    	} else {
    		return Protocol.Unknown;
    	}
    }
    
    public static String getAddressWithPort(String ip, int port) {
    	return ip + ":" + port;
    }
    
    public static Address getTcpAddress(String ip, int port) {
    	return new Address(Protocol.Tcp, getAddressWithPort(ip, port));
    }
    
    public static Address getUdpAddress(String ip, int port) {
    	return new Address(Protocol.Udp, getAddressWithPort(ip, port));
    }
    
    public static Address parse(String url) {
        if (url.startsWith("tcp://")) {
            return new Address(Protocol.Tcp, url.substring("tcp://".length()));
        } else if (url.startsWith("udp://")) {
            return new Address(Protocol.Udp, url.substring("udp://".length()));
        } else if (url.startsWith("pipe://")) {
            return new Address(Protocol.Pipe, url.substring("pipe://".length()));
        }
        return null;
    }
    
    public InetSocketAddress toSocketAddress() {
        if (protocol == Protocol.Tcp || protocol == Protocol.Udp) {
            String[] ipPort = address.split(":");
            return new InetSocketAddress(ipPort[0], NumberUtil.parseInt(ipPort[1]));
        }
        return null;
    }
    
    public Protocol getProtocol() {
        return this.protocol;
    }
    
    public String getAddress() {
        return this.address;
    }
    
    public static final Address fromSocketAddress(SocketAddress socketAddress) {
        return new Address(Protocol.Tcp, socketAddress.toString());
    }
    
    @Override
    public String toString() {
        return protocol.name().toLowerCase() + "://" + address;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Address) {
            Address addr = (Address) obj;
            if (addr.getProtocol().equals(this.protocol) &&
                addr.getAddress().equals(this.address)) {
                return true;
            }
            return false;
        }
        return false;
    }
}
