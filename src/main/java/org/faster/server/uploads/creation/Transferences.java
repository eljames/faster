package org.faster.server.uploads.creation;

import java.io.IOException;

import org.faster.connection.Connection;
import org.faster.virtualpath.VirtualPath;

public interface Transferences {
	TransferElement add(Connection connection) throws IOException;
	TransferElement add(final VirtualPath path, final Connection connection) throws IOException;
}
