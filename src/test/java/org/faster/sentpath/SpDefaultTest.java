package org.faster.sentpath;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringWriter;

import org.faster.pathinfo.request.PathInfoToken;
import org.faster.written.DfWritten;
import org.junit.Test;

public class SpDefaultTest {
	
	
	@Test
	public void sendPathInfo() throws IOException {
		
		String expected = "\n\n" +
				new PathInfoToken()
				.create(false, "/photos/pic.jpg", 21000);
		
		StringWriter strWriter = new StringWriter();
		
		SentPath sent = new SpDefault(
			new DfWritten(
				strWriter
			)
		);
		sent.send(new FakePathInfo(false, "/photos/pic.jpg", 21000));
		
		assertTrue(	
			expected.equals(strWriter.toString())
		);
	}
}
