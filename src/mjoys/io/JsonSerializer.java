package mjoys.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer implements Serializer {
	private static final ObjectMapper mapper = new ObjectMapper();
    
	@Override
	public void encode(Object obj, OutputStream stream) throws SerializerException {
		try {
			mapper.writeValue(stream, obj);
		} catch (IOException e) {
			throw new SerializerException(e);
		}
	}
	
	@Override
	public <T> T decode(InputStream stream, Class<T> tclass) throws SerializerException {
		try {
			return (T)mapper.readValue(stream, tclass);
		} catch (IOException e) {
			throw new SerializerException(e);
		}
	}
}