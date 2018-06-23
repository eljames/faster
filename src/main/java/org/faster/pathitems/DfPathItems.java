package org.faster.pathitems;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;
import org.faster.pathinfo.request.PtPath;
import org.faster.pathinfo.request.PtSize;
import org.faster.pathinfo.request.PtType;
import org.faster.token.LineToken;

/**
 * Decorator for 'Separator' token verification.
 * The serator token is what divide between each path information.
 * @author Eli James Aguiar
 *
 */
public class DfPathItems implements PathItems {
	
	private final LineToken token;
	private final Cache<Boolean> next;
	private final Cache<PathInfo> path;
	
	private static final String END_TOKEN = "e";
	
	public DfPathItems(final LineToken tokenPath) {
		this.token = tokenPath;
		this.next = new Cache<Boolean>(true);
		this.path = new Cache<PathInfo>(PathInfo.NO_PATH_INFO);
	}

	@Override
	public boolean next() throws IOException, ProtocolSyntaxErrorException {
		
		if(this.next.element()) {
			
			String nextToken = this.token.next();
			
			if(hasNext(nextToken)) {
				nextElement();
				return true;
			}
			else if(nextToken.equals(END_TOKEN)) {
				return this.next.element(false);
			}
			else {
				this.next.element(false);
				throw new ProtocolSyntaxErrorException("Expected token '' (empty token) or '" + END_TOKEN + "' (end token)");
			}
		}
		return false;
	}

	@Override
	public PathInfo pathInfo() {
		
		if(this.next.element())
			return this.path.element();
		
		return PathInfo.NO_PATH_INFO;
	}
	
	private boolean hasNext(String nextToken) throws IOException, ProtocolSyntaxErrorException {
		
		// If token is empty string, there is next item;
		if( ! nextToken.equals(""))
			return false;
			
		return true;
	}
	
	private void nextElement() throws IOException, ProtocolSyntaxErrorException {

		this.path.element(
			new PtSize(
				new PtPath(
					new PtType(
						this.token),
					this.token),
				this.token
			)
		);
	}
}
