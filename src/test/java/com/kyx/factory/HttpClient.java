package com.kyx.factory;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClient {
	public static class Charset {
		public static final String UTF8 = "UTF-8";
		public static final String GBK = "GBK";
	}
	
	private String charset = Charset.UTF8;
	private int connectionTimeout = 20000;
	private int soTimeout = 30000;
	
	private Map<String, String> credentials;
	
	private DefaultHttpClient httpClient = new DefaultHttpClient();
	
	public HttpClient() {
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, connectionTimeout);
		HttpConnectionParams.setSoTimeout(params, soTimeout);
		httpClient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, charset);
		httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(3, true));
	}
	
	public HttpApiResponse get(String url) throws HttpApiException, IOException {
		return get(url, null);
	}
	
	public HttpApiResponse get(String url, Map<String, String> parameters) throws HttpApiException, IOException {
		if (null != parameters && parameters.size() > 0) {
			String encodedParams = encodeParameters(parameters);
			if (0 <= url.indexOf("?")) {
				url += "&" +  encodedParams;
			} else {
				url += "?" + encodedParams;
			}
		}
		HttpGet httpget = new HttpGet(url);
		httpget.addHeader("contentType", "application/x-www-form-urlencoded");
		
		return handleRequest(httpget);
	}
	
	public HttpApiResponse post(String url, Map<String, String> parameters) throws HttpApiException, IOException {
		HttpPost httpPost = new HttpPost(url);
		
		if (parameters != null && !parameters.isEmpty()) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			Set<String> keySet = parameters.keySet();
			for (String key : keySet) {
				String value = parameters.get(key);
				nvps.add(new BasicNameValuePair(key, value));
			}
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
			} catch (UnsupportedEncodingException e) {
				throw new HttpApiException("Unsupported encoding", e);
			}
		}
		
		return handleRequest(httpPost);
	}
	
	public HttpApiResponse delete(String url) throws HttpApiException, IOException {
		HttpDelete httpDelete = new HttpDelete(url);
		return handleRequest(httpDelete);

	}
	
	public HttpApiResponse put(String url, Map<String, String> parameters) throws HttpApiException, IOException {
		HttpPut httpPut = new HttpPut(url);

		if (parameters != null && !parameters.isEmpty()) {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			Set<String> keySet = parameters.keySet();
			for (String key : keySet) {
				String value = parameters.get(key);
				nvps.add(new BasicNameValuePair(key, value));
			}
			try {
				httpPut.setEntity(new UrlEncodedFormEntity(nvps, charset));
			} catch (UnsupportedEncodingException e) {
				throw new HttpApiException("Unsupported encoding", e);
			}
		}

		return handleRequest(httpPut);
	}
	
	private HttpApiResponse handleRequest(HttpUriRequest request) throws HttpApiException, IOException {
		try {
			if (credentials == null || credentials.isEmpty()) 
				throw new IllegalStateException("credentials is not set");
			
			Set<String> keySet = credentials.keySet();
			for (String key : keySet) {
				String value = credentials.get(key);
				request.addHeader(key, value);
			}
			
			HttpResponse response = httpClient.execute(request);
			
			HttpEntity entity = response.getEntity();
			String strData = EntityUtils.toString(entity);
			int code = response.getStatusLine().getStatusCode();
			if (code >= 200 && code < 300) {
				HttpApiResponse apiResponse = new Gson().fromJson(strData, HttpApiResponse.class);
				return apiResponse;
			} else {
				HttpApiException exception = new Gson().fromJson(strData, HttpApiException.class);
				exception.setHttpErrorCode(code);
				throw exception;
			}
		} catch (ClientProtocolException e) {
			throw new HttpApiException("client protocol error", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	private String encodeParameters(Map<String, String> parameters) {
		if (parameters == null || parameters.size() == 0) return null;
		
		StringBuilder strBuilder = new StringBuilder();
		Set<String> keySet = parameters.keySet();
		for (String key : keySet) {
			String value = parameters.get(key);
			if (strBuilder.length() != 0) 
				strBuilder.append("&");
			try {
				strBuilder.append(key).append("=").append(URLEncoder.encode(value, charset));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return strBuilder.toString();
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSoTimeout() {
		return soTimeout;
	}

	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

	public Map<String, String> getCredentials() {
		return credentials;
	}

	public void setCredentials(Map<String, String> credentials) {
		this.credentials = credentials;
	}
}
