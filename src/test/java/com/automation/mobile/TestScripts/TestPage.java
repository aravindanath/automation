package com.automation.mobile.TestScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestPage {

	@BeforeMethod
	public void TestPage_1()
	{
		System.out.println("TestPage_1");
		// Initialize browser

System.setProperty("webdriver.chrome.driver", "C:/BackUp/CGI/Projects/L2S/Project_L2S/L2S/driver/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		 
		// Open Google
		driver.get("http://www.google.com");
		 
		// Close browser
		driver.close();
	}
	
	@Test
	public void TestPage_2()
	{
		System.out.println("TestPage_2");
	}
	
	@AfterTest
	public void TestPage_3()
	{
		System.out.println("TestPage_3");
	}
}
