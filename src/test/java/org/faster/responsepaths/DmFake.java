package org.faster.responsepaths;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.faster.dirmap.DirMap;
import org.faster.pathinfo.PathInfo;
import org.faster.pathinfo.response.PtResponse;

/**
 * A instance of this class will operate over paths from a given root path.
 * If root path is /home/user and a given path is /docs/manual then it will work on /home/user/docs/manual.
 * @author Eli James
 *
 */
public class DmFake implements DirMap {
	
	private final String root;
	private final boolean has;
	
	public DmFake(final String root) {
		this.root = root;
		this.has = true;
	}
	
	public DmFake(final boolean has) {
		this.has = has;
		this.root = "";
	}
	
	@Override
	public boolean has(CharSequence path) {
		return this.has;
	}

	@Override
	public Collection<PathInfo> paths(CharSequence path) {
		
		Path absoluteRoot = Paths.get(new ResourcePath().get(this.getClass()) + this.root);
		Path relativePath = Paths.get("/").relativize(Paths.get(path.toString()));
		
		File[] paths = absoluteRoot.resolve(relativePath).toFile().listFiles();
		List<PathInfo> pathInfoList = new ArrayList<PathInfo>();
		for(File file : paths) {
			pathInfoList.add(new PtResponse(absoluteRoot, file));
		}
		return pathInfoList;
	}


}
