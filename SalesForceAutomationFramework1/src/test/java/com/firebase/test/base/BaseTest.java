package com.firebase.test.base;

import org.testng.annotations.*;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;
import com.firebase.test.utility.CommonUtilities;
import com.firebase.test.utility.GenerateReports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	public static Logger logger =LogManager.getLogger(BaseTest.class);
	public static GenerateReports report=null;
	public static String 	mainWindowHandle=null;
	@BeforeTest
	public static void setupBeforetest() {
		report=GenerateReports.getInstance();
		report.startExtentReport();
	}
	@Parameters({ "browsername" })
	@BeforeMethod
	public static void setUp( String browsername, Method m) {
		//waitUntilPageLoads();
		//implicitdriverTimeout();
		logger.info("$$$$$beofre method execution started$$$$$");
		report.startSingleTestReport(m.getName());
		setDriver(browsername);
		CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile = CU.loadFile("applicationProperties");
		String url = CU.getApplicationProperty("url", applicationPropertiesFile);
		gotoUrl(url);
		waitUntilPageLoads();
	}
	public static void accountLink() {
		
		WebElement Accounts=driver.findElement(By.linkText("Accounts"));
	waitUntilVisible(Accounts,"Accounts");
		clickElement(Accounts,"Accounts");
		//waitUntilPageLoads();
		}
	public static void popup() {
		WebElement popup=driver.findElement(By.id("tryLexDialogX"));
		waitUntilVisible(popup,"popup");
		clickElement(popup,"popup");
		}
	public static void logout()
	{
		//waitUntilPageLoads();
		WebElement logout = driver.findElement(By.xpath("//a[@title='Logout']"));
		//waitUntilVisible(logout, "logout");
		clickElement(logout, "logout");
		//waitUntilPageLoads();
	}
	public static void opportunityLink() {
		WebElement opportunities=driver.findElement(By.linkText("Opportunities"));
		waitUntilVisible(opportunities,"opportunities link");
		clickElement(opportunities,"opportunities home page is displayed");
		}
		public static void leadTab() {
		WebElement lead=driver.findElement(By.linkText("Leads"));
		waitUntilVisible(lead,"lead");
		clickElement(lead,"lead should navigate to lead Home page");
		}
@AfterMethod
	public static void tearDown() {
	implicitdriverTimeout();
	logger.info("after method execution is started");
		report.logTestInfo("after method execution");
		closeBrowser();
		waitUntilPageLoads();
	}
	@AfterTest
	public static void teardownAfterTest()
	{
		report.endReport();
	}
	public static void implicitdriverTimeout()
	{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	public static void homeTab()
	{
	WebElement home = driver.findElement(By.xpath("//a[@title='Home Tab']"));
	clickElement(home,"click on");
	popup();
}
	public static void CurrentDatelink()
	{
	WebElement currentdatelink = driver.findElement(By.xpath("//a[normalize-space()='Sunday October 2, 2022']"));
	clickElement(currentdatelink,"click on");
	}
	public static void subjectandOther()
	{
	WebElement subject = driver.findElement(By.xpath("//img[@title='Subject Combo (New Window)']"));
	clickElement(subject,"click on");
	swithToNewWindow();
	WebElement other = driver.findElement(By.xpath("//li[@class='listItem4']//a[1]"));
	clickElement(other,"click on");
	swithToMainWindow();
	}
	public static void firstAndLastNameLink() {
	WebElement firstandlastname = driver.findElement(By.cssSelector("h1[class='currentStatusUserName'] a"));
	clickElement(firstandlastname,"click on firstandlastname");
	}
	public static void setDriver(String browser) {

		switch (browser) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		default:
			break;
		}
	}
	
	public static void testCase6()
	{
	WebElement edit = driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]" + "/div[1]/h3/div/div/a/img"));
	clickElement(edit, "click on edit");

	driver.switchTo().frame("contactInfoContentId");
	WebElement about = driver.findElement(By.linkText("About"));
	clickElement(about, "click on about");

	WebElement lastname = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
	enterText(lastname, "ABCD", "last name");

	WebElement saveall = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div/input[1]"));
	clickElement(saveall, "save button");
	driver.switchTo().defaultContent();
	}
public static void loginToSalesForce()  {
	driver.manage().window().maximize();
	CommonUtilities CU = new CommonUtilities();
	Properties applicationPropertiesFile = CU.loadFile("applicationProperties");
		String usrname = CU.getApplicationProperty("username", applicationPropertiesFile);
		WebElement username1 = driver.findElement(By.id("username"));
		waitUntilVisible(username1, "username field");
		enterText(username1, usrname, "username field");
		String password = CU.getApplicationProperty("password", applicationPropertiesFile);
		WebElement password1 = driver.findElement(By.id("password"));
		enterText(password1, password, "password field");
		WebElement loginButton = driver.findElement(By.id("Login"));
		clickElement(loginButton, "login button");
		waitUntilPageLoads();
		//Thread.sleep(2000);
	}

	public static String getPageTitle() {
	String result=	driver.getTitle();
	System.out.println("Title is  "+result);
		return driver.getTitle();
		
	}
	public static void usermenudropdown() {
		WebElement usermenu=driver.findElement(By.cssSelector("#userNavLabel"));
		waitUntilVisible(usermenu,"user menu dropdown");
		clickElement(usermenu,"click on usermenu");
		}
	public static void refreshPage() {
		driver.navigate().refresh();
		report.logTestInfo("page got refreshed");
	}
	public static void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}

	public static void enterText(WebElement element, String text, String objName) {
		
		if (element.isDisplayed()) {
			clearElement(element, objName);
			element.sendKeys(text);
		report.logTestInfo("text entered in " + objName + "field");
		} else {
		report.logTestFailed("fail: " + objName + " element not displayed");
		}
		waitUntilVisible(element, objName);
	}

	public static void clickElement(WebElement element, String objName) {
		waitUntilVisible(element, "objName");
		if (element.isDisplayed()) {
			element.click();
			report.logTestInfo("pass:" + objName + " element clicked");
		} else {
			report.logTestFailed("fail:" + objName + "  element not displayed");

		}
		//waitUntilElementToBeClickable(element, "element clicked");
	}

	public static void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			report.logTestInfo("pass:" + objName + "  element cleared");

		} else {
			report.logTestFailed("fail:" + objName + " element not displayed");
		}
	}

	public static WebDriver getDriverInstance() {
		return driver;
	}

	public static void gotoUrl(String url) {
		driver.get(url);
	}

	public static void moveToElement(WebElement element, String objectName) {
		waitUntilVisible(element, objectName);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		report.logTestInfo("moved to " + objectName);

	}

	public static void sleep() throws InterruptedException
	{
		Thread.sleep(2000);
	}
	public static void closeBrowser() {
	
		driver.close();
	}

	public static void closeAllbrowser() {
		driver.quit();
	}

	public static void waitUntilVisibilityOf(By locator, String objName) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	

	public static void waitUntilVisible(WebElement element, String objName) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitUntilAlertIsPresent() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void waitUntilElementToBeClickable(WebElement locator, String objName) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
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
		report.logTestInfo("Alert dismissed");

	}

	public static void selectByVisibleText(WebElement element, String text, String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByVisibleText(text);
		report.logTestInfo(objName + " seelcted " + text);

	}
	public static void validateSelectMenu(Select  select,String objectName)
	{
	List<WebElement>  selectOptions=select.getOptions();
	System.out.println("The following are the" + objectName);
	for(WebElement element:selectOptions)
	{
		System.out.println(element.getText());
	}
	}
	public static void selectByIndexData(WebElement element, int index, String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByIndex(index);
	}

	public static void selectByValueData(WebElement element, String text) {
		Select selectCity = new Select(element);
		selectCity.selectByValue(text);
	}
	public static void contactTab() {
	WebElement contact = driver.findElement(By.xpath("//a[@title='Contacts Tab']"));
	clickElement(contact, "contact tab should clicked");
	popup();
	}
	public static void createNewView()
	{
	WebElement createNewview = driver.findElement(By.linkText("Create New View"));
	clickElement(createNewview, "createNewview");

	WebElement viewname = driver.findElement(By.id("fname"));
	enterText(viewname, "view_name23", "view name");

	WebElement viewuname = driver.findElement(By.id("devname"));
	enterText(viewuname, "view_name23", "view unique name");

	WebElement save = driver.findElement(By.xpath(
			"/html/body/div/div[2]/table/tbody/tr/td[2]" + "/div[2]/form/div[3]/table/tbody/tr/td[2]/input[1]"));
	clickElement(save, "click on save");
	}
	/*
	 * public static void switchToWindowOpned(String mainWindowHandle) 
	 * { 
	 * Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle :allWindowHandles)
		 { 
		 if (!mainWindowHandle.equalsIgnoreCase(handle))
	 * driver.switchTo().window(handle); }
	 * report.logTestInfo("switched to new window"); }
	 */
	public static void swithToNewWindow()
	{
	mainWindowHandle=driver.getWindowHandle();
	Set <String> allWindowHandle=driver.getWindowHandles();
	for(String handle: allWindowHandle)
	{ 
		if(!mainWindowHandle.equalsIgnoreCase(handle))
	{
		driver.switchTo().window(handle);
		System.out.println("Switched to developer console window");
		break;
	}
	}
	}
	public static void swithToMainWindow()
	{
		driver.switchTo().window(mainWindowHandle);
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
	public static void closeAllBrowser()
	{
		driver.quit();
	}
	
	public static void postCondition() {
		usermenudropdown();
		logout();
		}
	public static void swithToIframe(WebElement element, String objectName)
	{
		driver.switchTo().frame(element);
		System.out.println("'driver is in iframe  " + objectName);
	}
	public static String readText(WebElement element, String objectName) {
		waitUntilVisible(element, objectName);
		String text = element.getText();
		return text;
	}
}