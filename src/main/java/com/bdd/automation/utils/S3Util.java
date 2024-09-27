package com.bdd.automation.utils;

import java.io.File;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class S3Util extends CommonUtil {
	private static Logger logger = LoggerFactory.getLogger(S3Util.class);

	private void setupAWSSessions() {
		logger.info("Setting aws s3 accesses");
		if (Objects.nonNull(System.getenv(FrameworkConstants.TEST_EXECUTION_TYPE))
				&& System.getenv(FrameworkConstants.TEST_EXECUTION_TYPE).equalsIgnoreCase("pipeline")) {
			System.setProperty("aws.accessKeyId", System.getenv(FrameworkConstants.S3_ACCESS_KEY));
			System.setProperty("aws.secretKey", System.getenv(FrameworkConstants.S3_SECRET_KEY));
			System.setProperty("aws.sessionToken", System.getenv(FrameworkConstants.S3_SESSION_KEY));
			logger.info("***Jenkins execution started with pipeline credential***");
		} else {
			System.setProperty("aws.accessKeyId", environment.getProperty("S3accesskey"));
			System.setProperty("aws.secretKey", environment.getProperty("S3secretkey"));
			System.setProperty("aws.sessionToken", environment.getProperty("S3sessionToken"));
			logger.info("***local execution started local credential***");
		}
	}

	public boolean downloadFileFromS3Bucket(String bucketName, String objectName) throws Exception {
		try {
			setupAWSSessions();
			AmazonS3 s3client = AmazonS3ClientBuilder.standard()
					.withCredentials(new SystemPropertiesCredentialsProvider()).withRegion(Regions.US_EAST_1).build();
			GetObjectRequest objectRequest = new GetObjectRequest(bucketName, objectName);
			String fileName = "s3Validation_" + generateRandomString() + ".csv";
			File localFile = new File(FrameworkConstants.TARGET_DOWNLOAD_LOCATION + File.separator + fileName);
			s3client.getObject(objectRequest, localFile);
			String targetPath = getResultPath(System.getProperty("user.dir") + File.separator + "target"
					+ File.separator + "Report" + File.separator);
			moveFile(FrameworkConstants.TARGET_DOWNLOAD_LOCATION + File.separator + fileName,
					targetPath + File.separator + fileName);
			runTimeTestData.get().getRunTimeDataMap().put("s3File", targetPath + File.separator + fileName);
			return true;
		} catch (Exception e) {
			throw new Exception("S3 file download error :" + e.getLocalizedMessage());
		}
	}

	public boolean getS3ObjectDetails(String bucketName, String prefix) throws Exception {
		setupAWSSessions();
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new SystemPropertiesCredentialsProvider())
				.withRegion(Regions.US_EAST_1).build();
		try {
			ObjectListing listing = s3client.listObjects(bucketName, prefix);
			List<S3ObjectSummary> summaries = listing.getObjectSummaries();
			String filePath = summaries.get(0).getKey();
			GetObjectRequest objectRequest = new GetObjectRequest(bucketName, filePath);
			String fileName = "s3Validation_" + generateRandomString() + ".csv";
			File localFile = new File(FrameworkConstants.TARGET_DOWNLOAD_LOCATION + File.separator + fileName);
			s3client.getObject(objectRequest, localFile);
			String targetPath = getResultPath(System.getProperty("user.dir") + File.separator + "target"
					+ File.separator + "Report" + File.separator);
			moveFile(FrameworkConstants.TARGET_DOWNLOAD_LOCATION + File.separator + fileName,
					targetPath + File.separator + fileName);
			runTimeTestData.get().getRunTimeDataMap().put("s3File", targetPath + File.separator + fileName);
			return true;
		} catch (Exception e) {
			throw new Exception("S3 file download error :" + e.getLocalizedMessage());
		}
	}

	public boolean getS3ObjectDetails(String bucketName, String prefix, String file) throws Exception {
		setupAWSSessions();
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(new SystemPropertiesCredentialsProvider())
				.withRegion(Regions.US_EAST_1).build();

		try {
			ObjectListing listing = s3client.listObjects(bucketName, prefix);
			List<S3ObjectSummary> summaries = listing.getObjectSummaries();
			summaries.stream().forEach(i -> {
				if (i.getKey().contains(file)) {
					runTimeTestData.get().getRunTimeDataMap().put("S3Path", i.getKey());
				}
			});
			GetObjectRequest objectRequest = new GetObjectRequest(bucketName,
					runTimeTestData.get().getRunTimeDataMap().get("S3Path"));
			String fileName = "s3Validation_" + generateRandomString() + ".csv";
			File localFile = new File(FrameworkConstants.TARGET_DOWNLOAD_LOCATION + File.separator + fileName);
			s3client.getObject(objectRequest, localFile);
			String targetPath = getResultPath(System.getProperty("user.dir") + File.separator + "target"
					+ File.separator + "Report" + File.separator);
			moveFile(FrameworkConstants.TARGET_DOWNLOAD_LOCATION + File.separator + fileName,
					targetPath + File.separator + fileName);
			runTimeTestData.get().getRunTimeDataMap().put("s3File", targetPath + File.separator + fileName);

			return true;
		} catch (Exception e) {
			throw new Exception("S3 file download error :" + e.getLocalizedMessage());
		}
	}
}
