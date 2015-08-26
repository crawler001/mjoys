package mjoys.frame;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import mjoys.util.Logger;

public class ByteBufferParser {
	private static Logger logger = new Logger().addPrinter(System.out);
	
	public static List<TLV<ByteBuffer>> parseTLVs(ByteBuffer buffer) {
		List<TLV<ByteBuffer>> tlvs = new ArrayList<TLV<ByteBuffer>>();
		while (buffer.hasRemaining()) {
			TLV<ByteBuffer> tlv = parseTLV(buffer);
			if (tlv == null) {
				break;
			} else {
				tlvs.add(tlv);
			}
		}
		return tlvs;
	}

	public static List<LV<ByteBuffer>> parseLVs(ByteBuffer buffer) {
		List<LV<ByteBuffer>> lvs = new ArrayList<LV<ByteBuffer>>();
		while (buffer.hasRemaining()) {
			LV<ByteBuffer> lv = parseLV(buffer);
			if (lv == null) {
				break;
			} else {
				lvs.add(lv);
			}
		}
		return lvs;
	}

	public static TLV<ByteBuffer> parseTLV(ByteBuffer buffer) {
		TLV<ByteBuffer> tlv = new TLV<ByteBuffer>();
		tlv.tag = buffer.getInt();
		tlv.length = buffer.getInt();
		if (buffer.remaining() < tlv.length) {
			logger.log("parse TLV frame error: bad length");
			return null;
		}
		tlv.body = buffer;
		return tlv;
	}

	public static LV<ByteBuffer> parseLV(ByteBuffer buffer) {
		LV<ByteBuffer> lv = new LV<ByteBuffer>();
		lv.length = buffer.getInt();
		if (buffer.remaining() < lv.length) {
			logger.log("parse LV frame error: bad length");
			return null;
		}
		lv.body = buffer.slice();
		lv.body.limit(lv.body.position() + lv.length);
		buffer.position(buffer.position() + lv.length);
		return lv;
	}

	public static TV<ByteBuffer> parseTV(ByteBuffer buffer) {
		TV<ByteBuffer> tv = new TV<ByteBuffer>();
		tv.tag = buffer.getInt();
		tv.body = buffer;
		return tv;
	}
}
