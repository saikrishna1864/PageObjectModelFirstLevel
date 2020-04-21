package com.starhub.constants;

import org.testng.annotations.AfterSuite;

import com.starhub.base.Page;

public class TestBase 
{

	
	@AfterSuite
	public void tearDown()
	{
		Page.Quit();
	}
	
}
