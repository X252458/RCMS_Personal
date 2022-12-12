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

import junit.framework.Assert;

public class TC15_Telus_DB_DF_BIB_ACB_AdRewardBalance_BIB_CORRECTION  extends BaseTest {

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
	String jsonStrResponse= null;

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

	@Test(groups = {"Loyalty_Management","Adjust_Reward_Balance","TC15_Telus_DB_DF_BIB_ACB_AdRewardBalance_BIB_CORRECTION","CompleteRegressionSuite"})

	public void testMethod_Termination(ITestContext iTestContext) throws Exception {

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

		/*** Test Case - Activation - AccessoryFinance ***/

		Reporting.setNewGroupName("ACCESS TOKEN GENERATION");
		String rewardServiceaccessToken = APIUtils.getAccessToken(environment, "rewardService");
		String violationaccessToken = APIUtils.getAccessToken(environment, "violation");
		String managementAccessToken = APIUtils.getAccessToken(environment,"management");
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN FOR REWARD SERVICE: " + rewardServiceaccessToken);
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN FOR VIOLATION SERVICE: " + violationaccessToken);
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
		System.setProperty("karate.auth_token", rewardServiceaccessToken);
		System.setProperty("karate.auth_token_reward", rewardServiceaccessToken);
		System.setProperty("karate.auth_token_violation", violationaccessToken);
		System.setProperty("karate.auth_token_management", managementAccessToken);
		System.setProperty("karate.accID", accountID);
		System.setProperty("karate.subID", subscriptionID);
		System.setProperty("karate.subNum", subscriberNum);
		System.setProperty("karate.startDate", startDate);
		System.setProperty("karate.apiEnv", apiEnv);

		Map<String, Object> apiOperation1 = GenericUtils.featureFileFailLoop_status(environment,"classpath:tests/RCMS/Activation/Others/activationTC7.feature","tc07ActivateTelusSubwithDF_DB_BIB_ACBStatus","200");
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation1.get("tc07ActivateTelusSubwithDF_DB_BIB_ACBStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation1.get("tc07ActivateTelusSubwithDF_DB_BIB_ACBRequest"));
		Reporting.printAndClearLogGroupStatements();
		
		// Adjust Reward Balance API Call to Make BIB balance 0.
	
		Reporting.setNewGroupName("ADJUST REWARD BALANCE API CALL - DB+DF+BIB+ACB_Adjust_BIB");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		
		System.setProperty("karate.itemType","BIB");

		Map<String, Object> apiOperation2 = GenericUtils.featureFileFailLoop_status(environment,"classpath:tests/RCMS/AdjustRewardBalance/AdjustRewardBalanceTC15.feature","apiDetailsStatus","200");
		Reporting.logReporter(Status.INFO,
				"API Operation Response: " + apiOperation2.get("apiDetailsResponse"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation2.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation2.get("apiDetailsStatus"));
		
		String apiDetailsResponse=String.valueOf(apiOperation2.get("apiDetailsResponse"));
		String AdjBalStatus_Api1= String.valueOf(apiOperation2.get("apiDetailsStatus"));
		
		if (AdjBalStatus_Api1.equals("200")) {
			Reporting.printAndClearLogGroupStatements();
		} else {
			Assert.fail(apiDetailsResponse);
		}
		// Adjust Reward Balance API Call to Make BIB balance 0 to -ve.
		
		Reporting.setNewGroupName("ADJUST REWARD BALANCE API CALL - DB+DF+BIB+ACB_BIB_Correction");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		System.setProperty("karate.itemType","BIB");
		Map<String, Object> apiOperation3 = GenericUtils.featureFileFailLoop_status(environment,"classpath:tests/RCMS/AdjustRewardBalance/AdjustRewardBalanceTC15.feature","apiDetailsStatus","500" );
		Reporting.logReporter(Status.INFO,
				"API Operation Response: " + apiOperation3.get("apiDetailsResponse"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation3.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation3.get("apiDetailsStatus"));
		
		String apiDetailsResponse1=String.valueOf(apiOperation3.get("apiDetailsResponse"));
		String AdjBalStatus_Api2= String.valueOf(apiOperation3.get("apiDetailsStatus"));
		
		if (AdjBalStatus_Api2.equals("500")) {
			
			Reporting.logReporter(Status.INFO, "Expected http response is 500 because" 
					+apiOperation3.get("apiDetailsResponse"));
			Reporting.printAndClearLogGroupStatements();
		}else {
			Assert.fail(apiDetailsResponse1);
		}
		
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
