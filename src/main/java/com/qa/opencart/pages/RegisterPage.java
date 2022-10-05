package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;
import com.qa.opencart.util.JavaScriptUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil jsUtil;
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	private By subscribeYes = By.id("input-newsletter-yes");
	private By subscribeNo = By.id("input-newsletter-no");

	private By agreeCheckBox = By.xpath("//input[@type='checkbox']");
	private By continueButton = By.xpath("//button[@type='submit' and text()='Continue']");
	private By sucessMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	private By footerLink = By.xpath("//h5[text()='Customer Service']");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public boolean accountRegisteration(String firstName, String lastName,String email,String password) throws InterruptedException {
		eleUtil.waitForPageActTitle("Register Account", 20);
		jsUtil.scrollIntoView(driver.findElement(footerLink));
		eleUtil.waitForElementVisiblity(this.firstName, 10);
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.password, password);
		jsUtil.scrollIntoView(driver.findElement(agreeCheckBox));
//		eleUtil.doSendKeys(this.telephone, telephone);
	  	eleUtil.waitForElementVisiblity(subscribeYes, 10);
	  	eleUtil.waitForElementClickable(agreeCheckBox, 10);
		eleUtil.doClick(subscribeYes);	
     	eleUtil.waitForElementClickable(this.agreeCheckBox, 10);
		eleUtil.doClick(agreeCheckBox);
		eleUtil.waitForElementClickable(continueButton, 10);
		eleUtil.doClick(continueButton);
		eleUtil.doClick((By.xpath("(//a[text()='Login'])[2]")));
//		eleUtil.waitForPageActTitle("Register Account", 20);

		if (eleUtil.doGetText(sucessMessg).contains("success")) {
			eleUtil.doClick(logoutLink);
			return true;
		} else {
			return false;
		}
	}

}
