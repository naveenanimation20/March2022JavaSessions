package com.qa.opencart.pages;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By prouductMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By prouductPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By cartSuccessMessg = By.cssSelector("div.alert.alert-success");
	private By cart = By.cssSelector("div#cart button.dropdown-toggle");

	Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getProductHeaderName() {
		return eleUtil.waitForElementVisible(productHeader, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}

	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(productImages, Constants.DEFAULT_ELEMENT_TIME_OUT).size();
	}

	public Map<String, String> getProductInformation() {

		productInfoMap = new HashMap<String, String>();
		productInfoMap.put("name", getProductHeaderName());
		getProuctMetaData();
		getProductPriceData();
		productInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
		return productInfoMap;
	}

	private void getProuctMetaData() {
		// meta data:
		List<WebElement> metaDataList = eleUtil.getElements(prouductMetaData);
		System.out.println("total product meta data: " + metaDataList.size());

		for (WebElement e : metaDataList) {
			String meta[] = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
	}

	private void getProductPriceData() {
		// price:
		List<WebElement> priceList = eleUtil.getElements(prouductPriceData);
		String price = priceList.get(0).getText().trim();// $2000.00
		String ExTaxPrice = priceList.get(1).getText().trim();

		productInfoMap.put("price", price);
		productInfoMap.put("extaxprice", ExTaxPrice);
	}

	public String getProductInfoPageInnerText() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String pageInnerText = js.executeScript("return document.documentElement.innerText").toString();
		System.out.println("==============================\n" + pageInnerText + "\n=============");
		return pageInnerText;
	}

	public ProductInfoPage enterQty(String qty) {
		eleUtil.doSendKeys(quantity, qty);
		return this;
	}

	public ProductInfoPage clickOnAddToCart() {
		eleUtil.doClick(addToCartBtn);
		return this;
	}
	
	public String getCartSuccessMessg() {
		return eleUtil.waitForElementVisible(cartSuccessMessg, Constants.DEFAULT_ELEMENT_TIME_OUT).getText();
	}
	
	public String getCartItemText() {
		return eleUtil.doGetText(cart);
	}
	

}
