package org.faster.pathmap;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CpmDefault implements CreatedPathMap {
	
	private final Map<String, Path> map;
	
	public CpmDefault() {
		this.map = new HashMap<>();
	}
	
	public CreatedPathMap add(String virtual, String real) throws IOException {
		if(!virtual.startsWith("/")) {
			throw new IOException("The new virtual path must be a absolute path. Eg: /audio/music insead of audio/music");
		}
		this.map.put(virtual, Paths.get(real));
		return this;
	}
	
	public PathMap map() {
		return new PmNoRelative(
			new PmDefault(this.map)
		);
	}

	@Override
	public CreatedPathMap remove(String virtual) {
		this.map.remove(virtual);
		return this;
	}

}
