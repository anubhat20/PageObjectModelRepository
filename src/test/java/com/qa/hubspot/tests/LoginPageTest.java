package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.TestBase;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.TestUtil;

public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	
	//this ensures properties file is loaded and we wont get NullPointerException
	public LoginPageTest() {
		//calling super class ie. TestBase class constructor
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		
		//initialize driver and other pre-requisites
		initialize();
		//create object of Login Page
		loginPage = new LoginPage();
	}
	
	@Test
	public void verifyloginPageTitleTest() {
		String loginPagetitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(loginPagetitle, TestUtil.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void verifySignUpLinkDisplayedTest() {
		boolean displayed = loginPage.isSignUpLinkDisplayed();
		Assert.assertTrue(displayed);
	}
	
	@Test
	public void verifyLoginTest() {
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homePage.getClass(), HomePage.class);
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
