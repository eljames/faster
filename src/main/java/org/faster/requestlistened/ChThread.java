package org.faster.requestlistened;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class ChThread implements ConnectedHost {
	
	private final ExecutorService executor;
	private final ConnectedHost host;
	
	public ChThread(final ConnectedHost origin, ExecutorService exec) {
		this.host = origin;
		this.executor = exec;
	}

	@Override
	public void connected(final Socket socket) {
		this.executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					host.connected(socket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}	
}
