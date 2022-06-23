package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class BaseTest {

	public DriverFactory df;
	public Properties prop;

	public WebDriver driver;

	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;

	public SoftAssert softAssert;

	@Parameters({ "browser", "browserversion" })
	@BeforeTest
	public void setup(String browser, String browserVersion) {
		df = new DriverFactory();
		prop = df.init_prop();

		if (browser != null) {
			prop.setProperty("browser", browser);
			prop.setProperty("browserversion", browserVersion);
		}

		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
