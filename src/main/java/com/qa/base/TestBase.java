package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	public Properties prop;
	public int Response_Status_Code_200 = 200;
	public int Response_Status_Code_201 = 201;
	public int Response_Status_Code_400 = 400;
	public int Response_Status_Code_401 = 401;
	public int Response_Status_Code_500 = 500;
	public int Response_Status_Code_404 = 404;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"C:\\Users\\Vanshul Suneja\\eclipse-workspace\\APIAutomation\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
