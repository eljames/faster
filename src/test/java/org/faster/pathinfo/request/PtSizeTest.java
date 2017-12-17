package org.faster.pathinfo.request;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.junit.Test;

public class PtSizeTest {

	
	@Test
	public void returnsSize() throws IOException, ProtocolSyntaxErrorException {
		
		
		long size = 1024;
		String sizeStream = ""+size;
		
		PathInfo pathInfo = new PtSize(
			PathInfo.NO_PATH_INFO,
			new FakeTokenPathInfo(sizeStream + "\n")
		);
		
		assertEquals(pathInfo.size(), size);
	}
	
	@Test(expected = ProtocolSyntaxErrorException.class)
	public void throwsIfNotNumber() throws IOException, ProtocolSyntaxErrorException {
		
		String sizeStream = "notNumber\n";
		
		new PtSize(
			PathInfo.NO_PATH_INFO,
			new FakeTokenPathInfo(sizeStream + "\n")
		);
	}
	
	@Test(expected = ProtocolSyntaxErrorException.class)
	public void throwsIfPartialNumber() throws IOException, ProtocolSyntaxErrorException {
		
		String sizeStream = "1234b\n";
		
		new PtSize(
			PathInfo.NO_PATH_INFO,
			new FakeTokenPathInfo(sizeStream + "\n")
		);
	}
	
}
