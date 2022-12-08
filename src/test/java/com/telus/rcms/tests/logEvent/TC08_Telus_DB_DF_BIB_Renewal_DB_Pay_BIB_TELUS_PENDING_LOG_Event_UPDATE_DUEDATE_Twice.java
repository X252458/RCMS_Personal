package com.telus.rcms.tests.logEvent;

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

public class TC08_Telus_DB_DF_BIB_Renewal_DB_Pay_BIB_TELUS_PENDING_LOG_Event_UPDATE_DUEDATE_Twice extends BaseTest {

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
	String jsonString1 = null;
	String startDateUpdate=null;
	
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

	@Test(groups = {"Loyalty_Management", "logEvent", "TC08_Telus_DB_DF_BIB_Renewal_DB_Pay_BIB_TELUS_PENDING_LOG_Event_UPDATE_DUEDATE_Twice",
			"CompleteRegressionSuite" })

	public void testMethod_LogEvent(ITestContext iTestContext) throws Exception {

		parentTest = ExtentTestManager.getTest();
		parentTest.assignCategory("LOG_EVENT");

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
		System.setProperty("karate.startDateUpdate", startDateUpdate);
		System.setProperty("karate.apiEnv", apiEnv);

		Map<String, Object> apiOperation1 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/Activation/Others/activationTC6.feature");
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation1.get("tc06ActivateTelusSubwithDF_DB_BIBStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation1.get("tc06ActivateTelusSubwithDF_DB_BIBRequest"));

		Reporting.printAndClearLogGroupStatements();

		// Renewal API Call

		Reporting.setNewGroupName("RENEWAL SERVICE API CALL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation2 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/Renewal/notifySubscriptionRenewalTC04.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation2.get("tc04RenewalStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation2.get("tc04RenewalRequest"));
		paymentMech="BIB_TELUS_PENDING";
		Reporting.printAndClearLogGroupStatements();

		//Added for loop to call twice UPDATE DUEDATE API

		Reporting.setNewGroupName("LOG EVENT UPDATE DUEDATE API CALL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation3 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/LogEvent/LogEventTC5.feature");
		Reporting.logReporter(Status.INFO, 
				"API Operation status: " + apiOperation3.get("apiDetailsStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation3.get("apiDetailsRequest"));
		jsonString = String.valueOf(apiOperation3.get("apiDetailsRequest")).replace("=", ":");
		
		/*** DB VALIDATION ***/
		Reporting.setNewGroupName("DB VERIFICATION SINGLE UPDATE");
		responseAndDbCheckFirstUpdate();
		
		//Added for loop to call twice UPDATE DUEDATE API
		Reporting.setNewGroupName("LOG EVENT UPDATE DUEDATE API CALL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		Map<String, Object> apiOperation4 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/LogEvent/LogEventTC8.feature");
		Reporting.logReporter(Status.INFO, 
				"API Operation status: " + apiOperation4.get("apiDetailsStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation4.get("apiDetailsRequest"));
		jsonString1 = String.valueOf(apiOperation4.get("apiDetailsRequest")).replace("=", ":");
		Reporting.printAndClearLogGroupStatements();
		
		/*** DB VALIDATION ***/
		Reporting.setNewGroupName("DB VERIFICATION TWICE UPDATE");
		responseAndDbCheckUpdatedTwice();
		Reporting.printAndClearLogGroupStatements();
		
		}

	
	public void responseAndDbCheckFirstUpdate() throws SQLException, IOException, InterruptedException {

		 DBUtils.callDBConnect();

		/**
		 * DB Verification Steps
		 */

		Reporting.logReporter(Status.INFO, "Pretty Payload: " + jsonString);

		// Declaring variable from payload

		
		GenericUtils.responseDBCheckLogEvent(jsonString, paymentMech);
		Reporting.logReporter(Status.INFO, "--------------------DB Validation Completed for single update--------------------");
	
	}
	public void responseAndDbCheckUpdatedTwice() throws SQLException, IOException, InterruptedException {

		 DBUtils.callDBConnect();

		/**
		 * DB Verification Steps
		 */
			
		Reporting.logReporter(Status.INFO, "Pretty Payload: " + jsonString1);

		// Declaring variable from payload

		
		GenericUtils.responseDBCheckLogEvent(jsonString1, paymentMech);
	
		
	
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
