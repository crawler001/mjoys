package mjoys.util;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Map;

public class Formater {
    public static String formatMac(byte[] mac) {
        return String.format("%02X-%02X-%02X-%02X-%02X-%02X", 
                mac[0],mac[1],mac[2],mac[3],mac[4],mac[5]);
    }
    public static String formatBytes(byte[] bytes, int offset, int length) {
        StringBuilder str = new StringBuilder();
        for (int i = offset; i < offset + length; i++) {
            str.append(String.format("%02X-", bytes[i]));
        }
        str.setLength(str.length() - 1);
        return str.toString();
    }
    
    public static String formatBytes(byte[] bytes) {
    	return formatBytes(bytes, 0, bytes.length);
    }
    
    public static String formatBytes(ByteBuffer buffer) {
    	return formatBytes(buffer.array(), buffer.position(), buffer.remaining());
    }
    
    public static String formatCollection(Collection<?> es) {
        return formatArray(es.toArray());
    }
    
    public static <T> String formatArray(T[] es) {
    	if (es == null) {
    		return "";
    	}
    	
        StringBuilder str = new StringBuilder();
        if (es.length != 0) {
        	str.append('[');
        }
        for (Object e : es) {
            str.append(e.toString()).append(", ");
        }
        if (es.length != 0) {
            str.setLength(str.length() - ", ".length());
            str.append(']');
        }
        return str.toString();
    }
    
    public static String formatEntry(Object key, Object value) {
        return new StringBuilder().append(key).append(":").append(value).toString();
    }
    
    public static String formatEntries(Object ...es) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < es.length - 1; i+=2) {
            str.append(formatEntry(es[i], es[i + 1])).append(", ");
        }
        if (es.length > 0) {
            str.setLength(str.length() - ", ".length());
        }
        return str.toString();
    }
    
    public static String format(Object ...es) {
        return formatArray(es);
    }
    
    public static <K, V> String formatMap(Map<K, V> map) {
    	Object[] set = new Object[map.size() * 2];
    	int i = 0;
    	for (K k : map.keySet()) {
    		set[i++] = k;
    		set[i++] = map.get(k);
    	}
    	return formatEntries(set);
    }
}