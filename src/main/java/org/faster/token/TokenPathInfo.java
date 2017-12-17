package org.faster.token;


public interface TokenPathInfo extends AutoCloseable {
	
	String next();
	void close();

}
