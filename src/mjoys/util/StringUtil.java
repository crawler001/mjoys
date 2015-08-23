package mjoys.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class StringUtil {
    public static byte[] toBytes(String str, String code) {
        try {
            return str.getBytes(code);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String getString(byte[] bytes, String code) {
    	return getString(bytes, 0, bytes.length, code);
    }
    
    public static String getString(byte[] bytes, int offset, int length, String code) {
        try {
            return new String(bytes, offset, length, code);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    
    public static String getUTF8String(ByteBuffer buf) {
    	return getUTF8String(buf.array(), buf.position(), buf.remaining());
    }
    
    public static String getUTF8String(byte[] bytes, int offset, int length) {
    	return getString(bytes, offset, length, "UTF-8");
    }
    
    public static String getUTF8String(byte[] bytes) {
        return getString(bytes, "UTF-8");
    }
    
    public static boolean isEmpty(String str) {
    	if (str == null || str.isEmpty()) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean isNotEmpty(String str) {
    	return !isEmpty(str); 
    }
}
