package org.faster.filedata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.faster.feedback.transfer.BufferFeedbackCreated;
import org.faster.filedata.CdDefault;
import org.faster.filedata.CopiedData;
import org.faster.filedata.HandledFile;
import org.faster.pathinfo.PathInfo;
import org.faster.virtualpath.VpPath;

public class DefaultHandledFile implements HandledFile {
	
	private final File target;
	private final BufferFeedbackCreated createdbufferfb;
	
	public DefaultHandledFile(final File target, final BufferFeedbackCreated cbfb) {
		this.target = target;
		this.createdbufferfb = cbfb;
	}

	@Override
	public void handle(final InputStream input, final PathInfo info) throws IOException {
		
		final File file = new File(this.target.getAbsolutePath() + File.separator + info.path());
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		FileOutputStream out = new FileOutputStream(file);
		CopiedData copied = new CdDefault(
			out,
			this.createdbufferfb.create(new VpPath(info))
		);
		copied.copy(input);
	}
}
