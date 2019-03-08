package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.faster.feedback.BufferFeedback;

public class CdDefault implements CopiedData {
	
	final OutputStream output;
	final BufferFeedback feed;
	final int buffer;
	
	public CdDefault(final OutputStream out, final BufferFeedback fd, final int buff) {
		this.output = out;
		this.feed = fd;
		this.buffer = buff;
	}
	
	public CdDefault(final OutputStream out, final BufferFeedback fd) {
		this(out, fd, 8*1024);
	}
	
	public CdDefault(final OutputStream out) {
		this(out, BufferFeedback.NOTHING);
	}

	@Override
	public void copy(InputStream input) throws IOException {
		int read = 0;
		byte[] bytes = new byte[this.buffer];
		while((read = input.read(bytes)) != -1) {
			this.output.write(bytes, 0, read);
			feed.feed(read);
		}
		this.feed.finished();
	}
}
