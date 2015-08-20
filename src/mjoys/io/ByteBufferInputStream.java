package mjoys.io;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferInputStream extends InputStream {
	private ByteBuffer buffer;
	
	public ByteBufferInputStream(ByteBuffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public int read() throws IOException {
		if (buffer.hasRemaining() == false) {
			return -1;
		}
		return buffer.get();
	}
}
