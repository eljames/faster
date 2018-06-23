package org.faster.token;


public interface LineToken extends AutoCloseable {
	
	String next();
	void close();

}
