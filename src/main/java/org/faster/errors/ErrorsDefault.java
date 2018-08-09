package org.faster.errors;

import java.util.ArrayList;
import java.util.List;

public class ErrorsDefault implements Errors {
	
	private final List<Error> list;
	private final HandledError unknown;
	
	/**
	 * 
	 * @param error When the error is unknown, the {@code error} will be executed. 
	 */
	public ErrorsDefault(final HandledError error) {
		this.list = new ArrayList<>();
		this.unknown = error;
	}
	
	public ErrorsDefault() {
		this(new HandledErrorNothing());
	}

	@Override
	public Errors add(Error error) {
		this.list.add(error);
		return this;
	}

	@Override
	public void handle(final CharSequence name, final CharSequence path) {
		for(final Error error : this.list) {
			if(error.name().equals(name)) {
				error.execute(path);
				return;
			}
		}
		this.unknown.handle(name, path);
	}

}
