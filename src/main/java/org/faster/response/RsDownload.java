package org.faster.response;

import java.io.IOException;
import java.io.OutputStream;

import org.faster.connection.Connection;
import org.faster.pathmap.PathMap;
import org.faster.responsefiles.CreatedSentData;
import org.faster.responsefiles.ResponseFiles;
import org.faster.responsefiles.ResponseFilesDefault;
import org.faster.token.LtDefault;

public class RsDownload implements Response {
	
	private final PathMap map;
	private final CreatedSentData sent;
	
	public RsDownload(final PathMap mp, final CreatedSentData created) {
		this.map = mp;
		this.sent = created;
	}

	@Override
	public void execute(Connection connection) throws IOException {
		OutputStream out = connection.output();
		ResponseFiles response = new ResponseFilesDefault(
			map,
			out,
			this.sent
		);
		response.send(new LtDefault(connection.input()).next());
	}

	@Override
	public String name() {
		return "fd";
	}

}
