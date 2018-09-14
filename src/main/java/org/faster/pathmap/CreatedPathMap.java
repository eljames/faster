package org.faster.pathmap;

public interface CreatedPathMap {
	CreatedPathMap add(String virtual, String real);
	CreatedPathMap remove(String virtual);
	PathMap map();
}
