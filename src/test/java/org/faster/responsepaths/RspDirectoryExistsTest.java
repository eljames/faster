package org.faster.responsepaths;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.faster.dirmap.DmDefault;
import org.faster.responsepaths.ResponsePaths;
import org.faster.responsepaths.RspDirectoryExists;
import org.faster.written.WtDefault;
import org.faster.written.Written;
import org.junit.Test;

public class RspDirectoryExistsTest {
	
	
	@Test
	public void sendErrorIfPathNotFound() throws IOException {
		
		/*From protocol (Error, directory not found):
		 * err
		 * dnf
		 */
		String expected = "err\ndnf\n";
		
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		Written written = new WtDefault(byteOut);
		
		ResponsePaths responsed = new RspDirectoryExists(
			new ResponsePaths() {
				@Override
				public void respond(CharSequence relativePath) throws IOException {}
			},
			new DmDefault(new CreatedPathMapRes().create("org/faster/responsepaths/root")),
			written
		);
		
		responsed.respond("/ABC");
		
		assertEquals(expected, new String(byteOut.toByteArray()));
		
	}

}
