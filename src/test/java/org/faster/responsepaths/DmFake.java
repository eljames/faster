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

public class DmFake implements DirMap {
	
	private final String root;
	private final boolean has;
	
	public DmFake(final String path) {
		this.root = path;
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
	public Collection<PathInfo> paths(String path) {
		Path dirPath = Paths.get(new ResourcePath().get(this.getClass()) + this.root);
		File[] paths = dirPath.resolve(Paths.get("/").relativize(Paths.get(path))).toFile().listFiles();
		List<PathInfo> pathInfoList = new ArrayList<PathInfo>();
		for(File file : paths) {
			pathInfoList.add(new PtResponse(dirPath, file));
		}
		return pathInfoList;
	}


}
