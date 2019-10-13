package org.faster.pathsize;

import java.io.IOException;

import org.faster.connection.Connection;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.token.LtDefault;
import org.faster.written.WtDefault;

public final class PathSize {

	private final Connection con;
	
	public PathSize(Connection con) {
		this.con = con;
	}
	
	public ResponseSize size(final String virtualPath) throws IOException, ProtocolSyntaxErrorException {
		request(virtualPath);
		return response();
	}
	
	/**
	 * Faster Protocol
	 * Request path size:
	 * 
	 * siz\n
	 * [path]\n
	 * 
	 * @param virtualPath
	 * @throws IOException
	 */
	private void request(String virtualPath) throws IOException {
		WtDefault written = new WtDefault(this.con.output());
		written
		.write("siz")
		.writeLine()
		.write(virtualPath)
		.writeLine()
		.flush();
	}
	
	/**
	 * Faster Protocol
	 * Response path size:
	 * 
	 * -- When virtual path exists
	 * k\n
	 * [size]\n
	 * 
	 * -- When virtual path not exists
	 * err\n -- (Error)
	 * fnf\n -- (File/Directory not found)
	 * 
	 * @return
	 * @throws IOException
	 * @throws ProtocolSyntaxErrorException
	 */
	private ResponseSize response() throws IOException, ProtocolSyntaxErrorException {
		LtDefault lineToken = new LtDefault(this.con.input());
		final String statusToken = lineToken.next();
		if(checkOk(statusToken)) {
			final String size = lineToken.next();
			return new ResponseSizeDefault("", size);
		}
		return checkError(statusToken, lineToken);
	}
	
	private boolean checkOk(String token) throws IOException, ProtocolSyntaxErrorException {
		if(token.equals("k")) {
			return true;
		}
		return false;
	}

	private ResponseSize checkError(String statusToken, LtDefault token) throws ProtocolSyntaxErrorException, IOException {
		if(statusToken.equals("err")) {
			String error = token.next();
			return new ResponseSizeDefault(error, "");
		}
		throw new ProtocolSyntaxErrorException("Expect 'k' or 'err' token. But returned '" + statusToken + "'");
	}
}
