package com.qa.hubspot.pages;

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
 * Page class for Login page
 * Contains Login Page Factory and Actions
 *
 */
public class LoginPage extends TestBase {
	
	//Page Factory - OR:
	
	@FindBy(id = "username")
	WebElement username;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(xpath = "//button[@id='loginBtn']")
	WebElement loginBtn;
	
	@FindBy(linkText = "Sign up")
	WebElement signUpLink;
	
	@FindBy(xpath = "//button[contains(getText(),'Password')]")
	WebElement showPasswordBtn;
	
	//Initializing the Page Objects using Page Factory
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions:---------------------------------------
	
	//Get title of login page
	public String getLoginPageTitle() {
		WebDriverWait wait = new WebDriverWait(driver, TestUtil.IMPLICIT_WAIT);
		wait.until(ExpectedConditions.titleIs(TestUtil.LOGIN_PAGE_TITLE));
		return driver.getTitle();
	}
	
	//Check if sign up link is displayed
	public boolean isSignUpLinkDisplayed() {
		return signUpLink.isDisplayed();
	}
	
	//perform login
	public HomePage login(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		loginBtn.click();
		
		return new HomePage(); //lands on HomePage hence it will return HomePage object
	}
	
}
