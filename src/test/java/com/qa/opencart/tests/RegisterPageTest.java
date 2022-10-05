package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void RegisterPageSetup() {
		regPage = loginPage.navigateToRegister();
	}

	@DataProvider
	public Object[][] registerData() {
		String a []= {"niks","ej"};
		Object regData[][] = { { "niks", "p", "niksp@gmail.com","abc@123" }, { "ekta", "p", "pekta@gmail.com","cde@123"} };
		return regData;
	}

	@Test(dataProvider = "registerData")
	public void testRegisteration(String fn, String ln, String email, String pswd) throws InterruptedException {
//		System.out.println(fn);
		Assert.assertFalse(regPage.accountRegisteration(fn, ln, email, pswd));
	//	Thread.sleep(2000);

	}
}
