package com.automation.web.TestScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.core.web.DriverInit;
import com.automation.core.web.GenerticMethods;
import com.automation.web.ObjectRepository.Header.HeaderPageObject;
import com.automation.web.ObjectRepository.Header.LoginFlashScreenPageObject;
import com.automation.web.ObjectRepository.SearchResult.SamsungMobilesPageObject;

public class VerifyMenuElements {

	// Only methods need to call
	// all class reference create here
	GenerticMethods objGenerticMethods;
	DriverInit objDriverInit;
	HeaderPageObject objHeaderPageObject;
	LoginFlashScreenPageObject objLoginFlashScreenPageObject ;
	SamsungMobilesPageObject objSamsungMobilesPageObject ;
	public WebDriver driver;

	@Test(priority=1)
	public void LoginStep()
	{
		
		
		objLoginFlashScreenPageObject.Login("9538268171", "neeraj123");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority=2)
	public void SearchItem() {
		// test cases steps
		// assert always
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement menMenu = objHeaderPageObject.getHeaderMenu("ELECTRONICS");
		objGenerticMethods.HoverOnWebElement(menMenu);		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		objSamsungMobilesPageObject.getLatestMobileDetailsByModelName("Samsung Galaxy J2-2017 (Metallic gold, 8 GB)").click();
		
		
//		System.out.println("Successful Hover on " + menMenu.getText());
	}

	@BeforeTest
	public void beforeMethod() {

		// Initial browser Objects and methods objects
		// Open URL
		// create Excel Reference

		objDriverInit = new DriverInit();
		driver = objDriverInit.getWebDriver("Chrome");
		objHeaderPageObject = new HeaderPageObject(driver);
		objGenerticMethods = new GenerticMethods(driver);
		objLoginFlashScreenPageObject = new LoginFlashScreenPageObject(driver);
		objSamsungMobilesPageObject = new SamsungMobilesPageObject(driver);
		objGenerticMethods.openURL();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@AfterTest
	public void afterMethod() {
		// quite browser object
		// close excel reference
		// check condition before closing or quite
		driver.quit();
	}

}
