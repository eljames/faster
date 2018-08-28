package org.faster.pathmap;

import java.nio.file.Path;
import java.util.Map;

public class CreatedPathMap {
	
	public PathMap create(Map<String, Path> files) {
		return new PmNoRelative(
			new PmDefault(files)
		);
	}

}
