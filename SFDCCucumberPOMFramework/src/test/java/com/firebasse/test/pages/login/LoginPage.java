
package com.firebasse.test.pages.login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.firebasse.test.pages.base.BasePage;

public class LoginPage extends BasePage{
	
	@FindBy(id="username") WebElement username;
	@FindBy(id="password") WebElement password;
	@FindBy(id="Login") WebElement loginButton;
	@FindBy(xpath="//div[@id='error']")WebElement errorMessage;
	@FindBy(xpath="//input[@id='rememberUn']") WebElement remember;
	@FindBy(id="forgot_password_link") WebElement forgetpassword;
	@FindBy(xpath="//*[@id=\"idcard-identity\"]") WebElement ID;
	//*[@id="idcard-identity"]
	public  void clickonForgetPassword()
	{
		clickElement(forgetpassword,"click on remember me");
		
	}
	public void userNameIsDisplayed()
	{
		if (username.isDisplayed()) {
			
			System.out.println("pass:" +  "  element cleared");

		} else {
			System.out.println("fail:" + "  element cleared");
		}
	}
	public String GetTextFromLoginPage() {
		String text=readText(errorMessage, "text form field");
		captureWebElementScreenshot(errorMessage,"error message image");
		return text;
	}
	public String GetTextFromID() {
		//waitUntilVisible(username,"user name field");
		String text=readText(ID, "username");
		
		//captureWebElementScreenshot(username,"ID");
		return text;
	}
	public  void clickonRemeberMe(){
		clickElement(remember,"remember me");	
	}
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	public void enterUserName(String usrname) {
		waitUntilVisible(username,"user name field");
		enterText(username, usrname, "username field");
	}
	public void enterPassword(String passWord) {
		enterText(password, passWord, "password field");	
	}
	public void clickLoginButton() {
		System.out.println("Click loging element*********");
		clickElement(loginButton,"login button");
		waitUntilPageLoads(); 
	}
	public void login(String usrname,String passWrd) {
		enterUserName(usrname);
		enterPassword(passWrd);
		clickLoginButton();
	}
	public void loginWithRememberme(String usrname,String passWrd) {
		enterUserName(usrname);
		enterPassword(passWrd);
		clickonRemeberMe();
		clickLoginButton();
	}
	public void clearPassword(String passWrd) {
		waitUntilVisible(password,"passwordfield");
		clearElement(password,"passWrd");
	}
	public  void clearPwd() {
		clearElement(password,"password");
	}
	public static void gotoUrl(String url)	
	{
		driver.get(url);
	}
	}
	

