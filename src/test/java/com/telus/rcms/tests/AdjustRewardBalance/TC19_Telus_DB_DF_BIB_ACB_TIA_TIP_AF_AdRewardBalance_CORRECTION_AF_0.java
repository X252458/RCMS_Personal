package com.telus.rcms.tests.AdjustRewardBalance;

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

public class TC19_Telus_DB_DF_BIB_ACB_TIA_TIP_AF_AdRewardBalance_CORRECTION_AF_0 extends BaseTest {

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

	@Test(groups = { "Loyalty_Management","Adjust_Reward_Balance","TC19_Telus_DB_DF_BIB_ACB_TIA_TIP_AF_AdRewardBalance_CORRECTION_AF_0","CompleteRegressionSuite" })

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

		Map<String, Object> apiOperation = GenericUtils.featureFileFailLoop_status(environment,"classpath:tests/RCMS/Activation/Others/activationTC20.feature","tc01ActivateTelusSubWithAllStatus","200");
		 Reporting.logReporter(Status.INFO, "API Operation status: " +
		 apiOperation.get("tc01ActivateTelusSubWithAllRequest"));
		 Reporting.logReporter(Status.INFO, "API Operation Request: " +
		 apiOperation.get("tc01ActivateTelusSubWithAllStatus"));
		Reporting.printAndClearLogGroupStatements();
		
		// Adjust Reward Balance API Call to Make AF 0

		Reporting.setNewGroupName("ADJUST REWARD BALANCE API CALL - DB+DF+BIB+ACB+TIAssetCredit+TIPromoCredit+AccessoryFinance");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		
		System.setProperty("karate.itemType","ACCESSORYFINANCE");

		Map<String, Object> apiOperation2 = GenericUtils.featureFileFailLoop_status(environment,"classpath:tests/RCMS/AdjustRewardBalance/AdjustRewardBalanceTC1.feature","apiDetailsStatus","200");
		Reporting.logReporter(Status.INFO,
				"API Operation Response: " + apiOperation2.get("apiDetailsResponse"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation2.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation2.get("apiDetailsStatus"));

		// Adjust Reward Balance API Call TC19

		Reporting.setNewGroupName("ADJUST REWARD BALANCE API CALL - DB+DF+BIB+ACB+TIAssetCredit+TIPromoCredit+AccessoryFinance_CORRECTION");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation3 = GenericUtils.featureFileFailLoop_status(environment,"classpath:tests/RCMS/AdjustRewardBalance/AdjustRewardBalanceTC19.feature","apiDetailsStatus","500" );
		Reporting.logReporter(Status.INFO,
				"API Operation Response: " + apiOperation3.get("apiDetailsResponse"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation3.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation3.get("apiDetailsStatus"));
		Reporting.logReporter(Status.INFO, "Expected http response is 500 because"
				+ "  rewardAccount balance is 0, adjust balance not allowed for typeCode:ACCESSORYFINANCE "
				+ "daoTypeCode:ACCESSORYFINANCE "
				+ "optOutProgramType:ACCESSORYFINANCE "
				+ "isValidRewardType:true "
				+ "chargeType:AFDB"
				+ "chargeType:AFHB"
				+ "chargeType:AFECF");
		Reporting.printAndClearLogGroupStatements();

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