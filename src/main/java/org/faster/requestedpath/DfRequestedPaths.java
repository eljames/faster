package org.faster.request;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.faster.connection.Connection;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathitems.PathItems;
import org.faster.token.DfTokenPathInfo;

public class DfRequestedPaths implements RequestedPaths {
	
	private final Connection con;
	private final RequestedPaths requested;
	private static final int BUFFER = 4096;
	
	public DfRequestedPaths(Connection connection) throws UnknownHostException, IOException {
		
		this.con = connection;
		
		this.requested = new RpRequest(
			new RpResponse(
				new DfTokenPathInfo(
					new Scanner(
						new BufferedInputStream(this.con.input(), BUFFER), StandardCharsets.UTF_8.name()
					)
				)
			),
			new BufferedOutputStream(this.con.output(), BUFFER)
		);
		
	}

	@Override
	public PathItems request(CharSequence path) throws IOException, ProtocolSyntaxErrorException {
		return this.requested.request(path);
	}
}
