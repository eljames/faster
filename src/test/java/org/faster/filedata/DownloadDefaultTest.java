package org.faster.filedata;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.filedata.FdFileTest.FileDeliveredContent;
import org.faster.pathinfo.request.PathInfoToken;
import org.faster.request.FakeConnection;
import org.faster.responsepaths.ResourcePath;
import org.junit.Test;

public class DownloadDefaultTest {
	
	@Test
	public void downloadSingleFile() throws IOException, ProtocolSyntaxErrorException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		String filepath = new ResourcePath().get(SingleFileStream.class) + "/file.txt";
		File file = new File(filepath);
		String stream = new StringBuilder()
			.append("k\n")
			.append(new PathInfoToken().create(false, "/abc", file.length()))
			.append(new Stream(new FileInputStream(file)).asString())
			.toString();
		ByteArrayInputStream byteIn = new ByteArrayInputStream(stream.getBytes());
		FileDeliveredContent delivered = new FileDeliveredContent();
		Download download = new DownloadDefault(new FakeConnection(byteOut, byteIn), delivered);
		download.download("/abc");
		assertEquals("this example must end here because this is a test.", delivered.result());
	}

}
