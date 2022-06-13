package com.salesforce.pages.sdfclogin;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.base.BasePage;

public class ForgotPasswordPage extends BasePage {
	@FindBy(xpath = "//input[@id='un']")WebElement userName;
	@FindBy(xpath = "//h1[text()='Forgot Your Password']")WebElement forgotYourPassword;
	@FindBy(xpath = "//input[@id='continue']")WebElement continuebtn;

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}
	public String getTextFromForgotYourPassword() {
		String text = readText(forgotYourPassword, "text form field");
		return text;
	}

	public void enterUserName(String username) {
		waitUntilVisible(userName, "user name");
		clearElement(userName, "user name field");
		enterText(userName, username, "user name field");

	}
	
	public void clickContinueButton() {
		clickElement(continuebtn, "continue button");
	}
}
