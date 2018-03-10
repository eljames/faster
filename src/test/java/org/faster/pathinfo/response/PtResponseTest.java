package org.faster.pathinfo.response;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

public class PtResponseTest {
	
	@Test
	public void pathShouldRelativize() throws IOException {
		
		String rootDir =  resourcePath(this.getClass());
		String fileTest = rootDir + "/file_size_test.txt";
		
		assertEquals("/file_size_test.txt", new PtResponse(Paths.get(rootDir), new File(fileTest)).path());
	}
	
	@Test
	public void returnFileSize() throws IOException {
		
		String rootDir =  resourcePath(this.getClass());
		String fileTest = rootDir + "/file_size_test.txt";
		
		assertEquals(new PtResponse(Paths.get(rootDir), new File(fileTest)).size(), 25);
	}
	
	private <T> String  resourcePath(Class<T> c) {
		return  System.getProperty("user.dir") + "/src/test/resources/" + c.getPackage().getName().replace(".", "/");
	}

}
