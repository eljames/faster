package org.faster.pathmap;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CreatedMap {
	
	private final HashMap<String, Path> map;
	
	public CreatedMap() {
		this.map = new HashMap<>();
	}
	
	public CreatedMap add(String virtual, String real) {
		this.map.put(virtual, Paths.get(real));
		return this;
	}
	
	public Map<String, Path> map() {
		return this.map;
	}

}
