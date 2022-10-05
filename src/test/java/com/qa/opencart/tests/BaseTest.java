package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	 Properties prop;
	
	LoginPage loginPage;
	AccountsPage accountsPage;
	RegisterPage regPage;
	 SoftAssert softAssert;

	@BeforeTest
	public void setUp() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_Driver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
