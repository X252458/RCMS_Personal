package com.telus.rcms.tests.FutureActivation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.telus.api.test.utils.APIJava;
import com.telus.rcms.jsonPathLibrary.All;
import com.telus.rcms.jsonPathLibrary.ActivationPayloadJsonPath;
import com.telus.rcms.jsonPathLibrary.AgreementItem;
import com.telus.rcms.utils.APIUtils;
import com.telus.rcms.utils.DBUtils;
import com.telus.rcms.utils.GenericUtils;
import com.telus.rcms.utils.JSONUtils;
import com.telus.rcms.utils.ValidationUtils;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseTest;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.SystemProperties;

import com.aventstack.extentreports.ExtentTest;
import com.test.reporting.ExtentTestManager;

/**
 * 
 * Testcase name : TC07 Activate Telus  subscriber with TIASSETCREDIT+TIPROMOCREDIT

 *
 */
public class TC07_Telus_TAB_HWS_DB_DF_BIB extends BaseTest {

	String testCaseName = null;
	String scriptName = null;
	String testCaseDescription = null;
	String requestPayloadFilePath = null;
	String jsonPathLibrary = null;

	String environment = null;
	static String connectionString = null;

	String accountID = null;
	String subscriptionID = null;
	String subscriberNum = null;
	String startDate = null;

	ExtentTest parentTest = null;


	@BeforeTest(alwaysRun = true)
	public void BeforeMethod(ITestContext iTestContext) {

		testCaseName = this.getClass().getName();
		scriptName = GenericUtils.getTestCaseName(testCaseName);
		testCaseDescription = "The purpose of this test case is to verify \"" + scriptName + "\" workflow";
		requestPayloadFilePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testSpecs\\RCMS\\FutureActivation\\" + scriptName + ".json";
		environment = SystemProperties.EXECUTION_ENVIRONMENT;

	}

	@Test(groups = { "Loyalty_Agreement","FutureActivation","TC07_Telus_TAB_HWS_DB_DF_BIB","CompleteRegressionSuite" })

	public void testMethod_activation(ITestContext iTestContext) throws Exception {

		parentTest = ExtentTestManager.getTest();
		parentTest.assignCategory("FUTURE_ACTIVATION");

		Reporting.setNewGroupName("Automation Configurations / Environment Details & Data Setup");
		Reporting.logReporter(Status.INFO,
				"Automation Configuration - Environment Configured for Automation Execution [" + environment + "]");
		Reporting.printAndClearLogGroupStatements();

		/*** Test Case Details ***/
		Reporting.setNewGroupName("Test Case Details");
		Reporting.logReporter(Status.INFO, "Test Case Name : [" + scriptName + "]");
		Reporting.logReporter(Status.INFO, "Test Case Description : [" + testCaseDescription + "]");
		Reporting.logReporter(Status.INFO, "Request Payload Path : [" + requestPayloadFilePath + "]");
		Reporting.printAndClearLogGroupStatements();

		/**
		 * API Call Steps
		 */

		/*** Test Case - Activation 7 ***/

		Reporting.setNewGroupName("ACCESS TOKEN GENERATION");
		String accessToken = APIUtils.getAccessToken(environment, "rewardService");
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN: " + accessToken);
		Reporting.printAndClearLogGroupStatements();

		// Activation API Call

		Reporting.setNewGroupName("FUTURE ACTIVATION SERVICE API CALL - TC07");
		String apiEnv = GenericUtils.getAPIEnvironment(environment);
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		accountID = GenericUtils.getUniqueAccountID(apiEnv);
		subscriptionID = GenericUtils.getUniqueSubscriptionID(apiEnv);
		subscriberNum = GenericUtils.getUniqueSubscriberNumber(apiEnv);
		startDate = JSONUtils.getGMTFutureStartDate();
		System.setProperty("karate.auth_token", accessToken);
		System.setProperty("karate.accID", accountID);
		System.setProperty("karate.subID", subscriptionID);
		System.setProperty("karate.subNum", subscriberNum);
		System.setProperty("karate.startDate", startDate);
		System.setProperty("karate.apiEnv", apiEnv);

		Map<String, Object> apiOperation = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/FutureActivation/futureActivationTC7.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: "
				+ apiOperation.get("apiStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: "
				+ apiOperation.get("apiResponse"));

		Reporting.printAndClearLogGroupStatements();

		/*** DB VALIDATION ***/
		Reporting.setNewGroupName("DB VERIFICATION - TC07");
		payloadAndDbCheck();
		Reporting.printAndClearLogGroupStatements();

	}

	public void payloadAndDbCheck() throws SQLException, IOException {

		DBUtils.callDBConnect();
		/**
		 * DB Verification Steps
		 */

		ActivationPayloadJsonPath jsonPath = new ActivationPayloadJsonPath();

		String jsonString = GenericUtils.readFileAsString(requestPayloadFilePath);
		jsonString = jsonString.replace("#(subID)", subscriptionID).replace("#(subNum)", subscriberNum)
				.replace("#(accID)", accountID).replace("#(startDate)", startDate);
		Reporting.logReporter(Status.INFO, "Pretty Payload: " + jsonString);

		// Declaring variable from payload
		ValidationUtils.payloadValueDeclarationFuture(jsonString);
		ValidationUtils.payloadnDBCheckFutureAgrmtItem(jsonString, 5);
		ValidationUtils.extraDBvalidation(jsonString, "futureActivation", 5);

	}

	/**
	 * Close DB Connection
	 */
	/*
	 * @AfterMethod(alwaysRun = true) public void afterTest() {
	 * Reporting.setNewGroupName("Close DB Connection"); try {
	 * DBUtils.dbDisConnect(); } catch (SQLException e) {
	 * Reporting.logReporter(Status.INFO, "DB Connection Closed Successfully!"); }
	 * Reporting.printAndClearLogGroupStatements(); }
	 */
	/**
	 * Close any opened browser instances
	 */
	@AfterMethod(alwaysRun = true)
	public void afterTest1() {
		Reporting.setNewGroupName("Close All Browser");
		WebDriverSteps.closeTheBrowser();
		Reporting.printAndClearLogGroupStatements();
	}
}