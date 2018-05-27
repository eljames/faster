package org.faster.responsepaths;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;

import org.faster.dirmap.DmDefault;
import org.faster.pathinfo.request.PathItemsToken;
import org.faster.responsepaths.ResponsePaths;
import org.faster.responsepaths.RspFinished;
import org.faster.responsepaths.RspResponse;
import org.faster.sentpath.DfSentPath;
import org.faster.written.DfWritten;
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
				.append("\n")
				.append("e")
				.toString();
		
		StringWriter strWriter = new StringWriter();
		Written written = new DfWritten(strWriter); 
		
		ResponsePaths responsed = new RspOK(
			new RspResponse(
				new DmDefault(new CreatedPathMap().create("/org/faster/responsepaths/root")),
				new DfSentPath(written),
				new RspFinished(written)
			),
			written
		);
		
		responsed.respond("/media/pics");
		assertEquals(expected, strWriter.toString());
	}

}