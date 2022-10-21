
package com.firebasse.test.pages.home;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.firebasse.test.pages.base.BasePage;

public class HomePage extends BasePage{
	
	@FindBy(xpath ="	//*[@id=\"userNavLabel\"]") WebElement usermenu;
	@FindBy(xpath ="//a[@title='Logout']") WebElement logout;
	@FindBy(xpath ="/html/body/div[1]/div[1]/div/div/nav/ul/li[1]/a") WebElement hometab;

	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
	}
	public void clickOnUserMenuDropDown() {
		clickElement(usermenu,"click on user menu drop down");
		//waitUntilVisible(usermenu,"click on user menu drop down");
	}
	public void clickOnLogout() {
		clickElement(logout,"click on Logout");
		waitUntilPageLoads();
	}
	public String GetTextFromHomeTab() {
		String text=readText(hometab, "text form field");
	captureWebElementScreenshot(hometab,"error message image");
		return text;
	}
	
	}
	
	

