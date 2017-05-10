package com.kyx.factory;

import com.google.gson.Gson;

public class HttpApiResponse {
	private Object data;
	private String requestId;
	
	public HttpApiResponse(String requestId) {
		this(requestId, null);
	}
	
	public HttpApiResponse(String requestId, Object data) {
		this.requestId = requestId;
		this.data = data;
	}
	
	public String getDataString() {
		if (data == null) {
			return null;
		}
		return new Gson().toJson(data);
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(data);
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public String getRequestId() {
		return requestId;
	}
	
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
}
