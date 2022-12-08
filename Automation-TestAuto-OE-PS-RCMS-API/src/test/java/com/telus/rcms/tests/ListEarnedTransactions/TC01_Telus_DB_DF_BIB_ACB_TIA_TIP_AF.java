package com.telus.rcms.tests.ListEarnedTransactions;

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
 * Testcase Name : TC01 Activate Telus Subscriber with
 * DB+DF+BIB+ACB+TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE
 *
 * 
 */
public class TC01_Telus_DB_DF_BIB_ACB_TIA_TIP_AF extends BaseTest {

	String testCaseName = null;
	String scriptName = null;
	String testCaseDescription = null;
	String requestPayloadFilePath = null;
	String jsonPathLibrary = null;

	String environment = null;
	static String connectionString = null;

	static Statement statement = null;
	static ResultSet resultSet = null;
	static ResultSet rsAgreementItem = null;

	String accountID = null;
	String subscriptionID = null;
	String subscriberNum = null;
	String startDate = null;
	
	String type =null;
	String jsonResponse=null;

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

		requestPayloadFilePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testSpecs\\RCMS\\Activation\\" + scriptName + ".json";
	}

	@Test(groups = { "Loyalty_Management", "ListEarnedTransaction", "TC01_Telus_DB_DF_BIB_ACB_TIA_TIP_AF",
			"CompleteRegressionSuite" })

	public void testMethod_listEarnedTransaction(ITestContext iTestContext) throws Exception {

		parentTest = ExtentTestManager.getTest();
		parentTest.assignCategory("LIST_EARNED_TRANSACTION");

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

		/*** Test Case - Activation 1 ***/

		Reporting.setNewGroupName("ACCESS TOKEN GENERATION");
		String accessToken = APIUtils.getAccessToken(environment, "rewardService");
		String accessTokenMgmt = APIUtils.getAccessToken(environment, "management");
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN: " + accessToken);
		Reporting.printAndClearLogGroupStatements();

		// Activation API Call

		Reporting.setNewGroupName("ACTIVATION SERVICE API CALL");
		String apiEnv = GenericUtils.getAPIEnvironment(environment);
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		accountID = GenericUtils.getUniqueAccountID(apiEnv);
		subscriptionID = GenericUtils.getUniqueSubscriptionID(apiEnv);
		subscriberNum = GenericUtils.getUniqueSubscriberNumber(apiEnv);
		startDate = JSONUtils.getGMTStartDate();

		System.setProperty("karate.auth_token", accessToken);
		System.setProperty("karate.auth_token_Mgmt", accessTokenMgmt);
		System.setProperty("karate.accID", accountID);
		System.setProperty("karate.subID", subscriptionID);
		System.setProperty("karate.subNum", subscriberNum);
		System.setProperty("karate.startDate", startDate);
		System.setProperty("karate.apiEnv", apiEnv);

		Map<String, Object> apiOperation = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/activation/activationTC1.feature");
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation.get("tc01ActivateTelusSubWithAllStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation.get("tc01ActivateTelusSubWithAllRequest"));
		Reporting.printAndClearLogGroupStatements();

		Reporting.setNewGroupName("LIST EARNED TRANSACTION SERVICE API CALL");
		type = "ACCESSORYFINANCE";
		System.setProperty("karate.type", type);
		Map<String, Object> apiOperation1 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/ListEarnedTransaction/ListEarnedTransactionTC01.feature");
		Reporting.logReporter(Status.INFO, "API Operation URL: " + apiOperation1.get("apiUrl"));
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation1.get("apiStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation1.get("apiResponse"));
		Reporting.printAndClearLogGroupStatements();
		
		jsonResponse = String.valueOf(apiOperation1.get("apiResponse")).replace("=", ":");
		
		Reporting.logReporter(Status.INFO, " Preety Response " + jsonResponse);
		/*** DB VALIDATION ***/
		Reporting.setNewGroupName("DB VERIFICATION - TC01");
		payloadAndDbCheck();
		Reporting.printAndClearLogGroupStatements();

	}

	public void payloadAndDbCheck() throws SQLException, IOException {

		DBUtils.callDBConnect();

		/**
		 * DB Verification Steps
		 */

		// Declaring variable from payload

		ValidationUtils.responseDBCheckListEarnedTransaction(jsonResponse,type,subscriptionID);
		
		Reporting.logReporter(Status.INFO, "--------------------DB Validation Completed--------------------");

	}

	/**
	 * Close DB Connection
	 */
	
	@AfterMethod(alwaysRun = true)
	public void afterTest1() {
		Reporting.setNewGroupName("Close All Connection");
		try {
			DBUtils.dbDisConnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporting.logReporter(Status.INFO, "DB Connection Closed Successfully!");
		WebDriverSteps.closeTheBrowser();
		Reporting.printAndClearLogGroupStatements();
	}
}