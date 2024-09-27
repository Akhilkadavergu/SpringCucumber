package com.bdd.automation.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/feature" }, glue = {
		"com.bdd.automation.stepdefinations" }, tags = "@demoOne", plugin = { "pretty",
				"json:Report/cucumber.json" }, monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

}
