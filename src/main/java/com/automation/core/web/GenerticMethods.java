/**
 * 
 */
package com.automation.core.web;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * @author neerajkumar.b.lad
 *
 */
public class GenerticMethods {

	static String PROJECT_ROOT_FOLDER = "..//Automation";
	public WebDriver driver;

	public GenerticMethods(WebDriver driver) {
		this.driver = driver;
	}

	public String getValueFromPropertyFile(String Key) {

		FileReader reader;
		try {
			Properties prop = new Properties();
			reader = new FileReader(PROJECT_ROOT_FOLDER + "/Configuration/common.properties");
			prop.load(reader);

			String KeyValue = prop.getProperty(Key);
			System.out.println(Key + " : " + KeyValue);
			return KeyValue;
		} catch (IOException e1) {
			System.out.println(PROJECT_ROOT_FOLDER + "/Configuration/common.properties File Not found");
			e1.printStackTrace();
		}

		return null;
	}

	public void windowMax() throws IOException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public void deletecookies() {
		driver.manage().deleteAllCookies();
	}

	public boolean isElementPresent(By by) {
		try {
			return driver.findElements(by).size() > 0;
		} catch (Exception e) {
			System.out.println("Element not found");
			return false;
		}
	}

	public void openURL() {
		String Url = null;
		try {
			Url = getValueFromPropertyFile("URL");
			deletecookies();
			driver.get(Url);
			System.out.println("URL: " + Url);
			windowMax();
		} catch (IOException e1) {
			System.out.println("URL: " + Url + " Not Opened");
			e1.printStackTrace();
		}
	}

	public void SendMailJava(boolean isFileFound) throws IOException {

		FileReader reader;
		Properties prop;
		// try {
		reader = new FileReader("../POC_Web/config.properties");
		prop = new Properties();
		prop.load(reader);

		// final String username = prop.getProperty("username_FromAddress");
		// final String password = prop.getProperty("passowrd");
		// final String ToEmail = prop.getProperty("EmailTo");

		final String UserName = getValueFromPropertyFile("EmailUserName");
		final String Password = getValueFromPropertyFile("EmailPassword");
		String ToEmail = getValueFromPropertyFile("ToEmails");

		System.out.println("SendMailJava isFileFound: " + isFileFound);
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dt;
		dt = ft.format(date);

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.googlemail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(UserName, Password);
			}
		});

		String[] cc = prop.getProperty("EmailCC").split(",");
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(UserName));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ToEmail));

			InternetAddress[] addressCC = new InternetAddress[cc.length];
			for (int i = 0; i < cc.length; i++) {
				addressCC[i] = new InternetAddress(cc[i]);
			}
			message.setRecipients(RecipientType.CC, addressCC);

			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			System.out.println("======Start " + isFileFound + "========");
			if (isFileFound == true) {
				// failed case
				message.setSubject("POC_Web Automation :: Failed");
				messageBodyPart.setText(
						"Hi Team,\n\nThis is auto generated mail from Automation script. Please do not reply back. \n\nURL: http://162.248.104.37 \nDate of Execution : "
								+ dt.toString() + " \n\nResult : " + "Failed"
								+ " \n\nWe will investigating for this fail and update you all soon.  \n\nThanks & Regards \ncgi QA Team");
			} else {
				// passed case
				message.setSubject("POC_Web Automation :: Passed");
				messageBodyPart.setText(
						"Hi Team,\n\nThis is auto generated mail from Automation script. Please do not reply back. \n\nURL: https://automation..org \nDate of Execution : "
								+ dt.toString() + " \n\nResult : " + "Passed" + " \n\nThanks & Regards \nQA Team");
			}
			Multipart multipart = new MimeMultipart();

			MimeBodyPart messageAttachement;
			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageAttachement = new MimeBodyPart();
			String filename = PROJECT_ROOT_FOLDER + "/test-output/emailable-report.html";
			DataSource source = new FileDataSource(filename);
			messageAttachement.setDataHandler(new DataHandler(source));
			messageAttachement.setFileName(filename);
			multipart.addBodyPart(messageAttachement);

			// Send the complete message parts
			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Email Report Sent");

		} catch (MessagingException e) {
			System.out.println("Some problem in sending mail");
			throw new RuntimeException(e);
		}

	}

	// Date Format
	public String datetime(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	// Find and Return Element
	public WebElement getfindElement(String identifyBy, String locator) {
		WebDriverWait driverWait = new WebDriverWait(driver, 50);
		WebElement element = null;
		if (identifyBy.equalsIgnoreCase("id")) {
			element = driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(locator))));
		} else if (identifyBy.equalsIgnoreCase("xpath")) {
			element = driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator))));
		} else if (identifyBy.equalsIgnoreCase("class")) {
			element = driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(locator))));
		} else if (identifyBy.equalsIgnoreCase("css")) {
			element = driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(locator))));
		} else if (identifyBy.equalsIgnoreCase("name")) {
			element = driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name(locator))));
		} else if (identifyBy.equalsIgnoreCase("linkText")) {
			element = driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(locator))));
		} else if (identifyBy.equalsIgnoreCase("partialLinkText")) {
			element = driverWait
					.until(ExpectedConditions.visibilityOf(driver.findElement(By.partialLinkText(locator))));
		} else if (identifyBy.equalsIgnoreCase("tagName")) {
			element = driverWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName(locator))));
		}
		return element;
	}

	public void HoverOnWebElement(final WebElement webElement) {
		Actions action = new Actions(driver);
		action.moveToElement(webElement).build().perform();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
	}

	public void verifyWebElement(WebElement element, String strExpectedTitle) {

		Assert.assertEquals(strExpectedTitle, element.getText());

	}

	public boolean checkVisibilitybyElement(String identifierString, String locatorType) {
		int size = 0;

		switch (locatorType.toLowerCase()) {
		case "xpath":
			size = driver.findElements(By.xpath(identifierString)).size();
			break;

		case "id":
			size = driver.findElements(By.id(identifierString)).size();
			break;

		case "linktext":
			size = driver.findElements(By.linkText(identifierString)).size();
			break;

		case "css":
			size = driver.findElements(By.cssSelector(identifierString)).size();
			break;

		case "tagname":
			size = driver.findElements(By.tagName(identifierString)).size();
			break;

		case "classname":
			size = driver.findElements(By.className(identifierString)).size();
			break;

		case "name":
			size = driver.findElements(By.name(identifierString)).size();
			break;

		case "partiallinktext":
			size = driver.findElements(By.partialLinkText(identifierString)).size();
			break;

		default:
			System.out.println("Error : Please check your identifierString or locatorType");
			break;
		}

		if (size == 0) {
			System.out.println("Error : " + identifierString + "Element is not found.");
			return false;

		} else {
			System.out.println("Element found and it is " + identifierString);
			return true;

		}

	}

	public List<WebElement> getFindElements(String identifyBy, String locatorValue) {
		WebDriverWait driverWait = new WebDriverWait(driver, 50);
		List<WebElement> elements = null;
		if (identifyBy.equalsIgnoreCase("id")) {
			elements = driverWait
					.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id(locatorValue))));
		} else if (identifyBy.equalsIgnoreCase("xpath")) {
			elements = driverWait
					.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(locatorValue))));
		} else if (identifyBy.equalsIgnoreCase("css")) {
			elements = driverWait.until(
					ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(locatorValue))));
		} else if (identifyBy.equalsIgnoreCase("class")) {
			elements = driverWait
					.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.className(locatorValue))));
		} else if (identifyBy.equalsIgnoreCase("name")) {
			elements = driverWait
					.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.name(locatorValue))));
		} else if (identifyBy.equalsIgnoreCase("linkText")) {
			elements = driverWait
					.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.linkText(locatorValue))));
		} else if (identifyBy.equalsIgnoreCase("partialLinkText")) {
			elements = driverWait.until(
					ExpectedConditions.visibilityOfAllElements(driver.findElements(By.partialLinkText(locatorValue))));
		} else if (identifyBy.equalsIgnoreCase("tagName")) {
			elements = driverWait
					.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.tagName(locatorValue))));
		}
		return elements;
	}

	public WebElement getWebElementByName(List<WebElement> elements, String MenuName) {

		List<WebElement> menuItems = elements;
		for (WebElement menu : menuItems) {
			if (menu.getText().equalsIgnoreCase(MenuName)) {
				return menu;
			}
		}
		return null;
	}

	/**
	 * @author Aravindanath
	 */

	// Need to test this code!

	public void blueVisibility(String element) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("var x = $(\'" + element + "\');");
		stringBuilder.append("x.blur();");
		js.executeScript(stringBuilder.toString());

	}

	/**
	 * @author Aravindanath
	 */

	public void waitingForTheElementToDisappear(By element) {
		WebDriverWait wait = new WebDriverWait(driver, 200);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}

	/**
	 * @author Aravindanath
	 */

	public void Dropdown(WebElement element, String value) {
		Select selectList = new Select(element);
		selectList.selectByVisibleText(value);
		Assert.assertEquals(selectList.getFirstSelectedOption().getText(), selectList.equals(value));
		System.out.println("Dropdown Value-->" + value);
	}

	/**
	 * @author Aravindanath
	 */
	public void scrollup(String xValue) {

		String parameter = "scroll(" + xValue + ",0)";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(parameter); // x value '500' can be altered

	}

	/**
	 * @author Aravindanath
	 */
	public void scrollDown(String yValue) throws InterruptedException {

		String parameter = "scroll(0," + yValue + ")";
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript(parameter); // y value '500' can be altered

	}

	/**
	 * @author Aravindanath
	 */
	public void scrollDown() throws AWTException {

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}

	/**
	 * @author Aravindanath
	 */

	public void pressEscape() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}

	/**
	 * @author Aravindanath
	 */
	public void pressTab() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	/**
	 * @author Aravindanath
	 */
	public void pressEnter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	/**
	 * @author Aravindanath
	 */

	public void scrollUp() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_UP);
		robot.keyRelease(KeyEvent.VK_PAGE_UP);
	}

	/**
	 * @author Aravindanath
	 */

	public void scrollToElementViaJavascript(WebElement element) {
		assert element.isDisplayed();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	/**
	 * @author Aravindanath
	 */
	public void scrollToElementViaCordinate(WebElement element) {
		Coordinates coordinate = ((Locatable) element).getCoordinates();
		coordinate.onPage();
		coordinate.inViewPort();
	}

	/**
	 * @author Aravindanath
	 */
	public List<WebElement> getDropdownItems(WebElement element) {

		Select select = new Select(element);
		return select.getOptions();
	}

	/**
	 * @author Aravindanath
	 */

	public void clickAction(WebElement element) {
		Actions act = new Actions(driver);

		act.click(element).build().perform();
	}

	/**
	 * @author Aravindanath
	 */
	public void mouseHoverAndClick(WebElement element) {
		System.out.println(element.isEnabled());
		Actions act = new Actions(driver);
		act.moveToElement(element).build().perform();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].focus();", element);
		System.out.println(element.isEnabled());
		element.click();
	}

	/**
	 * @author Aravindanath
	 */

	public void switchToNewWindow() {
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		itr.next();
		String w2 = (String) itr.next();
		driver.switchTo().window(w2);
	}

	/**
	 * @author Aravindanath
	 */

	public void switchToOldWindow() {
		Set<String> s = driver.getWindowHandles();
		Iterator<String> itr = s.iterator();
		String w1 = (String) itr.next();
		itr.next();
		driver.switchTo().window(w1);
	}

	/*
	 * Frames
	 */

	/**
	 * @author Aravindanath
	 *         <h1>switchToDefaultContent!</h1> This method will helps us to switch
	 *         to a default content
	 */
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	/**
	 * @author Aravindanath
	 *         <h1>switchToFrame_byName!</h1> This method will helps us to switch to
	 *         a Frame. Here you need to pass name of the frame
	 */
	public void switchToFrame_byName(String frameName) {
		driver.switchTo().frame(frameName);
	}

	/**
	 * <h1>switchToFrame_byIndex!</h1> This method will helps us to switch to a
	 * Frame. Here you need to pass number of the frame
	 */
	public void switchToFrame_byIndex(int frameValue) {
		driver.switchTo().frame(frameValue);
	}

	/**
	 * @author Aravindanath
	 */

	public void turnOffImplicitWaits() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}

	
	
	
	
	
	
	
	
}
