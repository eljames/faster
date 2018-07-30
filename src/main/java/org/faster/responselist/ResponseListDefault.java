package org.faster.responselist;

import java.util.ArrayList;
import java.util.List;

import org.faster.response.Response;

public class ResponseListDefault implements ResponseList {
	
	private final List<Response> responses;
	private final OptionNotFound notfound;
	
	public ResponseListDefault(final OptionNotFound notfound) {
		this.responses = new ArrayList<Response>();
		this.notfound = notfound;
	}
	
	public ResponseListDefault() {
		this(OptionNotFound.NONE);
	}

	@Override
	public ResponseList add(Response response) {
		this.responses.add(response);
		return this;
	}

	@Override
	public Response get(CharSequence name) {
		for(Response response : this.responses) {
			if(response.name().equals(name)) {
				return response;
			}
		}
		this.notfound.inform(name);
		return Response.NONE;
	}

}
