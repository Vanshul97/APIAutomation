package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient { // Client calls the APIs

	// 1. Get Method without headers
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); // Will Create Client Connnection
		HttpGet httpget = new HttpGet(url); // http get request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); // hit the Get URL
		return closeableHttpResponse;

	}

	// 1. Get Method with headers
	public CloseableHttpResponse get(String url, HashMap<String, String> headermap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http get request
		for (Map.Entry<String, String> entry : headermap.entrySet()) {
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);
		return closeableHttpResponse;
	}

	// 1. Post Method with headers
	public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headermap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url); // http post request
		// For Entity
		httppost.setEntity(new StringEntity(entityString));
		// For headers
		for (Map.Entry<String, String> entry : headermap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httppost);
		return closeableHttpResponse;
	}

}
