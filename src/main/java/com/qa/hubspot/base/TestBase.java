package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.hubspot.utils.TestUtil;
import com.qa.hubspot.utils.WebEventListener;

/**
 * 
 * @author Anuradha
 * Base class for Page Object Model
 * Contains initialization
 *
 */
public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public static EventFiringWebDriver eventFireDriver;
	public static WebEventListener eventListener;
	
	public TestBase() {
		//Initialize properties file
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("D:\\Selenium_workspace\\PageObjectModelProject\\"
								+ "src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialize() {
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\geckodriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		// For Web Driver Fire events 
		eventFireDriver = new EventFiringWebDriver(driver);
		//Now create object of EventListener to register to EvenFiringWebDriver
		eventListener = new WebEventListener();
		eventFireDriver.register(eventListener);
		driver = eventFireDriver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT	, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
	}

}
