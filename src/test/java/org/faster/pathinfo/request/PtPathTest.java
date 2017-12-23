package org.faster.pathinfo.request;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.token.TokenPathInfo;
import org.junit.Test;

public class PtPathTest {
	
	
	@Test
	public void returnsPath() throws IOException, ProtocolSyntaxErrorException {
		
		String path = "/abc/cde";
		
		TokenPathInfo token = new FakeTokenPathInfo(
			new PathInfoToken("f", path, -1).create()
		);
		
		assertEquals(
			new PtPath(
				new PtType(token), token
			).path(), path
		);
		
	}

}
