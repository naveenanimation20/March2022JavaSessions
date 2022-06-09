package com.qa.opencart.pages;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By address1 = By.id("input-address-1");
	private By city = By.id("input-city");
	private By postcode = By.id("input-postcode");
	private By country = By.id("input-country");
	private By regional = By.id("input-zone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1' and @type='radio']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0' and @type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By registerSuccessMesg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public boolean registerUser(String firstName, String lastName,
			String email, String telephone, String password, String subsribe) {
		WebElement firstName_ele = eleUtil.waitForElementVisible(this.firstName, Constants.DEFAULT_ELEMENT_TIME_OUT);
		firstName_ele.clear();
		firstName_ele.sendKeys(firstName);
		
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmpassword, password);


		if (subsribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);

		String successMesg = eleUtil.waitForElementVisible(registerSuccessMesg, Constants.DEFAULT_TIME_OUT).getText();

		if (successMesg.contains(Constants.ACCOUNT_REGISTER_SUCCESS_MESSG)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}
		else {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
		}
		return false;
	}

}
