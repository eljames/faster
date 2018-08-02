package org.faster.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import org.faster.connection.Connection;
import org.faster.errors.Errors;
import org.faster.errors.ErrorsDefault;
import org.faster.errors.HandledError;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.request.PathItemsToken;
import org.faster.pathitems.PathItems;
import org.faster.requestedpath.RpDefault;
import org.faster.requestedpath.errors.DirectoryNotFound;
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
	
	@Test
	public void handleErrorWhenFileNotFound() throws UnknownHostException, IOException, ProtocolSyntaxErrorException {
		Connection con = new FakeConnection(new ByteArrayOutputStream(), new ByteArrayInputStream(
				(
					"err\n"
				  + "dnf"
				).getBytes(StandardCharsets.UTF_8)
			)
		);
		HandledErrorTesting handled = new HandledErrorTesting();
		Errors errors = new ErrorsDefault()
		.add(new DirectoryNotFound(handled));
		RequestedPaths req = new RpDefault(con, errors);
		try {
			req.request("/aaa");
		} catch (Exception e) {}
		assertTrue(handled.pass());
	}
	
	static class HandledErrorTesting implements HandledError {
		
		private boolean pass;
		
		public HandledErrorTesting() {
			this.pass = false;
		}

		@Override
		public void handle(CharSequence error, CharSequence path) {
			this.pass = true;
		}
		
		public boolean pass() {
			return this.pass;
		}
		
	}

}
