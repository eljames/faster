package org.faster.pathmap;

import java.io.IOException;

public interface CreatedPathMap {
	CreatedPathMap add(String virtual, String real) throws IOException;
	CreatedPathMap remove(String virtual) throws IOException;
	PathMap map();
}
