package org.faster.response;

import java.io.IOException;

import org.faster.connection.Connection;
import org.faster.pathmap.PathMap;
import org.faster.sentsize.SentSize;

public class RsSize implements Response {
	
	private final PathMap pathMap;

	public RsSize(PathMap pathMap) {
		this.pathMap = pathMap;
	}

	@Override
	public void execute(Connection connection) throws IOException {
		new SentSize(connection, this.pathMap).send();
	}

	@Override
	public String name() {
		return "sz";
	}

}
