package org.faster.requestedpath;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.faster.connection.Connection;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathitems.PathItems;
import org.faster.token.LtDefault;
import org.faster.written.DfWritten;

public class RpDefault implements RequestedPaths {
	
	private final Connection con;
	private final RequestedPaths requested;
	private static final int BUFFER = 4096;
	
	
	public RpDefault(Connection connection) throws UnknownHostException, IOException {
		
		this.con = connection;
		
		this.requested = new RpRequest(
				
			// Decorator to get the host's response (Note that host does not mean server here)
			new RpResponse(
				new LtDefault(
					new Scanner(
						new BufferedInputStream(this.con.input(), BUFFER), StandardCharsets.UTF_8.name()
					)
				)
			),
			
			// It's a wrapper for Writer
			new DfWritten(
				Channels.newWriter(
					Channels.newChannel(this.con.output()),
					StandardCharsets.UTF_8.newEncoder(),
					BUFFER
				)
			)
		);
		
	}

	@Override
	public PathItems request(CharSequence path) throws IOException, ProtocolSyntaxErrorException {
		return this.requested.request(path);
	}
}
