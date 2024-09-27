package com.bdd.automation.stepdefinations;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdd.automation.scenarioTest.AbstractBeans;
import com.bdd.automation.scenarioTest.ReportInstance;
import com.bdd.automation.scenarioTest.ReportingManager;
import com.bdd.automation.utils.FrameworkConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;

public class GlobalHooks extends AbstractBeans {
	@Autowired
	ObjectMapper objectMapper;
	@Autowired
	ReportInstance reportInstance;
	@Autowired
	ReportingManager reportingManager;
	private static Logger logger = LoggerFactory.getLogger(GlobalHooks.class);
	private ThreadLocal<Integer> currentStepDefIndex = new ThreadLocal<>();

	@Before(order = 0)
	public void beforeScenario(Scenario scn) throws Exception {
		File dir = new File(System.getProperty("user.dir") + File.separator + "/target/Downloads");
		System.out.println("**********" + dir.getName());
		if (!dir.exists())
			dir.mkdirs();
		try {
			if (Objects.isNull(Objects.nonNull(reportingManager.getReportFolderLocation())))
				logger.warn("Report folder has already been created");
			else {
				reportingManager.createInstance();
				reportInstance.setBaseTest(reportingManager.getExtentReports());
			}
			scenario.set(scn);
			currentStepDefIndex.set(0);
			reportInstance.createTestCase(scn.getName());
			String testDesc = "Test Details :" + scn.getName();
			String author = "Environment - INT";
			String[] details = { "Module Name - MODULENAME", "TC ID- TC001" + "Section -SECTIONNAME" };
			reportInstance.createFirstTestStep(testDesc, "IM", author, details);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeStep(order = 0)
	public void beforeEachStep(Scenario scenario) throws Exception {
		reportInstance.createTestStep(getStepName(scenario));
	}

	@AfterStep(order = 0)
	public void afterEachstep(Scenario scn) throws Exception {
		Status status = scn.getStatus();
		String stepNm = getStepName(scn);
		if (status.toString().equals("PASSED") && !scn.getSourceTagNames().toString().contains("API"))
			reportInstance.updateTestLog(com.aventstack.extentreports.Status.PASS,
					"This Step - " + stepNm + " has been passed", "screenshot");
		if (status.toString().equals("FAILED") && !scn.getSourceTagNames().toString().contains("API")) {
			reportInstance.updateTestLog(com.aventstack.extentreports.Status.FAIL,
					"This Step " + stepNm + " has been failed", "screenshot");
		}
		if (status.toString().equals("PASSED") && scn.getSourceTagNames().toString().contains("API")) {
			reportInstance.updateTestLog(com.aventstack.extentreports.Status.PASS,
					"This Step" + stepNm + "has been passed", "api");
		}
		if (status.toString().equals("FATLED") && scn.getSourceTagNames().toString().contains("API"))

			reportInstance.updateTestLog(com.aventstack.extentreports.Status.FAIL,
					"This step" + stepNm + "has been failed", "api");
		currentStepDefIndex.set(currentStepDefIndex.get() + 1);
	}

	@After(order = 0)
	public synchronized void afterScenario(Scenario scn) throws Exception {
		Status status = scn.getStatus();
		if (status.toString().equals("PASSED") && !scn.getSourceTagNames().toString().contains("API")) {
			reportInstance.passTest(scn.getName() + " scenario has been passed.");
			reportInstance.addScreenshot("Pass screenshot");
			reportInstance.getBaseTest().flush();
		}
		if (status.toString().equals("FAILED") && !scn.getSourceTagNames().toString().contains("API")) {
			reportInstance.failTest(scn.getName() + " scenario has been failed.");
			reportInstance.addScreenshot("fail screenshot");
			reportInstance.getBaseTest().flush();
		}
		if (status.toString().equals("PASSED") && scn.getSourceTagNames().toString().contains("API")) {
			reportInstance.passTest(scn.getName() + " scenario has been passed.");
			reportInstance.getBaseTest().flush();
		}
		if (status.toString().equals("FAILED") && scn.getSourceTagNames().toString().contains("API")) {
			reportInstance.failTest(scn.getName() + " scenario has been failed.");
			reportInstance.getBaseTest().flush();
		}
//		if (CollectionUtils.isNotEmpty(dataSetup.getTestDataSetupDetailsList())) {
//			objectMapper.writeValue(new File(FrameworkConstants.DATA_SETUP_DOWNLOAD_LOCATION), dataSetup);
//		}
	}

	private String getStepName(Scenario scenario) throws Exception {
		String stepName;
		Field field = scenario.getClass().getDeclaredField("delegate");
		field.setAccessible(true);
		TestCaseState tcs = (TestCaseState) field.get(scenario);
		Field tcField = tcs.getClass().getDeclaredField("testCase");
		tcField.setAccessible(true);
		TestCase r = (TestCase) tcField.get(tcs);
		List<PickleStepTestStep> stepDefs = r.getTestSteps().stream().filter(x -> x instanceof PickleStepTestStep)
				.map(x -> (PickleStepTestStep) x).collect(Collectors.toList());
		PickleStepTestStep currentStepDef = stepDefs.get(currentStepDefIndex.get());
		stepName = currentStepDef.getStep().getKeyword() + ":" + currentStepDef.getStep().getText();
		return stepName;

	}
}
