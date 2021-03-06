package org.faster.responsepaths;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.faster.dirmap.DmDefault;
import org.faster.pathinfo.request.PathItemsToken;
import org.faster.responsepaths.ResponsePaths;
import org.faster.responsepaths.RspFinished;
import org.faster.responsepaths.RspResponse;
import org.faster.sentpath.SpDefault;
import org.faster.written.WtDefault;
import org.faster.written.Written;
import org.junit.Test;

public class ResponsePathsTest {
	
	@Test
	public void returnsPathItemsWithEnd() throws IOException {
		String expected = new PathItemsToken()
				.ok()
				.append(false, "/media/pics/me.txt", 55)
				.append(true, "/media/pics/travel", -1)
				.append(false, "/media/pics/file_test.txt", 25)
				.builder()
				.append("e")
				.append("\n")
				.toString();
		
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		Written written = new WtDefault(byteOut); 
		
		ResponsePaths responsed = new RspOK(
			new RspResponse(
				new RspFinished(written),
				new DmDefault(new CreatedPathMapRes().create("/org/faster/responsepaths/root")),
				new SpDefault(written)
			),
			written
		);
		
		responsed.respond("/media/pics");
		assertEquals(expected, new String(byteOut.toByteArray()));
	}

}