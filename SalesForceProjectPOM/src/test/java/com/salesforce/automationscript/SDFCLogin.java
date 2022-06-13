package com.salesforce.automationscript;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.salesforce.basetest.BaseTest;
import com.salesforce.pages.sdfclogin.ForgotPasswordPage;
import com.salesforce.pages.sdfclogin.HomePage;
import com.salesforce.pages.sdfclogin.SDFCLoginPage;
import com.salesforce.utilities.ForceCommonUtilities;

public class SDFCLogin extends BaseTest {

	@Test
	public static void LoginErrorMessage() {
		SDFCLoginPage login = new SDFCLoginPage(driver);
		String userName = ForceCommonUtilities.getApplicationProperty("username");
		String password = ForceCommonUtilities.getApplicationProperty("password");
		login.enterUsername(userName);
		report.logTestInfo("Username entered");
		login.clearPasswordfield(password);
		report.logTestInfo("Password entered");
		login.clickloginbutton();
		report.logTestInfo("Login button clicked");
		String actual = login.getTextFromErrorMessage();
		String expected = "Please enter your password.";
		Assert.assertEquals(actual, expected);
		
		 report.logTestpassed();

	}

	@Test
	public static void LoginToSalesForce() throws IOException {
		SDFCLoginPage login = new SDFCLoginPage(driver);
		String userName = ForceCommonUtilities.getApplicationProperty("username");
		String password = ForceCommonUtilities.getApplicationProperty("password");
		login.login(userName, password);
		report.logTestInfo("Username and password entered");
		report.logTestInfo("Login button clicked");
		HomePage home = new HomePage(driver);
		String actual = home.getTextFromHomePage();
		String expected = "Welcome to your free trial";
		Assert.assertEquals(actual, expected);
		takesScreenShot("LoginToSalesForce.PNG");
	    report.attachScreeshot("/SeleniumAutomationAssignment/screenshots/Sat_May_21_13-56-55_PDT_2022SDFCloginToSalesForce.PNG");
		report.logTestFailed();

	}

	@Test
	public static void CheckRememberMe() throws InterruptedException {
		SDFCLoginPage login = new SDFCLoginPage(driver);
		String userName = ForceCommonUtilities.getApplicationProperty("username");
		String password = ForceCommonUtilities.getApplicationProperty("password");
		login.enterUsername(userName);
		report.logTestInfo("Username entered");
		login.enterPassword(password);
		report.logTestInfo("Password entered");
		login.clickrememberMeCheckBox();
		login.clickloginbutton();
		report.logTestInfo("Login button clicked");
		HomePage home = new HomePage(driver);
		home.getUserMenu();
		report.logTestInfo("User Menu dropdown clicked");
		home.clicklogoutbutton();
		report.logTestInfo("Logout button clicked");
		String actual1 = login.checkUserNameField();
		String expected1 = "deepikabalu@tekarch.com";
		Assert.assertEquals(actual1, expected1);
		report.logTestInfo("Username displayed");
		report.logTestpassed();

	}

	@Test
	public static void ForgetPasswordA() {
		SDFCLoginPage login = new SDFCLoginPage(driver);
		String userName = ForceCommonUtilities.getApplicationProperty("username");
		login.clickForgotPassword();
		report.logTestInfo("forgot password link clicked");
		ForgotPasswordPage forgotpswd = new ForgotPasswordPage(driver);
		String actual = forgotpswd.getTextFromForgotYourPassword();
		String expected = "Forgot Your Password";
		Assert.assertEquals(actual, expected);
		forgotpswd.enterUserName(userName);
		report.logTestInfo("Username provided in forget password page");
		forgotpswd.clickContinueButton();
		report.logTestInfo("Continue button clicked");
		report.logTestpassed();

	}

	@Test
	public static void ForgetPasswordB() {
		SDFCLoginPage login = new SDFCLoginPage(driver);
		login.enterWrongUserName();
		login.enterWrongPassword();
		login.clickloginbutton();
		String actual = login.getTextFromWrongUsernameAndPassword();
		String expected = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		Assert.assertEquals(actual, expected);
		report.logTestpassed();

	}

}
