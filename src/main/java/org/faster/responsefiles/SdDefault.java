package org.faster.responsefiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.faster.feedback.BufferFeedBack;
import org.faster.feedback.FileFeedBack;
import org.faster.pathinfo.response.PtResponse;
import org.faster.virtualpath.VirtualPath;

public class SdDefault implements SentData {
	
	private final OutputStream output;
	private final FileFeedBack feedback;
	
	public SdDefault(final OutputStream out, final FileFeedBack feed) {
		this.output = out;
		this.feedback = feed;
	}

	@Override
	public void send(final VirtualPath path) throws IOException {
		copy(new FileInputStream(path.real()), this.feedback.create(new PtResponse(path)));
	}
	
	private void copy(final InputStream in, final BufferFeedBack feed) throws IOException {
		int i = -1;
		while((i = in.read()) != -1) {
			this.output.write(i);
			feed.feed(i);
		}
	}
}
