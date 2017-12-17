package org.faster.pathinfo.request;

import java.io.IOException;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class PtTypeTest {
	
	@Test
	public void isFile() throws IOException, ProtocolSyntaxErrorException {
		
		PathInfo path = new PtType(
			new FakeTokenPathInfo("f\n")
		);
		
		MatcherAssert.assertThat("Should be a file", !path.isDirectory());
	}
	
	@Test
	public void isDirectory() throws IOException, ProtocolSyntaxErrorException {
		
		PathInfo path = new PtType(
			new FakeTokenPathInfo("d\n")
		);
		
		MatcherAssert.assertThat("Should be a directory", path.isDirectory());
	}
	
	@Test(expected = ProtocolSyntaxErrorException.class)
	public void throwsIfDirOrNotFile() throws IOException, ProtocolSyntaxErrorException {
		new PtType(
			new FakeTokenPathInfo("w\n")
		);
	}

}
