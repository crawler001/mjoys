package mjoys.util;

public class ClassUtil {
    private final static Logger logger = new Logger().addPrinter(System.out);
    
    @SuppressWarnings("unchecked")
    public final static <T> T newInstance(String name) {
        try {
            return (T)Class.forName(name).newInstance();
        } catch (Exception e) {
            logger.log("can't create class:%s", e, name);
            return null;
        }
    }
}
