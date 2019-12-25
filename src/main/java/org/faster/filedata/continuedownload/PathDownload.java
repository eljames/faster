package org.faster.filedata.continuedownload;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;

public interface PathDownload {
	void download(PathInfo info) throws ProtocolSyntaxErrorException, IOException;
}
