package com.telus.rcms.tests.Migration;

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
import com.telus.rcms.jsonPathLibrary.ActivationPayloadJsonPath;
import com.telus.rcms.jsonPathLibrary.AgreementItem;
import com.telus.rcms.jsonPathLibrary.All;
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
 * Testcase Name : TC04 Call notifySubscriptionMigration to migrate from Prepaid
 * to Postpaid_FROM PRESOC and PRECREDIT to DB + DF + BIB + ACB + RCB+AF
 *
 * 
 */
public class TC03_Post_DB_DF_BIB_ACB_AF_TIA_TIP_to_Pre_PRECREDIT extends BaseTest {

	String testCaseName = null;
	String scriptName = null;
	String testCaseDescription = null;
	String requestPayloadFilePath = null;
	String jsonPathLibrary = null;

	String environment = null;

	static Statement statement = null;
	static ResultSet resultSet = null;
	static ResultSet rsAgreementItem = null;

	String accountID = null;
	String subscriptionID = null;
	String subscriberNum = null;
	String startDate = null;
	String jsonString = null;

	ExtentTest parentTest = null;

	/**
	 * @param iTestContext
	 */
	@BeforeTest(alwaysRun = true)
	public void BeforeMethod(ITestContext iTestContext) {

		testCaseName = this.getClass().getName();
		scriptName = GenericUtils.getTestCaseName(testCaseName);
		testCaseDescription = "The purpose of this test case is to verify \"" + scriptName + "\" workflow";
		environment = SystemProperties.EXECUTION_ENVIRONMENT;

	}

	@Test(groups = { "Loyalty_Agreement", "Migration", "TC04_Pre_PRESOC_PRECREDIT_to_Post_DB_DF_ACB_RCB_AF",
			"CompleteRegressionSuite" })

	public void testMethod_migration(ITestContext iTestContext) throws Exception {

		parentTest = ExtentTestManager.getTest();
		parentTest.assignCategory("MIGRATION");

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

		Reporting.setNewGroupName("ACCESS TOKEN GENERATION");
		String accessToken = APIUtils.getAccessToken(environment, "rewardService");
		String accessToken_violation = APIUtils.getAccessToken(environment,"violation");
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN: " + accessToken);
		Reporting.printAndClearLogGroupStatements();

		// Activation API Call

		Reporting.setNewGroupName("ACTIVATION SERVICE API CALL - TC01");
		String apiEnv = GenericUtils.getAPIEnvironment(environment);
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		accountID = GenericUtils.getUniqueAccountID(apiEnv);
		subscriptionID = GenericUtils.getUniqueSubscriptionID(apiEnv);
		subscriberNum = GenericUtils.getUniqueSubscriberNumber(apiEnv);
		startDate = JSONUtils.getGMTStartDate();

		System.setProperty("karate.auth_token_reward", accessToken);
		System.setProperty("karate.auth_token", accessToken);
		System.setProperty("karate.auth_token_violation", accessToken_violation);
		System.setProperty("karate.accID", accountID);
		System.setProperty("karate.subID", subscriptionID);
		System.setProperty("karate.subNum", subscriberNum);
		System.setProperty("karate.startDate", startDate);
		System.setProperty("karate.apiEnv", apiEnv);

		Map<String, Object> apiOperation = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/activation/activationTC1.feature","tc01ActivateTelusSubWithAllStatus","200");
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation.get("tc01ActivateTelusSubWithAllRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation.get("tc01ActivateTelusSubWithAllStatus"));
		Reporting.printAndClearLogGroupStatements();

		// GetMigrationPenalty API Call
		Reporting.setNewGroupName("GET MIGRATION PENALTY API CALL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation3 = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/GetMigrationPenalty/GetMigrationPenaltyTC02.feature","apiStatus","201");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation3.get("apiStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation3.get("apiResponse"));
		Reporting.printAndClearLogGroupStatements();

		// Migration API Call
		Reporting.setNewGroupName("MIGRATION API CALL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation2 = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/Migration/migrationTC3.feature","apiStatus","200");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation2.get("apiStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation2.get("apiRequest"));

		jsonString = String.valueOf(apiOperation2.get("apiRequest")).replace("=", ":");
		Reporting.printAndClearLogGroupStatements();

		/*** DB VALIDATION ***/
		Reporting.setNewGroupName("DB VERIFICATION");
		payloadAndDbCheck(jsonString);
		Reporting.printAndClearLogGroupStatements();

	}

	public void payloadAndDbCheck(String jsonString) throws SQLException, IOException {

		DBUtils.callDBConnect();
		/**
		 * DB Verification Steps
		 */
		// Declaring variable from payload
		ValidationUtils.beforeMigrationDBcheck(subscriptionID);

		Reporting.logReporter(Status.INFO, "--------------------DB Validation Completed--------------------");

	}

	/**
	 * Close DB Connection
	 */

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		Reporting.setNewGroupName("Close All Connection");
		try {
			DBUtils.dbDisConnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporting.logReporter(Status.INFO, "DB Connection Closed Successfully!");
		Reporting.printAndClearLogGroupStatements();
	}
}