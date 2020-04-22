package org.faster.responsefiles;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.token.LineToken;
import org.faster.token.LtDefault;
import org.junit.Test;

public class ResponseAllFilesTest {

	@Test
	public void respondThreeFiles() throws IOException, ProtocolSyntaxErrorException {
		
		String requestedFile = new StringBuilder()
		.append("c")
		.append("\n")
		.append("c")
		.append("\n")
		.append("c")
		.append("\n")
		.append("df")
		.append("\n")
		.toString();
		ByteArrayInputStream in = new ByteArrayInputStream(requestedFile.getBytes());
		//Connection con = new FakeConnection(new ByteArrayOutputStream(), in);
		final Count count = new Count();
		final LineToken lt = new LtDefault(in);
		new RespondAllFiles(
			lt,
			() -> {
				count.sum();
			}
		).respondAll();
		assertEquals(3, count.total());
	}
	
	
	
	public static class Count {
		
		private int countNumber = 0;
		
		public void sum() {
			countNumber++;
		}
		
		public int total() {
			return this.countNumber;
		}
	}
}
