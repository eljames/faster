package org.faster.sentsize;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.faster.connection.Connection;
import org.faster.pathmap.CpmDefault;
import org.faster.pathmap.PathMap;
import org.faster.request.FakeConnection;
import org.junit.Test;

public class SentSizeTest {
	
	
	@Test
	public void returnsRecursiveSize() throws IOException {
		String requestath = System.getProperty("user.dir") + "/src/test/resources";
		final StringBuilder inBuilder = new StringBuilder();
		inBuilder
			.append("/media")
			.append("\n");
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ByteArrayInputStream in = new ByteArrayInputStream(inBuilder.toString().getBytes());
		Connection con = new FakeConnection(out, in);
		
		PathMap map = new CpmDefault()
				.add("/media", System.getProperty("user.dir") + requestath + "/org/faster/responsepaths")
				.map();
		
		new SentSize(con, map).send();
		assertEquals(new String(out.toByteArray()), "k\n85\n");
	}
}