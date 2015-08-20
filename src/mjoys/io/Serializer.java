package mjoys.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Serializer {
    void encode(Object obj, OutputStream stream) throws IOException;
    <T> T decode(InputStream stream, Class<T> tclass) throws IOException, ClassNotFoundException;
}