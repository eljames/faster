package org.faster.responsefiles;

import java.io.OutputStream;

import org.faster.virtualpath.VirtualPath;

public interface CreatedSentData {
	
	SentData directory(VirtualPath dir, OutputStream out);
	SentData file(VirtualPath dir, OutputStream out);
}
