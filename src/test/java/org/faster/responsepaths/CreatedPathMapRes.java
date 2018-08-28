package org.faster.responsepaths;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.faster.path.TestPath;
import org.faster.pathmap.PathMap;
import org.faster.pathmap.PmDefault;
import org.faster.pathmap.PmNoRelative;

/**
 * Create a {@link PathMap} for teste resource.
 * @author Eli James Aguiar
 *
 */
public class CreatedPathMapRes {
	public PathMap create(String resourcePath) {
		Map<String, Path> dirs = new HashMap<>();
		String path = new TestPath().resources() + resourcePath;
		dirs.put("media", Paths.get(path));
		return new PmNoRelative(
			new PmDefault(dirs)
		);
	}
}
