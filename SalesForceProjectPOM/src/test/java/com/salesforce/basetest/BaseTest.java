package com.salesforce.basetest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.salesforce.utilities.ForceCommonUtilities;
import com.salesforce.utilities.ForceConstants;
import com.salesforce.utilities.GenerateReports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected static WebDriver driver;
	protected static WebDriverWait wait;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest Logger;
	public static GenerateReports report;
	
	
	@BeforeTest
	public static void initialSetUp() {
		System.out.println("BeforeTest execution started");
		report = GenerateReports.getInstance();
		report.startExtentReport();
					}
	@BeforeMethod
	public static void setup(Method method) {
		report.startSingleTestReport(method.getName());
		System.out.println("BeforeMethod execution started");	
		String url = ForceCommonUtilities.getApplicationProperty("url");
		getDriver();
		windowMaximize();
		gotoUrl(url);
	}
	
	@AfterMethod
	public static void tearDown() {
		System.out.println("AfterMethod execution started");
		closeAllDriver();
	}
	
    @AfterTest
	public static void finalTearDown() {
		System.out.println("AfterTest execution started");
		report.endReport();
	}
	
	public static void getDriver() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		System.out.println("Chrome driver instance created");
		report.logTestInfo("Driver instance created");
		
	}

	public static void windowMaximize() {
		driver.manage().window().maximize();
	}

	public static void gotoUrl(String url) {
		driver.get(url);	
		System.out.println("url entered is:" + url);
		report.logTestInfo("url entered is:" + url);
	}
	
	

	public static void closeDriver() {
		driver.close(); // to close the current window browser in focus
	}

	public static void closeAllDriver() {
		driver.quit(); // to close all the opened window browser (whole browsers)
	}
	
	public static void takesScreenShot(String filename) throws IOException {
		TakesScreenshot ob = (TakesScreenshot)driver; //type-casting driver to screenshot 
		Date date = new Date();
		String file = date.toString().replace(":", "-").replace(" ", "_")+filename;
		File sourcepath = ob.getScreenshotAs(OutputType.FILE);
		report.logTestInfo("Screen shot captured");
		String path = ForceConstants.SCREENSHOT_PATH+file;
		File Destinationpath = new File(path);
		FileUtils.copyFile(sourcepath, Destinationpath);
	}
	
}
