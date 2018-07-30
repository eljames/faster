package org.faster.responselist;

public interface OptionNotFound {
	
	void inform(CharSequence command);
	public static final OptionNotFound NONE = new OnfNone();
	
	
	/**
	 * Default class that does not do anything when a option is not found. This class can be used when the 'Option not found' is not handled.
	 * @author Eli James
	 */
	 static class OnfNone implements OptionNotFound {

		@Override
		public void inform(CharSequence command) {/*Do nothing. */}
		 
	 }

}
