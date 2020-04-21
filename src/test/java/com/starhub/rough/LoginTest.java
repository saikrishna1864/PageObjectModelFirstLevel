package com.starhub.rough;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.starhub.Pages.HomePage;
import com.starhub.Pages.ZohoAppPage;
import com.starhub.Pages.ZohoLoginPage;
import com.starhub.base.Page;
import com.starhub.listners.CustomTestListners;

@Listeners(CustomTestListners.class)
public class LoginTest extends Page{
	
	
	@Test	
	public void verifyLoginTest() throws IOException
	{
		
		//Page p = new Page();
		
		/*System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\Runnables\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.zoho.com/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		
		*/
		
		
		 HomePage hp = new HomePage();
		 
		 hp.goToLogin();
		
		 log.debug("Clicked on login in homepage");
		 
		 ZohoLoginPage lp = new ZohoLoginPage();
		 
		 lp.loginToZohoAccount();
		 
		 log.debug("Logged into the zoho account");
		 
		 //verifyEquals("abc", "kljk");
		 
		 ZohoAppPage ap = new ZohoAppPage();
		 
		 ap.goToCrm();
		 
		 log.debug("Clicked on CRM page");
		 
}		 
		 
		 
		
		
	}


