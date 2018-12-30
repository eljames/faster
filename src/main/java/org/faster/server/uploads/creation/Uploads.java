package org.faster.server.uploads.creation;

import org.faster.connection.Connection;
import org.faster.virtualpath.VirtualPath;

public interface Uploads {
	public UploadElement add(VirtualPath path, Connection connection);
}
