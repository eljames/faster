package org.faster.server.multicast;

import java.io.IOException;
import java.net.SocketException;

public class FasterServerMulticast {
	
	private final Thread thread;
	
	public FasterServerMulticast(String name, String hostIp, int hostPort) {
		this.thread = new Thread(() -> {
			try {
				new SentMulticast(
					new MessageDefault(
						name,
						hostIp,
						hostPort
					),
					4000
				).keepSendSignal();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
	
	public FasterServerMulticast start() throws SocketException, IOException {
		this.thread.setDaemon(true);
		this.thread.start();
		return this;
	}
	
	 public void stop() {
		 this.thread.interrupt();
	 }
	 
	 public void join() throws InterruptedException {
		 this.thread.join();
	 }
	
	class MessageDefault implements Message {
		
		private final String name;
		private final String hostIp;
		private final int hostPort;
		
		public MessageDefault(String name, String hostIp, int hostPort) {
			this.name = name;
			this.hostIp = hostIp;
			this.hostPort = hostPort;
		}

		@Override
		public String message() {
			StringBuilder message = new StringBuilder();
			message
				.append("faster")
				.append("\n")
				.append(this.hostIp)
				.append("\n")
				.append(this.hostPort)
				.append("\n")
				.append(this.name);
			return message.toString();
		}
		
	}
}
