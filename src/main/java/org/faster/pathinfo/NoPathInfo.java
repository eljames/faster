package org.faster.pathinfo;

/**
 * This type indicates that the path information does not exist.
 * @author Eli James Aguiar
 *
 */
public class NoPathInfo implements PathInfo {

	@Override
	public boolean isDirectory() {
		throw exception();
	}

	@Override
	public CharSequence path() {
		throw exception();
	}

	@Override
	public long size() {
		throw exception();
	}
	
	private UnsupportedOperationException exception() {
		return new UnsupportedOperationException("Not supported for '" + this.getClass().getSimpleName() + "' class.");
	}

}
