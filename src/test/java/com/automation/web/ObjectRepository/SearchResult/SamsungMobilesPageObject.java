package com.automation.web.ObjectRepository.SearchResult;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.core.web.GenerticMethods;

public class SamsungMobilesPageObject {

	public WebElement Element = null;
	GenerticMethods objGenerticMethods ;
	WebDriver driver;
	public SamsungMobilesPageObject(WebDriver driver) {
		super();
		this.driver = driver;
		objGenerticMethods = new GenerticMethods(this.driver);
	}
	
	public WebElement getLatestMobileDetailsByModelName(String ModelName)
	{
		List<WebElement> elements = objGenerticMethods.getFindElements("xpath", ".//*[@data-tracking-id='Latest Samsung mobiles ']/..//*[@class='_2kSfQ4']");
		return objGenerticMethods.getWebElementByName(elements, ModelName);
	}
	
	
}
