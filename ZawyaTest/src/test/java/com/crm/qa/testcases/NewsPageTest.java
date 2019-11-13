package com.crm.qa.testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.crm.qa.base.TestBase;
import com.crm.qa.pages.NewstabPage;
import com.crm.qa.util.TestUtil;

public class NewsPageTest extends TestBase {
	NewstabPage NewsPage;
	TestUtil testUtil;
	ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reporter/Zawyareuslt.html");
	ExtentReports extent = new ExtentReports();
	ExtentTest logger;
	public NewsPageTest() {
		super();
	}

	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();		
		NewsPage = new NewstabPage();
	
	}
	
	@Test
	public void verifyHomePageTitleTest(){
		extent.attachReporter(reporter);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		String homePageTitle = NewsPage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "ZAWYA - Middle East's trusted source of business & financial news");
		logger = extent.createTest(" Zawya HomePage TitleTest");
	}
	
	
	/*@Test
	public void clickonNewsTabTest(){
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		String homePageTitle = testUtil.menutabs("News");
		
	}*/
	

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {

			String screenShotPath = TestUtil.takeScreenshotAtEndOfTest().getAbsolutePath();
			logger.log(Status.FAIL, MarkupHelper
					.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
			logger.fail(result.getThrowable());
			logger.fail("Snapshot below: " + logger.addScreenCaptureFromPath(screenShotPath));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			logger.skip(result.getThrowable());
		}
		extent.flush();
		driver.quit();
	}

}
