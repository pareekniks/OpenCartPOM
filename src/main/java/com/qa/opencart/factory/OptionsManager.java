package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;

	private ChromeOptions co;

	private FirefoxOptions fo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			co.setHeadless(true);
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			co.addArguments("--inccognito");
		
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			 co.setPlatformName("linux");
		//	 co.setCapability("enableVNC", true);
		}
		co.addArguments("--window-size=1280,800");
		 co.addArguments("--disable-gpu");
		 co.addArguments("--disable-setuid-sandbox");
		 co.addArguments("--no-sandbox");	
		 //co.addArguments("--headless");
		 co.addArguments("--verbose");
		 co.addArguments("--whitelisted-ips=");
		 co.addArguments("--disable-extensions"); 
		return co;

	}

	public FirefoxOptions getffOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			fo.setHeadless(true);
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			fo.addArguments("--inccognito");
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setPlatformName("linux");
		//	fo.setCapability("enableVNC", true);
		}
		return fo;

	}

}
