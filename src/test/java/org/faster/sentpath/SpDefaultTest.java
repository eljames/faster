package org.faster.sentpath;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.faster.pathinfo.request.PathInfoToken;
import org.faster.written.WtDefault;
import org.junit.Test;

public class SpDefaultTest {
	
	
	@Test
	public void sendPathInfo() throws IOException {
		
		String expected = "\n\n" +
				new PathInfoToken()
				.create(false, "/photos/pic.jpg", 21000);
		
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		
		SentPath sent = new SpDefault(
			new WtDefault(
				byteOut
			)
		);
		sent.send(new FakePathInfo(false, "/photos/pic.jpg", 21000));
		
		assertTrue(	
			expected.equals(new String(byteOut.toByteArray()))
		);
	}
}
