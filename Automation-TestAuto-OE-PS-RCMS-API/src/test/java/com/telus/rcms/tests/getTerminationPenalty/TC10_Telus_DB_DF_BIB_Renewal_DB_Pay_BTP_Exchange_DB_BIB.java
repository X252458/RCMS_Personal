package com.telus.rcms.tests.getTerminationPenalty;

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
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseTest;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.SystemProperties;

import com.aventstack.extentreports.ExtentTest;
import com.test.reporting.ExtentTestManager;

/**
 * 
 * TC10 Call getTerminationPenalty for Telus Subscriber who did Exchange after
 * Renewal for Customer having DB+DF+BIB - Renewed to DB with Payment Method as
 * BIB_TELUS_PENDING and exchanged to DB+BIB
 *
 * 
 */
public class TC10_Telus_DB_DF_BIB_Renewal_DB_Pay_BTP_Exchange_DB_BIB extends BaseTest {

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
	String jsonString = null;
	
	String paymentMech=null;

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

	@Test(groups = {"Loyalty_Agreement_Violation","getTerminationPenlty", "Termination_TC10_Telus_DB_DF_BIB_Renewal_DB_Pay_BTP_Exchange_DB_BIB",
			"CompleteRegressionSuite" })

	public void testMethod_getTerminationPenalty(ITestContext iTestContext) throws Exception {

		parentTest = ExtentTestManager.getTest();
		parentTest.assignCategory("GET_TERMINATION_PENALTY");

		Reporting.setNewGroupName("Automation Configurations / Environment Details & Data Setup");
		Reporting.logReporter(Status.INFO,
				"Automation Configuration - Environment Configured for Automation Execution [" + environment + "]");
		Reporting.printAndClearLogGroupStatements();

		/*** Test Case Details ***/
		Reporting.setNewGroupName("Test Case Details");
		Reporting.logReporter(Status.INFO, "Test Case Name : [" + scriptName + "]");
		Reporting.logReporter(Status.INFO, "Test Case Description : [" + testCaseDescription + "]");
		Reporting.printAndClearLogGroupStatements();

		/**
		 * API Call Steps
		 */

		/*** Test Case - Activation - AccessoryFinance ***/

		Reporting.setNewGroupName("ACCESS TOKEN GENERATION");
		String rewardServiceaccessToken = APIUtils.getAccessToken(environment, "rewardService");
		String violationaccessToken = APIUtils.getAccessToken(environment, "violation");
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN FOR REWARD SERVICE: " + rewardServiceaccessToken);
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN FOR VIOLATION SERVICE: " + violationaccessToken);
		Reporting.printAndClearLogGroupStatements();

		// Activation API Call

		Reporting.setNewGroupName("ACTIVATION SERVICE API CALL");
		String apiEnv = GenericUtils.getAPIEnvironment(environment);
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		accountID = GenericUtils.getUniqueAccountID(apiEnv);
		subscriptionID = GenericUtils.getUniqueSubscriptionID(apiEnv);
		subscriberNum = GenericUtils.getUniqueSubscriberNumber(apiEnv);
		startDate = JSONUtils.getGMTStartDate();
		System.setProperty("karate.auth_token", rewardServiceaccessToken);
		System.setProperty("karate.auth_token_reward", rewardServiceaccessToken);
		System.setProperty("karate.auth_token_violation", violationaccessToken);
		System.setProperty("karate.accID", accountID);
		System.setProperty("karate.subID", subscriptionID);
		System.setProperty("karate.subNum", subscriberNum);
		System.setProperty("karate.startDate", startDate);
		System.setProperty("karate.apiEnv", apiEnv);

		Map<String, Object> apiOperation1 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/Activation/Others/activationTC6.feature");
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation1.get("tc06ActivateTelusSubwithDF_DB_BIBStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation1.get("tc06ActivateTelusSubwithDF_DB_BIBRequest"));

		Reporting.printAndClearLogGroupStatements();

		// Renewal API call
		Reporting.setNewGroupName("RENEWAL SERVICE API CALL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation2 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/Renewal/getTerminationPenalty/renewalTC2.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation2.get("apiStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation2.get("apiRequest"));
		paymentMech="BIB_TELUS_PENDING";
		Reporting.printAndClearLogGroupStatements();

		// Exchange API Call

		Reporting.setNewGroupName("EXCHANGE SERVICE API CALL - DB+AF Payment BIB_TELUS_PENDING");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation3 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/Exchange/GetTerminationPenalty/exchangeTC3.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation3.get("apiStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation3.get("apiRequest"));

		Reporting.printAndClearLogGroupStatements();

		// GetTerminationPenalty API Call
		Reporting.setNewGroupName("GET TERMINATION SERVICE API CALL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation4 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/GetTerminationPenalty/GetTerminationPenaltyTC09.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation4.get("getTerminationPenaltyStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation4.get("getTerminationPenaltyResponse"));

		jsonString = String.valueOf(apiOperation4.get("getTerminationPenaltyResponse")).replace("=", ":");
		Reporting.printAndClearLogGroupStatements();

		/*** DB VALIDATION ***/
		Reporting.setNewGroupName("DB VERIFICATION");
		responseAndDbCheck();
		Reporting.printAndClearLogGroupStatements();

	}

	public void responseAndDbCheck() throws SQLException, IOException, InterruptedException {

		// DBUtils.callDBConnect();

		/**
		 * DB Verification Steps
		 */

		Reporting.logReporter(Status.INFO, "Pretty Payload: " + jsonString);

		// Declaring variable from payload

		GenericUtils.responseDBCheckTerminationPenalty(jsonString, subscriptionID, 1,paymentMech);

		Reporting.logReporter(Status.INFO, "--------------------DB Validation Completed--------------------");
	}

	/**
	 * Close Connections
	 */

	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		Reporting.setNewGroupName("Close DB Connection");
		try {
			DBUtils.dbDisConnect();
		} catch (SQLException e) {
			Reporting.logReporter(Status.INFO, "DB Connection Closed Successfully!");
		}
		Reporting.printAndClearLogGroupStatements();
		Reporting.setNewGroupName("Close All Browser");
		WebDriverSteps.closeTheBrowser();
		Reporting.printAndClearLogGroupStatements();
	}

}