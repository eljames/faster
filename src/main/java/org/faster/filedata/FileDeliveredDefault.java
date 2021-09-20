package org.faster.filedata;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.faster.feedback.transfer.BufferFeedbackCreated;
import org.faster.feedback.transfer.DeltaSize;
import org.faster.feedback.transfer.SizeLeft;
import org.faster.feedback.transfer.TaTotal;
import org.faster.feedback.transfer.TotalSize;
import org.faster.feedback.transfer.TransferedSize;
import org.faster.pathinfo.PathInfo;
import org.faster.server.uploads.creation.TransferElement;
import org.faster.virtualpath.VirtualPath;
import org.faster.virtualpath.VpPath;

public class FileDeliveredDefault implements FileDelivered {

	private final ChosenPath chosen;
	private final TransferElement transferElement;
	private final TotalSize totalSize;
	private final TransferedSize totalTransfered;
	
	public FileDeliveredDefault(final ChosenPath chosen, final TransferElement transferElement, final TransferedSize totalTransfered, final TotalSize totalSize) {
		this.chosen = chosen;
		this.transferElement = transferElement;
		this.totalTransfered = totalTransfered;
		this.totalSize = totalSize;
	}
	
	@Override
	public void file(InputStream input, PathInfo info) throws IOException {
		this.download(info, this.chosen.get()).handle(input, info);
	}

	@Override
	public HandledFile directory(PathInfo info) throws IOException {
		return this.download(info, this.chosen.get());
	}
	
	private HandledFile download(final PathInfo info, File finaldir) throws IOException {
		final VirtualPath virtual = new VpPath(info);
		this.transferElement.change(virtual);
		return new DefaultHandledFile(
			finaldir,
			new BufferFeedbackCreated(
					transferElement,
					totalTransfered,
				new TaTotal(
						transferElement,
					new DeltaSize(totalTransfered),
					new SizeLeft(
							totalTransfered,
						this.totalSize.total()
					),
					totalTransfered
				)
			),
			info
		);
	}
}
