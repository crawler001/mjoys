package mjoys.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.StringReader;

public class SystemUtil {
	private final static Logger logger = new Logger().addPrinter(System.out);
	
	public static String run(String cmd) {
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			
			String s = null;
            StringBuilder str = new StringBuilder();
            InputStream in = p.getInputStream();
			if (in.available() > 0) {
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(in));
	            while ((s = stdIn.readLine()) != null) {
	                str.append(s).append("\r\n");
	            }
			}
			in.close();
			
			InputStream error = p.getErrorStream();
			if (error.available() > 0) {
				BufferedReader stdError = new BufferedReader(new InputStreamReader(error));
	            while ((s = stdError.readLine()) != null) {
	                str.append(s).append("\r\n");
	            }
			}
			error.close();
            
			p.waitFor();
			p.destroy();
			logger.log("run cmd: %s\r\nresult: %s", cmd, str.toString());
	        return str.toString();
		} catch (Exception e) {
			logger.log("run cmd exception", e);
			return null;
		}
	}
	
	public static int getPid(String pname) {
		String result = run("ps");
		LineNumberReader reader = new LineNumberReader(new StringReader(result));
		String s = null;
		try {
			while((s = reader.readLine()) != null) {
				if (s.toLowerCase().contains(pname.toLowerCase())) {
					String[] words = s.split("\\s");
					return NumberUtil.parseInt(words[0].trim());
				}
			}
		}
		catch(IOException e) {
			return -1;
		}
		return -1;
	}
	
	public static int getPidByJps(String pname) {
		String result = run("jps -l");
		LineNumberReader reader = new LineNumberReader(new StringReader(result));
		String s = null;
		try {
			while((s = reader.readLine()) != null) {
				if (s.toLowerCase().contains(pname.toLowerCase())) {
					String[] words = s.split("\\s");
					return NumberUtil.parseInt(words[0].trim());
				}
			}
		}
		catch(IOException e) {
			return -1;
		}
		return -1;
	}
}
