package org.faster.requestlistened;

import java.io.IOException;
import java.net.Socket;

import org.faster.exception.ProtocolSyntaxErrorException;

public interface ConnectedHost {
	void connected(Socket connection) throws IOException, ProtocolSyntaxErrorException;
}
