package org.faster.response;

import java.io.IOException;

import org.faster.connection.Connection;

public interface Response {
	
	/**
	 * Execute the response.
	 * @param connection
	 * @throws IOException
	 */
	void execute(Connection connection) throws IOException;
	
	/**
	 * Returns the request command name. If the response has "f" as its name, this could be a command representing "request file" for example.
	 * @return
	 */
	String name();
	
	
	public static final Response NONE = new RsNone();
	
	static class RsNone implements Response {
	
		@Override
		public void execute(Connection connection) throws IOException {}
	
		@Override
		public String name() {
			return "";
		}
	}

}
