package org.faster.responsepaths;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.io.StringWriter;
import org.faster.dirmap.DmDefault;
import org.faster.pathinfo.request.PathItemsToken;
import org.faster.responsepaths.ResponsePaths;
import org.faster.responsepaths.RspResponse;
import org.faster.sentpath.DfSentPath;
import org.faster.written.DfWritten;
import org.junit.Test;

public class RspResponseTest {
	
	@Test
	public void responsePath() throws IOException {
		
		String expected = new PathItemsToken()
				.append(false, "/media/pics/me.txt", 55)
				.append(true, "/media/pics/travel", -1)
				.append(false, "/media/pics/file_test.txt", 25)
				.builder()
				.toString();
		
		StringWriter strWriter = new StringWriter();
		
		ResponsePaths responsed = new RspResponse(
			new DmDefault(new CreatedPathMap().create("/org/faster/responsepaths/root")),
			new DfSentPath(new DfWritten(strWriter)),
			new ResponsePaths() {
				
				@Override
				public void respond(CharSequence relativePath) throws IOException {}
			}
		);
		
		responsed.respond("/media/pics");
		assertEquals(expected, strWriter.toString());
	}

}
