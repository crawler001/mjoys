package mjoys.util;

import java.io.File;

public class PathUtil {
    public final static String combine(String ...es) {
    	StringBuilder str = new StringBuilder();
    	for (String e : es) {
    		str.append(e).append(File.separator);
    	}
    	if (es.length > 0)
    		str.setLength(str.length() - File.separator.length());
    	return str.toString();
    }
    
    public final static String replaceEnvInPath(String path) {
    	String rep = File.separator;
    	if (rep.equals("\\")) {
    		rep = "\\\\";
    	}
    	String[] words = path.split(rep);
    	for (int i = 0; i < words.length; i++) {
    		String word = words[i];
    		if (word.startsWith("$")) {
    			words[i] = System.getenv(word.substring(1));
    			if (StringUtil.isEmpty(words[i])) {
    				return path;
    			}
    		}
    	}
    	return combine(words);
    }
}