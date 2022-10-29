package com.firebase.test.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import com.firebase.test.base.BaseTest;
import com.firebase.test.utility.CommonUtilities;
//import com.firebasse.test.pages.forgetpassword.CheckEmailPage;


public class AutomationScripts extends BaseTest {	
	
	@Test
	public static void TestID_1LoginErrorMessage() throws Exception {
		CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile = CU.loadFile("applicationProperties");
		String usrname = CU.getApplicationProperty("username", applicationPropertiesFile);
		WebElement username1 = driver.findElement(By.id("username"));
		waitUntilVisible(username1, "username field");
		enterText(username1, usrname, "username field");
		WebElement password1 = driver.findElement(By.id("password"));
		clearElement(password1,  "password field");
		WebElement loginButton = driver.findElement(By.id("Login"));
		clickElement(loginButton, "login button");
		waitUntilPageLoads();
		String expected="Please enter your password.";
		WebElement error=driver.findElement(By.xpath("//div[@id='error']"));
		String actual=readText(error,"error message is displayed");
		Assert.assertEquals(actual, expected);
		logger.error("TestScript exection has error");
		report.logTestInfo("test script execution completed");
		}
	@Test
	public static void TestID_2LoginToSalesForce() throws Exception {
	
		String expected = "Home Page ~ Salesforce - Developer Edition";
		loginToSalesForce();
		String actual = getPageTitle();
		AssertJUnit.assertEquals(actual, expected);
	}
	@Test
	public static void TestID_3CheckRemeberMe()  throws Exception{ //not working
		implicitdriverTimeout();
		CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile = CU.loadFile("applicationProperties");
		String usrname = CU.getApplicationProperty("username", applicationPropertiesFile);
		WebElement username = driver.findElement(By.id("username"));
		waitUntilVisible(username, "username field");
		enterText(username, usrname, "username field");
		String passwrd = CU.getApplicationProperty("password", applicationPropertiesFile);
		WebElement password = driver.findElement(By.id("password"));
		waitUntilVisible(password,"password fiels");
		enterText(password, passwrd, "password field");
		WebElement remember = driver.findElement(By.id("rememberUn"));
		clickElement(remember, " remember me checkbox");		
		WebElement loginButton = driver.findElement(By.id("Login"));
		clickElement(loginButton, "login button");
		waitUntilPageLoads();
		WebElement usermenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		clickElement(usermenu, "user-menu drown");
		WebElement logout = driver.findElement(By.xpath("//a[@title='Logout']"));
		clickElement(logout, "back to login page and useranme should " + "be displayed in username field");
	if(username.isDisplayed()){
		report.logTestpassed("user name should be displayed");
	}
	else {
		report.logTestFailed("user name should not be displayed");
	}	
	}
	@Test
	public static void TestID_4AForgotPassword() throws Exception {
		WebElement forget = driver.findElement(By.id("forgot_password_link"));
		clickElement(forget, "click on forget button");
		System.out.println("test forget page is displayed");
		CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile = CU.loadFile("applicationProperties");
		String usrname = CU.getApplicationProperty("username", applicationPropertiesFile);
		WebElement username = driver.findElement(By.id("un"));
		waitUntilVisible(username, "username field");
		enterText(username, usrname, "username field");
		WebElement continue1 = driver.findElement(By.id("continue"));
		clickElement(continue1, " continue ");
		String expected="Check Your Email";
		WebElement check=driver.findElement(By.xpath("//h1[@id='header']"));
		String actual=readText(check,"read text");
		logger.info(actual);
		Assert.assertEquals(actual, expected);
		report.logTestInfo("Password reset message page is displayed. "
				+ "An email associated with the <username> account is sent.");
	}
	@Test
	public static void TestID_4BForgotPassword() throws Exception {
		CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile = CU.loadFile("applicationProperties");
		String wrongusrname = CU.getApplicationProperty("wrongusername", applicationPropertiesFile);
		WebElement wrongusername = driver.findElement(By.id("username"));
		waitUntilVisible(wrongusername, "wrong username field");
		enterText(wrongusername, wrongusrname, "wrong username field");
	
		String wrongpasswrd = CU.getApplicationProperty("wrongpassword", applicationPropertiesFile);
		WebElement wrongpassword = driver.findElement(By.id("password"));
		waitUntilVisible(wrongpassword,"password fiels");
		enterText(wrongpassword, wrongpasswrd, "password field");
		
		WebElement login = driver.findElement(By.id("Login"));
		clickElement(login, "login button ");
		String expected="Please check your username and password."
				+ " If you still can't log in, contact your Salesforce administrator.";
		WebElement validation=driver.findElement(By.xpath("//div[@id='error']"));
		String actual=readText(validation,"read text");
		Assert.assertEquals(actual, expected);
		report.logTestInfo("\"Error message should be displayed\n"
				+ "'Your login attempt has failed. The username or password may be incorrect, or your location or login time may be restricted. "
				+ "Please contact the administrator at your company for help'\"");
		}
	@Test
	public static void TestID_5Usermenudropdown() throws Exception { // implicitpageLoadTimeout();//not working
		loginToSalesForce();
		implicitdriverTimeout();
		usermenudropdown();
		WebElement myprofile=driver.findElement(By.xpath("//a[@title='My Profile']"));
		if (myprofile.isDisplayed())
			report.logTestpassed("test is passed");
	}
	@Test
	public static void TestID_6MyProfileFromUsermenudropdown()throws IOException, InterruptedException { // implicitpageLoadTimeout()
		loginToSalesForce();
		implicitdriverTimeout();
		usermenudropdown();
		WebElement myprofile = driver.findElement(By.xpath("/html/body/div/div[1]/table/" + "tbody/tr/td[3]/div/div[3]/div/div/div[2]/div[3]/a[1]"));
		waitUntilVisible(myprofile, " myprofile");
		clickElement(myprofile, "click on  myprofile");

		WebElement edit = driver.findElement(By.xpath("//*[@id=\"chatterTab\"]/div[2]/div[2]" + "/div[1]/h3/div/div/a/img"));
		waitUntilVisible(edit, "edit");
		clickElement(edit, "click on edit");

		driver.switchTo().frame("contactInfoContentId");
		Thread.sleep(8000);

		WebElement about = driver.findElement(By.linkText("About"));
		waitUntilVisible(about, "about");
		clickElement(about, "click on about");

		WebElement lastname = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
		waitUntilVisible(lastname, "last-name");
		enterText(lastname, "Rajput", "last name");

		WebElement saveall = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div/input[1]"));
		waitUntilVisible(saveall, "save-all-button");
		clickElement(saveall, "save button");

		driver.switchTo().defaultContent();

		WebElement post = driver.findElement(By.xpath("//*[@id=\"publisherAttachTextPost\"]"));
		waitUntilVisible(post, "post");
		clickElement(post, "clicking on post");

		WebElement frame1 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[3]/div[1]"
				+ "/div/div[1]/div/div[2]/ul/li[1]/div/div/div[1]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/iframe"));
		driver.switchTo().frame(frame1);

		Thread.sleep(4000);
		WebElement text = driver.findElement(By.xpath("/html/body"));
		waitUntilVisible(text, "message should display on text box");
		enterText(text, "Hello Tekarch team", "text");

		driver.switchTo().defaultContent();
		WebElement share = driver
				.findElement(By.xpath("//input[@id='publishersharebutton' and @name='publishersharebutton']"));
		waitUntilVisible(share, "share");
		clickElement(share, "share");

		Thread.sleep(4000);
		WebElement file = driver.findElement(By.xpath("//span[normalize-space()='File']"));
		clickElement(file, "click on file");

		Thread.sleep(4000);
		WebElement uploadfile = driver.findElement(By.xpath("//a[@id='chatterUploadFileAction']"));
		clickElement(uploadfile, "click on upload file");
		System.out.println("Enter the dragon");
		Thread.sleep(8000);
		WebElement browse = driver.findElement(By.id("chatterFile"));
		Thread.sleep(8000);
		browse.sendKeys("/Users/jitendrasolanki/Desktop/csvfile.rtf");
		Thread.sleep(8000);

		WebElement share1 = driver
				.findElement(By.xpath("//input[@id='publishersharebutton' and @name='publishersharebutton']"));
		waitUntilVisible(share1, "share");
		clickElement(share1, "share");

		Thread.sleep(3000);
		WebElement myProfilePhoto = driver.findElement(By.cssSelector(".profileImage > img:nth-child(1)"));

		Actions ac = new Actions(driver);
		ac.moveToElement(myProfilePhoto).build().perform();
		WebElement addPhoto = driver.findElement(By.cssSelector("#uploadLink"));
		Thread.sleep(8000);
		clickElement(addPhoto, "click on add photo");
		WebElement frame = driver.findElement(By.id("uploadPhotoContentId"));
		Thread.sleep(8000);
		swithToIframe(frame, "move to that frame");
		Thread.sleep(8000);

		WebElement photoFile = driver.findElement(By.cssSelector("#j_id0\\:uploadFileForm\\:uploadInputFile"));
		Thread.sleep(3000);
		photoFile.sendKeys("/Users/jitendrasolanki/Downloads/blacktipreeflevel1jpg.jpg");
		System.out.println("new Photofile choosen");
		Thread.sleep(8000);
		WebElement savePhoto = driver.findElement(By.cssSelector("#j_id0\\:uploadFileForm\\:uploadBtn"));
		clickElement(savePhoto, "Save the Photo");
		Thread.sleep(7000);
		WebElement cropImage = driver.findElement(By.cssSelector("#j_id0\\:j_id7\\:save"));
		Thread.sleep(5000);
		clickElement(cropImage, " cropImage the Photo");
		System.out.println("test case is passed");
			}
@Test
public static void TestID_7MySettingFromUsermenudropdown() throws IOException
{
		loginToSalesForce();
		implicitdriverTimeout();
		usermenudropdown();
		System.out.println("user menu dropdown is displayed");

		WebElement mysetting = driver.findElement(
		By.xpath("/html/body/div/div[1]/table/tbody/tr/td[3]/div/div[3]/div/div/div[2]/div[3]/a[2]"));
		waitUntilVisible(mysetting, "mysetting");
		clickElement(mysetting,"Mysetting page is displaye");
	
		WebElement personal = driver.findElement(By.id("PersonalInfo_font"));
		waitUntilVisible(personal, "personal");
			clickElement(personal,"click on personal link");

		WebElement loginhistory = driver.findElement(By.id("LoginHistory_font"));
		waitUntilVisible(loginhistory, "loginhistory");
		clickElement(loginhistory,"clicked on login history link");

		WebElement download = driver.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr/td[2]/div[3]/div[1]/div/div[2]/div/a"));
		waitUntilVisible(download, "download");
		clickElement(download," loginhistory page is displayed and data is downloaded in .csv format");

		WebElement displaylayout = driver.findElement(By.id("DisplayAndLayout_font"));
		waitUntilVisible(displaylayout, "displaylayout");
		clickElement(displaylayout,"click on display layout link");

		WebElement customizemytab = driver.findElement(By.id("CustomizeTabs_font"));
		waitUntilVisible(customizemytab, "customizemytab");
		clickElement(customizemytab,"click on customizemytab link");
		
		WebElement customapp = driver.findElement(By.xpath("//*[@id=\"p4\"]"));
		waitUntilVisible(customapp, "customapp");
		Select select = new Select(customapp);
		report.logTestInfo("salesforce chatter option is selected");

		WebElement availableTabs = driver.findElement(By.xpath("//select[@id='duel_select_0']"));
		waitUntilVisible(availableTabs, "availableTabs");
		selectByVisibleText(availableTabs, "Reports", "availableTabs");
		// Thread.sleep(4000);
		WebElement Add = driver.findElement(By.xpath("//a[@id='duel_select_0_right']"));
		waitUntilVisible(Add, "AddButton");
		clickElement(Add,"click on Add button");

		WebElement email = driver.findElement(By.id("EmailSetup_font"));
		waitUntilVisible(email, "email");
		clickElement(email,"email is clicked");
		
		WebElement emails = driver.findElement(By.id("EmailSettings_font"));
		waitUntilVisible(emails, "emailSetting");
		System.out.println("emailSetting is clicked");
		emails.click();

		WebElement emailname = driver.findElement(By.id("sender_name"));
		waitUntilVisible(emailname, "emailname");
		enterText(emailname, "deepti rajput", "email name");

		WebElement emailaddress = driver.findElement(By.id("sender_email"));
		waitUntilVisible(emailaddress, "emailaddress");
		enterText(emailaddress, "deepti.solanki@gmail.com", "email address");

		WebElement bccbutton = driver.findElement(By.id("auto_bcc1"));
		waitUntilVisible(bccbutton, "radio button");
		clickElement(bccbutton, "button clicked");

		WebElement save = driver.findElement(By.xpath("//input[@title='Save']"));
		waitUntilVisible(save, "save button");
		clickElement(save, "save button clicked");

		dismisAlert();
		WebElement calenderremainder = driver.findElement(By.xpath("//span[@id='CalendarAndReminders_font']"));
		waitUntilVisible(calenderremainder, "calenderremainder");
		clickElement(calenderremainder, "calenderremainder clicked");

		WebElement activityremainder = driver.findElement(By.id("Reminders_font"));
		waitUntilVisible(activityremainder, "activityremainder");
		clickElement(activityremainder, "activityremainder clicked");

		WebElement testremainder = driver.findElement(By.xpath("//input[@id='testbtn']"));
		waitUntilVisible(testremainder, "testremainder");
		clickElement(testremainder, "test remainder button clicked");
		report.logTestInfo("sample event pop window is displayed");
		report.logTestpassed("Test case passed");
	
	}
	@Test
public static void TestID_8DeveloperConsoleFromUsermenudropdown()throws IOException { // implicitpageLoadTimeout()
		loginToSalesForce();
		implicitdriverTimeout();
		usermenudropdown();
		WebElement developerconsole = driver.findElement(By.xpath("//a[@title='Developer Console (New Window)']"));
		waitUntilVisible(developerconsole, "developerconsole");
		clickElement(developerconsole, "developerconsole");
		swithToNewWindow();
		String title = driver.getTitle();
		System.out.println("'Current window is " + title);
		driver.close();
		swithToMainWindow();
		System.out.println("test case is passed");
		driver.quit();
	}
	@Test
public static void TestID_9LogoutFromUsermenudropdown() throws IOException //not run
{ 		//implicitdriverTimeout();
		loginToSalesForce();
		usermenudropdown();
		logout();
		report.logTestInfo("test case is passed");
		String expected="Salesforce";
		WebElement validation=driver.findElement(By.xpath("//img[@id='logo']"));
		String actual=readText(validation,"read text");;
		Assert.assertEquals(actual, expected);
		report.logTestInfo("Salesforce login page is displaying");
		driver.close();
	}
	@Test
public static void TestID_10CreateAccount() throws IOException
	{ 
	// implicitpageLoadTimeout()
		loginToSalesForce();
		implicitdriverTimeout();
		accountLink();
		popup();
		WebElement New = driver.findElement(By.xpath("//input[@title='New']"));
			clickElement(New, "New");

		WebElement Accountname = driver.findElement(By.xpath("//input[@id='acc2']"));
			enterText(Accountname, "saving", "Account name");

		WebElement type = driver.findElement(By.xpath("//select[@id='acc6']"));
		Select select = new Select(type);
		select.selectByVisibleText("Technology Partner");

		WebElement customerpriority = driver.findElement(By.xpath("//select[@id='00N5g00000VstgP']"));
		Select select1 = new Select(customerpriority);
		select1.selectByVisibleText("High");
		report.logTestInfo("Test Case is passed");
		driver.close();
	}
	@Test
public static void TestID_11Createnewview() throws IOException {
		waitUntilPageLoads();
		loginToSalesForce();
		//implicitdriverTimeout();
		accountLink();
		popup();
		createNewView();
		report.logTestInfo("test is passed");
	}
	@Test
public static void TestID_12Editview() throws IOException, InterruptedException {
	//waitUntilPageLoads(); 
	loginToSalesForce();
	implicitdriverTimeout();
	//waitUntilPageLoads();
		accountLink();
		Thread.sleep(1000);
	//	waitUntilPageLoads();
		popup();
		//waitUntilPageLoads();
		//Thread.sleep(4000);
		WebElement view = driver.findElement(By.id("fcf"));
		waitUntilVisible(view, "view");
		Select select = new Select(view);
		select.selectByVisibleText("my_view1");
		waitUntilPageLoads();
		WebElement Edit = driver.findElement(By.linkText("Edit"));
		Thread.sleep(1000);
		clickElement(Edit, "view name edit page is displyed");
		WebElement viewname = driver.findElement(By.id("fname"));
		enterText(viewname, "my_view1", "view name");
		WebElement viewuname = driver.findElement(By.id("devname"));
		enterText(viewuname, "my_view1", "view unique name");
		Thread.sleep(4000);
		WebElement filter = driver.findElement(By.xpath("//*[@id=\"fcol1\"]"));
		waitUntilVisible(filter, "filter");
//clickElement(filter,"filter");
		Select select1 = new Select(filter);
		select1.selectByVisibleText("Account Name");
		Thread.sleep(4000);
		WebElement operator = driver.findElement(By.id("fop1"));
		waitUntilVisible(operator, "filter");
		Select select2 = new Select(operator);
		select2.selectByVisibleText("contains");
		WebElement value = driver.findElement(By.id("fval1"));
		enterText(value, "a", "value");
		Thread.sleep(1000);
		WebElement availableFields = driver
				.findElement(By.xpath("//select[@id=\"colselector_select_0\" and @name=\"colselector_select_0\"]"));
		selectByVisibleText(availableFields, "Account Name", "Account Name");

		WebElement add = driver.findElement(By.xpath("//a[@id='colselector_select_0_right']//img[@title='Add']"));
		clickElement(add, "account name should be added in selected fields");

		WebElement save = driver.findElement(By.xpath(
				"/html/body/div/div[2]" + "/table/tbody/tr/td[2]/div[2]/form/div[3]/table/tbody/tr/td[2]/input[1]"));
		clickElement(save, "click on save button");

		String actual = getPageTitle();
		System.out.println(actual + "    test case is passed");
		driver.quit();
	}
	@Test
public static void TestID_13MergeAccounts() throws IOException, InterruptedException {
		loginToSalesForce();
		implicitdriverTimeout();
		accountLink();
		popup();

		WebElement mergeaccount = driver.findElement(By.linkText("Merge Accounts"));
		waitUntilVisible(mergeaccount, "mergeaccount");
		clickElement(mergeaccount, "click on mergeaccount");

		WebElement accountname = driver.findElement(By.id("srch"));
		waitUntilVisible(accountname, "accountname");
		enterText(accountname, "saving", "accountname");

		WebElement findaccount = driver.findElement(By.name("srchbutton"));
		waitUntilVisible(findaccount, "findaccount");
		clickElement(findaccount, "click on findccount");
		Thread.sleep(5000);

		WebElement next = driver.findElement(
				By.xpath("/html/body/div/div[2]/table/tbody/tr/td[2]/form/div/div[2]/div[1]/div/input[1]"));
		waitUntilVisible(next, "next button");
		clickElement(next, "click on next button");

		WebElement merge = driver.findElement(By.xpath("//div[@class='pbBottomButtons']//input[@title='Merge']"));
		waitUntilVisible(merge, "merge button");
		clickElement(merge, "click on merge button");
		report.logTestInfo("Test case is passed");
	}
	@Test
public static void TestID_14CreateAccountReport() throws IOException{
		loginToSalesForce(); //not completed
		implicitdriverTimeout();
		accountLink();
		popup();
		WebElement link = driver.findElement(By.linkText("Accounts with last activity > 30 days"));
		waitUntilVisible(link, "link");
		clickElement(link, "click on link");

		WebElement date = driver.findElement(By.id("//*[@id=\"ext-gen20\"]"));
		waitUntilVisible(date, "date");
		Select select = new Select(date);
		select.selectByVisibleText("Created Date");
	}
	@Test
public static void TestID_15opportunitiesDropdown() throws IOException{
		loginToSalesForce();
		implicitdriverTimeout();
		opportunityLink();
		popup();
		WebElement verify = driver.findElement(By.id("fcf"));
		waitUntilVisible(verify, "verify");
		clickElement(verify, "verify");
		Select optionsInselect = new Select(verify);
		validateSelectMenu(optionsInselect, "All opportunities menu list ");
		report.logTestInfo("test case is passed");

	}
	@Test
public static void TestID_16createanewOpty()throws IOException {
		loginToSalesForce(); 
		implicitdriverTimeout();
		opportunityLink();
		popup();
		WebElement createnew = driver.findElement(By.xpath("//div[@id='createNewButton']"));
		waitUntilVisible(createnew, "createnew");
		clickElement(createnew, "createnew");

		WebElement opportunity = driver.findElement(By.xpath("//a[@class='opportunityMru menuButtonMenuLink']"));
		waitUntilVisible(opportunity, "opportunity");
		clickElement(opportunity, "opportunity");

		WebElement opportunityname = driver.findElement(By.xpath("//input[@id='opp3']"));
		waitUntilVisible(opportunityname, "opportunityname");
		enterText(opportunityname, "opportunity3", "opportunityname");
//input[@id='opp4']
		WebElement accountname = driver.findElement(By.xpath("//input[@id='opp4']"));
		waitUntilVisible(accountname, "accountname");
		enterText(accountname, "saving", "accountname");

//		WebElement closeDate = driver.findElement(By.xpath("//*[@id=\"opp9\"]"));
//		waitUntilVisible(closeDate, "closeDate");
//		enterText(closeDate,"10/1/2022","closeDate");
       
		WebElement stage = driver.findElement(By.xpath("//select[@id='opp11']"));
		waitUntilVisible(stage, "stage");
		Select select = new Select(stage);
		select.selectByVisibleText("Prospecting");

		WebElement probability = driver.findElement(By.xpath("//input[@id='opp12']"));
		waitUntilVisible(probability, "probability");
		enterText(probability, "12", "probability");

		WebElement leadsource = driver.findElement(By.xpath("//select[@id='opp6']"));
		waitUntilVisible(leadsource, "leadsource");
		Select select1 = new Select(leadsource);
		select1.selectByVisibleText("Phone Inquiry");
		WebElement primarycampaign = driver.findElement(By.xpath("//*[@id=\"opp17\"]"));
		waitUntilVisible(primarycampaign, "primarycampaign");
		enterText(primarycampaign, "primarycampaign1", "primarycampaign");
		report.logTestInfo("test case is passed");
	}
	@Test
public static void TestID_17Test_Opportunity_Pipeline_Report()throws IOException {
		loginToSalesForce();
		implicitdriverTimeout();
		opportunityLink();
		popup();
		WebElement opportunitypipeline = driver.findElement(By.linkText("Opportunity Pipeline"));
		waitUntilVisible(opportunitypipeline, "opportunitypipeline");
		clickElement(opportunitypipeline, "Reportpage wit opportunity pipeline will displayed");
		report.logTestInfo("test case is passed");
	}
	@Test
public static void TestID_18Test_Stuck_Opportunity_Report()throws IOException {
		loginToSalesForce();
		implicitdriverTimeout();
		opportunityLink();
		popup();
		WebElement stuckopportunity = driver.findElement(By.linkText("Stuck Opportunities"));
		waitUntilVisible(stuckopportunity, "stuckopportunity");
		clickElement(stuckopportunity, "Reportpage wit stuckopportunity will displayed");
		report.logTestInfo("test case is passed");
		
	}
	@Test
public static void TestID_19Test_quarterly_Summary_Report()throws IOException
	{
		loginToSalesForce();
		implicitdriverTimeout();
		opportunityLink();
		popup();
		report.logTestInfo("Go to quarterly Summary Option");
//WebElement <List>interval=driver.findElement(By.xpath("//select[@id='quarter_q']"));
//waitUntilVisibilityof(interval,"stuckopportunity");

		System.out.println("test case is passed");
		driver.quit();
	}
	@Test
public static void TestID_20leads_Tab() throws IOException, InterruptedException {
		loginToSalesForce();
		implicitdriverTimeout();
		leadTab();
		popup();
		report.logTestInfo("test case is passed");
		postCondition();
	}
	@Test
public static void TestID_21leads_Select_View() throws IOException, InterruptedException {
		loginToSalesForce();
		implicitdriverTimeout();
		leadTab();
		popup();
		WebElement view = driver.findElement(By.id("fcf"));
		waitUntilVisible(view, "view");
		clickElement(view, "drpdown list display contains");
		report.logTestInfo("test case is passed");
		postCondition();
	}
	@Test
	public static void TestID_22default_View() throws IOException, InterruptedException{
		implicitdriverTimeout();
		loginToSalesForce(); // not run
		leadTab();
//		//waitUntilPageLoads();
	    popup();
		WebElement view = driver.findElement(By.id("fcf"));
		waitUntilVisible(view, "view");
		Select select = new Select(view);
		select.selectByVisibleText("Today's Leads");
		report.logTestInfo("today's leads is displaying");
		select.selectByVisibleText("My Unread Leads");
		report.logTestInfo("My unread leads is displaying");
		postCondition();
		
		loginToSalesForce();
		report.logTestInfo("User should be logged in");

        leadTab();
        report.logTestInfo("Lead home page should be displayed");
popup();
//		//input[@title='Go!']
WebElement go = driver.findElement(By.cssSelector("input.btn:nth-child(2)"));
clickElement(go, "click on go");
report.logTestInfo("My unread leads view is displyed");
	report.logTestInfo("test case is passed");
	postCondition();
	}
	@Test
public static void TestID_23Todays_Leads() throws IOException, InterruptedException {
		loginToSalesForce();
		implicitdriverTimeout();
		leadTab();
		report.logTestInfo("Leads home Page should be displayed");
		popup();
		WebElement view = driver.findElement(By.id("fcf"));
		waitUntilVisible(view, "view");
		Select select = new Select(view);
		select.selectByVisibleText("Today's Leads");
		report.logTestInfo("today's leads is displaying");
		report.logTestInfo("test case is passed");
		postCondition();
	}
	@Test
public static void TestID_24new_Button_On_Lead() throws IOException, InterruptedException {
		loginToSalesForce();
		implicitdriverTimeout();
		leadTab();
		report.logTestInfo("Leads home Page should be displayed");
		popup();

		WebElement new1 = driver.findElement(By.xpath("//input[@title='New']"));
		waitUntilVisible(new1, "new");
		clickElement(new1, "new lead creation page should open");
		Thread.sleep(4000);

		WebElement lastname = driver.findElement(By.xpath("//input[@id='name_lastlea2']"));
		waitUntilVisible(lastname, "new");
		enterText(lastname, "ABCD1", "ABCD is entered");

		WebElement companyname = driver.findElement(By.xpath("//input[@id='lea3']"));
		waitUntilVisible(companyname, "company name");
		enterText(companyname, "ABCD1", "ABCD is entered");

		WebElement save = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save']"));
		waitUntilVisible(save, "save");
		clickElement(save, "new lead should be saved");
		System.out.println("Test case is passed");
		postCondition();
	}
	@Test
	public static void TestID_25Create_new_contact() throws IOException
	{
		loginToSalesForce();
		implicitdriverTimeout();
		contactTab();
	
		WebElement new1 = driver.findElement(By.xpath("//input[@title='New']"));
		clickElement(new1, "new button should clicked");
		
		WebElement lastname = driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
		enterText(lastname,"Solankiji ","last name should be display");
		/*
		 * WebElement search = driver.findElement(By.
		 * xpath("//img[@title='Account Name Lookup (New Window)']"));
		 * clickElement(search, "search account name");
		 */
		WebElement accountname = driver.findElement(By.xpath("	//input[@id='con4']"));
		enterText(accountname,"Saving","Account Name should be display");
	
		WebElement save = driver.findElement(By.xpath("	//td[@id='topButtonRow']//input[@title='Save']"));
		clickElement(save, "information should saved");
		report.logTestpassed("new contact sholud be created");
		
	}
	@Test
	public static void TestID_26Create_new_View_contact() throws IOException
	{
		loginToSalesForce();
		implicitdriverTimeout();
		contactTab();	
		createNewView();
	//WebElement validate=driver.findElement(By.xpath("//*[@id=\"00B5g00000aBLc7_listSelect\"]"));
		report.logTestInfo("test is passed");
	}
	@Test
	public static void TestID_27Check_contact_Page() throws IOException
	{
		loginToSalesForce();
		implicitdriverTimeout();
		contactTab();	
		WebElement recentlycreated=driver.findElement(By.xpath("//select[@id='hotlist_mode']"));
		selectByVisibleText(recentlycreated,"Recently Created"," Recently Created");
		report.logTestInfo("test is passed");
	}
	@Test
	public static void TestID_28Check_My_contact_view() throws IOException
	{
		loginToSalesForce();
		implicitdriverTimeout();
		contactTab();	
		WebElement mycontact=driver.findElement(By.xpath("//select[@id='fcf']"));
		selectByVisibleText(mycontact,"My Contacts","My Contacts");
		report.logTestInfo("My contact view should display");
	}
	@Test
	public static void TestID_29View_Contact_contact_Page() throws IOException
	{
		loginToSalesForce();
		implicitdriverTimeout();
		contactTab();	
		WebElement contactname=driver.findElement(By.xpath("//a[contains(text(),'Saving')]"));
		clickElement(contactname,"realted information sholud display");
	report.logTestpassed("test case is passed");
	}	
	@Test
	public static void TestID_30Check_error() throws IOException
	{
		loginToSalesForce();
		implicitdriverTimeout();
		contactTab();	
		WebElement createNewview = driver.findElement(By.linkText("Create New View"));
		clickElement(createNewview, "createNewview");

		WebElement viewuname = driver.findElement(By.id("devname"));
		enterText(viewuname, "EFGH", "view unique name");

		WebElement save = driver.findElement(By.xpath(
				"/html/body/div/div[2]/table/tbody/tr/td[2]" + "/div[2]/form/div[3]/table/tbody/tr/td[2]/input[1]"));
		clickElement(save, "click on save");
		
		String expected="Error: You must enter a value";
		WebElement validate = driver.findElement(By.xpath("//div[@class='requiredInput']//div[@class='errorMsg']"));
		String actual=validate.getText();
		AssertJUnit.assertEquals(actual, expected);		
		report.logTestpassed("Pass")	;				
	}
	@Test
	public static void TestID_31Check_Cancel() throws IOException
	{
		loginToSalesForce();
		implicitdriverTimeout();
		contactTab();	
		WebElement createNewview = driver.findElement(By.linkText("Create New View"));
		clickElement(createNewview, "createNewview");
		
		WebElement viewname = driver.findElement(By.id("fname"));
		enterText(viewname, "ABCD", "view name");
		
		WebElement viewuname = driver.findElement(By.id("devname"));
		enterText(viewuname, "EFGH", "view unique name");
		WebElement cancel = driver.findElement(By.xpath("/html[1]/body[1]"
				+ "/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/div[2]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/input[2]"));
		clickElement(cancel,"click on");
		report.logTestpassed("Passed");
	}
	@Test
	public static void TestID_33Check_SaveandNew_button() throws IOException
	{
		loginToSalesForce();
		implicitdriverTimeout();
		homeTab();
		firstAndLastNameLink();
	}
	@Test
	public static void TestID_34verify_Edited_Lastname() throws IOException
	{
		loginToSalesForce();
		implicitdriverTimeout();
		homeTab();
		firstAndLastNameLink();
		testCase6();
	}
	@Test
	public static void TestID_35verify_Tab_Customization() throws IOException
	{
		loginToSalesForce();
		implicitdriverTimeout();
		WebElement allTab = driver.findElement(By.xpath("//img[@title='All Tabs']"));
		clickElement(allTab,"Click on");
		WebElement custmizeMyTab = driver.findElement(By.xpath("//input[@title='Customize My Tabs']"));
		clickElement(custmizeMyTab,"click on");
		WebElement selectedTab = driver.findElement(By.xpath("//select[@id='duel_select_1']"));
		selectByVisibleText(selectedTab,"Content","remove it");
		WebElement removeTab = driver.findElement(By.xpath("//img[@title='Remove']"));
		clickElement(removeTab,"click on");
		WebElement save = driver.findElement(By.xpath("//input[@title='Save']"));
		clickElement(save,"click on");
		postCondition();
		loginToSalesForce();
		report.logTestpassed("Test case is passed");
	}
	@Test
	public static void TestID_36blocking_An_Event() throws IOException
	{
		loginToSalesForce();//not run
		implicitdriverTimeout();
		homeTab();
		CurrentDatelink();
		WebElement PM8Link = driver.findElement(By.xpath("//a[normalize-space()='8:00 PM']"));
		clickElement(PM8Link,"click on");
		subjectandOther();
		//WebElement start = driver.findElement(By.xpath("//input[@id='StartDateTime_time']"));
		//selectByVisibleText(start,"9:00 PM","click on");
		WebElement end = driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
	
		selectByVisibleText(end,"9:00 PM","click on");
			//	popup();
		WebElement save = driver.findElement(By.cssSelector("#topButtonRow > input:nth-child(1)"));
		clickElement(save,"click on");
	}
	@Test
	public static void TestID_37blocking_An_Event_With_Weekly() throws IOException
	{
		loginToSalesForce();
		implicitdriverTimeout();
		homeTab();
		CurrentDatelink();
		WebElement PM4Link = driver.findElement(By.xpath("//a[normalize-space()='4:00 PM']"));
		clickElement(PM4Link,"click on");
		subjectandOther();
		WebElement end = driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
		enterText(end,"5:00 PM","click on");
		selectByVisibleText(end,"7:00 PM","click on");
		WebElement recurrence = driver.findElement(By.xpath("//input[@id='IsRecurrence']"));
		clickElement(recurrence,"click on");
		WebElement weekly = driver.findElement(By.xpath("//input[@id='rectypeftw']"));
		clickElement(weekly,"click on");
		WebElement save = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save']"));
		clickElement(save,"click on");
		WebElement monthlyview = driver.findElement(By.xpath("//img[@title='Month View']"));
		clickElement(monthlyview,"click on");
	}
}