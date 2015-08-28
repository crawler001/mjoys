package mjoys.io;

import java.io.File;

public class FileUtil {
	public static void handle(File file, FileFilter filter, FileHandler handler, Object ctx) {
		if (file.exists() == false) {
			return;
		}
		
		if (filter == null || filter.accept(file)) {
			handler.handle(file, ctx);
		}
		
		if (file.isFile()) {
			return;
		}
		
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				if (!child.equals(file))
					handle(child, filter, handler, ctx);
			}
		}
	}
}
