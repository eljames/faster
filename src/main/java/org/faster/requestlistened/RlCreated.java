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
	private final FileFeedback feed;
	private final int port;
	
	public RlCreated(final PathMap map, final FileFeedback fd, final int port) {
		this.pathmap = map;
		this.feed = fd;
		this.port = port;
	}
	
	public RlCreated(final PathMap map, final int port) {
		this(map, FileFeedback.NOTHING, port);
	}

	@Override
	public void start() throws IOException {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		ResponseList responses = responselist();
		try(final ServerSocket server = new ServerSocket(this.port)) {
			while(true) {
				RequestListened request = new RlThread(
					executor,
					server.accept(),
					responses
				);
				request.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
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
