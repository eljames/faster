package org.faster.request;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.request.FakeTokenPathInfo;
import org.junit.Test;

public class RpResponseTest {
	
	@Test
	public void responseOkShouldNotThrowsException() throws IOException, ProtocolSyntaxErrorException {
		RequestedPaths resp = new RpResponse(new FakeTokenPathInfo("k"));
		resp.request("");
	}
	
	@Test(expected = ProtocolSyntaxErrorException.class)
	public void UnknownResponseShouldThrowsException() throws IOException, ProtocolSyntaxErrorException {
		RequestedPaths resp = new RpResponse(new FakeTokenPathInfo("a"));
		resp.request("");
	}

}
