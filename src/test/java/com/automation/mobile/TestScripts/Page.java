package com.automation.mobile.TestScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class Page {

	@BeforeMethod
	public void LoginPage()
	{
		System.out.println("LoginPage");
		// Initialize browser

System.setProperty("webdriver.chrome.driver", "C:/BackUp/CGI/Projects/L2S/Project_L2S/L2S/driver/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		 
		// Open Google
		driver.get("http://www.google.com");
		 
		// Close browser
		driver.close();
	}
	
	@Test
	public void PerformPage()
	{
		System.out.println("PerformPage");
	}
	
	@AfterTest
	public void LogoutPage()
	{
		System.out.println("LogoutPage");
	}
}
