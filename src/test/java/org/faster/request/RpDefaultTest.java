package org.faster.request;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.request.PathItemsToken;
import org.faster.pathitems.PathItems;
import org.faster.requestedpath.RpDefault;
import org.faster.requestedpath.RequestedPaths;
import org.junit.Test;

public class RpDefaultTest {
	
	
	@Test
	public void shouldReturnsFourPaths() throws UnknownHostException, IOException, ProtocolSyntaxErrorException {
		RequestedPaths req = new RpDefault(
			new FakeConnection(new ByteArrayOutputStream(), new ByteArrayInputStream(
					(
						"k" +
						new PathItemsToken(new StringBuilder())
						.append(false, "/aaa/bbb.jpg", 4096)
						.append(true, "/aaa/ccc", -1)
						.append(true, "/ccc/ddd", -1)
						.append(false, "/ccc/ddd", 1234)
						.end()
					).getBytes(StandardCharsets.UTF_8)
				)
			)
		);
		
		PathItems items = req.request("abc/def");
		
		int n = 0;
		while(items.next()) {
			n++;
		}
		assertEquals(4, n);
	}

}
