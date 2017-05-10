package com.kyx.factory;

public class HttpApiException extends Exception {
	private static final long serialVersionUID = 8179750486558034441L;

	private int httpStatusCode = -1;
	private String code;
	private String msg;

	public HttpApiException(String msg) {
		super(msg);
	}

	public HttpApiException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public HttpApiException(String msg, Throwable throwable, int httpStatusCode) {
		super(msg, throwable);
		this.httpStatusCode = httpStatusCode;
	}

	public int getHttpErrorCode() {
		return httpStatusCode;
	}

	public void setHttpErrorCode(int httpErrorCode) {
		this.httpStatusCode = httpErrorCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("HttpErrorCode=").append(getHttpErrorCode());
		sb.append(";code=").append(getCode());
		sb.append(";msg=").append(getMsg());
		return sb.toString();
	}
}
