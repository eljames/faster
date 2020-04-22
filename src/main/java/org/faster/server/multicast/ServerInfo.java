package org.faster.server.multicast;

public interface ServerInfo {
	
	String name();
	String ip();
	int port();
	
	public final static ServerInfo NOSERVER = new ServerInfoNothing();
	

	class ServerInfoNothing implements ServerInfo {
		
		@Override
		public String name() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String ip() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int port() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}

}
