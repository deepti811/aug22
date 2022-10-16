
package com.firebasse.test.pages.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.firebase.test.utility.Constants;

public class BasePage {
	public static WebDriver driver=null;
	public   static WebDriverWait wait=null;
	 
	public BasePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}

	public static String getPageTitle() {
		return driver.getTitle();
		
	}
	public static void refreshPage() {
		driver.navigate().refresh();
		System.out.println("page got refreshed");
	}
	public static void enterText(WebElement element, String text, String objName) {
		if (element.isDisplayed()) {
			clearElement(element, objName);
			element.sendKeys(text);
			System.out.println("text entered in " + objName + "field");
		} else {
			System.out.println("fail: \" + objName + \" element not displayed");
		}
	}
	public static void clickElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.click();
			System.out.println("pass:" + objName + " element clicked");
		} else {
			System.out.println("fail:" + objName + "  element not displayed");

		}
	}

	public static void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			System.out.println("pass:" + objName + "  element cleared");

		} else {
			System.out.println("fail:" + objName + " element not displayed");
		}
	}

	public static WebDriver getDriverInstance() {
		return driver;
	}
	public static void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
	}
	public static void moveToElement(WebElement element, String objectName) {
		waitUntilVisible(element, objectName);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		System.out.println("moved to " + objectName);
	}

	public static void waitUntilVisibilityOf(By locator, String objName) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static String readText(WebElement element, String objectName) {
		waitUntilVisible(element, objectName);
	//	System.out.println("element text: " + element.getText());
	//	System.out.println("element : " + element.getAttribute(objectName));
		String text = element.getText();
		return text;
	}

	public static void waitUntilVisible(WebElement element, String objName) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitUntilAlertIsPresent() {
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void waitUntilElementToBeClickable(By locator, String objName) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static Alert switchToAlert() {
		// TODO Auto-generated method stub
		waitUntilAlertIsPresent();
		return driver.switchTo().alert();
	}

	public static void AcceptAlert(Alert alert) {

		System.out.println("Alert accepted");
		alert.accept();

	}

	public static String getAlertText(Alert alert) {

		return alert.getText();

	}

	public static void dismisAlert() {
		waitUntilAlertIsPresent();
		Alert alert = switchToAlert();
		alert.dismiss();
		System.out.println("Alert dismissed");

	}

	public static void selectByTextData(WebElement element, String text, String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByVisibleText(text);
		System.out.println(objName + " seelcted " + text);

	}

	public static void selectByIndexData(WebElement element, int index, String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByIndex(index);
	}

	public static void selectByValueData(WebElement element, String text) {
		Select selectCity = new Select(element);
		selectCity.selectByValue(text);
	}

	public static void switchToWindowOpned(String mainWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!mainWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
		System.out.println("switched to new window");
	}
	public static WebElement selectFromList(List<WebElement> list,String text) {
		WebElement country=null;
		for (WebElement i : list) {
			if (i.getText().equalsIgnoreCase(text)) {
				System.out.println("selected=" +i.getText());
				country=i;
				break;
			}
			
		}
		return country;
		
	}

	  public static String captureWebElementScreenshot(WebElement
	  elementLogo,String filename) {
	  
	  File src = elementLogo.getScreenshotAs(OutputType.FILE);
	  File dest = new File(Constants.SCREENSHOT_PATH+"/"+filename+".jpg");
	  
	  try
	  { FileHandler.copy(src, dest); 
	  } catch (IOException exception) {
		 System.out.println(exception); 
	  } 
	  return dest.getAbsolutePath(); }
	  
	  public static String captureWebElementScreenshot(WebElement elementLogo) {
	  SimpleDateFormat df=new SimpleDateFormat("dd:MM:yyyy HH:mm:ss"); Date
	  date=new Date(); String curDataAndTime=df.format(date); File src =
	  elementLogo.getScreenshotAs(OutputType.FILE);
	 System.out.println("web element screenshot captured"); File dest = new
	  File(Constants.SCREENSHOT_PATH+"/"+curDataAndTime+".jpg");
	  
	  try { FileHandler.copy(src, dest); } catch (IOException exception) {
	  exception.printStackTrace(); } return dest.getAbsolutePath(); }
	  
	 
}