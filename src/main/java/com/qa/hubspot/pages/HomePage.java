package com.qa.hubspot.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.TestBase;
import com.qa.hubspot.utils.TestUtil;


/**
 * 
 * @author Anuradha
 * Page class for Home page
 * Contains Home Page Factory and Actions
 *
 */
public class HomePage extends TestBase {
	
	//Page Factory : OR
	
	@FindBy(css = "h1.dashboard-selector__title")
	WebElement homePageHeader;
	
	@FindBy(id = "account-menu")
	WebElement accountMenu;
	
	@FindBy(css = "div.user-info-name")
	//@CacheLookup // will get element from cache memory
	WebElement accountUserName;
	
	@FindBy(css = "div.user-info-email")
	WebElement accountUserEmail;
	
	@FindBy(id = "nav-primary-contacts-branch")
	WebElement contactsMenu;
	
	@FindBy(id = "nav-secondary-contacts")
	WebElement contactsLink;
	
	@FindBy(id = "nav-secondary-companies")
	WebElement companiesLink;
	
	//initializing Page Objects using Page Factory
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions: --------------------------------
	
	//get title of home page
	public String getHomePageTitle() {
		WebDriverWait wait = new WebDriverWait(driver, TestUtil.IMPLICIT_WAIT);
		wait.until(ExpectedConditions.titleIs(TestUtil.HOME_PAGE_TITLE));
		return driver.getTitle();
	}
	
	//get header text if displayed else get blank
	public String getHomePageHeaderText() {
		if(homePageHeader.isDisplayed()) {
			return homePageHeader.getText();
		}
		return "";
	}
	
	//get account user name
	public String getAccountUserNameText() {
		TestUtil.waitTillElementIsVisible(accountMenu);
		accountMenu.click();
		TestUtil.waitTillElementIsVisible(accountUserName);
		return accountUserName.getText();
	}
	
	//get account user email
	public String getAccountUserEmailText() {
		TestUtil.waitTillElementIsVisible(accountMenu);
		accountMenu.click();
		TestUtil.waitTillElementIsVisible(accountUserEmail);
		return accountUserEmail.getText();
	}
	
	//go to contacts page
	public ContactsPage goToContactsPage() {
		TestUtil.waitTillElementIsVisible(contactsMenu);
		contactsMenu.click();
		TestUtil.waitTillElementIsVisible(contactsLink);
		contactsLink.click();
		return new ContactsPage(); //because landing page is Contacts page
	}
	
	public CompaniesPage goToCompaniesPage() {
		TestUtil.waitTillElementIsVisible(contactsMenu);
		contactsMenu.click();
		TestUtil.waitTillElementIsVisible(companiesLink);
		companiesLink.click();
		return new CompaniesPage();
	}

}
