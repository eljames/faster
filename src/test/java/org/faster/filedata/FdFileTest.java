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
	public void singleFileDownload() throws FileNotFoundException, IOException, ProtocolSyntaxErrorException {
		String filepath = new ResourcePath().get(SingleFileStream.class) + "/file.txt";
		File file = new File(filepath);
		String stream = new PathInfoToken().create(false, "/abc", file.length());
		stream += "\n" + new Stream(new FileInputStream(file)).asString();
		ByteArrayInputStream byteIn = new ByteArrayInputStream(stream.getBytes());
		LineToken token = new LtDefault(byteIn);
		FileDeliveredContent delivered = new FileDeliveredContent();
		new FdFile(
			new FileDeliveredNotConsumed(delivered), token
		).download(byteIn);
		assertEquals(delivered.expected(), "this example must end here because this is a test.");
	}
	
	static class FileDeliveredContent implements FileDelivered {
		private String content;
		
		@Override
		public void delivery(InputStream input, PathInfo info) throws IOException {
			this.content = new Stream(input).asString();
		}
		
		public String expected() {
			return this.content;
		}
	}
}
