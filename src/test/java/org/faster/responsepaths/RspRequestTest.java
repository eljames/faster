package org.faster.responsepaths;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.faster.responsepaths.ResponsePaths;
import org.faster.responsepaths.RspDirectoryExists;
import org.faster.written.DfWritten;
import org.faster.written.Written;
import org.junit.Test;

public class RspRequestTest {
	
	
	@Test
	public void sendErrorIfPathNotFound() throws IOException {
		
		/*From protocol (Error, directory not found):
		 * err
		 * dnf
		 */
		String expected = "err\ndnf";
		
		Writer writer = new StringWriter();
		Written written = new DfWritten(writer);
		
		ResponsePaths responsed = new RspDirectoryExists(
			new ResponsePaths() {
				@Override
				public void respond(Path relativePath) throws IOException {}
			},
			Paths.get(new ResourcePath().get(this.getClass()) + "/root"),
			written
		);
		
		responsed.respond(Paths.get("/ABC"));
		
		assertEquals(expected, writer.toString());
		
	}

}
