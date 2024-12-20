package TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> setThread = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		setThread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		setThread.get().log(Status.PASS, "Test Passes");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		setThread.get().log(Status.FAIL, "Test Failed");
		setThread.get().fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {

			e.printStackTrace();
		}

		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {

			e.printStackTrace();
		}
		setThread.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}
}
