package org.faster.requestedpath;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathitems.DfPathItems;
import org.faster.pathitems.PathItems;
import org.faster.token.TokenPathInfo;


/**
 * Decorator. Response requested peer.
 * @author Eli James Aguiar
 *
 */
public class RpResponse implements RequestedPaths {
	
	private final TokenPathInfo token;
	
	private static final String OK = "k";

	public RpResponse(TokenPathInfo token) {
		this.token = token;
	}

	@Override
	public PathItems request(CharSequence path) throws IOException, ProtocolSyntaxErrorException {
		
		if(tokenOk()) {
			return new DfPathItems(token);
		}
		
		throw new ProtocolSyntaxErrorException("The server has not been responded with ok.");
	}
	
	private boolean tokenOk() {
		return this.token.next().equals(OK);
	}

}
