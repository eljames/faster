package org.faster.filedata;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.faster.connection.Connection;
import org.faster.feedback.transfer.DeltaSize;
import org.faster.feedback.transfer.SizeLeft;
import org.faster.feedback.transfer.TaTotal;
import org.faster.feedback.transfer.TransferedSize;
import org.faster.filedata.FileDelivered;
import org.faster.filedata.HandledFile;
import org.faster.pathinfo.PathInfo;
import org.faster.server.uploads.creation.TransferElement;
import org.faster.server.uploads.creation.Transferences;
import org.faster.virtualpath.VirtualPath;
import org.faster.virtualpath.VpPath;

public class FileDeliveredDefault implements FileDelivered {

	private final ChosenPath chosen;
	private final Transferences downloads;
	private final Connection connection;
	
	public FileDeliveredDefault(final ChosenPath chosen, final Transferences downs, final Connection con) {
		this.chosen = chosen;
		this.downloads = downs;
		this.connection = con;
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
		final TransferedSize transfereddir = new TransferedSize();
		final TransferElement element = this.downloads.add(virtual, this.connection);
		return new DefaultHandledFile(
			finaldir,
			new TaTotal(
				element,
				new DeltaSize(transfereddir),
				new SizeLeft(
					transfereddir,
					info.size()
				),
				transfereddir
			),
			element,
			transfereddir
		);
	}
}
