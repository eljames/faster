package org.faster.responsefiles;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.faster.feedback.BufferFeedback;
import org.faster.filedata.SingleFileStream;
import org.faster.pathmap.CpmDefault;
import org.faster.pathmap.PathMap;
import org.faster.responsepaths.ResourcePath;
import org.faster.virtualpath.VirtualPath;
import org.junit.Test;

public class ResponseFilesDefaultTest {
	
	@Test
	public void sendFile() throws IOException {
		String filepath = new ResourcePath().get(SingleFileStream.class) + "/file.txt";
		PathMap pathmap = new CpmDefault()
			.add("/aaa", filepath)
			.map();
		ByteArrayOutputStream fileout = new ByteArrayOutputStream();
		new ResponseFilesDefault(
			pathmap,
			new ByteArrayOutputStream(),
			new CreatedSentData() {
				
				@Override
				public SentData file(VirtualPath dir, OutputStream out) {
					return new SdDefault(fileout, BufferFeedback.NOTHING);
				}
				
				@Override
				public SentData directory(VirtualPath dir, OutputStream out) {
					return new SdDefault(fileout, BufferFeedback.NOTHING);
				}
			}
		).send("/aaa");
		assertEquals("this example must end here because this is a test.", new String(fileout.toByteArray()));
	}
	
	@Test
	public void sendFilesFromDirectory() throws IOException {
		String dirpath = new ResourcePath().get(SingleFileStream.class);
		PathMap pathmap = new CpmDefault()
				.add("/aaa", dirpath)
				.map();
		ByteArrayOutputStream fileout = new ByteArrayOutputStream();
		new ResponseFilesDefault(
			pathmap,
			new ByteArrayOutputStream(),
			new CreatedSentData() {
				
				@Override
				public SentData file(VirtualPath dir, OutputStream out) {
					return new SdDefault(fileout, BufferFeedback.NOTHING);
				}
				
				@Override
				public SentData directory(VirtualPath dir, OutputStream out) {
					return new SdDefault(fileout, BufferFeedback.NOTHING);
				}
			}
		).send("/aaa");
		assertEquals(
			new StringBuilder()
				.append("this example must end here because this is a test.")
				.append("this file is the second file.")
				.toString(),
			new String(fileout.toByteArray())
		);
	}

}
