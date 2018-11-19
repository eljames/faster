package org.faster.requestlistened;

import java.io.IOException;
import java.net.Socket;

import org.faster.connection.Connection;
import org.faster.connection.CreatedConnection;
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
		Connection connection = new CreatedConnection().connect(socket);
		LineToken token = new LtDefault(
			connection.input()
		);
		while(true) {
			final String command = token.next();
			if(command.equals("")) {
				break;
			}
			this.responses.get(command).execute(connection);
		}
	}

}
