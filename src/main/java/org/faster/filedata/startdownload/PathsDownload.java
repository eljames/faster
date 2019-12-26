package org.faster.filedata.startdownload;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;

public interface PathsDownload {
	void download() throws ProtocolSyntaxErrorException, IOException;
}
