package org.faster.responsepaths;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.faster.dirmap.DmDefault;
import org.faster.responsepaths.ResponsePaths;
import org.faster.responsepaths.RspDirectoryExists;
import org.faster.written.DfWritten;
import org.faster.written.Written;
import org.junit.Test;

public class RspDirectoryExistsTest {
	
	
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
				public void respond(CharSequence relativePath) throws IOException {}
			},
			new DmDefault(new CreatedPathMap().create("org/faster/responsepaths/root")),
			written
		);
		
		responsed.respond("/ABC");
		
		assertEquals(expected, writer.toString());
		
	}

}
