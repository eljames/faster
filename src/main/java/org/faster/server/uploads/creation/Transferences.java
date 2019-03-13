package org.faster.server.uploads.creation;

import java.io.IOException;

import org.faster.connection.Connection;
import org.faster.virtualpath.VirtualPath;

public interface Transferences {
	public TransferElement add(VirtualPath path, Connection connection) throws IOException;
}
