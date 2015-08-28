package mjoys.test;

import java.nio.ByteBuffer;

import mjoys.io.ByteBufferInputStream;
import mjoys.io.ByteBufferOutputStream;
import mjoys.io.JavaObjectSerializer;
import mjoys.io.JsonSerializer;
import mjoys.io.Serializer;
import mjoys.util.PathUtil;
import mjoys.util.SystemUtil;

import org.junit.Assert;
import org.junit.Test;

public class TestAll {
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
	
	@Test
	public void testReplaceEnv() {
		String path = PathUtil.replaceEnvInPath("$JAVA_HOME\\bin");
		Assert.assertEquals(path, "C:\\Program Files\\Java\\jdk1.6.0_39\\bin");
	}
	
    @Test
    public void testGetPid() {
    	Integer pid = SystemUtil.getPidByJps("jps");
    	Assert.assertNotNull(pid);
    }
}
