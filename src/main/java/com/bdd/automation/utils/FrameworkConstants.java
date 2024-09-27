package com.bdd.automation.utils;

import java.io.File;

public class FrameworkConstants {
	public static final String BASE_PATH = System.getProperty("user.dir");
	public static final String TARGET_PATH = BASE_PATH + File.separator + "target";
	public static final String REPORT_PATH = TARGET_PATH + File.separator + "Report";
	public static final String RESULT_SUMMARY_PATH = TARGET_PATH + File.separator + "resultSummary.json";
	public static final String RESULT_SUMMARY_REPORT_PATH = BASE_PATH + File.separator + "TestResultSummary-index.html";
	public static final String CURRENT_REPORT_PATH = REPORT_PATH + File.separator + "CurrentReport";
	public static final String TARGET_DOWNLOAD_LOCATION = REPORT_PATH + File.separator + "CurrentReport";
	public static final String TEST_EXECUTION_TYPE = REPORT_PATH + File.separator + "CurrentReport";
	public static final String S3_ACCESS_KEY = REPORT_PATH + File.separator + "CurrentReport";
	public static final String S3_SECRET_KEY = REPORT_PATH + File.separator + "CurrentReport";
	public static final String S3_SESSION_KEY = REPORT_PATH + File.separator + "CurrentReport";
	

}
