package org.faster.response;

import java.io.IOException;
import java.io.OutputStream;

import org.faster.connection.Connection;
import org.faster.pathmap.PathMap;
import org.faster.requestlistened.ListenedConfiguration;
import org.faster.responsefiles.RespondAllFiles;
import org.faster.responsefiles.ResponseFiles;
import org.faster.responsefiles.ResponseFilesDefault;
import org.faster.token.LtDefault;

public class RsDownload implements Response {
	
	private final PathMap map;
	private final ListenedConfiguration configuration;
	
	public RsDownload(final PathMap mp, final ListenedConfiguration config) {
		this.map = mp;
		this.configuration = config;
	}

	@Override
	public void execute(Connection connection) throws IOException {
		new RespondAllFiles(
			connection,
			() -> {
				OutputStream out = connection.output();
				ResponseFiles response = new ResponseFilesDefault(
					map,
					out,
					this.configuration.sent(connection)
				);
				response.send(new LtDefault(connection.input()).next());
			}
		);
	}

	@Override
	public String name() {
		return "fd";
	}

}
