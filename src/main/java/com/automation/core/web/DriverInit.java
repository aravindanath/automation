/**
 * 
 */
package com.automation.core.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * @author neerajkumar.b.lad
 *
 */
public class DriverInit {

	static String PROJECT_ROOT_FOLDER="..//Automation";
	public WebDriver driver;
	public WebDriver getWebDriver(String Browser) {

		if(Browser.equalsIgnoreCase("IE"))
		{
			try {
				System.setProperty("webdriver.ie.driver", PROJECT_ROOT_FOLDER + "/driver/IEDriverServer.exe");
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				driver = new InternetExplorerDriver(ieCapabilities);
			} catch (Exception e) {
				System.out.println("IE Browser not initited");
				e.printStackTrace();
			}
		}
		else if(Browser.equalsIgnoreCase("firefox")){
			
			try {
				FirefoxProfile Profile = new FirefoxProfile();
				Profile.setPreference( "browser.helperApps.neverAsk.saveToDisk", "application/xml" );
				driver = new FirefoxDriver(Profile);
			} catch (Exception e) {
				System.out.println("Firefox Browser not initited");
				e.printStackTrace();
			}
		}
		else if(Browser.equalsIgnoreCase("chrome")){
			try {
				System.setProperty("webdriver.chrome.driver", PROJECT_ROOT_FOLDER + "/driver/chromedriver.exe");
				driver = new ChromeDriver();
			} catch (Exception e) {
				System.out.println("Chrome Browser not initited");
				e.printStackTrace();
			}
		}
		return driver;
	}
}
