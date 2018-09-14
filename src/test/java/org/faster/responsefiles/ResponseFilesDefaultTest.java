package org.faster.responsefiles;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.faster.feedback.FileFeedback;
import org.faster.filedata.SingleFileStream;
import org.faster.pathmap.CpmDefault;
import org.faster.pathmap.PathMap;
import org.faster.responsepaths.ResourcePath;
import org.junit.Test;

public class ResponseFilesDefaultTest {
	
	@Test
	public void sendFile() throws IOException {
		String filepath = new ResourcePath().get(SingleFileStream.class) + "/file.txt";
		PathMap pathmap = new CpmDefault()
			.add("aaa", filepath)
			.map();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		new ResponseFilesDefault(
			pathmap,
			new ByteArrayOutputStream(),
			new SdDefault(out, FileFeedback.NOTHING)
		).send("aaa");
		assertEquals("this example must end here because this is a test.", new String(out.toByteArray()));
	}
	
	@Test
	public void sendFilesFromDirectory() throws IOException {
		String dirpath = new ResourcePath().get(SingleFileStream.class);
		PathMap pathmap = new CpmDefault()
				.add("aaa", dirpath)
				.map();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		new ResponseFilesDefault(
			pathmap,
			new ByteArrayOutputStream(),
			new SdDefault(out, FileFeedback.NOTHING)
		).send("aaa");
		assertEquals(
			new StringBuilder()
				.append("this file is the second file.")
				.append("this example must end here because this is a test.")
				.toString(),
			new String(out.toByteArray())
		);
	}

}
