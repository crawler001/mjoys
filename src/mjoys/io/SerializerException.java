package mjoys.io;

public class SerializerException extends Exception {
	private static final long serialVersionUID = 1L;
	private Exception e;
	
	public SerializerException(Exception e) {
		this.e = e;
	}
	
	@Override
	public String getMessage() {
		e.getStackTrace();
		return e.toString();
	}
	
	@Override
	public StackTraceElement[] getStackTrace() {
		return e.getStackTrace();
	}
	
	@Override
	public Exception getCause() {
		return e;
	}
}
