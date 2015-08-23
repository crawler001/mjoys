package mjoys.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class JavaObjectSerializer implements Serializer {
	@Override
	public void encode(Object obj, OutputStream stream) throws SerializerException {
		try {
	        ObjectOutputStream objOut = new ObjectOutputStream(stream);
	        objOut.writeObject(obj);
	        objOut.close();
		} catch (IOException e) {
			throw new SerializerException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T decode(InputStream stream, Class<T> tclass) throws SerializerException {
		try {
	        ObjectInputStream objOut = new ObjectInputStream(stream);
	        Object obj = objOut.readObject();
	        objOut.close();
        	return (T) obj;
        } catch (IOException e) {
        	throw new SerializerException(e);
        } catch (ClassNotFoundException e) {
        	throw new SerializerException(e);
        }
	}
}
