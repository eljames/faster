package org.faster.pathinfo.request;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class PtPathTest {
	
	
	@Test
	public void returnsPath() throws IOException {
		
		String path = "/abc/cde";
		String pathToken = path + "\n";
		
		PathInfo pathInfo = new PtPath(
			PathInfo.NO_PATH_INFO, new FakeTokenPathInfo(pathToken)
		);
		
		Assert.assertEquals(pathInfo.path(), path);
		
	}

}
