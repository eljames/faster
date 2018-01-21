package org.faster.sentpath;

import java.io.IOException;

import org.faster.pathinfo.PathInfo;

public interface SentPath {
	
	void send(PathInfo pathInfo) throws IOException; 
}
