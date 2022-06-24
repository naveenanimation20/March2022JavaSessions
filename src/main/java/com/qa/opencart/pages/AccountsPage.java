package com.qa.opencart.pages;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By header = By.cssSelector("div#logo a");
	private By logoutLink = By.linkText("Logout");
	private By sectionsHeaders = By.cssSelector("div#content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By searchIcon1 = By.cssSelector("div#search button");


	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getAccountsPageTitle() {
		return eleUtil.waitForTitleIs(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountsPageURL() {
		return eleUtil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccPageHeader() {
		return eleUtil.doGetText(header);
	}

	public List<String> getAccountsPageSectionsList() {
		List<WebElement> secList = eleUtil.getElements(sectionsHeaders);
		List<String> secValList = new ArrayList<String>();
		for (WebElement e : secList) {
			String text = e.getText();
			secValList.add(text);
		}
		return secValList;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}

	public LoginPage clickOnLogout() {
		if (isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
		return new LoginPage(driver);
	}

	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, Constants.DEFAULT_ELEMENT_TIME_OUT).isDisplayed();
	}
	
	
	public SearchResultsPage doSearch(String searchKey) {
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}
		return null;
	}
	
	
	
	
	
	
	
	
	

}
