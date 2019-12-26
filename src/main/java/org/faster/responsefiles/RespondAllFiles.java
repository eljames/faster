package org.faster.responsefiles;

import java.io.IOException;
import java.io.InputStream;
import org.faster.connection.Connection;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.token.LtDefault;

public class RespondAllFiles {
	
	private Connection con;
	private EachPath eachPathSent;
	
	public RespondAllFiles(Connection con, EachPath eachPathSent) {
		this.con = con;
		this.eachPathSent = eachPathSent;
	}

	public void respondAll() throws IOException, ProtocolSyntaxErrorException {
		InputStream input = con.input();
		LtDefault lineToken = new LtDefault(input);
		String decisionToken = "";
		while((decisionToken = lineToken.next()).equals("c")) {
			this.eachPathSent.send();
		}
		if(decisionToken.equals("df") == false) {
			throw new ProtocolSyntaxErrorException("Expected 'c' (continue token) or 'df' (downloads finished token)");
		}
	}
}
