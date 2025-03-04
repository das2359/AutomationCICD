package ClinisysReasearchAndDevelopment.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ClinisysResearchAndDevelopment.Resources.ExtentReporterNG;


public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports reports = ExtentReporterNG.config();
	
	//Using ThreadLocal class to solve concurrency issues
	ThreadLocal<ExtentTest> tl = new ThreadLocal<ExtentTest>();
	
	@Override
    public void onTestStart(ITestResult result) {
        // Code to execute when a test starts
		test = reports.createTest(result.getMethod().getMethodName());
		tl.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Code to execute when a test passes
    	tl.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Code to execute when a test fails
    	
    	String path = null;
    	//This line is for failure log
    	tl.get().fail(result.getThrowable());
    	try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} 
    	
    	try {
			path = takeScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	tl.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Code to execute when a test is skipped
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Code to execute when a test fails but is within success percentage
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // Code to execute when a test fails due to a timeout
    }

    @Override
    public void onStart(ITestContext context) {
        // Code to execute before any test starts
    }

    @Override
    public void onFinish(ITestContext context) {
        // Adding flush method to generate report
    	reports.flush();
    }
}
