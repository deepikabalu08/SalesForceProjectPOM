package com.salesforce.pages.sdfclogin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.salesforce.base.BasePage;

public class HomePage extends BasePage {
	WebDriver driver;
	//@FindBy(xpath = "//h1[text()='Student Registration Form']")WebElement studentRegistration;
    @FindBy(linkText = "Close")WebElement lightningPopUp;
	@FindBy(xpath = "//a[contains(text(),'Home')]")WebElement homelink;
	@FindBy(xpath = "//div[@id='userNav-arrow']")WebElement userMenu;
	@FindBy(xpath = "//a[contains(text(),'Logout')]")WebElement logOut;
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public String getTextFromHomePage() {
		String text = readText(homelink, "home page");
		return text;
	}
	public void getUserMenu() {
		clickElement(userMenu, "user Menu dropdown");
	}
	public void clicklogoutbutton() {
		clickElement(logOut, "logout button");
	}
	public void closeLightningPopUp() {	
		waitUntilVisible(lightningPopUp, "lightning popup");
		clickElement(lightningPopUp, "lightning popup");
	}

	

}
