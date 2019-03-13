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

	private final ChosenPath path;
	private final Transferences downloads;
	private final Connection connection;
	
	public FileDeliveredDefault(final ChosenPath chosen, final Transferences downs, final Connection con) {
		this.path = chosen;
		this.downloads = downs;
		this.connection = con;
	}
	
	@Override
	public void file(InputStream input, PathInfo info) throws IOException {
		this.directory(info);
	}

	@Override
	public HandledFile directory(PathInfo info) throws IOException {
		final VirtualPath virtual = new VpPath(info);
		final File targetdir = this.path.get();
		final File finaldir = new File(targetdir.getAbsolutePath() + File.separator + name(info));
		final TransferedSize transfered = new TransferedSize();
		final TransferElement element = this.downloads.add(virtual, this.connection);
		return new DefaultHandledFile(
			finaldir,
			new TaTotal(
				element,
				new DeltaSize(transfered),
				new SizeLeft(
					transfered,
					info.size()
				),
				transfered
			),
			element
		);
	}
	
	private String name(final PathInfo info) throws IOException {
		if(info.isDirectory()) {
			String[] paths = info.path().toString().split("/");
			return paths[paths.length - 1];
		}
		return "";
	}
}
