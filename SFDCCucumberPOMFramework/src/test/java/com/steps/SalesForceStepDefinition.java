package com.steps;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.firebase.test.utility.CommonUtilities;
import com.firebasse.test.pages.forgetpassword.ForgetpasswordPage;
import com.firebasse.test.pages.home.HomePage;
import com.firebasse.test.pages.login.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceStepDefinition {
	public static WebDriver driver = null;
	LoginPage login;
	HomePage home;
	ForgetpasswordPage fpwd;
	CommonUtilities CU = new CommonUtilities();
	Properties applicationPropertiesFile = CU.loadFile("applicationProperties");
	String url = CU.getApplicationProperty("url", applicationPropertiesFile);
	String usrname = CU.getApplicationProperty("username", applicationPropertiesFile);
	String passwrd = CU.getApplicationProperty("password", applicationPropertiesFile);
	String wrongusrname = CU.getApplicationProperty("wrongusername", applicationPropertiesFile);
	String wrongpasswrd = CU.getApplicationProperty("wrongpassword", applicationPropertiesFile);
	Properties testVerificationPropertiesFile = CU.loadFile("testVerificationProperties");

	@Before
	public void setUp() {
		System.out.println("setUP$$$$");
		WebDriverManager.firefoxdriver().setup();;
		driver = new FirefoxDriver();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
		
	}
	@Given("navigate to url")
	public void navigate_to_url() {
		LoginPage login = new LoginPage(driver);
		System.out.println("URl from file  " + url);
		login.gotoUrl(url);
	}
	@When("user on {string}")
	public void user_on(String page) {
		if (page.equalsIgnoreCase("loginpage"))
			login = new LoginPage(driver);
		else if (page.equalsIgnoreCase("homepage"))
			home = new HomePage(driver);
		else if (page.equalsIgnoreCase("ForgetpasswordPage"))
			fpwd = new ForgetpasswordPage(driver);
	}
	@When("user enter value into textbox {string}")
	public void user_enter_value_into_textbox(String value) {
		login = new LoginPage(driver);
		if (value.equalsIgnoreCase("username"))
			login.enterUserName(usrname);
		else if (value.equalsIgnoreCase("password"))
			login.enterPassword(passwrd);
		else if (value.equalsIgnoreCase("wrongusername"))
			login.enterUserName(wrongusrname);
		else if (value.equalsIgnoreCase("wrongpassword"))
			login.enterPassword(wrongpasswrd);
	}
	@When("user enter value into textbox1  username")
	public void user_enter_value_into_textbox1_username() {
	//	ForgetpasswordPage fpwd =new ForgetpasswordPage(driver);
		fpwd.enterUserName(usrname);
	}

	@When("user click on {string}")
	public void user_click_on(String button) {
		System.out.println("Button: " +button);
		if (button.equalsIgnoreCase("login button"))
			login.clickLoginButton();
		else if (button.equalsIgnoreCase("Remember me checkbox"))
			login.clickonRemeberMe();
		else if (button.equalsIgnoreCase("user menu"))
			home.clickOnUserMenuDropDown();
		else if (button.equalsIgnoreCase("Logout"))
			home.clickOnLogout();
		else if (button.equalsIgnoreCase("Forgot password"))
			login.clickonForgetPassword();
		else if (button.equalsIgnoreCase("continue"))
			fpwd.clickonContinue();
	}

	@Then("verify {string} displayed")
	public void verify_displayed(String msg) {
		System.out.println("msg: " +msg);
		if (msg.equalsIgnoreCase("error message")) {
			String expected = CU.getApplicationProperty("expectedloginerrormsg", testVerificationPropertiesFile);
			String actual = login.GetTextFromLoginPage();
			assertEquals(actual, expected);
			System.out.println("Test Case is Passed");
		} else if (msg.equalsIgnoreCase("Home Page")) {
			String expected = "Home";
			String actual = home.GetTextFromHomeTab();
			assertEquals(actual, expected);
			System.out.println("Test Case is Passed: home page is displaying");
		} else if (msg.equalsIgnoreCase("username")) {
			String expected = CU.getApplicationProperty("ID", testVerificationPropertiesFile);
			System.out.println("expected: " +expected);
			String actual = login.GetTextFromID();
			System.out.println("actual: " +actual);
			assertEquals(actual, expected);
			System.out.println("Test Case is Passed: ID is displayed");
		} else if (msg.equalsIgnoreCase("confirmation message")) {
			String expected = CU.getApplicationProperty("expectedforgotmsg", testVerificationPropertiesFile);
			System.out.println("expected @@@" + expected);
			String actual = fpwd.GetTextFromCheckEmail();
			System.out.println("Actual @@@" + actual);
			assertEquals(actual, expected);
			System.out.println("Test Case is Passed: got email message");
		} else if (msg.equalsIgnoreCase("error message1")) {
			String expected = CU.getApplicationProperty("expectedloginerrormsg1", testVerificationPropertiesFile);
			String actual = login.GetTextFromLoginPage();
			assertEquals(actual, expected);
			System.out.println("Test Case is Passed: ");
		}

	}
	
	@When("user clear textbox password")
	public void user_clear_textbox_password() {
     login.clearPassword(passwrd);
	}
	

}
