package org.faster.responsefiles;

import java.io.IOException;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.token.LineToken;

public class RespondAllFiles {
	
	private LineToken lineToken;
	private EachPath eachPathSent;
	
	public RespondAllFiles(LineToken lineToken, EachPath eachPathSent) {
		this.lineToken = lineToken;
		this.eachPathSent = eachPathSent;
	}

	public void respondAll() throws IOException, ProtocolSyntaxErrorException {
		String decisionToken = "";
		while((decisionToken = this.lineToken.next()).equals("c")) {
			this.eachPathSent.send();
		}
		if(decisionToken.equals("df") == false) {
			throw new ProtocolSyntaxErrorException("Expected 'c' (continue token) or 'df' (downloads finished token)");
		}
	}
}
