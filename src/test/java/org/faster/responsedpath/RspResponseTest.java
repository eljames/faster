package org.faster.responsedpath;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Paths;

import org.faster.sentpath.DfSentPath;
import org.faster.written.DfWritten;
import org.junit.Test;

public class RspResponseTest {
	
	@Test
	public void responsePath() throws IOException {
		
		String expected = "\n" + 
				"\n" + 
				"f\n" + 
				"/pics/file_test.txt\n" + 
				"25";
		
		StringWriter strWriter = new StringWriter();
		
		ResponsedPath responsed = new RspResponse(
			Paths.get(
				new ResourcePath().get(this.getClass()) + "/root"),
				new DfSentPath(new DfWritten(strWriter)
			)
		);
		
		responsed.respond(
			Paths.get(
				"pics"
			)
		);
		assertEquals(strWriter.toString(), expected);
	}

}
