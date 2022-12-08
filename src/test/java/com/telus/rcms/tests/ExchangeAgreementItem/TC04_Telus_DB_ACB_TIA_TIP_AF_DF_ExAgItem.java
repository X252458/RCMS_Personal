package com.telus.rcms.tests.ExchangeAgreementItem;

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

public class TC04_Telus_DB_ACB_TIA_TIP_AF_DF_ExAgItem extends BaseTest {

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
	String endDate= null;
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

	@Test(groups = { "ExchangeAgreementItem","TC04_Telus_DB_ACB_TIA_TIP_AF_DF_ExAgItem","CompleteRegressionSuite" ,"LoyaltyAgreementServices"})

	public void testMethod_Migration(ITestContext iTestContext) throws Exception {

		parentTest = ExtentTestManager.getTest();
		parentTest.assignCategory("EXCHANGE_AGREEMENT_ITEM");

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
		requestPayloadFilePath = System.getProperty("user.dir") +  "\\src\\test\\resources\\testSpecs\\RCMS\\ExchangeAgreementItem\\" + scriptName + ".json";
		String jsonString = GenericUtils.readFileAsString(requestPayloadFilePath);

        jsonString = jsonString.replace("#(subID)", subscriptionID).replace("#(subNum)", subscriberNum)
                .replace("#(accID)", accountID).replace("#(startDate)", "\""+startDate+"\"")
                .replace("#(endDate)", "\""+"tempDate"+"\"");
        
		endDate = JSONUtils.getGMTEndDate(jsonString);
		System.setProperty("karate.auth_token", rewardServiceaccessToken);
		System.setProperty("karate.auth_token_reward", rewardServiceaccessToken);
		System.setProperty("karate.auth_token_violation", violationaccessToken);
		System.setProperty("karate.auth_token_management", managementAccessToken);
		System.setProperty("karate.accID", accountID);
		System.setProperty("karate.subID", subscriptionID);
		System.setProperty("karate.subNum", subscriberNum);
		System.setProperty("karate.startDate", startDate);
		System.setProperty("karate.endDate", endDate);
		System.setProperty("karate.apiEnv", apiEnv);

		Map<String, Object> apiOperation1 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/Activation/Others/activationTC18.feature");
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation1.get("apiStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation1.get("apiRequest"));

		Reporting.printAndClearLogGroupStatements();

		// Adjust Reward Balance API Call

				Reporting.setNewGroupName("ADJUST REWARD BALANCE API CALL - DB+DF+BIB+ACB+TIAssetCredit+TIPromoCredit+AccessoryFinance");
				Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

				System.setProperty("karate.itemType","ACCESSORYFINANCE");
				
				Map<String, Object> apiOperation3 = APIJava.runKarateFeature(environment,
						"classpath:tests/RCMS/ExchangeAgreementItem/AdjustBalanceTC4a.feature");
				Reporting.logReporter(Status.INFO,
						"API Operation Request: " + apiOperation3.get("apiDetailsRequest"));
				Reporting.logReporter(Status.INFO,
						"API Operation status: " + apiOperation3.get("apiDetailsStatus"));
				Reporting.logReporter(Status.INFO,
						"API Operation Response: " + apiOperation3.get("apiDetailsResponse"));
				
				jsonString = String.valueOf(apiOperation3.get("apiDetailsRequest")).replace("=", ":");
				Reporting.printAndClearLogGroupStatements();
		/*** Test Case - Exchange Agreement Item ***/
				
		// ExchangeAgreementItem API Call

		Reporting.setNewGroupName("EXCHANGEAGREEMENTITEM SERVICE API CALL - AccessoryFinance");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation2 = APIJava.runKarateFeature(environment,
				"classpath:tests/RCMS/ExchangeAgreementItem/ExchangeAgreementItemTC4.feature");
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation2.get("apiDetailsRequest"));
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation2.get("apiDetailsStatus"));

		Reporting.printAndClearLogGroupStatements();

		jsonString = String.valueOf(apiOperation2.get("apiDetailsRequest")).replace("=", ":");
		Reporting.logReporter(Status.INFO, "Expected http response is 500 because Return not allowed as AF balance is 0.0 for Rewards");
		Reporting.printAndClearLogGroupStatements();

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
