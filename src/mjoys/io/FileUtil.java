package mjoys.io;

import java.io.File;

public class FileUtil {
	public static void handle(File file, FileFilter filter, FileHandler handler, Object ctx) {
		if (file.exists() == false) {
			return;
		}
		
		if (file.isFile()) {
			if (filter == null || filter.accept(file)) {
				handler.handle(file, ctx);
			}
			return;
		}
		
		for (File child : file.listFiles()) {
			handle(child, filter, handler, ctx);
		}
	}
}
