package org.faster.responsefiles;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.faster.feedback.BufferFeedback;
import org.faster.filedata.CdDefault;
import org.faster.filedata.CopiedData;
import org.faster.virtualpath.VirtualPath;

public class SdDefault implements SentData {
	
	private final OutputStream output;
	private final BufferFeedback feedback;
	
	public SdDefault(final OutputStream out, final BufferFeedback feed) {
		this.output = out;
		this.feedback = feed;
	}
	
	public SdDefault(final OutputStream out) {
		this(out, BufferFeedback.NOTHING);
	}

	@Override
	public void send(final VirtualPath path) throws IOException {
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(path.real()));
		CopiedData copied = new CdDefault(
			this.output,
			this.feedback
		);
		copied.copy(input);
	}

	@Override
	public void finished() {}
}
