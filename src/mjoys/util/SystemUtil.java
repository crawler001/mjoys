package mjoys.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SystemUtil {
	private final static Logger logger = new Logger().addPrinter(System.out);
	
	public static String run(String cmd) {
		try {
			Process p = Runtime.getRuntime().exec(cmd);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line = reader.readLine();
	        StringBuilder str = new StringBuilder();
	        while (line != null) {
	            str.append(line).append(System.lineSeparator());
	            line = reader.readLine();
	        }
	        return str.toString();
		} catch (Exception e) {
			logger.log("run cmd exception", e);
			return null;
		}
	}
}
