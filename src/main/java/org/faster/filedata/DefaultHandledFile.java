package org.faster.filedata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import org.faster.feedback.BufferFeedback;
import org.faster.feedback.transfer.BufferFeedbackCreated;
import org.faster.pathinfo.PathInfo;
import org.faster.virtualpath.VpPath;

public class DefaultHandledFile implements HandledFile {
	
	private final File target;
	private final BufferFeedbackCreated createdbufferfb;
	private final PathInfo root;
	
	public DefaultHandledFile(final File target, final BufferFeedbackCreated cbfb, final PathInfo root) {
		this.target = target;
		this.createdbufferfb = cbfb;
		this.root = root;
	}

	@Override
	public void handle(final InputStream input, final PathInfo info) throws IOException {
		
		final File file = new CreatedTarget(
			new RelativisedPath(
				this.root,
				info
			),
			this.target
		).target();
		BufferFeedback bufferFeedback = this.createdbufferfb.create(new VpPath(info));
		final FileOutputStream out = new FileOutputStream(file);
		CopiedData copied = new CdDefault(
			out,
			bufferFeedback
		);
		copied.copy(input);
		bufferFeedback.finished();
	}
}

class RelativisedPath {
	
	private final PathInfo root;
	private final PathInfo file;
	
	public RelativisedPath(final PathInfo root, final PathInfo info) {
		this.root = root;
		this.file = info;
	}
	
	public String path() throws IOException {
		return last(this.root) +
		this.file.path().toString().replaceFirst(
			Pattern.quote(this.root.path().toString()),
			""
		);
	}
	
	private String last(final PathInfo info) throws IOException {
		final String[] parts = info.path().toString().split("/");
		final String fileName = parts[parts.length - 1];
		return fileName;
	}
}

class CreatedTarget {
	
	private final RelativisedPath relative;
	private final File target;
	
	public CreatedTarget(final RelativisedPath relative, final File target) {
		this.relative = relative;
		this.target = target;
	}
	
	public File target() throws IOException {
		File file = new File(this.target.getAbsoluteFile() + File.separator + this.relative.path());
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		return file;
	}

}
