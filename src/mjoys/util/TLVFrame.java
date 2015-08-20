package mjoys.util;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class TLVFrame {
    private short type;
    private short length;
    private ByteBuffer value;
    
    public final static int HeadLength = 4;
    
    public short getType() {
        return type;
    }
    public void setType(short type) {
        this.type = type;
    }
    public short getLength() {
        return length;
    }
    public void setLength(short length) {
        this.length = length;
    }
    public ByteBuffer getValue() {
        return value;
    }
    public void setValue(ByteBuffer value) {
        this.value = value;
    }
    
    public static final List<TLVFrame> parseTLVs(ByteBuffer bf) {
        List<TLVFrame> frames = new ArrayList<TLVFrame>();
        
        while (bf.remaining() > HeadLength) {
            short type = bf.getShort();
            short valueLength = bf.getShort();
            
            if (bf.remaining() >= valueLength) {
                TLVFrame frame = new TLVFrame();
                frame.setType(type);
                frame.setLength(valueLength);
                ByteBuffer value = bf.slice();
                value.limit(bf.position() + valueLength);
                bf.position(bf.position() + valueLength);
                frame.setValue(value);
                frames.add(frame);
            } else {
                break;
            }
        }
        
        return frames;
    }
    
    public static final TLVFrame parseTLV(ByteBuffer buffer) {
        List<TLVFrame> frames = parseTLVs(buffer);
        if (frames.size() != 1) {
            return null;
        }
        return frames.get(0);
    }
}
