package org.faster.responsedpath;

public class ResourcePath {
	
	public <T> String  get(Class<T> c) {
		return  System.getProperty("user.dir") + "/src/test/resources/" + c.getPackage().getName().replace(".", "/");
	}

}
