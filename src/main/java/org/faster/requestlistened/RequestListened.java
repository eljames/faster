package org.faster.requestlistened;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;

public interface RequestListened {
	void start() throws IOException, ProtocolSyntaxErrorException;
}
