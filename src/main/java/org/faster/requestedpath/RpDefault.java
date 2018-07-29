package org.faster.requestedpath;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
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
			new WtDefault(
				new OutputStreamWriter(this.con.output(), StandardCharsets.UTF_8.name())
			)
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
