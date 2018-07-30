package org.faster.requestlistened;

import java.io.IOException;
import org.faster.connection.Connection;
import org.faster.responselist.ResponseList;
import org.faster.token.LineToken;
import org.faster.token.LtDefault;

public class RlDefault implements RequestListened {
	
	private final Connection connection;
	private final ResponseList responses;
	
	public RlDefault(final Connection con, final ResponseList list) {
		this.connection = con;
		this.responses = list;
	}

	@Override
	public void start() throws IOException {
		LineToken token = new LtDefault(
			this.connection.input()
		);
		this.responses.get(token.next()).execute(this.connection);
	}
}
