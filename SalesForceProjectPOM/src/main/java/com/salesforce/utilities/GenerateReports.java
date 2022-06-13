package com.salesforce.utilities;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class GenerateReports {

	ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	private static GenerateReports ob;  
	// singleton class in line 25 and in line 29
	//by making constructor as private
	//whoever want to use generatereport() class functionality, 
	//they can't create generatereport ob = new generatereport(); because the constructor is private
	//they have to call the getInstance() method
	//in getInstance() method, it will check whether the object is null or not,
	//if the object is null new object will be created otherwise the same object will be returned 
	private GenerateReports() {

	}
    //one public method(static) that has a return type object of this singleton class
	public static GenerateReports getInstance() {
		if (ob == null) {
			ob = new GenerateReports();
		}
		return ob;
	}

	static String testcaseName = null;

	public void startExtentReport() {

		htmlReporter = new ExtentHtmlReporter(ForceConstants.GENERATE_REPORT_PATH);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Salesforce");
		extent.setSystemInfo("Environment", "Automation Testing");
		extent.setSystemInfo("User Name", "Deepika");

		htmlReporter.config().setDocumentTitle("Test Execution Report");
		htmlReporter.config().setReportName("firebase regression tests");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
	}

	public void startSingleTestReport(String testName) {
		testcaseName = testName;
		logger = extent.createTest(testName); // object is created
	

	}

	public void logTestInfo(String message) {

		logger.log(Status.INFO, message);
	}

	public void logTestpassed() {
		logger.log(Status.PASS, MarkupHelper.createLabel(testcaseName + "is passed", ExtentColor.GREEN));
	}

	public void logTestFailed() {
		logger.log(Status.FAIL, MarkupHelper.createLabel(testcaseName + "is not passed", ExtentColor.RED));
	}

	public void logTestFailedWithException(Exception e) {
		logger.log(Status.ERROR, e);
	}

	public void endReport() {
		extent.flush();
	}
		public void attachScreeshot(String path) throws IOException {
			logger.addScreenCaptureFromPath(path);
		}

}
