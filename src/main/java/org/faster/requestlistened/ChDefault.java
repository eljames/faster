package org.faster.requestlistened;

import java.io.IOException;
import java.net.Socket;

import org.faster.connection.CnDefault;
import org.faster.connection.Connection;
import org.faster.responselist.ResponseList;
import org.faster.token.LineToken;
import org.faster.token.LtDefault;

public class ChDefault implements ConnectedHost {
	
	private final ResponseList responses;
	
	public ChDefault(final ResponseList rsl) {
		this.responses = rsl;
	}

	@Override
	public void connected(Socket socket) throws IOException {
		Connection connection = new CnDefault(socket);
		LineToken token = new LtDefault(
			connection.input()
		);
		this.responses.get(token.next()).execute(connection);
	}

}
