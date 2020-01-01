package org.faster.requestlistened;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.faster.dirmap.DmDefault;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathmap.PathMap;
import org.faster.response.RsDownload;
import org.faster.response.RsPath;
import org.faster.responselist.ResponseList;
import org.faster.responselist.ResponseListDefault;

public class RlCreated implements RequestListened {
	
	private final PathMap pathmap;
	private final ServerSocket server;
	private final ListenedConfiguration configuration;
	
	public RlCreated(final ServerSocket serv, final PathMap map, ListenedConfiguration config) {
		this.server = serv;
		this.pathmap = map;
		this.configuration = config;
	}
	
	public RlCreated(final ServerSocket serv, final PathMap map) {
		this(serv, map, new LcDefault());
	}

	@Override
	public void start() throws IOException, ProtocolSyntaxErrorException {
		ExecutorService executor = Executors.newFixedThreadPool(5, new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setDaemon(true);
				thread.setName("Faster Server Request Thread");
				return thread;
			}
		});
		ResponseList responses = responselist();
		RequestListened request = new RlMultiUser(
			new ChThread(
				this.configuration.connected(
					new ChDefault(responses)
				),
				executor
			),
			this.server
		);
		request.start();
	}
	
	private ResponseList responselist() {
		return new ResponseListDefault()
			.add(
				this.configuration.path(
					new RsPath(
						new DmDefault(this.pathmap)
					)
				)
			).add(
				this.configuration.download(
					new RsDownload(
						this.pathmap,
						this.configuration
					)
				)
			);
	}
}
