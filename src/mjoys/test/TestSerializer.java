package mjoys.test;

import java.nio.ByteBuffer;

import mjoys.io.ByteBufferInputStream;
import mjoys.io.ByteBufferOutputStream;
import mjoys.io.JavaObjectSerializer;
import mjoys.io.JsonSerializer;
import mjoys.io.Serializer;

import org.junit.Assert;
import org.junit.Test;

public class TestSerializer {
	@Test
	public void testJsonSerializer() throws Exception{
		Ts ts = new Ts();
		ts.name = "name";
		ts.age = 3;
		
		testSerializer(new JsonSerializer(), new Object[]{3, "hellow world", 4.0, ts});
		testSerializer(new JavaObjectSerializer(), new Object[]{3, "hellow world", 4.0, ts});
	}
	
	private void testSerializer(Serializer serializer, Object[] objs) throws Exception{
		ByteBuffer buffer = ByteBuffer.allocate(4000);
		for (Object obj : objs) {
			serializer.encode(obj, new ByteBufferOutputStream(buffer));
			buffer.flip();
			Assert.assertEquals(obj, serializer.decode(new ByteBufferInputStream(buffer), obj.getClass()));
			buffer.clear();
		}
	}
}
