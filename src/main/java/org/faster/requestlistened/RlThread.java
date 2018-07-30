package org.faster.requestlistened;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import org.faster.connection.Connection;
import org.faster.connection.CreatedConnection;
import org.faster.responselist.ResponseList;

public class RlThread implements RequestListened {
	
	private final ExecutorService executor;
	private final Socket sock;
	private final ResponseList responselist;
	
	public RlThread(final ExecutorService exec, final Socket sock, final ResponseList list) {
		this.executor = exec;
		this.sock = sock;
		this.responselist = list;
	}

	@Override
	public void start() throws IOException {
		final ResponseList responses = this.responselist;
		final Socket socket = this.sock;
		this.executor.execute(new Runnable() {
			@Override
			public void run() {
				try(Connection connection = new CreatedConnection().connect(socket)) {
					new RlDefault(connection, responses).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
