package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager ops;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal();

	/**
	 * Used to initalize the driver
	 * 
	 * @param browserName
	 * @return webDriver
	 */

	public WebDriver init_Driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		ops = new OptionsManager(prop);
		System.out.println("Browser name is " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
			} else {
				tlDriver.set(new ChromeDriver(ops.getChromeOptions()));
			}
			// driver = new ChromeDriver(ops.getChromeOptions());
			
		} else if (browserName.equalsIgnoreCase("ff")) {
			WebDriverManager.firefoxdriver().setup();
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("ff");
			} else {
			tlDriver.set(new FirefoxDriver(ops.getffOptions()));
			}
			// driver = new ChromeDriver(ops.getChromeOptions());
			
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("Please pass the correct browser name " + browserName);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();

	}

	private void init_remoteDriver(String browser) {
		System.out.println("Running test cases on remote grid");

		if (browser.equals("chrome")) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), ops.getChromeOptions()));
			} catch (MalformedURLException e) {
				System.out.println("in catch block");
				e.printStackTrace();
			}
		}

		if (browser.equals("ff")) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), ops.getffOptions()));
			} catch (MalformedURLException e) {
				System.out.println("in catch block");
				e.printStackTrace();
			}
		}

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * Initialize the properties
	 * 
	 * @return Properties
	 */
	public Properties init_prop() {
		prop = new Properties();

		try {
			FileInputStream ip = new FileInputStream("./src/tests/resources/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
