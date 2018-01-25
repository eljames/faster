package org.faster.pathinfo.request;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;
import org.faster.pathinfo.request.PtType;
import org.faster.token.TokenPathInfo;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class PtTypeTest {
	
	@Test
	public void isFile() throws IOException, ProtocolSyntaxErrorException {
		
		PathInfo path = new PtType(
			new FakeTokenPathInfo("f\n")
		);
		
		assertFalse(path.isDirectory());
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
	
	@Test(expected = UnsupportedOperationException.class)
	public void throwsIfPathOperation() throws IOException, ProtocolSyntaxErrorException {
		new PtType(
			new FakeTokenPathInfo("f\n")
		).path();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void throwsIfSizeOperation() throws IOException, ProtocolSyntaxErrorException {
		new PtType(
			new FakeTokenPathInfo("f\n")
		).size();
	}

}
