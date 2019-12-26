package org.faster.responsefiles;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;

public interface EachPath {
	public void send() throws IOException, ProtocolSyntaxErrorException;
}
