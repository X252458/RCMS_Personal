package com.telus.rcms.tests.UpdateDeviceSerialNo;

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
 * Testcase Name : TC01 Activate Telus Subscriber with DB+DF+BIB+ACB+TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE

 *
 */
public class TC02_Telus_DB_DF_BIB_RCB_UpdateDeviceSerialNo
		extends BaseTest {

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

	/**
	 * @param iTestContext
	 */
	@BeforeTest(alwaysRun = true)
	public void BeforeMethod(ITestContext iTestContext) {

		testCaseName = this.getClass().getName();
		scriptName = GenericUtils.getTestCaseName(testCaseName);
		testCaseDescription = "The purpose of this test case is to verify \"" + scriptName + "\" workflow";
		environment = SystemProperties.EXECUTION_ENVIRONMENT;
		
		requestPayloadFilePath = System.getProperty("user.dir") +  "\\src\\test\\resources\\testSpecs\\RCMS\\Activation\\TC01_Telus_DB_DF_BIB_ACB_TIA_TIP_AF.json";
	}

	@Test(groups = { "Loyalty_Agreement","updateDeviceSerialNo","TC02_Telus_DB_DF_BIB_RCB_UpdateDeviceSerialNo","CompleteRegressionSuite" })

	public void testMethod_updateDeviceSerialNo(ITestContext iTestContext) throws Exception {

		 parentTest = ExtentTestManager.getTest();
		 parentTest.assignCategory("UPDATE_DEVICE_SERIAL_NO");
		 
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

		/*** Test Case - Activation 1 ***/

		Reporting.setNewGroupName("ACCESS TOKEN GENERATION");
		String accessToken = APIUtils.getAccessToken(environment,"rewardService");
		String rewardServiceaccessToken = accessToken;
		Reporting.logReporter(Status.INFO, "ACCESS_TOKEN: " + accessToken);
		Reporting.printAndClearLogGroupStatements();

		// Activation API Call

		Reporting.setNewGroupName("ACTIVATION SERVICE API CALL - TC01");
		String apiEnv = GenericUtils.getAPIEnvironment(environment);
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		accountID = GenericUtils.getUniqueAccountID(apiEnv);
		subscriptionID = GenericUtils.getUniqueSubscriptionID(apiEnv);
		subscriberNum = GenericUtils.getUniqueSubscriberNumber(apiEnv);
		startDate = JSONUtils.getGMTStartDate();

		System.setProperty("karate.auth_token", accessToken);
		System.setProperty("karate.auth_token_reward", rewardServiceaccessToken);
		System.setProperty("karate.accID", accountID);
		System.setProperty("karate.subID", subscriptionID);
		System.setProperty("karate.subNum", subscriberNum);
		System.setProperty("karate.startDate", startDate);
		System.setProperty("karate.apiEnv", apiEnv);

		
		Map<String, Object> apiOperation = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/Activation/Others/activationTC8.feature","tc08ActivateTelusSubwithDFStatus","200");
		Reporting.logReporter(Status.INFO,
				"API Operation status: " + apiOperation.get("tc08ActivateTelusSubwithDFStatus"));
		Reporting.logReporter(Status.INFO,
				"API Operation Request: " + apiOperation.get("tc08ActivateTelusSubwithDFRequest"));
		Reporting.printAndClearLogGroupStatements();

		// Renewal API Call

		Reporting.setNewGroupName("RENEWAL SERVICE API CALL - DB+AF Payment BIB_TELUS_PENDING");
		Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");

		Map<String, Object> apiOperation2 = GenericUtils.featureFileFailLoop_status(environment,
				"classpath:tests/RCMS/Renewal/getAccTerminationPenalty/renewalTC1.feature","apiStatus","200");
		Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation2.get("apiStatus"));
		Reporting.logReporter(Status.INFO, "API Operation Request: " + apiOperation2.get("apiRequest"));

		
		
		
		String jsonString = String.valueOf(apiOperation2.get("apiRequest")).replace("=", ":");
		Reporting.printAndClearLogGroupStatements();
		
		GenericUtils.valuesFromDBRenewal(jsonString);
		
		Reporting.setNewGroupName("UPDATE DEVICE SERIALNUMBER API CALL - TC02");
		Map<String, Object> apiOperation1 = GenericUtils.featureFileFailLoop_status(environment,
				 "classpath:tests/RCMS/UpdateDeviceSerialNo/UpdateDeviceSerialNoTC2.feature","apiStatus","200");
				 Reporting.logReporter(Status.INFO, "API Operation status: " +
				 apiOperation1.get("apiStatus"));
				 Reporting.logReporter(Status.INFO, "API Operation Request: " +
				 apiOperation1.get("apiResponse"));
				 
				 String updateSerialNojsonString = String.valueOf(apiOperation1.get("apiResponse")).replace("=", ":");
				 
				Reporting.printAndClearLogGroupStatements();
		
		

		/*** DB VALIDATION ***/
		Reporting.setNewGroupName("DB VERIFICATION - TC02");
		payloadAndDbCheck(updateSerialNojsonString);
		Reporting.printAndClearLogGroupStatements();

	}
	

	public void payloadAndDbCheck(String UpdateSerialNojson) throws SQLException, IOException {

		DBUtils.callDBConnect();
		
		/**
		 * DB Verification Steps
		 */

		
		Reporting.logReporter(Status.INFO, "Pretty Payload: " + UpdateSerialNojson);

		// Declaring variable from payload
		GenericUtils.serialNoUpdateCheck(UpdateSerialNojson);



		Reporting.logReporter(Status.INFO, "--------------------DB Validation Completed--------------------");

	}

	/**
	 * Close DB Connection
	 */
	
	@AfterMethod(alwaysRun = true)
	public void afterTest() {
		Reporting.setNewGroupName("Close All Connection");
		try {
			DBUtils.dbDisConnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Reporting.logReporter(Status.INFO, "DB Connection Closed Successfully!");
		Reporting.printAndClearLogGroupStatements();
	}
}