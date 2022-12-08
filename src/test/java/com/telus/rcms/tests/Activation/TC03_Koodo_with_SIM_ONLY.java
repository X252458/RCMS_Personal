package com.telus.rcms.tests.Activation;

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
 * Tesctcase Name : TC03 Activate Koodo subscriber with SIM ONLY
 *
 */
public class TC03_Koodo_with_SIM_ONLY extends BaseTest {

	String testCaseName = null;
	String scriptName = null;
	String testCaseDescription = null;
	String requestPayloadFilePath = null;

	String environment = null;

	static String connectionString = null;

	static Statement statement = null;
	static ResultSet resultSet = null;
	
	String accountID = null;
	String subscriptionID = null;
	String subscriberNum = null;
	String agrmtId = null;
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
		requestPayloadFilePath = System.getProperty("user.dir") +  "\\src\\test\\resources\\testSpecs\\RCMS\\Activation\\" + scriptName + ".json";
		environment = SystemProperties.EXECUTION_ENVIRONMENT;
	}

	@Test(groups = {"Loyalty_Agreement", "Activation","TC03_Koodo_with_SIM_ONLY","CompleteRegressionSuite" })

	public void testMethod_activation(ITestContext iTestContext) throws Exception {

		parentTest = ExtentTestManager.getTest();
		parentTest.assignCategory("ACTIVATION");
		
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

		/*** Test Case - Activation 3 ***/
		
		  Reporting.setNewGroupName("ACCESS TOKEN GENERATION");
		  String accessToken = APIUtils.getAccessToken(environment,"rewardService"); 
		  Reporting.logReporter(Status.INFO,
		  "ACCESS_TOKEN: " + accessToken); Reporting.printAndClearLogGroupStatements();
		  
		  // Activation API Call

		  Reporting.setNewGroupName("ACTIVATION SERVICE API CALL - TC03"); String
		  apiEnv = GenericUtils.getAPIEnvironment(environment);
		  Reporting.logReporter(Status.INFO, "API Test Env is : [" + apiEnv + "]");
		  accountID = GenericUtils.getUniqueAccountID(apiEnv); 
		  subscriptionID = GenericUtils.getUniqueSubscriptionID(apiEnv); 
		  subscriberNum =  GenericUtils.getUniqueSubscriberNumber(apiEnv);
		  startDate = JSONUtils.getStartDate();
		  System.setProperty("karate.auth_token", accessToken);
		  System.setProperty("karate.accID", accountID);
		  System.setProperty("karate.subID", subscriptionID);
		  System.setProperty("karate.subNum", subscriberNum);
		  System.setProperty("karate.startDate", startDate);
		  System.setProperty("karate.apiEnv", apiEnv);
		  
		  Map<String, Object> apiOperation = APIJava.runKarateFeature(environment,
		  "classpath:tests/RCMS/activation/activationTC3.feature");
		  Reporting.logReporter(Status.INFO, "API Operation status: " +
		  apiOperation.get("tc03ActivateKoodoWithSimOnlyRequest"));
		  Reporting.logReporter(Status.INFO, "API Operation Request: " +
		  apiOperation.get("tc03ActivateKoodoWithSimOnlyStatus"));
		  
		  Reporting.printAndClearLogGroupStatements();

		/*** DB VALIDATION ***/
		Reporting.setNewGroupName("DB VERIFICATION - TC03");
		payloadAndDbCheck();
		Reporting.printAndClearLogGroupStatements();

	}

	public void payloadAndDbCheck() throws SQLException, IOException {

		DBUtils.callDBConnect();
		statement = DBUtils.Conn.createStatement();

		resultSet = statement.executeQuery("SELECT * FROM  CUSTOMER_SERVICE_AGREEMENT agrmt "
				+ "where agrmt.subscriber_no ='" + subscriberNum + "' and current_ind='Y' ");

		/**
		 * DB Verification Steps
		 */
	
		Reporting.logReporter(Status.INFO, "Path of the file" + requestPayloadFilePath);
		String jsonString = GenericUtils.readFileAsString(requestPayloadFilePath);
	
		jsonString = jsonString.replace("#(subID)", subscriptionID).replace("#(subNum)", subscriberNum)
				.replace("#(accID)", accountID).replace("#(startDate)", startDate);
		Reporting.logReporter(Status.INFO, "Pretty Payload: " + jsonString);
		
		Reporting.logReporter(Status.INFO, "--------------------DB Validation Starts-----------------------");
		
		if(resultSet.next())
		{
			agrmtId = String.valueOf(resultSet.getInt("CUSTOMER_SVC_AGREEMENT_ID"));
			Assert.assertEquals(agrmtId, null,"Koodo Subscriber with SIM only should not flow to DB");
			Reporting.logReporter(Status.ERROR,"--Even its a Koodo Subscriber with SIM only TC's Data are flowing to DB which is not expected. CUSTOMER_SVC_AGREEMENT_ID ="+agrmtId);
		}
		else
		{
			Reporting.logReporter(Status.INFO, "--Since its a Koodo Subscriber with SIM Only TC, Data is not flowing to DB which is Expected.--");	
		}
		
		
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
	@AfterMethod(alwaysRun = true)
	public void afterTest1() {
		Reporting.setNewGroupName("Close All Browser");
		WebDriverSteps.closeTheBrowser();
		Reporting.printAndClearLogGroupStatements();
	}
}