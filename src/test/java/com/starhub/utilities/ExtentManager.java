package com.starhub.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager 
{


	
	
	private static ExtentReports extent ;
	
	public static ExtentReports getInstance(String filename)
	{
		
		ExtentHtmlReporter htmlreporter = new ExtentHtmlReporter(filename);
		
		htmlreporter.config().setEncoding("utf-8");
		
		htmlreporter.config().setDocumentTitle(filename);

		htmlreporter.config().setReportName(filename);
		
		htmlreporter.config().setTheme(Theme.STANDARD);
	
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlreporter);
		
		extent.setSystemInfo("Organizatio Name", "GSK");
		
		extent.setSystemInfo("Projecct", "Personal Practice");
		
		extent.setSystemInfo("Build Info", "GSK-1234");
		
		return extent;
		
		
	}



}
