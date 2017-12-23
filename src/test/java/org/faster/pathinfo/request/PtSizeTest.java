package org.faster.pathinfo.request;

import static org.junit.Assert.assertEquals;
import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.token.TokenPathInfo;
import org.junit.Test;

public class PtSizeTest {

	
	@Test
	public void returnsSize() throws IOException, ProtocolSyntaxErrorException {
		
		
		long size = 1024;
		TokenPathInfo token = new FakeTokenPathInfo(
			new PathInfoToken("f", "/abc/pic.jpg", size).create()
		);
		
		PathInfo pathInfo =
			new PtSize(
				new PtPath(
					new PtType(token),
					token),
				token
			);
		
		assertEquals(pathInfo.size(), size);
	}
	
	@Test(expected = ProtocolSyntaxErrorException.class)
	public void throwsIfNotNumber() throws IOException, ProtocolSyntaxErrorException {
		
		String sizeStream = "notNumber\n";
		TokenPathInfo token = new FakeTokenPathInfo("f\n" + sizeStream + "\n");
		
		new PtSize(
			new PtType(token),
			token
		);
	}
	
	@Test(expected = ProtocolSyntaxErrorException.class)
	public void throwsIfPartialNumber() throws IOException, ProtocolSyntaxErrorException {
		
		String sizeStream = "1234b\n";
		TokenPathInfo token = new FakeTokenPathInfo("f\n" + sizeStream + "\n");
		
		new PtSize(
			new PtType(token),
			token
		);
	}
	
	@Test
	public void returnsNegativeSizeIfDirectory() throws IOException, ProtocolSyntaxErrorException {
		
		String sizeStream = "1234b\n";
		TokenPathInfo token = new FakeTokenPathInfo("d\n" + sizeStream + "\n");
		
		PathInfo pathInfo = new PtSize(
			new PtType(token),
			token
		);
		
		assertEquals(pathInfo.size(), -1);
	}
	
}
