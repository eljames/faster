package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;
import org.faster.exception.ProtocolSyntaxErrorException;

public interface FileData {

	void download(InputStream input) throws IOException, ProtocolSyntaxErrorException;
}
