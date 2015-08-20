package mjoys.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class JavaObjectSerializer implements Serializer {
	@Override
	public void encode(Object obj, OutputStream stream) throws IOException {
        ObjectOutputStream objOut = new ObjectOutputStream(stream);
        objOut.writeObject(obj);
        objOut.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T decode(InputStream stream, Class<T> tclass) throws IOException, ClassNotFoundException {
        ObjectInputStream objOut = new ObjectInputStream(stream);
        Object obj = objOut.readObject();
        objOut.close();
        if (obj.getClass().equals(tclass)) {
        	return (T) obj;
        } else {
        	throw new ClassNotFoundException();
        }
	}
}
