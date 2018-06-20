package org.faster.pathmap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.faster.path.TestPath;
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
	
	@Test
	public void virtualPathCheckType() throws IOException {
		VirtualPath virtual = new CreatedPathMap().create("/org/faster/responsepaths/root/pics").get("/media");
		List<VirtualPath> list = new ArrayList<>(virtual.paths());
		Collections.sort(list, new VirtualComparator());
		
		// /media/file_test.txt
		assertTrue(!list.get(0).isDirectory());
		
		// /media/me.txt
		assertTrue(!list.get(1).isDirectory());
		
		// /media/travel
		assertTrue(list.get(2).isDirectory());
	}
	
	@Test
	public void getThirdLevelPathFiles() throws IOException {
		VirtualPath virtual = new CreatedPathMap().create("/org/faster/responsepaths/root/pics").get("/media");
		List<VirtualPath> list = new ArrayList<>(virtual.paths());
		Collections.sort(list, new VirtualComparator());
		
		// /media/travel/italy.txt
		VirtualPath path = list.get(2).paths().iterator().next();
		assertEquals("/media/travel/italy.txt", path.path());
	}
	
	
	@Test
	public void listRoot() throws IOException {
		String resourcePath = new TestPath().resources();
		
		HashMap<String, Path> map = new HashMap<>();
		map.put("/docs", Paths.get(resourcePath + "/org/faster/pathinfo/response"));
		map.put("/photos", Paths.get(resourcePath + "org/faster/responsepaths/root/pics"));
		
		PathMap pathmap = new PmNoRelative(
			new PmDefault(map)
		);
		VirtualPath path = pathmap.get("/");
		List<VirtualPath> paths = new ArrayList<>(path.paths());
		Collections.sort(paths, new VirtualComparator());
		assertEquals(paths.get(0).path().toString(), "/docs");
		assertEquals(paths.get(1).path().toString(), "/photos");
		
	}
	
	class VirtualComparator implements Comparator<VirtualPath> {

		@Override
		public int compare(VirtualPath o1, VirtualPath o2) {
			return o1.path().toString().compareTo(o2.path().toString());
		}
		
	}

}
