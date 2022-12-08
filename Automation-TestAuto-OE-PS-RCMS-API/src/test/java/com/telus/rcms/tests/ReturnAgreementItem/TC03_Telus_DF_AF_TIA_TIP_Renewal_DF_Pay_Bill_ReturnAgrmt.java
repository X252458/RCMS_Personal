package com.telus.rcms.tests.ReturnAgreementItem;

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
 * Testcase Name : TC03 Perform returnAgreementItem for Telus Subscriber having
 * DF+AF+TIASSETCREDIT+TIPROMOCREDIT - Renewed to DF with Payment Method as BILL
 *
 * 
 */
public class TC03_Telus_DF_AF_TIA_TIP_Renewal_DF_Pay_Bill_ReturnAgrmt extends BaseTest {

	String testCaseName = null;
	String scriptName = null;
	String testCaseDescription = null;
	String requestPayloadFilePath = null;
	String jsonPathLibrary = null;

	String environment = null;

	String accountID = null;
	String subscriptionID = null;
	String subscriberNum = null;

	String startDate = null;
	String endDate = null;
	String jsonString = null;

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

	@Test(groups = { "Loyalty_Agreement", "ReturnAgreementItem",
			"TC03_Telus_DF_AF_TIA_TIP_Renewal_DF_Pay_Bill_ReturnAgrmt", "CompleteRegressionSuite" })

	public void testMethod_Exchange(ITestContext iTestContext) throws Exception {

		parentTest = ExtentTestManager.getTest();
		parentTest.assignCategory("RETURN_AGREEMENT_ITEM");

		Reporting.setNewGroupName("Automation Configurations / Environment Details & Data Setup");
		Reporting.logReporter(Status.INFO,
				"Automation Configuration - Environment Configured for Automation Execution [" + environment + "]");
		Reporting.printAndClearLogGroupStatements();

		/*** Test Case Details ***/
		Reporting.setNewGroupName("Test Case Details");
		Reporting.logReporter(Status.INFO, "Test Case Name : [" + scriptName + "]");
		Reporting.logReporter(Status.INFO, "Test Case Description : [" + testCaseDescription + "]");
		Reporting.printAndClearLogGroupStatements();

		Reporting.setNewGroupName("ACCESS TOKEN GENERATION");
		String rewardServiceaccessToken = APIUtils.getAccessToken(environment, "rewardService");
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN FOR REWARD SERVICE: " + rewardServiceaccessToken);
		Reporting.printAndClearLogGroupStatements();

		// Activation API Call

		Reporting.setNewGroupName("ACTIVATION SERVICE API CALL");
		String apiEnv = GenericUtils.getAPIEnvironment(environment);
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		accountID = GenericUtils.getUniqueAccountID(apiEnv);
		subscriptionID = GenericUtils.getUniqueSubscriptionID(apiEnv);
		subscriberNum = GenericUtils.getUniqueSubscriberNumber(apiEnv);
		startDate = JSONUtils.getGMTStartDate();
		endDate = JSONUtils.getNewGMTEndDate(24);
		System.setProperty("karate.auth_token", rewardServiceaccessToken);
		System.setProperty("karate.auth_token_reward", rewardServiceaccessToken);
		System.setProperty("karate.accID", accountID);
		System.setProperty("karate.subID", subscriptionID);
		System.setProperty("karate.subNum", subscriberNum);
		System.setProperty("karate.startDate", startDate);
		System.setProperty("karate.endDate", endDate);
		System.setProperty("karate.apiEnv", apiEnv);

		Map<String, Object> apiOperation1 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/Activation/Others/activationTC1.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: "
				+ apiOperation1.get("tc01ActivateTelusSubWithDF_AF_TIASSETCREDIT_TIPROMOCREDITStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: "
				+ apiOperation1.get("tc01ActivateTelusSubWithDF_AF_TIASSETCREDIT_TIPROMOCREDITRequest"));

		Reporting.printAndClearLogGroupStatements();

		// Renewal API Call

		Reporting.setNewGroupName("RENEWAL API CALL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation2 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/Renewal/notifySubscriptionRenewalTC01.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation2.get("tc01RenewalStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation2.get("tc01RenewalRequest"));

		Reporting.printAndClearLogGroupStatements();

		// ReturnAgreementItem API Call

		Reporting.setNewGroupName("RETURN AGREEMENT ITEM API CALL");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation3 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/ReturnAgreementItem/returnAgreementItemTC1.feature");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation3.get("apiRequest"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation3.get("apiStatus"));

		jsonString = String.valueOf(apiOperation3.get("apiRequest")).replace("=", ":");
		Reporting.printAndClearLogGroupStatements();

		/*** DB VALIDATION ***/
		Reporting.setNewGroupName("DB VERIFICATION");
		responseAndDbCheck();
		Reporting.printAndClearLogGroupStatements();

	}

	public void responseAndDbCheck() throws SQLException, IOException, InterruptedException {

		/**
		 * DB Verification Steps
		 */

		Reporting.logReporter(Status.INFO, "Pretty Payload: " + jsonString);
		ValidationUtils.returnAgrmtItemDBCheck(jsonString, 1);
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
