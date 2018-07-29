package org.faster.requestedpath.errors;

import org.faster.errors.Error;
import org.faster.errors.HandledError;

public class DirectoryNotFound implements Error {
	
	private final HandledError handled;
	
	public DirectoryNotFound(final HandledError error) {
		this.handled = error;
	}

	@Override
	public CharSequence name() {
		return "dnf";
	}

	@Override
	public void execute(CharSequence error, CharSequence path) {
		this.handled.handle(error, path);
	}

}
