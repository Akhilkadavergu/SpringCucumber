package com.bdd.automation.scenarioTest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.bdd.automation.utils.FrameworkConstants;

@Component
public class ReportingManager {
	private static final String reportFileName = "ExecutionResult";
	private static ThreadLocal<String> reportFolderLocation = new ThreadLocal<>();
	private static ThreadLocal<String> tempFolderLocation = new ThreadLocal<>();
	ExtentReports extentReports = new ExtentReports();
	private static Logger logger = LoggerFactory.getLogger(ReportingManager.class);

	public synchronized void createInstance() throws Exception {
		try {
			String suitName = "SUITE NAME";
			String fileName = getReportFileLocation(suitName);
			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName).viewConfigurer().viewOrder()
					.as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST, ViewName.CATEGORY }).apply();
			htmlReporter.config().setTheme(Theme.STANDARD);
			String executionType = "Executed in Local";
			// handle the condition and assign exution type as local or pipeline

			htmlReporter.config().setDocumentTitle("Automation Test Execution Report");
			htmlReporter.config().setReportName(suitName + "_" + executionType);
			htmlReporter.config().setEncoding("utf-8");
			htmlReporter.config().setCss(".r-img{width:30%}");

			extentReports.setSystemInfo("Historical Report Repository", "http://HistoricalReport");
			extentReports.setSystemInfo("OS", System.getProperty("os.name"));
			extentReports.setSystemInfo("USER", System.getProperty("user.name"));
			extentReports.setSystemInfo("Author", "AKHIL KADAVERGU");
			extentReports.attachReporter(htmlReporter);
			logger.info("Report initiation has been completed for execution");
		} catch (Exception e) {
			throw new Exception("Received error while initiating the report instance");
		}
	}

	private static String getReportFileLocation(String suiteName) {
		String reportFileLocation = null;
		String folder = reportpath.get() + "_" + suiteName;
		String screenshotFolder = reportpath.get() + "_" + suiteName + File.separator + "Screenshot";
		String tempFolder = reportpath.get() + "_" + suiteName + File.separator + "tempDownload";
		createReportPath(FrameworkConstants.TARGET_PATH);
		createReportPath(FrameworkConstants.REPORT_PATH);
		createReportPath(folder);
		createReportPath(screenshotFolder);
		createReportPath(tempFolder);
		reportFileLocation = folder + File.separator + reportFileName + "_" + dateandtime.get() + ".html";
		reportFolderLocation.set(folder);
		tempFolderLocation.set(tempFolder);
		System.setProperty("ReportPath", folder);
		logger.info("ReportPath: " + System.getProperty("ReportPath"));
		return reportFileLocation;

	}

	private static void createReportPath(String path) {
		File testDir = new File(path);
		if (!testDir.exists()) {
			if (testDir.mkdir())
				logger.info("Directory: " + path + " is created");
			else
				logger.info("Failed to create Directory");
		} else
			logger.info("Directory is already exists");
	}

	private static Supplier<String> dateandtime = () -> {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyy_HHmmss");
		Date date = new Date();
		return formatter.format(date);
	};

	private static Supplier<String> reportpath = () -> {
		String formattedDate = dateandtime.get();
		return FrameworkConstants.REPORT_PATH + File.separator + formattedDate;
	};

	public String getReportFolderLocation() {
		return reportFolderLocation.get();
	}

	public ExtentReports getExtentReports() {
		return extentReports;
	}

	public String getTempFolderLocation() {
		return tempFolderLocation.get();
	}
}
