package org.faster.responsefiles;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.faster.responsepaths.CreatedPathMapRes;
import org.faster.written.Written;
import org.faster.written.WtDefault;
import org.junit.Test;

public class RfFileNotFoundTest {
	
	@Test
	public void sendsFileNotFound() throws IOException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		Written written = new WtDefault(byteOut);
		ResponseFiles response = new ResponseFilesDefault.RfFileNotFound(
			new ReponseFilesNone(),
			new CreatedPathMapRes().create("/org/faster/filedata"),
			written
		);
		response.send("not-exists");
		assertEquals("err\nfnf\n", new String(byteOut.toByteArray()));
	}
	
	static class ReponseFilesNone implements ResponseFiles {

		@Override
		public void send(CharSequence path) throws IOException {
			
		}
		
	}

}
