package org.faster.pathmap;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.faster.responsepaths.CreatedPathMap;
import org.faster.responsepaths.ResourcePath;
import org.faster.responsepaths.ResponsePaths;
import org.faster.virtualpath.VirtualPath;
import org.junit.Test;

public class PathMapTest {
	
	@Test
	public void getsRealPath() throws IOException {
		String path = new ResourcePath().get(ResponsePaths.class) + "/root";
		
		assertTrue(new CreatedPathMap().create("/org/faster/responsepaths/root").get("/media").real().getAbsolutePath().equals(path));
	}
	
	@Test
	public void virtualPathCorrect() throws IOException {
		VirtualPath virtual = new CreatedPathMap().create("/org/faster/responsepaths/root/pics").get("/media");
		List<VirtualPath> list = new ArrayList<>(virtual.paths());
		Collections.sort(list, new VirtualComparator());
		assertTrue(list.get(0).path().equals("/media/file_test.txt"));
		assertTrue(list.get(1).path().equals("/media/me.txt"));
		assertTrue(list.get(2).path().equals("/media/travel"));
	}
	
	class VirtualComparator implements Comparator<VirtualPath> {

		@Override
		public int compare(VirtualPath o1, VirtualPath o2) {
			return o1.path().toString().compareTo(o2.path().toString());
		}
		
	}

}
