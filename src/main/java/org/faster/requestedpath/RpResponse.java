package org.faster.requestedpath;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathitems.PiDefault;
import org.faster.pathitems.PathItems;
import org.faster.token.LineToken;


/**
 * Decorator. Response requested peer.
 * @author Eli James Aguiar
 *
 */
public class RpResponse implements RequestedPaths {
	
	private final LineToken token;
	
	private static final String OK = "k";

	public RpResponse(LineToken token) {
		this.token = token;
	}

	@Override
	public PathItems request(CharSequence path) throws IOException, ProtocolSyntaxErrorException {
		
		if(tokenOk()) {
			return new PiDefault(token);
		}
		
		throw new ProtocolSyntaxErrorException("The server has not been responded with ok.");
	}
	
	private boolean tokenOk() throws IOException {
		return this.token.next().equals(OK);
	}

}
