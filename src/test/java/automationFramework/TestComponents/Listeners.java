package automationFramework.TestComponents;
 
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import automationFramework.resources.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent=ExtentReportNG.getreportObject();
	@Override
	public void onTestStart(ITestResult result) 
	{
		test=extent.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) 
	{
		test.log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) 
	{
		test.fail(result.getThrowable());
		try {

			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

			} catch (Exception e) {

			e.printStackTrace();

			}
		String filepath= null;
		
		try {
			filepath=getScreenshot(result.getMethod().getMethodName(),driver);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) 
	{
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}
	
	@Override
	public void onTestFailedWithTimeout(ITestResult result) 
	{
		ITestListener.super.onTestFailedWithTimeout(result);
	}
	
	@Override
	public void onStart(ITestContext context) 
	{
		ITestListener.super.onStart(context);
	}
	
	@Override
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}
}
