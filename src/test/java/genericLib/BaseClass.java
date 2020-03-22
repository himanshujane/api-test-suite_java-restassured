package genericLib;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.LogStatus;

import dataProvider.Dp;
import genericLib.ExtentTestManager;
import io.restassured.RestAssured;

public class BaseClass {

	//Declaring Global Variables
	String BaseURL;

	@BeforeSuite
	public void setup() throws Exception {
		ExcelRW excel = new ExcelRW(Dp.dataSheetPath);
		
		//Reading BaseURL from Test Data Sheet
		BaseURL = excel.readCellValue("Environment", 0, 1);
		RestAssured.baseURI = BaseURL;
	}

	@BeforeMethod
	public void beforeMethod(Method method) throws Exception {
		//Starting the Test in ExtentReport
		ExtentTestManager.startTest(method.getName(), "Environment URL - " + BaseURL);
	}

	@AfterMethod
	public void afterMethod(ITestResult iTestResult) {
		
		//Checking the status of Test and saving it
		if (iTestResult.getStatus() == ITestResult.FAILURE) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Error is: " + iTestResult.getThrowable());
			System.out.println("TEST FAILED - Error is: " + iTestResult.getThrowable());
		} else if (iTestResult.getStatus() == ITestResult.SKIP) {
			ExtentTestManager.getTest().log(LogStatus.SKIP, "TEST SKIPPED " + iTestResult.getThrowable());
		} else {
			ExtentTestManager.getTest().log(LogStatus.PASS, "Status - TEST PASSED");
			System.out.println("TEST PASSED \n");
		}
		
		//To end the testcase in Extent Report
		ExtentTestManager.getReporter().endTest(ExtentTestManager.getTest());
		ExtentTestManager.getReporter().flush();
	}

	//Prints Log Information to report
	public static void logInfo(String info) {
		ExtentTestManager.getTest().log(LogStatus.INFO, info);
		System.out.println(info);
	}
	
	//Prints Test status as Pass to report
	public static void logPass(String info) {
		ExtentTestManager.getTest().log(LogStatus.PASS, info);
		System.out.println(info);
	}

	/**
	 * @author Himanshu
	 * @Note : This method is used to fetch data from config.properties. This is not
	 *       used in this framework currently.
	 * @param key
	 * @return Value of Key
	 * @throws Exception
	 */
	public static String getProp(String key) throws Exception {

		FileInputStream fis = new FileInputStream("./src/test/resources/config.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}
}
