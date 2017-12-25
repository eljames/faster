package org.faster.pathinfo.request;

/**
 * The path type. Definition of file and directory.
 * 
 * 
 * @author Eli James Aguiar
 *
 */
public interface PathType {
	
	public static final FilePathType FILE = new FilePathType();
	public static final DirectoryType DIRECTORY = new DirectoryType();

}
