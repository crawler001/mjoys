package mjoys.util;

import java.io.File;

public class PathUtil {
    public final static String combine(String path, String name) {
        return path + File.separator + name;
    }
}