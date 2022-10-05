package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	// page locators
	private By eMailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//button[@type='submit']");
	private By forgotPwdlink = By.linkText("Forgotten Password");
	private By registerlink = By.linkText("Register");

	// Page Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	// Public page actions/methods

	public String getLoginPageTitle() {
		return eleUtil.getTile(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);

	}

	public boolean isForgotPwdLinkExists() {
		return eleUtil.doIsdisplayed(forgotPwdlink);
	}

	public AccountsPage doLogin(String un, String pwd) {
		eleUtil.doSendKeys(eMailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);

	}
	
	public RegisterPage navigateToRegister() {
		eleUtil.doClick(registerlink);
		return new RegisterPage(driver);
	}

}
