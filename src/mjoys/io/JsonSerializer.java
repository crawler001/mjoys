package mjoys.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer implements Serializer {
	private static final ObjectMapper mapper = new ObjectMapper();
    
	@Override
	public void encode(Object obj, OutputStream stream) throws IOException {
		mapper.writeValue(stream, obj);
	}
	
	@Override
	public <T> T decode(InputStream stream, Class<T> tclass) throws IOException, ClassNotFoundException {
		return (T)mapper.readValue(stream, tclass);
	}
}