package com.firebasse.test.pages.forgetpassword;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.firebasse.test.pages.base.BasePage;
public class ForgetpasswordPage extends BasePage{
	
@FindBy(xpath="//input[@id='un']") WebElement username; 
@FindBy(xpath="//input[@id='continue']") WebElement continue1; 
@FindBy(xpath="//h1[@id='header']")WebElement errorMessage;
@FindBy(xpath="//*[@id=\"header\"]")WebElement errorMessage1;
public ForgetpasswordPage(WebDriver driver) {
	super(driver);
	// TODO Auto-generated constructor stub
}
public String GetTextFromCheckEmail() {
waitUntilVisible(errorMessage1,"click on");
String text=readText(errorMessage1, "text form field");
captureWebElementScreenshot(errorMessage1,"error message image");

return text;
}

public  void clickonContinue()
{	
	waitUntilVisible(continue1,"click on");
	clickElement(continue1,"click on continue")	;
}
public void enterUserName(String usrname) {
	//waitUntilVisible(username,"user name field");
	enterText(username, usrname, "username field");
}	
public void continue1(String usrname) {
	enterUserName(usrname);
	clickonContinue();
}
}



