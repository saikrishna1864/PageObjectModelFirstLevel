package com.starhub.listners;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.starhub.base.Page;
import com.starhub.utilities.ExtentManager;





public class CustomTestListners extends Page implements ITestListener
{

	static Date d = new Date();
	
	static String filename = "ExtentReport_"+d.toString().replace(" ", "_").replace(":", "_")+".html";

	private static ExtentReports extent  = ExtentManager.getInstance(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+filename);
	
	public static ExtentTest test ;
	
	public static ThreadLocal<ExtentTest> testreport = new ThreadLocal<ExtentTest>();
	
	public static String methodname;
	
	public void onFinish(ITestContext context) 
	
	{
		
		if (extent!=null)
		{
			
			extent.flush();
			
		}
	
	}

	public void onStart(ITestContext arg0) {
		
		
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult result) 
	{
		
		//captureScreenShot(result.getMethod().getMethodName());
		
		captureStepError();
		
		log.debug("Screenshot for the test case has been captured");
		
		//<font size="3" color="red">This is some text!</font>
		
		String execption = Arrays.toString(result.getThrowable().getStackTrace());
		
		testreport.get().fail("<details>"+
		                      "<summary>"+
				              "<b>"+
		                      "<font color=red>Exception has occured : Click here to view</font>"+
		                      "</b>"+
				              "</summary>"+
		                      execption.replaceAll(",", "<br>")+
				              "</details>"+
				              "/n");

		methodname = result.getMethod().getMethodName();
		String logtext = "<b>"+"TestCase : "+methodname+"</b>";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
		
		testreport.get().fail(m);
		
		try {
			CustomTestListners.testreport.get().addScreenCaptureFromPath(screenshotname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) 
	{
		
        methodname = result.getMethod().getMethodName();
		
		String logtext = "<b>"+"TestCase : "+methodname+"</b>";
		
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.LIME);
		
		testreport.get().skip(m);
		
	}

	public void onTestStart(ITestResult result)
	{
		
		String testname = result.getTestClass().getName()+"@TestCase :"+result.getMethod().getMethodName();
		test = extent.createTest(testname);
		testreport.set(test);
		
	}

	public void onTestSuccess(ITestResult result) 
	{
		methodname = result.getMethod().getMethodName();
		
		String logtext = "<b>"+"TestCase : "+methodname+"</b>";
		
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
		
		testreport.get().pass(m);
		
		
		
	}

}
