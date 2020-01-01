package org.faster.filedata.startdownload;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.faster.connection.Connection;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.request.FakeConnection;
import org.junit.Test;

public class StartDownloadTest {

	@Test
	public void mustStartAndEndDownload() throws IOException, ProtocolSyntaxErrorException {
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Connection con = new FakeConnection(out, new ByteArrayInputStream(new byte[1]));
		
		new StartDownloadFile(
			() -> {
				
			},
			con
		).start();
		
		assertEquals(startEndProtocol(), new String(out.toByteArray()));
	}
	
	private String startEndProtocol() {
		return new StringBuilder()
		.append("fd")
		.append("\n")
		.append("df")
		.append("\n")
		.toString();
	}
	
}
