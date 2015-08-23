package mjoys.util;

import java.io.File;

public class PathUtil {
    public final static String combine(String ...es) {
    	StringBuilder str = new StringBuilder();
    	for (String e : es) {
    		str.append(e).append(File.separator);
    	}
    	return str.toString();
    }
}