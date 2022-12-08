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
 * Test case Name : TC01 Telus Subscriber having
 * DF+AF+TIASSETCREDIT+TIPROMOCREDIT - Renewed to DF with Payment Method as BILL
 *
 */
public class TC04_Telus_DF_AF_TIA_TIP_Renewal_DF_Pay_BILL extends BaseTest {

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
	String jsonString_earlyPenalty = null;
	String jsonString_activation = null;
	
	String jsonResponse=null;
	String type=null;

	ExtentTest parentTest = null;

	@BeforeTest(alwaysRun = true)
	public void BeforeMethod(ITestContext iTestContext) {

		testCaseName = this.getClass().getName();
		scriptName = GenericUtils.getTestCaseName(testCaseName);
		testCaseDescription = "The purpose of this test case is to verify \"" + scriptName + "\" workflow";
		environment = SystemProperties.EXECUTION_ENVIRONMENT;

		requestPayloadFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testSpecs\\RCMS\\Renewal\\"
				+ scriptName + ".json";
	}

	@Test(groups = { "Loyalty_Management","ListEarnedTransaction","TC03_Telus_DF_AF_TIA_TIP_Renewal_DF_Pay_BILL","CompleteRegressionSuite" })

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

		Reporting.setNewGroupName("ACTIVATION SERVICE API CALL - DF_AF_TIA_TIP");
		String apiEnv = GenericUtils.getAPIEnvironment(environment);
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		accountID = GenericUtils.getUniqueAccountID(apiEnv);
		subscriptionID = GenericUtils.getUniqueSubscriptionID(apiEnv);
		subscriberNum = GenericUtils.getUniqueSubscriberNumber(apiEnv);
		startDate = JSONUtils.getGMTStartDate();
		System.setProperty("karate.auth_token_reward", rewardServiceaccessToken);
		System.setProperty("karate.auth_token_violation", violationaccessToken);
		System.setProperty("karate.auth_token_Mgmt", accessTokenMgmt);
		System.setProperty("karate.accID", accountID);
		System.setProperty("karate.subID", subscriptionID);
		System.setProperty("karate.subNum", subscriberNum);
		System.setProperty("karate.startDate", startDate);
		System.setProperty("karate.apiEnv", apiEnv);

		Map<String, Object> apiOperation1 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/Activation/Others/activationTC1.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: "
				+ apiOperation1.get("tc01ActivateTelusSubWithDF_AF_TIASSETCREDIT_TIPROMOCREDITStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: "
				+ apiOperation1.get("tc01ActivateTelusSubWithDF_AF_TIASSETCREDIT_TIPROMOCREDITRequest"));

		jsonString_activation = String
				.valueOf(apiOperation1.get("tc01ActivateTelusSubWithDF_AF_TIASSETCREDIT_TIPROMOCREDITRequest"))
				.replace("=", ":");

		Reporting.printAndClearLogGroupStatements();


		// Renewal API Call

		Reporting.setNewGroupName("RENEWAL SERVICE API CALL-DF WITH PAYMENT BILL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation2 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/Renewal/notifySubscriptionRenewalTC01.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation2.get("tc01RenewalStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation2.get("tc01RenewalRequest"));

		Reporting.printAndClearLogGroupStatements();

		Reporting.setNewGroupName("LIST EARNED TRANSACTION SERVICE API CALL");
		type = "FINANCE";
		System.setProperty("karate.type", type);
		Map<String, Object> apiOperation3 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/ListEarnedTransaction/ListEarnedTransactionTC01.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation3.get("apiStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation3.get("apiResponse"));
		Reporting.printAndClearLogGroupStatements();
		
		jsonResponse = String.valueOf(apiOperation3.get("apiResponse")).replace("=", ":");
		
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