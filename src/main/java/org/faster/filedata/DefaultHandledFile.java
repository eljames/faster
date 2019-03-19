package org.faster.filedata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.faster.feedback.transfer.BufferFeedbackCreated;
import org.faster.feedback.transfer.TickerAction;
import org.faster.feedback.transfer.TransferedSize;
import org.faster.filedata.CdDefault;
import org.faster.filedata.CopiedData;
import org.faster.filedata.HandledFile;
import org.faster.pathinfo.PathInfo;
import org.faster.server.uploads.creation.TransferElement;
import org.faster.virtualpath.VpPath;

public class DefaultHandledFile implements HandledFile {
	
	private final File target;
	private final TickerAction action;
	private final TransferElement element;
	private final TransferedSize tranfereddir;
	
	public DefaultHandledFile(final File target, final TickerAction act, final TransferElement elem, final TransferedSize tranfereddir) {
		this.action = act;
		this.target = target;
		this.element = elem;
		this.tranfereddir = tranfereddir;
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
			new BufferFeedbackCreated(
				this.element,
				this.tranfereddir,
				this.action
			).create(new VpPath(info))
		);
		copied.copy(input);
	}

}
