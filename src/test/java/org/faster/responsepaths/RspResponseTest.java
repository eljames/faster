package org.faster.responsepaths;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

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
				.append(false, "/pics/me.txt", 55)
				.append(true, "/pics/travel", -1)
				.append(false, "/pics/file_test.txt", 25)
				.builder()
				.toString();
		
		StringWriter strWriter = new StringWriter();
		
		ResponsePaths responsed = new RspResponse(
			Paths.get(
				new ResourcePath().get(this.getClass()) + "/root"),
				new DfSentPath(new DfWritten(strWriter)
			), new ResponsePaths() {
				
				@Override
				public void respond(Path relativePath) throws IOException {}
			}
		);
		
		responsed.respond(
			Paths.get(
				"/pics"
			)
		);
		assertEquals(expected, strWriter.toString());
	}

}