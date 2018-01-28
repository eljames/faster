package org.faster.responsedpath;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Paths;

import org.faster.pathinfo.request.PathItemsToken;
import org.faster.sentpath.DfSentPath;
import org.faster.written.DfWritten;
import org.faster.written.Written;
import org.junit.Test;

public class ResponsedPathTest {
	
	@Test
	public void returnsPathItemsWithEnd() throws IOException {
		String expected = new PathItemsToken()
				.append(false, "/pics/me.txt", 55)
				.append(true, "/pics/travel", -1)
				.append(false, "/pics/file_test.txt", 25)
				.builder()
				.append("\n")
				.append("e")
				.toString();
		
		StringWriter strWriter = new StringWriter();
		Written written = new DfWritten(strWriter); 
		
		ResponsedPath responsed = new RspResponse(
			Paths.get(new ResourcePath().get(this.getClass()) + "/root"),
			new DfSentPath(written),
			new RspFinished(written)
		);
		
		responsed.respond(
			Paths.get(
				"/pics"
			)
		);
		assertEquals(strWriter.toString(), expected);
	}

}
