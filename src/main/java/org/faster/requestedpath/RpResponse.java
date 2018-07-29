package org.faster.requestedpath;

import java.io.IOException;

import org.faster.errors.Errors;
import org.faster.errors.ErsNothing;
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
	private final Errors errors;
	
	private static final String OK = "k";

	public RpResponse(LineToken token, final Errors err) {
		this.token = token;
		this.errors = err;
	}
	
	public RpResponse(LineToken token) {
		this(token, new ErsNothing());
	}

	@Override
	public PathItems request(CharSequence path) throws IOException, ProtocolSyntaxErrorException {
		if(this.token.next().equals(OK)) {
			return new PiDefault(token);
		}
		this.errors.handle(this.token.next(), path);
		throw new ProtocolSyntaxErrorException("The server has not been responded with ok.");
	}
}
