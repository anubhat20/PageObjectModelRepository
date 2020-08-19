package com.qa.hubspot.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.TestBase;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.utils.TestUtil;

public class ContactsPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialize();
		loginPage = new LoginPage();
		contactsPage = new ContactsPage();
		//login should be done before all methods to check Home page testcases
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.goToContactsPage();
	}
	
	@Test
	public void verifyContactsPageTitleTest() {
		String homePageTitle = contactsPage.getContactsPageTitle();
		Assert.assertEquals(homePageTitle, TestUtil.CONTACTS_PAGE_TITLE, "Contacts page title is not matching");
	}
	
	@Test
	public void verifyContactsHeaderTest() {
		Assert.assertEquals(contactsPage.isContactsHeaderDisplayed(), TestUtil.CONTACTS_PAGE_HEADING);
	}
	
	@Test
	public void verifySelectContactsTest() throws InterruptedException {
		String name = "Anu Bhat";
		contactsPage.selectContactByName(name);
		Thread.sleep(5000);
		
	}
	
	@DataProvider
	public Object[][] getHubSpotTestData() {
		Object data [][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	//using data provider, system will create contacts using excel sheet
	@Test(dataProvider="getHubSpotTestData")
	public void verifyCreateNewContact(String email, String firstName, String lastName, String jobTitle) {
		contactsPage.clickOnCreateContactBtn();
		//contactsPage.createNewContact("abc@gmail.com", "Abc", "Xyz", "Manager");
		contactsPage.createNewContact(email, firstName, lastName, jobTitle);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
