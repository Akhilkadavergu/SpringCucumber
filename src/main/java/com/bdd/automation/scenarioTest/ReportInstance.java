package com.bdd.automation.scenarioTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;

@Component
public class ReportInstance {
	private static Platform platform;
	private ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> testCase = new ThreadLocal<>();
	private static ThreadLocal<ExtentTest> testStep = new ThreadLocal<>();
	public static Integer noOfStepsPassed = 0;
	public Integer noOfStepsFailed = 0;
	@Autowired
	ReportingManager reportingManager;
	private static Logger logger = LoggerFactory.getLogger(ReportInstance.class);

	public void createTestCase(String testCaseName) {
		testCase.set(extentReports.createTest(testCaseName));
		logger.info("Creating Testcase : " + testCaseName);
		noOfStepsFailed = 0;
		noOfStepsPassed = 0;
	}

	public void createTestStep(String testMethod) {
		if (Objects.nonNull(testCase) && Objects.nonNull(testCase.get())) {
			waitForSometime(1);
			testStep.set(testCase.get().createNode(testMethod));
			logger.info("Creating Test Step " + testMethod);
		} else {
			logger.warn("Test Step " + testMethod + " has not been created");
		}
	}

	public void createFirstTestStep(String msg, String category, String device, String[] author) {
		if (Objects.nonNull(testCase) && Objects.nonNull(testCase.get())) {
			waitForSometime(1);
			testStep.set(testCase.get().createNode("Test Details"));
			testCase.get().info(msg);
			testCase.get().assignCategory(category);
			testCase.get().assignAuthor(author);
			testCase.get().assignDevice(device);
		} else
			logger.warn("First Test Step has not been created");
	}

	public synchronized void updateTestCaseLog(String msg, String category, String device, String[] author) {
		if (Objects.nonNull(extentReports)) {
			testCase.get().info(msg);
			testCase.get().assignCategory(category);
			testCase.get().assignAuthor(author);
			testCase.get().assignDevice(device);
		} else
			logger.warn("Test Case " + msg + " has not been updated");
	}

	public synchronized void updateTestLog(Status status, String msg, String... args) {
		Boolean screenshot = false;
		if (Objects.nonNull(args) && args.length > 0 && args[0].contains("screenshot"))
			screenshot = true;
		if (Objects.nonNull(testStep) && status.getName().equalsIgnoreCase("Info") && screenshot) {
			Media media = MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot(), "Screenshot").build();
			testStep.get().info(msg, media);
			logger.info(msg);
		} else if (Objects.nonNull(testStep) && status.getName().equalsIgnoreCase("Info") && !screenshot) {
			testStep.get().log(status, msg);
			logger.info(msg);
		} else if (Objects.nonNull(testStep) && status.getName().equalsIgnoreCase("Fail") && screenshot) {
			logger.error(msg);
			Media media = MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot(), "Screenshot").build();
			testStep.get().fail(msg, media);
			noOfStepsFailed++;
		} else if (Objects.nonNull(testStep) && status.getName().equalsIgnoreCase("Warning") && screenshot) {
			logger.warn(msg);
			Media media = MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot(), "Screenshot").build();
			testStep.get().warning(msg, media);
		} else if (Objects.nonNull(testStep) && status.getName().equalsIgnoreCase("Pass") && screenshot) {
			logger.info(msg);
			Media media = MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot(), "Screenshot").build();
			testStep.get().pass(msg, media);
			noOfStepsPassed++;
		} else if (Objects.nonNull(testStep) && status.getName().equalsIgnoreCase("Fail") && !screenshot) {
			logger.error(msg);

			testStep.get().fail(msg);
			noOfStepsFailed++;
		} else if (Objects.nonNull(testStep) && status.getName().equalsIgnoreCase("Pass") && !screenshot) {
			logger.info(msg);

			testStep.get().pass(msg);
			noOfStepsPassed++;
		} else
			logger.info(msg);
	}

	public synchronized void passTest(String msg) {
		testCase.get().pass(MarkupHelper.createLabel(msg, ExtentColor.GREEN));
		logger.info(msg);
	}

	public synchronized void warnTest(String msg) {
		testCase.get().warning(MarkupHelper.createLabel(msg, ExtentColor.INDIGO));
		logger.info(msg);
	}

	public synchronized void failTest(String msg) {
		testCase.get().fail(MarkupHelper.createLabel(msg, ExtentColor.RED));
		logger.error(msg);
	}

	private String takeScreenshot() {
		String dest = null;
		System.out.println();
		try {
			String folder = reportingManager.getReportFolderLocation() + File.separator + "Screenshot";
			createReportPath(folder);
			String timeStamp = dateTimeStamp();
			dest = folder + File.separator + timeStamp + ".jpg";
			byte[] data = null;
			data = ((TakesScreenshot) AbstractBeans.webDriver.get()).getScreenshotAs(OutputType.BYTES);
			OutputStream stream = new FileOutputStream(dest);
			stream.write(data);
			stream.close();
			platform = getCurrentPlatform();
			if (Objects.nonNull(platform)) {
				switch (platform) {
				case MAC:
					String macpaths[] = folder.split("//");
					String macfolderName = macpaths[macpaths.length - 2];
					dest = ".." + File.separator + macfolderName + File.separator + "Screenshot" + File.separator
							+ timeStamp + ".jpg";
					break;
				case WINDOWS:
					String paths[] = folder.split("\\\\");
					String folderName = paths[paths.length - 2];
					dest = "../" + folderName + File.separator + "Screenshot" + File.separator + timeStamp + ".jpg";
					break;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dest;
	}

	private static void createReportPath(String path) {
		File testDir = new File(path);
		if (!testDir.exists())
			if (testDir.mkdir())
				logger.info("New Directory created");
			else
				logger.info("Filed to create Directory");
	}

	private static String dateTimeStamp() {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyy HHmmss");
		Date date = new Date();
		return formatter.format(date);
	}

	private static Platform getCurrentPlatform() {
		if (platform == null) {
			String operSYS = System.getProperty("os.name").toLowerCase();
			if (operSYS.contains("win"))
				platform = Platform.WINDOWS;
			else if (operSYS.contains("nix") || operSYS.contains("nux") || operSYS.contains("aix"))
				platform = Platform.LINUX;
			else if (operSYS.contains("mac"))
				platform = Platform.MAC;
		}
		return platform;
	}

	private void waitForSometime(int sec) {
		try {
			Thread.sleep(sec * 1000);
			if (sec > 3)
				logger.info("waiting for next Step");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ExtentReports getBaseTest() {
		return extentReports;
	}

	public void setBaseTest(ExtentReports extentReports) {
		this.extentReports = extentReports;
	}

	public ExtentTest getTestCase() {
		return testCase.get();
	}

	public ExtentTest getTestStep() {
		return testStep.get();
	}

	public static void setTestStep(ThreadLocal<ExtentTest> testStep) {
		ReportInstance.testStep = testStep;
	}

	public synchronized void addScreenshot(String title) {
		getTestStep().addScreenCaptureFromPath(takeScreenshot(), title);
	}

}
