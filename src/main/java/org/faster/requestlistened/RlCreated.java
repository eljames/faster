package org.faster.requestlistened;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.faster.dirmap.DmDefault;
import org.faster.pathmap.PathMap;
import org.faster.response.RsDownload;
import org.faster.response.RsPath;
import org.faster.responsefiles.CreatedSentData;
import org.faster.responsefiles.CreatedSentDataDefault;
import org.faster.responselist.ResponseList;
import org.faster.responselist.ResponseListDefault;

public class RlCreated implements RequestListened {
	
	private final PathMap pathmap;
	private final ServerSocket server;
	private final CreatedSentData sent;
	
	public RlCreated(final ServerSocket serv, final PathMap map, final CreatedSentData created) {
		this.server = serv;
		this.pathmap = map;
		this.sent = created;
	}
	
	public RlCreated(final ServerSocket serv, final PathMap map) {
		this(serv, map, new CreatedSentDataDefault());
	}

	@Override
	public void start() throws IOException {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		ResponseList responses = responselist();
		while(true) {
			RequestListened request = new RlThread(
				executor,
				this.server.accept(),
				responses
			);
			request.start();
		}
	}
	
	private ResponseList responselist() {
		return new ResponseListDefault()
		.add(
			new RsPath(
				new DmDefault(this.pathmap)
			)
		).add(
			new RsDownload(
				this.pathmap, this.sent
			)
		);
	}
}
