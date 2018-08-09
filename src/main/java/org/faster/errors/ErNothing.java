package org.faster.errors;

/**
 *  It can be used when the error will not be handled
 */
public class ErNothing implements Error{

	@Override
	public CharSequence name() {
		return "";
	}

	@Override
	public void execute(CharSequence path) {/* Nothing */}

}
