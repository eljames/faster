package org.faster.path;

public class TestPath {
	
	/**
	 * Returns the resource path that belongs to the test path.
	 * @return
	 */
	public String resources() {
		return new AppPath().get() + "/src/test/resources";
	}

}
