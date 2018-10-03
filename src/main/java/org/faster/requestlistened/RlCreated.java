package org.faster.requestlistened;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.faster.dirmap.DmDefault;
import org.faster.feedback.FileFeedback;
import org.faster.pathmap.PathMap;
import org.faster.response.RsDownload;
import org.faster.response.RsPath;
import org.faster.responselist.ResponseList;
import org.faster.responselist.ResponseListDefault;

public class RlCreated implements RequestListened {
	
	private final PathMap pathmap;
	private final ServerSocket server;
	private final FileFeedback feed;
	
	public RlCreated(final ServerSocket serv, final PathMap map, final FileFeedback fd) {
		this.server = serv;
		this.pathmap = map;
		this.feed = fd;
	}
	
	public RlCreated(final ServerSocket serv, final PathMap map) {
		this(serv, map, FileFeedback.NOTHING);
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
				this.pathmap, this.feed
			)
		);
	}
}
