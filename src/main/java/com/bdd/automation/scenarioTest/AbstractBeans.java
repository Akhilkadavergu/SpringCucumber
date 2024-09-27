package com.bdd.automation.scenarioTest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.bdd.automation.model.RunTimeTestData;

import io.cucumber.java.Scenario;

public class AbstractBeans {
	public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
	public static ThreadLocal<JavascriptExecutor> js = new ThreadLocal<>();
	public static ThreadLocal<Actions> actions = new ThreadLocal<>();
	public static ThreadLocal<FluentWait<WebDriver>> fluentWait = new ThreadLocal<>();
	public static ThreadLocal<WebDriverWait> webDriverWait = new ThreadLocal<>();
	public static ThreadLocal<RunTimeTestData> runTimeTestData = new ThreadLocal<>();
	public static ThreadLocal<Scenario> scenario = new ThreadLocal<>();
	public static ThreadLocal<String> currentStepName = new ThreadLocal<>();

}
