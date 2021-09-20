package org.faster.pathinfo.request;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.token.LineToken;
import org.junit.Test;

public class PtPathTest {
	
	
	@Test
	public void returnsPath() throws IOException, ProtocolSyntaxErrorException {
		
		String path = "/abc/cde";
		
		LineToken token = new FakeTokenPathInfo(
			new PathInfoToken().create(false, path, -1)
		);
		
		assertEquals(
			path,
			new PtPath(
				new PtType(token), token
			).path()
		);
		
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void returnsSize() throws IOException, ProtocolSyntaxErrorException {
		String path = "/abc/cde";
		
		LineToken token = new FakeTokenPathInfo(
			new PathInfoToken().create(false, path, 2)
		);
		
		new PtPath(
			new PtType(token), token
		).size();
	
	}

}
