package com.qa.opencart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public By getBy(String locatorType, String locatorValue) {

		By locator = null;
		switch (locatorType) {
		case "id":
			locator = By.id(locatorValue);

			break;

		case "name":
			locator = By.name(locatorValue);

			break;

		case "class":
			locator = By.className(locatorValue);

			break;
		case "xpath":
			locator = By.xpath(locatorValue);

			break;
		case "css":
			locator = By.cssSelector(locatorValue);

			break;
		case "linktext":
			locator = By.linkText(locatorValue);

			break;
		case "partiallinktext":
			locator = By.partialLinkText(locatorValue);

			break;

		case "tag":
			locator = By.tagName(locatorValue);

			break;
		default:
			break;
		}
		return locator;
	}

	public WebElement getElement(By locator) {

		WebElement ele = driver.findElement(locator);
		return ele;

	}

	public boolean doIsdisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doSendKeys(String locatorType, String locatorValue, String value) {
//		getBy(locatorType, locatorValue)
		getElement(getBy(locatorType, locatorValue)).sendKeys(value);
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();

	}

	public String doGetAttribute(By locator, String attributeName) {
		return getElement(locator).getAttribute(attributeName);

	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);

	}

	public int getElementsCount(By locator) {
		return getElements(locator).size();

	}

	public List<String> getElementTextList(By locator) {
		List<WebElement> eleList = getElements(locator);

		List<String> eleTextList = new ArrayList<>();
		for (WebElement s : eleList) {
			eleTextList.add(s.getText());
		}
		return eleTextList;
	}

	public List<String> getElementAttributeList(By locator, String attributeName) {
		List<WebElement> eleList = getElements(locator);

		List<String> eleAttributeList = new ArrayList<>();
		for (WebElement s : eleList) {
			eleAttributeList.add(s.getAttribute(attributeName));
		}
		return eleAttributeList;
	}

	/************ DropDownUtils ******************/

	public void doSelectByIndex(By locator, int index) {
		Select s = new Select(getElement(locator));
		s.selectByIndex(index);
	}

	public void doSelectByValue(By locator, String value) {
		Select s = new Select(getElement(locator));
		s.selectByValue(value);
	}

	public void doSelectByVisibletext(By locator, String text) {
		Select s = new Select(getElement(locator));
		s.selectByVisibleText(text);
	}

	public List<String> getAllOptionsList(By locator) {
		Select s = new Select(getElement(locator));
		List<WebElement> country = s.getOptions();
		System.out.println(country.size());
		List<String> textList = new ArrayList<>();
		for (WebElement webElement : country) {
			String text = webElement.getText();
			textList.add(text);
		}
		return textList;
	}

	/************* Actions ***************/

	public void doSendKeysActions(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doClickActions(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}

	public WebElement waitForElementPresense(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement waitForElementVisiblity(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementClickable(By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public boolean waitForTile(String title, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(title))) {
			return true;
		} else {
			return false;
		}
	}

	public String getTile(String title, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(title))) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	public boolean waitForURL(String url, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlContains(url))) {
			return true;
		} else {
			return false;
		}
	}

	public String waitForURLContrains(String urlValue, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlContains(urlValue))) {
			return driver.getCurrentUrl();
		} else {
			return null;
		}
	}

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public WebDriver waitForFrameByIndex(String frameName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	public WebDriver waitForFrameByElement(WebElement element, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	public List<WebElement> waitForAllElementVisible(By footerlist, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(footerlist));
	}

	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement fluentWaitVisiblityOfElement(By locator, int timeOut, int pollingTime) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public boolean waitForPageTitle(String titleVal, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(titleVal));
	}

	public String doGetPageTitleContains(String titleVal, int timeOut) {
		if (waitForPageTitle(titleVal, timeOut)) {
			return driver.getTitle();
		}
		return null;
	}

	public boolean waitForPageActTitle(String actTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(actTitle));
	}

	public String doGetPageTitleIs(String titleVal, int timeOut) {
		if (waitForPageActTitle(titleVal, timeOut)) {
			return driver.getTitle();
		}
		return null;
	}

}
