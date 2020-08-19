package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.TestBase;
import com.qa.hubspot.pages.CompaniesPage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.TestUtil;

public class HomePageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	CompaniesPage companiesPage;
	
	//to make sure properties are loaded
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
		//login should be done before all methods to check Home page testcases
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.getHomePageTitle();
		Assert.assertEquals(homePageTitle, TestUtil.HOME_PAGE_TITLE, "Home page title is not matching");
	}
	
	@Test
	public void verifyHomePageHeaderTest() {
		String homePageHeaderText = homePage.getHomePageHeaderText();
		Assert.assertEquals(homePageHeaderText, TestUtil.HOME_PAGE_HEADING, "Home page header does not match");
	}
	
	@Test
	public void verifyUserNameTest() {
		String userName = homePage.getAccountUserNameText();
		Assert.assertEquals(userName, prop.getProperty("accountName"), "Account user name does not match");
	}
	
	@Test
	public void verifyUserEmailTest() {
		String userEmail = homePage.getAccountUserEmailText();
		Assert.assertEquals(userEmail, prop.getProperty("username"), "Account email does not match");
	}
	
	@Test
	public void verifyContactsPageTest() {
		contactsPage = homePage.goToContactsPage();
		Assert.assertEquals(contactsPage.getClass(), ContactsPage.class);
	}
	
	@Test
	public void verifyCompaniesPageTest() {
		companiesPage = homePage.goToCompaniesPage();
		Assert.assertEquals(companiesPage.getClass(), CompaniesPage.class);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	

}
