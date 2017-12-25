package org.faster.exception;

public class ProtocolSyntaxErrorException extends Exception {

	public static String defaultMsg = "Procotol syntax error.";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ProtocolSyntaxErrorException() {
		super(defaultMsg);
	}
	
	public ProtocolSyntaxErrorException(String msg) {
		super(msg);
	}

}
