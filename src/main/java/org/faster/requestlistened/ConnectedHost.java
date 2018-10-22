package org.faster.requestlistened;

import java.io.IOException;
import java.net.Socket;

public interface ConnectedHost {
	void connected(Socket connection) throws IOException;
}
