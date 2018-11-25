package org.faster.responsefiles;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.faster.feedback.BufferFeedback;
import org.faster.feedback.FileFeedback;
import org.faster.filedata.CdDefault;
import org.faster.filedata.CopiedData;
import org.faster.pathinfo.response.PtResponse;
import org.faster.virtualpath.VirtualPath;

public class SdDefault implements SentData {
	
	private final OutputStream output;
	private final FileFeedback feedback;
	
	public SdDefault(final OutputStream out, final FileFeedback feed) {
		this.output = out;
		this.feedback = feed;
	}

	@Override
	public void send(final VirtualPath path) throws IOException {
		BufferFeedback bufferfeed = this.feedback.create(new PtResponse(path));
		BufferedInputStream input = new BufferedInputStream(new FileInputStream(path.real()));
		CopiedData copied = new CdDefault(
			this.output,
			bufferfeed
		);
		copied.copy(input);
	}

	@Override
	public void finished() {
		this.feedback.finished();
	}
}
