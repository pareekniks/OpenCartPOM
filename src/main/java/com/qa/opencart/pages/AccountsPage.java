package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By header = By.cssSelector("div# logo a");
	private By sections = By.cssSelector("div# logo h2");
	private By logout = By.linkText("Logout");
	private By search = By.name("search");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountsPageTitle() {
		return eleUtil.doGetPageTitleIs(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountsPageUrl() {
		return eleUtil.waitForURLContrains(Constants.ACCOUNTS_PAGE_URL_FRACTIONS, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccPageHeader() {
		return eleUtil.doGetText(header);

	}

	public boolean checkLogOutLink() {
		return eleUtil.doIsdisplayed(logout);
	}

	public List<String> getAccpageSections() {

		List<WebElement> sectionsList = eleUtil.waitForAllElementVisible(sections, Constants.DEFAULT_TIME_OUT);
		List<String> secList = new ArrayList<>();
		for (WebElement string : sectionsList) {
			secList.add(string.getText().trim());
		}
		return secList;

	}

	public boolean checkSearchbox() {
		return eleUtil.doIsdisplayed(search);
	}

}
