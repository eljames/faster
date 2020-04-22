package org.faster.server.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class ReceivedMulticast {
	
	private AddedServers servers;
	private final long timeWaiting;
	
	/**
	 * 
	 * @param listedServers
	 * @param timeWaiting How long do you want to wait for servers on network? |(In milliseconds)
	 */
	public ReceivedMulticast(AddedServers servers, long timeWaiting) {
		this.servers = servers;
		this.timeWaiting = timeWaiting;
	}

	public void receive() throws IOException {

		String ip = "230.0.0.0";
		int port = 4321;
		MulticastSocket socket = new MulticastSocket(port);
		InetAddress group = InetAddress.getByName(ip);
		socket.joinGroup(group);
		this.receive(socket, group);
	}
	
	private ServerInfo serverInfo(String msg) {
		String[] splited = msg.split("\\n");
		String fasterMsg = splited[0];
		if(fasterMsg.equals("faster")) {
			String ip = splited[1];
			int port = Integer.parseInt(splited[2]);
			String name = splited[3];
			return new ServerInfoDefault(
				name,
				ip,
				port
			);
		}
		return ServerInfo.NOSERVER;
	}
	
	
	private void receive(MulticastSocket socket, InetAddress group) throws IOException {
		byte[] buffer = new byte[1024];
		try {
			Timeleft time = new Timeleft(this.timeWaiting).start();
			ArrayList<ServerInfo> arrayServer = new ArrayList<>();
			while (time.timeleft() >= 0) {
				String msg = receiveMsg(socket, buffer, time);
				ServerInfo server = serverInfo(msg);
				if(server != ServerInfo.NOSERVER) {
					if(arrayServer.contains(server) == false) {
						this.servers.add(server);
						arrayServer.add(server);
					}
				}
			}
			System.out.println("Looking for servers has ended.");
			this.servers.finished();
		} catch (SocketTimeoutException e) {
			System.out.println("Timeout ocurred to find servers.");
			this.servers.finished();
		} finally {
			socket.leaveGroup(group);
			socket.close();
		}
	}
	
	private String receiveMsg(MulticastSocket socket, byte[] buffer, Timeleft time) throws IOException {
		long timeleft = time.timeleft();
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.setSoTimeout((int) timeleft);
		socket.receive(packet);
		
		String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
		return msg;
	}
	
	class Timeleft {
		
		private final long timeToExpire;
		private long startTime;
		
		public Timeleft(long timeToExpire) {
			this.timeToExpire = timeToExpire;
		}
		
		public Timeleft start() {
			this.startTime = System.currentTimeMillis();
			return this;
		}
		
		public long timeleft() {
			long timeleft = this.timeToExpire - (System.currentTimeMillis() - this.startTime);
			if(timeleft <= 0) {
				return 0;
			}
			return timeleft;
		}
	}
}
