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
 * Testcase name : TC09 Call getTerminationPenalty for Telus Subscriber who did
 * Exchange after Renewal for a customer having DF to DB + TIASSETCREDIT +AF and
 * perform exchange to DF + DB + TIPROMOCREDIT
 *
 * 
 */
public class TC05_Telus_DF_Renewal_DB_AF_TIA_Exchange_DF_DB_TIP extends BaseTest {

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
	
	String jsonResponse =null;
	String type=null;

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

	@Test(groups = {"Loyalty_Management","ListEarnedTransaction", "TC05_Telus_DF_Renewal_DB_AF_TIA_Exchange_DF_DB_TIP",
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
		Reporting.printAndClearLogGroupStatements();

		/**
		 * API Call Steps
		 */

		/*** Test Case - Activation - AccessoryFinance ***/

		Reporting.setNewGroupName("ACCESS TOKEN GENERATION");
		String rewardServiceaccessToken = APIUtils.getAccessToken(environment, "rewardService");
		String violationaccessToken = APIUtils.getAccessToken(environment, "violation");
		String accessTokenMgmt = APIUtils.getAccessToken(environment, "management");
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN FOR REWARD SERVICE: " + rewardServiceaccessToken);
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN FOR VIOLATION SERVICE: " + violationaccessToken);
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN FOR MANAGEMENT SERVICE: " + accessTokenMgmt);
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
		System.setProperty("karate.auth_token_Mgmt", accessTokenMgmt);
		System.setProperty("karate.accID", accountID);
		System.setProperty("karate.subID", subscriptionID);
		System.setProperty("karate.subNum", subscriberNum);
		System.setProperty("karate.startDate", startDate);
		System.setProperty("karate.apiEnv", apiEnv);

		Map<String, Object> apiOperation1 = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/Activation/Others/activationTC8.feature","tc08ActivateTelusSubwithDFStatus","200");
		Reporting.logReporter(Status.INFO, "API Operation status: "
				+ apiOperation1.get("tc08ActivateTelusSubwithDFStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: "
				+ apiOperation1.get("tc08ActivateTelusSubwithDFRequest"));

		Reporting.printAndClearLogGroupStatements();

		//Renewal API call
		Reporting.setNewGroupName("RENEWAL SERVICE API CALL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation2 = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/Renewal/getTerminationPenalty/renewalTC1.feature","apiStatus","200");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation2.get("apiStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation2.get("apiRequest"));

		Reporting.printAndClearLogGroupStatements();
		
		// Exchange API Call

		Reporting.setNewGroupName("EXCHANGE SERVICE API CALL - DB+AF Payment BIB_TELUS_PENDING");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation3 = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/Exchange/GetTerminationPenalty/exchangeTC2.feature","apiStatus","200");
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation3.get("apiStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation3.get("apiRequest"));
		paymentMech="NA";
		Reporting.printAndClearLogGroupStatements();

		Reporting.setNewGroupName("LIST EARNED TRANSACTION SERVICE API CALL");
		type = "FINANCE";
		System.setProperty("karate.type", type);
		Map<String, Object> apiOperation4 = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/ListEarnedTransaction/ListEarnedTransactionTC01.feature","apiStatus","200");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation4.get("apiStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation4.get("apiResponse"));
		Reporting.printAndClearLogGroupStatements();
		
		jsonResponse = String.valueOf(apiOperation4.get("apiResponse")).replace("=", ":");
		
		Reporting.logReporter(Status.INFO, " Preety Response " + jsonResponse);
		/*** DB VALIDATION ***/
		Reporting.setNewGroupName("DB VERIFICATION - TC05");
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