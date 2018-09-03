package org.faster.filedata;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;
import org.faster.pathinfo.request.PathInfoToken;
import org.faster.responsepaths.ResourcePath;
import org.faster.token.LineToken;
import org.faster.token.LtDefault;
import org.junit.Test;

public class FdFileTest {
	
	@Test
	public void singleFileDownloadOnDirectory() throws FileNotFoundException, IOException, ProtocolSyntaxErrorException {
		String filepath = new ResourcePath().get(SingleFileStream.class) + "/file.txt";
		File file = new File(filepath);
		String stream = new PathInfoToken().create(false, "/abc", file.length());
		stream += new Stream(new FileInputStream(file)).asString();
		ByteArrayInputStream byteIn = new ByteArrayInputStream(stream.getBytes());
		LineToken token = new LtDefault(byteIn);
		HandledFileContent handled = new HandledFileContent();
		new FdFile(
			token, byteIn, handled
		).download();
		assertEquals("this example must end here because this is a test.", handled.result());
	}
	
	class HandledFileContent implements HandledFile {
		
		private String content;
		
		public String result() {
			return this.content;
		}
		
		@Override
		public void handle(InputStream input, PathInfo info) throws IOException {
			content = new Stream(input).asString();
		}
	}
}
