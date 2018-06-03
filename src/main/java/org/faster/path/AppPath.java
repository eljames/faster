package org.faster.path;

public class AppPath {
	
	/**
	 * Get the path where the application is running (if jar extension it will return the jar's path).
	 * @return The application path
	 */
	public String get() {
		return System.getProperty("user.dir");
	}

}
