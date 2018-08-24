package org.faster.response;

import java.io.IOException;
import java.io.OutputStream;

import org.faster.connection.Connection;
import org.faster.feedback.FileFeedBack;
import org.faster.pathmap.PathMap;
import org.faster.responsefiles.ResponseFiles;
import org.faster.responsefiles.ResponseFilesDefault;
import org.faster.responsefiles.SdDefault;
import org.faster.token.LtDefault;

public class RsDownload implements Response {
	
	private final PathMap map;
	private final FileFeedBack feed;
	
	public RsDownload(final PathMap mp, final FileFeedBack fd) {
		this.map = mp;
		this.feed = fd;
	}

	@Override
	public void execute(Connection connection) throws IOException {
		OutputStream out = connection.output();
		ResponseFiles response = new ResponseFilesDefault(
			map,
			out,
			new SdDefault(
				out,
				feed
			)
		);
		response.send(new LtDefault(connection.input()).next());
	}

	@Override
	public String name() {
		return "fd";
	}

}
