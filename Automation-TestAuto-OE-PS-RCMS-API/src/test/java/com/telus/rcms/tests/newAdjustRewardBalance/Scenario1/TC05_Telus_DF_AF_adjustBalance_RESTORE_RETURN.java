package com.telus.rcms.tests.newAdjustRewardBalance.Scenario1;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class TC05_Telus_DF_AF_adjustBalance_RESTORE_RETURN  extends BaseTest {

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
	
	ExtentTest parentTest = null;
	String jsonString = null;
	String jsonStrResponse = null;

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

	@Test(groups = { "Loyalty_Agreement","TC05_Telus_DF_AF_adjustBalance_RESTORE_RETURN","Adjust_Reward_Balance","CompleteRegressionSuite" })

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

		/*** Test Case - Activation 1 ***/

		Reporting.setNewGroupName("ACCESS TOKEN GENERATION");
		String rewardAccessToken = APIUtils.getAccessToken(environment,"rewardService");
		String managementAccessToken = APIUtils.getAccessToken(environment,"management");
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN: " + rewardAccessToken);
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
		
		System.setProperty("karate.auth_token", rewardAccessToken);
		System.setProperty("karate.auth_token_management", managementAccessToken);
		System.setProperty("karate.accID", accountID);
		System.setProperty("karate.subID", subscriptionID);
		System.setProperty("karate.subNum", subscriberNum);
		System.setProperty("karate.startDate", startDate);
		System.setProperty("karate.apiEnv", apiEnv);

		 Map<String, Object> apiOperation = APIJava.runKarateFeature(environment,
		 "classpath:tests/RCMS/NewAdjustBalance/activationTC1.feature");
		 Reporting.logReporter(Status.INFO, "API Operation status: " +
		 apiOperation.get("tc01ActivateTelusSubWithAllRequest"));
		 Reporting.logReporter(Status.INFO, "API Operation Request: " +
		 apiOperation.get("tc01ActivateTelusSubWithAllStatus"));
		Reporting.printAndClearLogGroupStatements();
		
		// Adjust Reward Balance API Call

		Reporting.setNewGroupName("ADJUST REWARD BALANCE API CALL - AF+DF_CANCELLATION_PAYMENT");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation2 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/NewAdjustBalance/Scenario1/AdjustBalanceTC1.feature");
		Reporting.logReporter(Status.INFO,
				"API Operation Response: " + apiOperation2.get("apiDetailsResponse"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation2.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation2.get("apiDetailsStatus"));
		Reporting.printAndClearLogGroupStatements();
		
		// RETURN AGREEMENT ITEM API CALL

		Reporting.setNewGroupName("RETURN AGREEMENT ITEM API CALL - AF+DF");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation3 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/NewAdjustBalance/Scenario1/ReturnAgreementItemTC2.feature");
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation3.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation3.get("apiDetailsStatus"));
				
		Reporting.logReporter(Status.INFO, "Expected http response is 500 because "
				+ apiOperation3.get("apiDetailsResponse"));
		Reporting.printAndClearLogGroupStatements();
		
		//Notify Subscription Return API CALL
		
		Reporting.setNewGroupName("Notify Subscription Return API CALL - AF+DF");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		System.setProperty("karate.accID", accountID);

		Map<String, Object> apiOperation4 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/NewAdjustBalance/Scenario1/NotifySubscriptionReturnTC3.feature");
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation4.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation4.get("apiDetailsStatus"));		
		Reporting.printAndClearLogGroupStatements();
		
		//Exchange  Agreement  Item API CALL
		
		Reporting.setNewGroupName("Exchange  Agreement  Item API CALL - AF+DF");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		System.setProperty("karate.accID", accountID);

		Map<String, Object> apiOperation5 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/NewAdjustBalance/Scenario1/ExchangeAgreementItemTC4.feature");
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation5.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation5.get("apiDetailsStatus"));		
		
		Reporting.logReporter(Status.INFO, "Expected http response is 500 because "
				+ apiOperation5.get("apiDetailsResponse"));
		Reporting.printAndClearLogGroupStatements();
		
		//Adjust balance Restore Return
		Reporting.setNewGroupName("Adjust Balance API CALL - AF+DF_RESTORE_RETURN");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation6 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/NewAdjustBalance/Scenario1/AdjustBalanceRestoreReturnTC5.feature");
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation6.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation Response: " + apiOperation6.get("apiDetailsResponse"));
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation6.get("apiDetailsStatus"));
		
		jsonString = String.valueOf(apiOperation6.get("apiDetailsRequest")).replace("=", ":");
		jsonStrResponse = String.valueOf(apiOperation6.get("apiDetailsResponse")).replace("=", ":");
		Reporting.printAndClearLogGroupStatements();
		/*** DB VALIDATION ***/
		Reporting.setNewGroupName("DB VERIFICATION - TC05");
		//payloadAndDbCheck();
		Reporting.printAndClearLogGroupStatements();
	}
	
	public void payloadAndDbCheck() throws SQLException, IOException {

		DBUtils.callDBConnect();
		
		/**
		 * DB Verification Steps
		 */

		String balanceAmount = String.valueOf(
				JSONUtils.checkValue(jsonStrResponse, "$.quantity.balance"));
		Reporting.logReporter(Status.INFO, "Pretty Payload: " + jsonString);

		// Declaring variable from payload

		GenericUtils.responseDBCheckAdjustRewardBalance(jsonString, balanceAmount,17);
		
		
		Reporting.logReporter(Status.INFO, "--------------------DB Validation Completed--------------------");

	}


	/**
	 * Close DB Connection
	 */
	 @AfterMethod(alwaysRun = true) public void afterTest() {
		  Reporting.setNewGroupName("Close DB Connection"); try {
		  DBUtils.dbDisConnect(); } catch (SQLException e) {
		  Reporting.logReporter(Status.INFO, "DB Connection Closed Successfully!"); }
		  Reporting.printAndClearLogGroupStatements(); }
	/**
	 * Close any opened browser instances
	 */
	//@AfterMethod(alwaysRun = true)
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