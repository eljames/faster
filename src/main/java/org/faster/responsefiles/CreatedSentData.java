package org.faster.responsefiles;

import java.io.IOException;
import java.io.OutputStream;

import org.faster.virtualpath.VirtualPath;

public interface CreatedSentData {
	
	SentData directory(VirtualPath dir, OutputStream out) throws IOException;
	SentData file(VirtualPath dir, OutputStream out) throws IOException;
}
