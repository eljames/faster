package org.faster.server.uploads.creation;

import java.io.IOException;

import org.faster.connection.Connection;
import org.faster.virtualpath.VirtualPath;

public interface Uploads {
	public UploadElement add(VirtualPath path, Connection connection) throws IOException;
}
