package com.bdd.automation.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bdd.automation.model.RunTimeTestData;

import io.github.bonigarcia.wdm.WebDriverManager;

@Component
public class SetupUtil extends AbstractUtil {
	private static Logger logger = LoggerFactory.getLogger(SetupUtil.class);

	public synchronized void setUp(String sType) throws IOException {
		String fileName = "application.properties";

		// Can be used to read different property files

//		if(Objects.nonNull(System.getenv("spring.profiles.active"))||Objects.nonNull(System.getenv("spring.profiles.active"))) {
//			String extension=System.getenv("spring.profiles.active").toUpperCase();
//			fileName="application-"+extension+".properties";
//		}
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
		Properties prop = new Properties();
		prop.load(inputStream);
		if (sType.equals("webOnly"))
			setupWebOnly(prop);
		if (Objects.isNull(runTimeTestData.get()))
			runTimeTestData.set(new RunTimeTestData());

	}

	private void setupWebOnly(Properties prop) {
		if (Objects.isNull(webDriver.get())) {
			webDriver.set(getChromeDriver(prop));
			js.set(getJavaScriptExecutor(webDriver.get()));
			actions.set(getActions(webDriver.get()));
			fluentWait.set(getFluentWait(webDriver.get()));
			webDriverWait.set(getWebDriverWait(webDriver.get()));
		}
	}

	private WebDriverWait getWebDriverWait(WebDriver webDriver) {
		return null;
	}

	private FluentWait<WebDriver> getFluentWait(WebDriver webDriver) {
		// TODO Auto-generated method stub
		return null;
	}

	private Actions getActions(WebDriver webDriver) {
		// TODO Auto-generated method stub
		return null;
	}

	private JavascriptExecutor getJavaScriptExecutor(WebDriver webDriver) {
		// TODO Auto-generated method stub
		return null;
	}

	private WebDriver getChromeDriver(Properties prop) {
		logger.info("Local execution");
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setHeadless(false);
		System.setProperty("java.awt.headless", "false");
		chromeOptions.addArguments("--disable-gpu");
		ChromeDriver webDriver = new ChromeDriver(chromeOptions);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return webDriver;
	}
}
