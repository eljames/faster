package org.faster.response;

import java.io.IOException;
import org.faster.connection.Connection;
import org.faster.dirmap.DirMap;
import org.faster.responsepaths.ResponsePaths;
import org.faster.responsepaths.RspDirectoryExists;
import org.faster.responsepaths.RspFinished;
import org.faster.responsepaths.RspOK;
import org.faster.responsepaths.RspResponse;
import org.faster.sentpath.SentPath;
import org.faster.sentpath.SpDefault;
import org.faster.token.LineToken;
import org.faster.token.LtDefault;
import org.faster.written.Written;
import org.faster.written.WtDefault;

public class RsPath implements Response {

	private final DirMap dirmap;
	
	public RsPath(final DirMap map) {
		this.dirmap = map;
	}
	
	@Override
	public void execute(Connection connection) throws IOException {
		Written written = written(connection);
		SentPath sent = new SpDefault(written);
		ResponsePaths response = new RspDirectoryExists(
			new RspOK(
				new RspResponse(
					new RspFinished(written), this.dirmap, sent
				),
				written
			),
			this.dirmap,
			written
		);
		response.respond(token(connection).next());
	}
	
	@Override
	public String name() {
		return "pi";
	}
	
	private Written written(Connection connection) throws IOException {
		return new WtDefault(
			connection.output()
		);
	}
	
	private LineToken token(Connection connection) throws IOException {
		return new LtDefault(
			connection.input()
		);
	}

}
