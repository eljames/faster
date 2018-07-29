package org.faster.connection;

import java.io.IOException;
import java.net.Socket;

public class CreatedConnection {
	
	public Connection connect(final String ip, final int port) throws IOException {
		return connection(new Socket(ip, port));
	}
	
	public Connection connect(Socket socket) throws IOException {
		return connection(socket);
	}
	
	private Connection connection(Socket socket) throws IOException {
		Connection con = new CnBuffered(
			new CnDefault(socket)
		);
		return new CnCached(con, con.input(), con.output());
	}
}
