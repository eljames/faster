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
		final OutputStream out = connection.output();
		final LtDefault linetoken = new LtDefault(connection.input());
		new RespondAllFiles(
			connection,
			() -> {
				ResponseFiles response = new ResponseFilesDefault(
					this.map,
					out,
					this.configuration.sent(connection)
				);
				response.send(linetoken.next());
			}
		);
	}

	@Override
	public String name() {
		return "fd";
	}

}
