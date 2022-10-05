package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class LoginPageTest extends BaseTest {

	@Description("TC:01")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageTitleTest() {
		Assert.assertEquals(loginPage.getLoginPageTitle(), Constants.LOGIN_PAGE_TITLE);
	}

	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void isForgotPwdLinkExists() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExists(), "link doesnt exists");
	}

	@Test
	public void doLogin() {
		accountsPage =loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accountsPage.checkSearchbox());
	}

}
