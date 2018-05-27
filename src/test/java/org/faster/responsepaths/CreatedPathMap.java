package org.faster.responsepaths;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.faster.pathmap.PathMap;
import org.faster.pathmap.PmDefault;
import org.faster.pathmap.PmNoRelative;

public class CreatedPathMap {
	public PathMap create(String resourcePath) {
		Map<String, Path> dirs = new HashMap<>();
		String path = System.getProperty("user.dir") + "/src/test/resources" + resourcePath;
		dirs.put("media", Paths.get(path));
		return new PmNoRelative(
			new PmDefault(dirs)
		);
	}
}
