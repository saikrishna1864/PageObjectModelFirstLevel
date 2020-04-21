package com.starhub.Pages;

import org.openqa.selenium.By;

import com.starhub.base.Page;

public class HomePage extends Page
{
	
	
	
	public void goToCustomers()
	{
		
		driver.findElement(By.cssSelector("div.zh-user-account>a.zh-customers")).click();
		
	}
	
	public void goToSupport()
	{
		
		driver.findElement(By.cssSelector("div.zh-user-account>a.zh-support")).click();
	}
	
	public void goToContactSales()
	{
		
		driver.findElement(By.cssSelector("div.zh-user-account>a.zh-contact")).click();
	}

	public void goToLogin()
	{
		
		driver.findElement(By.cssSelector("div.zh-user-account>a.zh-login")).click();
	}
	
	public void goToFreeSignUp()
	{
		
		driver.findElement(By.cssSelector("div.zh-user-account>a.zh-signup")).click();
	}
	
	
	
}
