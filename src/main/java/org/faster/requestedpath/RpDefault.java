package org.faster.requestedpath;

import java.io.IOException;
import java.net.UnknownHostException;
import org.faster.connection.Connection;
import org.faster.errors.Errors;
import org.faster.errors.ErsNothing;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathitems.PathItems;
import org.faster.token.LtDefault;
import org.faster.written.WtDefault;

public class RpDefault implements RequestedPaths {
	
	private final Connection con;
	private final RequestedPaths requested;
	
	public RpDefault(Connection connection,  Errors error) throws UnknownHostException, IOException {
		
		this.con = connection;
		
		this.requested = new RpRequest(
				
			// Decorator to get the host's response (Note that host does not mean server here)
			new RpResponse(
				new LtDefault(
					this.con.input()
				),
				error
			),
			
			// It's a wrapper for Writer
			new WtDefault(this.con.output())
			
		);
		
	}
	
	public RpDefault(Connection connection) throws UnknownHostException, IOException {
		this(connection, new ErsNothing());
	}

	@Override
	public PathItems request(CharSequence path) throws IOException, ProtocolSyntaxErrorException {
		return this.requested.request(path);
	}
}
