package org.faster.pathsize;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import org.faster.connection.Connection;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.filedata.SingleFileStream;
import org.faster.request.FakeConnection;
import org.faster.responsepaths.ResourcePath;
import org.junit.Test;

public class PathSizeTest {
	
	
	@Test
	public void getFileSize() throws IOException, ProtocolSyntaxErrorException {
		String filepath = new ResourcePath().get(SingleFileStream.class) + "/file.txt";
		File file = new File(filepath);
		final String stream = new StringBuilder()
				.append("k\n")
				.append(file.length())
				.toString();
		final Connection con = new FakeConnection(
				new ByteArrayOutputStream(),
				new ByteArrayInputStream(stream.getBytes())
		);
		assertEquals(file.length(), Long.parseLong(new PathSize(con).size("/whatever").size()));
	}
	
	@Test
	public void fileNotFound() throws IOException, ProtocolSyntaxErrorException {
		final String stream = new StringBuilder()
				.append("err\n")
				.append("fnf\n")
				.toString();
		final Connection con = new FakeConnection(
				new ByteArrayOutputStream(),
				new ByteArrayInputStream(stream.getBytes())
		);
		assertEquals("fnf", new PathSize(con).size("/whatever").error());
	}
}
