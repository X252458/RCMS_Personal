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

public class TC07_Telus_DB_DF_BIB_Renewal_DB_Pay_BIB_TELUS_PENDING_LOG_Event extends BaseTest {

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

	@Test(groups = {"Loyalty_Management", "logEvent", "TC07_Telus_DB_DF_BIB_Renewal_DB_Pay_BIB_TELUS_PENDING_LOG_Event",
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
		
		//Added loop to run the api with both the reason to validate DUE DATE
		for (int i=1;i<=3;i++) {
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

		Map<String, Object> apiOperation1 = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/Activation/Others/activationTC6.feature","tc06ActivateTelusSubwithDF_DB_BIBStatus","200");
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation1.get("tc06ActivateTelusSubwithDF_DB_BIBStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation1.get("tc06ActivateTelusSubwithDF_DB_BIBRequest"));

		Reporting.printAndClearLogGroupStatements();

		// Renewal API Call

		Reporting.setNewGroupName("RENEWAL SERVICE API CALL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation2 = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/Renewal/notifySubscriptionRenewalTC04.feature","tc04RenewalStatus","200");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation2.get("tc04RenewalStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation2.get("tc04RenewalRequest"));
		paymentMech="BIB_TELUS_PENDING";
		Reporting.printAndClearLogGroupStatements();

		// LogEvent API Call RETURN COMPLETE
		if (i==1) {
			Reporting.setNewGroupName("LOG EVENT RETURN COMPLETE API CALL");
		}else {
			Reporting.setNewGroupName("LOG EVENT CHARGE COMPLETE API CALL");
		}
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation3 = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/LogEvent/LogEventTC"+i+".feature","apiDetailsStatus","201");
		Reporting.logReporter(Status.INFO, 
				"API Operation status: " + apiOperation3.get("apiDetailsStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation3.get("apiDetailsRequest"));
		
		// LogEvent API Call UPDATE DUEDATE
		Reporting.setNewGroupName("LOG EVENT UPDATE DUEDATE API CALL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation4 = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/LogEvent/LogEventTC7.feature","apiDetailsStatus","500");
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation4.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO, 
				"API Operation status: " + apiOperation4.get("apiDetailsStatus"));
		
		Reporting.logReporter(Status.INFO, "Expected http response is 500 because after RETURN/CHARGE COMPLETE "
				+ "we cannot update the DUEDATE." );
				
		Reporting.printAndClearLogGroupStatements();
		
		i=i+1;
		
	}
		}

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
