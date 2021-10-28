package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {
	TestBase testBase;
	RestClient restClient;
	String url;
	String serviceURL;
	String apiURL;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("ServiceURL");
		url = serviceURL + apiURL;

	}

	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("User-Agent", "PostmanRuntime/7.28.2");

		// Jackson API: Java to JSON and vice versa
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("Vanshul", "SDET");// Expected Users Object
		// Object to JSON file:
		mapper.writeValue(new File(
				"C:\\Users\\Vanshul Suneja\\eclipse-workspace\\APIAutomation\\src\\main\\java\\com\\qa\\data\\users.json"),
				users);

		// Object to JSON in String
		String usersJSONString = mapper.writeValueAsString(users);
		System.out.println(usersJSONString);
		closeableHttpResponse = restClient.post(url, usersJSONString, headermap);

		// 1. Get Status
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, Response_Status_Code_201);

		// 2. JsonString
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		// Converting into JSON String
		JSONObject responseJSON = new JSONObject(responseString);
		System.out.println("Response from API is: " + responseJSON);

		// JSON to Java object -->UnMarshelling
		Users userResObj = mapper.readValue(responseString, Users.class); // Actual Users Object
		System.out.println(userResObj);

		System.out.println(users.getName().equals(userResObj.getName()));// These two failing
		System.out.println(users.getJob().equals(userResObj.getJob()));
		System.out.println(userResObj.getId());
		System.out.println(userResObj.getCreatedAt());

	}

}
