package mjoys.util;

public class NullUtil {
	public <T> T replace(T t, T defaultValue) {
		if (t == null) {
			return defaultValue;
		}
		return t;
	}
}
