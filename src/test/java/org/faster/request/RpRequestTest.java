package org.faster.request;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathitems.PathItems;
import org.faster.requestedpath.RequestedPaths;
import org.faster.requestedpath.RpRequest;
import org.junit.Test;

public class RpRequestTest {
	
	@Test
	public void returnsRequest() throws IOException, ProtocolSyntaxErrorException {
		
		String path = "/abc/music";
		String resp = "pi\n" + path;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		new RpRequest(
			new RequestedPaths() {
				
				@Override
				public PathItems request(CharSequence path) throws IOException, ProtocolSyntaxErrorException {
					return null;
				}
			},
			out
		).request(path);
		
		assertTrue(out.toString(StandardCharsets.UTF_8.name()).equals(resp));
	}

}
