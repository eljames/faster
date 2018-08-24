package org.faster.responsefiles;

import java.io.IOException;

import org.faster.virtualpath.VirtualPath;

public interface SentData {
	void send(VirtualPath virtual) throws IOException;
}
