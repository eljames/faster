package org.faster.server.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SentMulticast {
	
	
	private final Message message;
	private final long period;
	
	public SentMulticast(Message message, long period) {
		this.message = message;
		this.period = period;
	}
	
	public void keepSendSignal() throws UnknownHostException, SocketException, IOException, InterruptedException {
		String ip = "230.0.0.0";
		int port = 4321;
		DatagramSocket socket = new DatagramSocket();
		DatagramPacket packet = createDatagramSocket(ip, port);
		try {
			while(true) {
				System.out.println("Sending package multicasting.");
				socket.send(packet);
				Thread.sleep(this.period);
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
			socket.close();
		}
	}
	
	
	private DatagramPacket createDatagramSocket(String ip, int port) throws UnknownHostException {
		InetAddress group = InetAddress.getByName(ip);
		byte[] msgBytes = this.message.message().getBytes();
		DatagramPacket packet = new DatagramPacket(msgBytes, msgBytes.length,group, port);
		return packet;
	}
	
}
