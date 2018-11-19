package org.faster.requestlistened;

import org.faster.response.Response;
import org.faster.responsefiles.CreatedSentData;
import org.faster.responsefiles.CreatedSentDataDefault;

public class LcDefault implements ListenedConfiguration {

	@Override
	public CreatedSentData sent() {
		return new CreatedSentDataDefault();
	}

	@Override
	public ConnectedHost connected(final ConnectedHost connected) {
		return connected;
	}

	@Override
	public Response download(Response rsdownload) {
		return rsdownload;
	}

	@Override
	public Response path(Response rspath) {
		return rspath;
	}

}
