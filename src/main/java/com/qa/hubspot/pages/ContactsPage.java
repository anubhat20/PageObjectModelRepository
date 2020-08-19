package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.TestBase;
import com.qa.hubspot.utils.TestUtil;



/**
 * 
 * @author Anuradha
 * Page class for Contacts page
 * Contains Contacts Page Factory and Actions
 *
 */
public class ContactsPage extends TestBase {
	
	@FindBy(xpath = "//h1//i18n-string[contains(text(),'Contacts')]")
	WebElement contactsPageHeader;
	
	@FindBy(xpath = "//button//span//span[text()='Create contact']")
	WebElement createContactBtn;
	
	@FindBy(xpath = "//h3[text()='Create contact']")
	WebElement createContactHeader;
	
	@FindBy(xpath = "//input[@data-field='email']")
	WebElement newContactEmail;
	
	@FindBy(xpath = "//input[@data-field='firstname']")
	WebElement newContactFirstName;
	
	@FindBy(xpath = "//input[@data-field='lastname']")
	WebElement newContactLastName;
	
	@FindBy(xpath = "//input[@data-field='jobtitle']")
	WebElement newContactJobTitle;
	
	@FindBy(xpath = "//button//span[(starts-with(@class,'private-loading-button')) and (text()='Create contact')]")
	WebElement createNewContactBtn;
	
	//constructor for initializing driver
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	//get title of home page
	public String getContactsPageTitle() {
		WebDriverWait wait = new WebDriverWait(driver, TestUtil.IMPLICIT_WAIT);
		wait.until(ExpectedConditions.titleIs(TestUtil.CONTACTS_PAGE_TITLE));
		return driver.getTitle();
	}
	
	//get Contacts heading
	public String isContactsHeaderDisplayed() {
		if(contactsPageHeader.isDisplayed()) {
			return contactsPageHeader.getText();
		}
		return "";
	}
	
	//select checkbox for contact based on name
	public void selectContactByName(String name) {
		driver.findElement(By.xpath("//span[contains(text(),'"+ name +"')]//ancestor::td//"
				+ "preceding-sibling::td//descendant::span[@class=\"private-checkbox__indicator\"]")).click();
	}
	
	//click on create contact button
	public void clickOnCreateContactBtn() {
		TestUtil.waitTillElementIsVisible(createContactBtn);
		createContactBtn.click();		
	}
	
	//create a new contact
	public void createNewContact(String email, String fName, String lName, String jTitle) {
		TestUtil.waitTillElementIsVisible(createContactHeader);		
		newContactEmail.sendKeys(email);
		newContactFirstName.sendKeys(fName);
		newContactLastName.sendKeys(lName);
		//wait for First name to be enabled
		TestUtil.waitTillElementIsClickable(newContactJobTitle);
		newContactJobTitle.sendKeys(jTitle);
		
		//click on create contact button to add new contact
		createNewContactBtn.click();
		
	}

}
