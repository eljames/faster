package org.faster.requestedpath.errors;

import org.faster.errors.Error;
import org.faster.errors.HandledError;

/**
 * When file or directory not found on server, the client will call 'execute' method from this class.
 * @author Eli James Aguiar
 *
 */
public class FileNotFound implements Error {
	
	private final HandledError handled;
	
	public FileNotFound(final HandledError hdl) {
		this.handled = hdl;
	}

	@Override
	public CharSequence name() {
		return "fnf";
	}

	@Override
	public void execute(CharSequence path) {
		this.handled.handle(this.name(), path);
	}

}
