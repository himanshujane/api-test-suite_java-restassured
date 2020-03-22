package genericLib;

import java.util.HashMap;
import java.util.Map;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentTestManager {

	static ExtentReports extent;
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<Integer, ExtentTest>();
	final static String filePath = "./TestReport.html";

	//ExtentManager - Creates the TestReport.html
	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			extent = new ExtentReports(filePath, true);
		}
		return extent;
	}

	//To Start the Testcase in Extent Report
	public static synchronized ExtentTest startTest(String testName, String desc) {
		extent = getReporter();
		ExtentTest test = extent.startTest(testName, desc);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
		return test;
	}

	//To End the Testcase in Extent Report
	public static synchronized void endTest() {
		extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
	}
	
	//To get the Testcase from Extent
	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}
}
