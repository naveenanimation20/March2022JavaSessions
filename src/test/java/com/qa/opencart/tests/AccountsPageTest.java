package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@Epic("Epic - 200: this epic is for Accounts page of open cart application")
@Story("ACCPAGE - 201: design Accounts page with various features")
public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		 accPage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		//accPage = new AccountsPage(driver);
	}

	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccountsPageTitle();
		System.out.println("Acc page title: " + actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accPageURLTest() {
		String actURL = accPage.getAccountsPageURL();
		System.out.println("Acc page URL: " + actURL);
		Assert.assertTrue(actURL.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}

	@Test
	public void accPageHeaderTest() {
		Assert.assertEquals(accPage.getAccPageHeader(), Constants.ACCOUNTS_PAGE_HEADER);
	}

	@Test
	public void accPageSectionsTest() {
		List<String> actAccSecList = accPage.getAccountsPageSectionsList();
		System.out.println("Act Accounts Section list: " + actAccSecList);
		Assert.assertEquals(actAccSecList, Constants.EXPECTED_ACCOUNTS_SECTION_LIST);
	}

	@Test
	public void logoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void SearchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}

	@Test
	public void logoutTest() {
		Assert.assertEquals(accPage.clickOnLogout().getLogoutSuccessMessg(), Constants.LOGOUT_SUCCESS_MESSG);
	}

	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] { { "Macbook" }, { "iMac" }, { "Apple" }, { "Samsung" } };
	}

	@Test(dataProvider = "getSearchKey")
	public void searchTest(String searchKey) {
		searchResPage = accPage.doSearch(searchKey);
		Assert.assertTrue(searchResPage.getSearchResultsCount() > 0);
	}

//	@DataProvider
//	public Object[][] getProductName() {
//		return new Object[][] { { "Macbook", "MacBook Pro" }, 
//			{ "Macbook", "MacBook Air" }, 
//			{ "Macbook", "MacBook" },
//				{ "iMac", "iMac" }, 
//				{ "Apple", "Apple Cinema 30\"" }, 
//				{ "Samsung", "Samsung SyncMaster 941BW" } };
//	}
	
	
	@DataProvider
	public Object[][] getProductName(){
		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
	}

	@Test(dataProvider = "getProductName", enabled = false)
	public void selectProductTest(String searchKey, String productName) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String productHeader = productInfoPage.getProductHeaderName();
		System.out.println("product header: " + productHeader);
		Assert.assertEquals(productHeader, productName);
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { 
			{ "Macbook", "MacBook Pro", 4 }, 
			{ "Samsung", "Samsung SyncMaster 941BW", 1 } };
	}

	@Test(dataProvider = "getProductData", enabled = false)
	public void productImageTest(String searchKey, String productName, int productImageCount) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), productImageCount);
	}

	
	@Test
	public void productInfoTest() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productInfoPage.getProductInformation();
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		softAssert.assertAll();
	}
	
	@Test(enabled = false)
	public void productInfoDescriptionTest() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("Latest Intel mobile architecture"));
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("new Core 2 Duo MacBook Pro is over 50%"));
		softAssert.assertTrue(productInfoPage.getProductInfoPageInnerText().contains("Connect. Create. Communicate."));
		softAssert.assertAll();
	}
	
	
	@Test(enabled = false)
	public void addToCartTest() {
		searchResPage = accPage.doSearch("Macbook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		String actSuccessMessg = productInfoPage
				.enterQty("1")
							.clickOnAddToCart()
									.getCartSuccessMessg();
		System.out.println("cart messg: " + actSuccessMessg);
		softAssert.assertTrue(actSuccessMessg.contains("MacBook Pro"));
		String actCartItemText = productInfoPage.getCartItemText();
		softAssert.assertTrue(actCartItemText.contains("1" + " item(s)"));
	}
	
	}
