package mjoys.util;

public interface Serializer {
    byte[] encode(Object obj);
    Object decode(byte[] data);
}