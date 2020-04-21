package com.starhub.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.starhub.listners.CustomTestListners;


public class Page {

	public static WebDriver driver;

	public static Properties Config = new Properties();

	public static Properties Locator = new Properties();

	public static FileInputStream fi;

	//public static Logger log = Logger.getLogger("saikrishna");
	
	public static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("saikrishna");

	public static String browser;

	public static WebDriverWait wait ; 
	
	public Page() {

		if (driver == null) {

			try {
				fi = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\PropertyFiles\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Config.load(fi);
				log.debug("Loaded Config property file");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fi = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\PropertyFiles\\Locator.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Locator.load(fi);
				log.debug("Loaded Locator property file");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// CONFIGURING BROWSER FROM JENKINS OR FROM CONFIG FILE IF NOT RECEIVED ANYTHING
			// FROM JENKINS

			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");

			} else {

				browser = Config.getProperty("browser");

			}

			Config.setProperty("browser", browser);

			log.debug("Browser is configured as :" + browser);

			if (Config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\Runnables\\chromedriver.exe");
				

				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 2);
				prefs.put("credentials_enable_service", false);
				prefs.put("profile.password_manager_enabled", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--disable-extensions");
				options.addArguments("--disable-infobars");

				driver = new ChromeDriver(options);

			} else if (Config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\Runnables\\geckodriver.exe");

				driver = new FirefoxDriver();

			} else if (Config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\Runnables\\iedriver.exe");

				driver = new InternetExplorerDriver();

			}
			
			
			driver.manage().deleteAllCookies();
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
			driver.get(Config.getProperty("AUTSite"));
			
			wait =  new WebDriverWait(driver, 10);

		}

		
		
	}

	
	public static void Quit()
	{
		driver.quit();
	}
	
	//common key strokes 
	
	public static void click(String locator)
	{
		
		if (locator.endsWith("_CSS"))
		{
		
			driver.findElement(By.cssSelector(Locator.getProperty(locator))).click();
		} else if (locator.endsWith("_Xpath"))
		{
			driver.findElement(By.xpath(Locator.getProperty(locator))).click();

		}else if (locator.endsWith("ID"))
		{
			driver.findElement(By.id(Locator.getProperty(locator))).click();

		}else if(locator.endsWith("_LinkText"))
		{
			driver.findElement(By.linkText(Locator.getProperty(locator))).click();
		}
		
		log.debug("Clicked on element :" + locator);
		
	}
	
	
	public static Alert alertReceived()
	{
		
		Alert alert =  wait.until(ExpectedConditions.alertIsPresent());
		
		return alert;
	}
	
	
	public static void sendKeys(String locator,String value)
	{

		if (locator.endsWith("_CSS"))
		{
		
			driver.findElement(By.cssSelector(Locator.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPTAH"))
		{
			 driver.findElement(By.xpath(Locator.getProperty(locator))).sendKeys(value);

		}else if (locator.endsWith("_ID"))
		{
			 driver.findElement(By.id(Locator.getProperty(locator))).sendKeys(value);

		}
	
		
		CustomTestListners.testreport.get().log(Status.INFO , "Found the element "+locator+" Value given as input is "+value);
	}

	
	public static void verifyEquals(String exp , String act) throws IOException
	{
		
		
		try {
			
			Assert.assertEquals(exp, act);
			
		} catch (Throwable T)
		{
			
			captureStepError();
			
			log.debug("screenshot for test step has been captured");
			
			CustomTestListners.testreport.get().log(Status.FAIL, "Verification failed with execption :"+T.getMessage());
			
			CustomTestListners.testreport.get().addScreenCaptureFromPath(screenshotname);
			
				//	CustomTestListners.testreport.get().addScreencastFromPath(BaseClass.screenshotname);
			
			
			
			
		}
	}
	
	public static String screenshotname ;

	public static void captureStepError()
	{
		
		
		Date d = new Date();
		
		 screenshotname = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		
		
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		
		try {
			FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotname));
			log.debug("Placed the file in ");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	static WebElement dropdown;

	public static void selectOption(String locator , String value)
	{
		
		
		if (locator.endsWith("_CSS"))
		{
		
			log.debug("CSS is the selector given");
			dropdown = driver.findElement(By.cssSelector(Locator.getProperty(locator)));
		} else if (locator.endsWith("_XPTAH"))
		{
			dropdown = driver.findElement(By.xpath(Locator.getProperty(locator)));

		}else if (locator.endsWith("_ID"))
		{
			dropdown = driver.findElement(By.id(Locator.getProperty(locator)));

		}

		
		Select fromlist = new Select(dropdown);
		
		fromlist.selectByVisibleText(value);
		
	}




	
}
