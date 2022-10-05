package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.util.Constants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accPageSetUp() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), "password");
	}

	@Test
	public void accountsPageTitleTest() {

		Assert.assertEquals(accountsPage.getAccountsPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test
	public void accountPageUrlTest() {
		Assert.assertTrue(accountsPage.getAccPageHeader().contains(Constants.ACCOUNTS_PAGE_URL_FRACTIONS));
	}
	
	@Test
	public void accountPageHeaderTest() {
		Assert.assertEquals(accountsPage.getAccPageHeader(),Constants.ACCOUNTS_PAGE_HEADER_FRACTIONS);
	}
	
	@Test
	public void getSearchLinkTest() {
		Assert.assertTrue(accountsPage.checkSearchbox());
	}
	
	@Test
	public void accPageList() {
		Assert.assertEquals(accountsPage.getAccpageSections(), Constants.ACCOUNTS_PAGE_LIST);
	}

}
