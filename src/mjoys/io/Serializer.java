package mjoys.io;

import java.io.InputStream;
import java.io.OutputStream;

public interface Serializer {
    void encode(Object obj, OutputStream stream) throws SerializerException;
    <T> T decode(InputStream stream, Class<T> tclass) throws SerializerException;
}