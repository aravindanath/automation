package com.automation.web.ObjectRepository.Header;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.core.web.GenerticMethods;

public class HeaderPageObject {

	
	public WebElement Element = null;
	WebDriver driver;
	GenerticMethods objGenerticMethods;
	/**
	 * @param driver
	 */
	public HeaderPageObject(WebDriver driver) {
		this.driver = driver;
		objGenerticMethods = new GenerticMethods(this.driver);
	}
	
	public WebElement getHeaderMenu(String menuName)
	{
		List<WebElement> elements = objGenerticMethods.getFindElements("class", "Wbt_B2");
		return objGenerticMethods.getWebElementByName(elements, menuName);
	}
	
	
	
	
	
	
	
}
