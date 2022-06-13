package com.salesforce.base;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.salesforce.utilities.ForceCommonUtilities;

public class BasePage {
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		BasePage.driver = driver;
		PageFactory.initElements(driver,this);
	}
	public static void getUsername() {
		String userName = ForceCommonUtilities.getApplicationProperty("username");
		WebElement username = driver.findElement(By.id("username"));
		waitUntilVisible(username, "user name");
		enterText(username, userName, "user name");
		// report.logTestInfo("Username entered");
	}
	
	public static void getPassword() {
		String password = ForceCommonUtilities.getApplicationProperty("password");
		WebElement pswd = driver.findElement(By.id("password"));
		clearElement(pswd, password);
		enterText(pswd, password, "password");
		// report.logTestInfo("Password entered");
	}

	public static void getLoginbutton() {
		WebElement login = driver.findElement(By.id("Login"));
		clickElement(login, "login button");
		// report.logTestInfo("Login button clicked");
	}

	
	public static void Logout() {
		WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
		clickElement(logout, "logout");
	}

	public static String readText(WebElement element, String objName) {
		waitUntilVisible(element, objName);
		String text = element.getText();
		return text;
	}

	public static void enterText(WebElement element, String text, String objName) {
		if (element.isDisplayed()) {
			clearElement(element, objName);
			element.sendKeys(text);
			System.out.println("pass: " + objName + " text entered");
			// report.logTestInfo("Text entered in "+ objName + " field");
		} else {
			System.out.println("fail: " + objName + " element not displayed");
			// report.logTestInfo("Text not entered in "+ objName + " field");
		}
	}

	public static void clickElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.click();
			System.out.println("pass: " + objName + " element clicked");
			// report.logTestInfo("Element "+ objName + " is clicked");
		} else {
			System.out.println("fail: " + objName + " element not displayed");
			// report.logTestInfo("Element "+ objName + " is not displayed");
		}
	}

	public static void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			System.out.println("pass: " + objName + " element cleared");
			// report.logTestInfo("Element "+ objName + " is cleared");
		} else {
			System.out.println("fail: " + objName + " element not displayed");
			// report.logTestInfo("Element "+ objName + " is not cleared");
		}

	}

	public static void waitUntilVisible(WebElement element, String objName) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitUntilVisiblityOfElementLocated(By locator, String objName) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitUntilAlertIsPresent() {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void waitUntilElementToBeClickable(By locator, String objname) {
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void mouseOver(WebElement element, String objName) {
		waitUntilVisible(element, objName);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	private static Alert switchToAlert() {
		// switchToAlert() is used within baseMethod class. Its not used by any other
		// class. so its made private
		return driver.switchTo().alert();
	}

	public static void AcceptAlert() {
		waitUntilAlertIsPresent();
		Alert alert = switchToAlert();
		System.out.println("alert text=" + alert.getText());
		alert.accept();
	}

	public static void dismissAlert() {
		waitUntilAlertIsPresent();
		Alert alert = switchToAlert();
		alert.dismiss();
	}

	public static void selectByTextData(WebElement element, String text, String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByVisibleText(text);
		System.out.println(objName + " selected " + text);
	}

	public static void selectByIndexData(WebElement element, int index, String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByIndex(index);
		// System.out.println(objName+" selected "+index);
	}

	public static void selectByValueData(WebElement element, String text) {
		Select selectCity = new Select(element);
		selectCity.selectByValue(text);
		// System.out.println(objName+" selected "+text);
	}

	public static void switchToOneWindow(String mainWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!mainWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
	}

}
