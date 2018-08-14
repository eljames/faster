package org.faster.filedata;

import java.io.IOException;
import org.faster.exception.ProtocolSyntaxErrorException;

public interface FileData {

	void download() throws IOException, ProtocolSyntaxErrorException;
}
