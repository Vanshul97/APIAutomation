package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Util.TestUtil;
import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase {
	TestBase testBase;
	RestClient restClient;
	String url;
	String serviceURL;
	String apiURL;
	CloseableHttpResponse closeableHttpResponse;
//	WebDriver driver;

	@BeforeMethod
	public void setup() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("ServiceURL");
		url = serviceURL + apiURL;

	}

	@Test(priority=1)
	public void getAPITestwithoutHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		// Combination of staus code, string and header
		// a. Status Code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is: " + statuscode);
		Assert.assertEquals(statuscode, Response_Status_Code_200);

		// b. Get the JSON Content
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");// entire string it
																									// will give from
																									// JSON
		JSONObject respnseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API: " + respnseJson);
		// per_page:
		String perPageValue = TestUtil.getValueByJPath(respnseJson, "/per_page");
		System.out.println("Value of per page is: " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		// total:
		String totalValue = TestUtil.getValueByJPath(respnseJson, "/total");
		System.out.println("Value of total is: " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		// Validate JsonArray
		String lastName = TestUtil.getValueByJPath(respnseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(respnseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(respnseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(respnseJson, "/data[0]/first_name");
		System.out.println("LastName is:" + lastName);
		System.out.println("FirstName is: " + firstName);
		System.out.println("ID is:" + id);
		System.out.println("Avatar is: " + avatar);

		// c.To get Header
		Header headerArr[] = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		for (Header header : headerArr) {
			headerMap.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Arrays-->" + headerMap);
	}

	@Test(priority=2)
	public void getAPITestwitHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		HashMap<String, String> Map = new HashMap<String, String>();
		Map.put("User-Agent", "PostmanRuntime/7.28.2");
		// headerMap.put("username", "test");
		closeableHttpResponse = restClient.get(url, Map);
		// Combination of staus code, string and header
		// a. Status Code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code is: " + statuscode);
		Assert.assertEquals(statuscode, Response_Status_Code_200);

		// b. Get the JSON Content
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");// entire string it
																									// will give from
																									// JSON
		JSONObject respnseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API: " + respnseJson);
		// per_page:
		String perPageValue = TestUtil.getValueByJPath(respnseJson, "/per_page");
		System.out.println("Value of per page is: " + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		// total:
		String totalValue = TestUtil.getValueByJPath(respnseJson, "/total");
		System.out.println("Value of total is: " + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		// Validate JsonArray
		String lastName = TestUtil.getValueByJPath(respnseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(respnseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(respnseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(respnseJson, "/data[0]/first_name");
		System.out.println("LastName is:" + lastName);
		System.out.println("FirstName is: " + firstName);
		System.out.println("ID is:" + id);
		System.out.println("Avatar is: " + avatar);

		// c.To get Header
		Header headerArr[] = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		for (Header header : headerArr) {
			headerMap.put(header.getName(), header.getValue());

		}
		System.out.println("Headers Arrays-->" + headerMap);
	}

//	@AfterMethod
//	public void teardown() {
//		driver.quit();
//	}

}
