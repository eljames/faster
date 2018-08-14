package org.faster.filedata;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;
import org.faster.pathinfo.request.PathInfoToken;
import org.faster.responsepaths.ResourcePath;
import org.junit.Test;

public class FdDefaultTest {
	
	@Test
	public void manyFilesDownload() throws IOException, ProtocolSyntaxErrorException {
		String folder = new ResourcePath().get(SingleFileStream.class);
		File file = new File(folder + "/file.txt");
		File file2 = new File(folder + "/file2.txt");
		StringBuilder builder = new StringBuilder()
			.append(new PathInfoToken().create(true, "/abc", -1, true) + "\n")
			.append("\n")
			.append(new PathInfoToken().create(false, "/file.txt", file.length()))
			.append("\n")
			.append(new Stream(new FileInputStream(file)).asString())
			.append("\n")
			.append(new PathInfoToken().create(false, "/file2.txt", file2.length()))
			.append("\n")
			.append(new Stream(new FileInputStream(file2)).asString())
			.append("e\n");
		ByteArrayInputStream byteIn = new ByteArrayInputStream(builder.toString().getBytes());
		FileDeliveredMany delivered = new FileDeliveredMany();
		new FdDefault(byteIn, delivered).download();
		assertEquals(delivered.expected().get(0), "this example must end here because this is a test.");
		assertEquals(delivered.expected().get(1), "this file is the second file.");
	}
	
	static class FileDeliveredMany implements FileDelivered {
		private final List<String> content;
		
		public FileDeliveredMany() {
			this.content = new ArrayList<>();
		}
		
		@Override
		public void delivery(InputStream input, PathInfo info) throws IOException {
			this.content.add(new Stream(input).asString());
		}
		
		public List<String> expected() {
			return this.content;
		}
	}

}
