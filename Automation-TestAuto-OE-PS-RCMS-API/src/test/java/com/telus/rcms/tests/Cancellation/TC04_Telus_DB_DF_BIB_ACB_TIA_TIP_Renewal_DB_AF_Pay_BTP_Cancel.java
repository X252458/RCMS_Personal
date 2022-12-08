package com.telus.rcms.tests.Cancellation;

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
 * Testcase name : TC05 Customer having DB+DF+BIB - Renewed to DB with Payment
 * Method as TRADE_IN_PENDING(TRADE_IN_PENDING and BIB_TELUS_PENDING can only be
 * used to pay off BIB.)
 *
 * 
 */
public class TC04_Telus_DB_DF_BIB_ACB_TIA_TIP_Renewal_DB_AF_Pay_BTP_Cancel extends BaseTest {

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
	String jsonString_activation = null;
	String jsonString_earlyPenalty = null;

	ExtentTest parentTest = null;

	@BeforeTest(alwaysRun = true)
	public void BeforeMethod(ITestContext iTestContext) {

		testCaseName = this.getClass().getName();
		scriptName = GenericUtils.getTestCaseName(testCaseName);
		testCaseDescription = "The purpose of this test case is to verify \"" + scriptName + "\" workflow";
		environment = SystemProperties.EXECUTION_ENVIRONMENT;

		requestPayloadFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testSpecs\\RCMS\\Cancel\\"+scriptName+".json";
	}

	@Test(groups = { "Loyalty_Agreement","Cancellation", "TC04_Telus_DB_DF_BIB_ACB_TIA_TIP_Renewal_DB_AF_Pay_BTP_Cancel",
			"CompleteRegressionSuite" })

	public void testMethod_Renewal(ITestContext iTestContext) throws Exception {

		parentTest = ExtentTestManager.getTest();
		parentTest.assignCategory("CANCELLATION");

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
		String accessToken = APIUtils.getAccessToken(environment, "rewardService");
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN: " + accessToken);
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
				"classpath:tests/RCMS/Activation/Others/activationTC2.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: "
				+ apiOperation1.get("tc01ActivateTelusSubWithDF_BIB_ACB_DB_TIASSETCREDIT_TIPROMOCREDITStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: "
				+ apiOperation1.get("tc01ActivateTelusSubWithDF_BIB_ACB_DB_TIASSETCREDIT_TIPROMOCREDITRequest"));

		Reporting.printAndClearLogGroupStatements();

		// Renewal API Call

		Reporting.setNewGroupName("RENEWAL SERVICE API CALL - DB+AF Payment BIB_TELUS_PENDING");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation2 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/Renewal/notifySubscriptionRenewalTC02.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation2.get("tc02RenewalStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation2.get("tc02RenewalRequest"));

		Reporting.printAndClearLogGroupStatements();

		//Cancel API call
		
		Reporting.setNewGroupName("CANCELLATION SERVICE API CALL");
		Map<String, Object> apiOperation3 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/Cancel/cancelTC4.feature");
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation3.get("apiDetailsStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation3.get("apiDetailsRequest"));
		Reporting.printAndClearLogGroupStatements();
		
		
		/*** DB VALIDATION ***/
		Reporting.setNewGroupName("DB VERIFICATION -  TC04");
		payloadAndDbCheck();
		Reporting.printAndClearLogGroupStatements();

	}

	public void payloadAndDbCheck() throws SQLException, IOException, InterruptedException {

		DBUtils.callDBConnect();

		/**
		 * DB Verification Steps
		 */


		String jsonString = GenericUtils.readFileAsString(requestPayloadFilePath);
		jsonString = jsonString.replace("#(subID)", subscriptionID).replace("#(subNum)", subscriberNum)
				.replace("#(accID)", accountID).replace("#(startDate)", startDate);
		
		Reporting.logReporter(Status.INFO, "Pretty Payload: " + jsonString);
		
		GenericUtils.payloadnDBCheckCancel(jsonString,subscriptionID);
		GenericUtils.cancelDbCheck(jsonString,subscriptionID);

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