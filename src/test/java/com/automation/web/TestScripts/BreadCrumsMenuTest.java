package com.automation.web.TestScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.automation.core.web.DriverInit;
import com.automation.core.web.GenerticMethods;
import com.automation.web.ObjectRepository.BreadCrumbsMenu;

public class BreadCrumsMenuTest {

	public WebDriver driver ;
	@Test
	public void f() {
		DriverInit objDriverInit = new DriverInit();
		driver = objDriverInit.getWebDriver("Chrome");
		GenerticMethods objGenerticMethods = new GenerticMethods(driver);
		BreadCrumbsMenu objBreadCrumbsMenu = new BreadCrumbsMenu(driver);
		objGenerticMethods.openURL();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement appleStoremnu = objBreadCrumbsMenu.getMenuByName("Apple Store");
		appleStoremnu.click();
		driver.quit();
	}
	@AfterMethod
	public void closeBrowser()
	{
		//driver.quit();
	}

}
