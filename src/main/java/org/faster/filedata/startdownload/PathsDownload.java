package org.faster.filedata.startdownload;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;

public interface PathsDownload {
	void download() throws ProtocolSyntaxErrorException, IOException;
}
