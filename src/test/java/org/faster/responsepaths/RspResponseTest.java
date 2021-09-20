package org.faster.responsepaths;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.faster.dirmap.DmDefault;
import org.faster.pathinfo.request.PathItemsToken;
import org.faster.sentpath.SpDefault;
import org.faster.written.WtDefault;
import org.junit.Test;

public class RspResponseTest {
	
	@Test
	public void responsePath() throws IOException {
		
		String expected = new PathItemsToken()
				.append(false, "/media/pics/file_test_çã.txt", 25)
				.append(false, "/media/pics/me.txt", 55)
				.append(true, "/media/pics/travel", -1)
				.builder()
				.toString();
		
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		
		ResponsePaths responsed = new RspResponse(
			new ResponsePaths() {
				
				@Override
				public void respond(CharSequence relativePath) throws IOException {}
			},
			new DmDefault(new CreatedPathMapRes().create("/org/faster/responsepaths/root")),
			new SpDefault(new WtDefault(byteOut))
			
		);
		
		responsed.respond("/media/pics");
		assertEquals(expected, new String(byteOut.toByteArray(), StandardCharsets.UTF_8));
	}

}
