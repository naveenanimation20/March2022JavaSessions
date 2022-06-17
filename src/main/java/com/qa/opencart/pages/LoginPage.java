package com.qa.opencart.pages;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private By Locators: OR
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By logoutSuccessMesg = By.cssSelector("div#common-success h1");

	// 2. page const....
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// 3. page actions:
	@Step("getting login page title of open cart app...")
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	@Step("getting login page url of open cart app...")
	public String getLoginPageUrl() {
		return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}

	@Step("user is able to login with username: {0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("login creds are: " + un + " : " + pwd);
		eleUtil.waitForElementVisible(emailId, Constants.DEFAULT_ELEMENT_TIME_OUT).sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	@Step("isForgotPwdLinkExist...")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}

	@Step("isRegisterLinkExist...")
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}

	@Step("fetching success messg for logout...")
	public String getLogoutSuccessMessg() {
		return eleUtil.waitForElementVisible(logoutSuccessMesg, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}
	
	@Step("navigating to register page after clicking on register link....")
	public RegisterPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
}