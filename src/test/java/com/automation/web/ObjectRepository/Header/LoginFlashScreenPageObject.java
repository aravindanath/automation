package com.automation.web.ObjectRepository.Header;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.core.web.GenerticMethods;

public class LoginFlashScreenPageObject {

	public WebElement Element = null;
	GenerticMethods objGenerticMethods ;
	WebDriver driver;
	public LoginFlashScreenPageObject(WebDriver driver) {
		super();
		this.driver = driver;
		objGenerticMethods = new GenerticMethods(this.driver);
		
	}
	
	public WebElement getUserName_txt()
	{
		return objGenerticMethods.getfindElement("class", "_2zrpKA");
	}
	
	public WebElement getPassword_txt()
	{
		return objGenerticMethods.getfindElement("xpath", ".//*[@type='password']");
	}
	
	public void clickLoginBtn()
	{
		objGenerticMethods.getfindElement("xpath", ".//*[@class='_1avdGP']/button").click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void Login(String userName, String Password)
	{
		getUserName_txt().sendKeys(userName);
		getPassword_txt().sendKeys(Password);
		clickLoginBtn();
	}
	
}
