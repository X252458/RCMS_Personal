package com.telus.rcms.tests.AdjustRewardBalance;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.telus.api.test.utils.APIJava;
import com.telus.rcms.utils.APIUtils;
import com.telus.rcms.utils.DBUtils;
import com.telus.rcms.utils.GenericUtils;
import com.telus.rcms.utils.JSONUtils;
import com.test.reporting.ExtentTestManager;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseTest;
import com.test.ui.actions.WebDriverSteps;
import com.test.utils.SystemProperties;

public class TC08_Koodo_with_TAB_HWS_AdRewardBalance_RETAIL  extends BaseTest {

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
	String jsonStrResponse = null;

	ExtentTest parentTest = null;


	@BeforeTest(alwaysRun = true)
	public void BeforeMethod(ITestContext iTestContext) {

		testCaseName = this.getClass().getName();
		scriptName = GenericUtils.getTestCaseName(testCaseName);
		testCaseDescription = "The purpose of this test case is to verify \"" + scriptName + "\" workflow";
		requestPayloadFilePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testSpecs\\RCMS\\Activation\\" + scriptName + ".json";
		environment = SystemProperties.EXECUTION_ENVIRONMENT;
	}

	@Test(groups = {"Loyalty_Management","Adjust_Reward_Balance","TC08_Koodo_with_TAB_HWS_AdRewardBalance_TAB_HWS_RETAIL","CompleteRegressionSuite" })

	public void testMethod_Activation(ITestContext iTestContext) throws Exception {

		parentTest = ExtentTestManager.getTest();
		parentTest.assignCategory("ADJUST_REWARD_BALANCE");

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

		/*** Test Case - Activation 2 ***/

		Reporting.setNewGroupName("ACCESS TOKEN GENERATION");
		String accessToken = APIUtils.getAccessToken(environment, "rewardService");
		String managementAccessToken = APIUtils.getAccessToken(environment,"management");
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN: " + accessToken);
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN: " + managementAccessToken);
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
		System.setProperty("karate.auth_token_management", managementAccessToken);
		System.setProperty("karate.accID", accountID);
		System.setProperty("karate.subID", subscriptionID);
		System.setProperty("karate.subNum", subscriberNum);
		System.setProperty("karate.startDate", startDate);
		System.setProperty("karate.apiEnv", apiEnv);

		Map<String, Object> apiOperation = GenericUtils.featureFileFailLoop(environment,"classpath:tests/RCMS/activation/activationTC2.feature","tc02ActivateKoodoTAB_HWSStatus" );
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation.get("tc02ActivateKoodoTAB_HWSRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation.get("tc02ActivateKoodoTAB_HWSStatus"));
		Reporting.printAndClearLogGroupStatements();
		
		//ADJUST REWARD BALANCE API CALL for TAB
		
		Reporting.setNewGroupName("ADJUST REWARD BALANCE API CALL - TAB_RETAIL ");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		
		System.setProperty("karate.itemType","TAB");
		
		Map<String, Object> apiOperation2 = GenericUtils.featureFileFailLoop(environment,"classpath:tests/RCMS/AdjustRewardBalance/AdjustRewardBalanceTC8.feature","apiDetailsStatus" );
		Reporting.logReporter(Status.INFO,
				"API Operation Response: " + apiOperation2.get("apiDetailsResponse"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation2.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation2.get("apiDetailsStatus"));

		jsonString = String.valueOf(apiOperation2.get("apiDetailsRequest")).replace("=", ":");
		jsonStrResponse = String.valueOf(apiOperation2.get("apiDetailsResponse")).replace("=", ":");

		/*** DB VALIDATION FOR TAB ***/
		Reporting.setNewGroupName("DB VERIFICATION FOR TAB - TC08");
		payloadAndDbCheck_TAB();
		Reporting.printAndClearLogGroupStatements();
		
		//ADJUST REWARD BALANCE API CALL for HWS
		
		Reporting.setNewGroupName("ADJUST REWARD BALANCE API CALL - HWS_RETAIL ");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		System.setProperty("karate.itemType","HWS");
		Map<String, Object> apiOperation3 = APIJava.runKarateFeature(environment,
					"classpath:tests/RCMS/AdjustRewardBalance/AdjustRewardBalanceTC8.feature");
		Reporting.logReporter(Status.INFO,
					"API Operation Response: " + apiOperation3.get("apiDetailsResponse"));
		Reporting.logReporter(Status.INFO,
					"API Operation Request: " + apiOperation3.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO,
					"API Operation status: " + apiOperation3.get("apiDetailsStatus"));

		jsonString = String.valueOf(apiOperation3.get("apiDetailsRequest")).replace("=", ":");
		jsonStrResponse = String.valueOf(apiOperation3.get("apiDetailsResponse")).replace("=", ":");

		/*** DB VALIDATION FOR TAB ***/
		Reporting.setNewGroupName("DB VERIFICATION FOR HWS - TC08");
		payloadAndDbCheck_HWS();
		Reporting.printAndClearLogGroupStatements();


	}

	public void payloadAndDbCheck_TAB() throws SQLException, IOException {

		DBUtils.callDBConnect();

		/**
		 * DB Verification Steps
		 */
		// Get Remaining balance from response
		String balanceAmount = String.valueOf(
				JSONUtils.checkValue(jsonStrResponse, "$.quantity.balance"));
		Reporting.logReporter(Status.INFO, "Pretty Payload: " + jsonString);
				

		// Declaring variable from payload
		GenericUtils.responseDBCheckAdjustRewardBalance(jsonString, balanceAmount, 13);//TAB

		Reporting.logReporter(Status.INFO, "--------------------DB Validation Completed For TAB--------------------");
	}
	
	
	
	public void payloadAndDbCheck_HWS() throws SQLException, IOException {

		DBUtils.callDBConnect();

		/**
		 * DB Verification Steps
		 */
		// Get Remaining balance from response
		String balanceAmount = String.valueOf(
				JSONUtils.checkValue(jsonStrResponse, "$.quantity.balance"));
		Reporting.logReporter(Status.INFO, "Pretty Payload: " + jsonString);
				

		// Declaring variable from payload
		GenericUtils.responseDBCheckAdjustRewardBalance(jsonString, balanceAmount, 14);//HWS

		Reporting.logReporter(Status.INFO, "--------------------DB Validation Completed For HWS--------------------");
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