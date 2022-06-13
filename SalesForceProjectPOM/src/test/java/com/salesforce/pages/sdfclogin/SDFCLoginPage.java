package com.salesforce.pages.sdfclogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.salesforce.base.BasePage;

public class SDFCLoginPage extends BasePage {
	
	WebDriver driver;
	//identifying the web-elements
	@FindBy(id="username")WebElement userName;
	@FindBy(id="password")static WebElement password;
	@FindBy(id="Login")WebElement login;
	@FindBy(xpath = "//div[text()='Please enter your password.']")WebElement errorMessage;
	@FindBy(id="rememberUn")WebElement rememberMe;
	@FindBy(id="forgot_password_link")WebElement forgotPassword;
	@FindBy(xpath = "//div[@id='error']")WebElement wrongUnameAndPswd;
	@FindBy(id="idcard-identity") WebElement rememberUserName;
	//@FindBy(id = "//a/span[text()='deepikabalusamy-e5pl@force.com']")WebElement rememberUserName;
	public SDFCLoginPage(WebDriver driver) {
		//activating the web-elements
		super(driver);
	}
	public void enterUsername(String username) {
		waitUntilVisible(userName, "user name");
		clearElement(userName, "user name field");
		enterText(userName, username, "user name field");
		
	}
	public void clearPasswordfield(String pswd) {
		clearElement(password, "pswd");
	}
	public String getTextFromErrorMessage() {
		waitUntilVisible(errorMessage, "text from field");
		String text = readText(errorMessage, "text form field");
		return text;
	}
	public String getTextFromWrongUsernameAndPassword() {
		waitUntilVisible(wrongUnameAndPswd, "text from field");
		String text = readText(wrongUnameAndPswd, "text from field");
		return text;
	}
	
	public void enterWrongUserName() {
		waitUntilVisible(userName, "user name");
		clearElement(userName, "user name field");
		userName.sendKeys("123");
		
	}
	public void enterWrongPassword() {
		waitUntilVisible(password, "passWord");
		clearElement(password, "passWord");
		password.sendKeys("22131");
		
	}
	
	public String checkUserNameField() {
		//waitUntilVisible(rememberUserName, "user name field");
		String text = readText(rememberUserName, "user name field");
		return text;
	}

	public void enterPassword(String passWord) {
		waitUntilVisible(password, "passWord");
		clearElement(password, "passWord");
		enterText(password, passWord, "password field");
		
	}
	
	public void clickrememberMeCheckBox() {
		clickElement(rememberMe,"remember me check box");
	}
	public void clickForgotPassword() {
		clickElement(forgotPassword, "forgot password link");
	}
	
	public void clickloginbutton() {
		clickElement(login, "login button");
	}
	//reusable method
	public void login(String usrname, String pswd) {
		enterUsername(usrname);
		enterPassword(pswd);
		clickloginbutton();
		
	}	

}
