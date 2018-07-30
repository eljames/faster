package org.faster.responselist;

import org.faster.response.Response;

public interface ResponseList {
	
	ResponseList add(Response response);
	Response get(CharSequence name);
}
