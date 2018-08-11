package org.faster.filedata;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import org.faster.responsepaths.ResourcePath;
import org.junit.Test;

public class SingleFileStreamTest {
	
	@Test
	public void consumeRightSize() throws IOException {
		String filepath = new ResourcePath().get(SingleFileStream.class) + "/file.txt";
		String text = new Stream(
			new SingleFileStream(
				new FileInputStream(filepath), 26
			)
		).asString();
		assertEquals("this example must end here", text);
	}
}
