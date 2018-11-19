package org.faster.requestlistened;

import org.faster.response.Response;
import org.faster.responsefiles.CreatedSentData;

public interface ListenedConfiguration {
	CreatedSentData sent();
	ConnectedHost connected(ConnectedHost connected);
	Response download(Response download);
	Response path(Response path);
}
