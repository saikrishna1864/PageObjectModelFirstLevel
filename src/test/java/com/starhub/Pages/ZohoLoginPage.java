package com.starhub.Pages;

import com.starhub.base.Page;

public class ZohoLoginPage extends Page
{
	
	
	public void loginToZohoAccount()
	{
		
		sendKeys("username_CSS", Locator.getProperty("username"));
		click("NextButton_CSS");
		sendKeys("password_CSS", Locator.getProperty("password"));
		click("SigninButton_CSS");
		
		
	}

}
