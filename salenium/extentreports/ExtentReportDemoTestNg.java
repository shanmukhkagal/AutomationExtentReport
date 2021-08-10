package com.salenium.extentreports;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportDemoTestNg {
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	WebDriver driver;
	
	
	@BeforeSuite
	public void setUp() {
		htmlReporter = new ExtentHtmlReporter("extent1.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
	}
	
	@BeforeTest
	public void setUpTest() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver_win32\\chromedriver.exe");
		 
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		
	}
	
	@Test
	public void test1() throws IOException {
		ExtentTest test = extent.createTest("First test"," First test Description");
		//driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		test.pass("Navigate to google");
		test.log(Status.INFO, "Starting test case");
		test.info("This step shows the usage of info(details)");
		test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath("schreenshot.png").build());
		test.addScreenCaptureFromPath("schreenshot.png");
	}
	
	
	@Test
	public void test2() throws IOException {
		ExtentTest test = extent.createTest("First test"," First test Description");
		test.log(Status.INFO, "Starting test case");
		test.info("This step shows the usage of info(details)");
		test.pass("details", MediaEntityBuilder.createScreenCaptureFromPath("schreenshot.png").build());
		test.addScreenCaptureFromPath("schreenshot.png");
	}
	
	@AfterTest
	public void tearDownTest() {
		driver.close();
		System.out.println("Test Completed successfully");
	}

	
	@AfterSuite
	public void tearDown() {
		extent.flush();
	}
}

