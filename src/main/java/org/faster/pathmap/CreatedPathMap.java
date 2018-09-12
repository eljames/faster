package org.faster.pathmap;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CreatedPathMap {
	
	private final Map<String, Path> map;
	
	public CreatedPathMap() {
		this.map = new HashMap<>();
	}
	
	public CreatedPathMap add(String virtual, String real) {
		this.map.put(virtual, Paths.get(real));
		return this;
	}
	
	public PathMap map() {
		return new PmNoRelative(
			new PmDefault(this.map)
		);
	}

}
