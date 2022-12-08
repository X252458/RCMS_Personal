package com.telus.rcms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import com.aventstack.extentreports.Status;
import com.test.reporting.Reporting;
import com.test.utils.DatesUtils;
import com.test.utils.SystemProperties;

import com.aventstack.extentreports.Status;
import com.telus.api.test.utils.APIJava;
import com.telus.rcms.jsonPathLibrary.All;
import com.test.reporting.Reporting;
import com.test.ui.actions.WebDriverSteps;

public class DBUtils {

	static String connectionString = null;
	public static Connection Conn = null;
	static Statement Stmt = null;
	static ResultSet Resultset = null;
	static ResultSet Resultset2 = null;

	
	public static void crDbConnect() throws SQLException {

		System.out.println("-------- DB Connect  ------");

		String environment = SystemProperties.EXECUTION_ENVIRONMENT;

		String connectionStringPT148 = "jdbc:oracle:thin:@//CRDBPT-SCAN.CORP.ADS:41521/CRDBPTSV1.WORLD";
		String connectionStringPT168 = "jdbc:oracle:thin:@//CRDBPV-SCAN.CORP.ADS:41521/CRDBPVSV1.WORLD";
		String connectionStringPT140 = "jdbc:oracle:thin:@//CRDBPS-SCAN.CORP.ADS:41521/CRDBPSSVA.WORLD";

		if (environment.equals("PT148")) {
			connectionString = connectionStringPT148;
		} else if (environment.equals("PT168")) {
			connectionString = connectionStringPT168;
		} else if (environment.equals("PT140")) {
			connectionString = connectionStringPT140;
		}
		try {
			// Returns the Class object associated with the class
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException exception) {
			System.out.println("Oracle Driver Class Not found Exception: " + exception.toString());
		}

		// Set connection timeout. Make sure you set this correctly as per your need
		DriverManager.setLoginTimeout(5);
		
		

		try {
			// Attempts to establish a connection
			Conn = DriverManager.getConnection(connectionString, "PORTAL", "PORTAL");
			Reporting.logReporter(Status.INFO, "------------CR DB connected successfully------------");
			System.out.println("Oracle JDBC Driver Successfully Registered! Let's make connection now");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	
	public static void dbConnect() throws SQLException {

		System.out.println("-------- DB Connect  ------");

		String environment = SystemProperties.EXECUTION_ENVIRONMENT;

		String connectionStringPT148 = "jdbc:oracle:thin:@//RWRDPT-SCAN.CORP.ADS:41521/RWRDPTSV2.WORLD";
		String connectionStringPT168 = "jdbc:oracle:thin:@//RWRDPV-SCAN.CORP.ADS:41521/RWRDPV.WORLD";
		String connectionStringPT140 = "jdbc:oracle:thin:@//RWRDPV-SCAN.CORP.ADS:41521/RWRDPSSV1.WORLD";

		if (environment.equals("PT148")) {
			connectionString = connectionStringPT148;
		} else if (environment.equals("PT168")) {
			connectionString = connectionStringPT168;
		} else if (environment.equals("PT140")) {
			connectionString = connectionStringPT140;
		}
		try {
			// Returns the Class object associated with the class
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException exception) {
			System.out.println("Oracle Driver Class Not found Exception: " + exception.toString());
		}

		// Set connection timeout. Make sure you set this correctly as per your need
		DriverManager.setLoginTimeout(5);
		
		

		try {
			// Attempts to establish a connection
			Conn = DriverManager.getConnection(connectionString, "X241410", "X241410");
			Reporting.logReporter(Status.INFO, "------------DB connected successfully------------");
			System.out.println("Oracle JDBC Driver Successfully Registered! Let's make connection now");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void dbDisConnect() throws SQLException {

		try {
			if (!Conn.isClosed()) {
				Resultset = null;
				Conn.close();
				Conn = null;
			}
		} catch (SQLException e) {
			Reporting.logReporter(Status.INFO, "Exception during DB Disconnect");

		}
		Reporting.logReporter(Status.INFO, "DB Disconnected successfully");
	}

	/*
	 * To verify whether particular AccountID is available in DB or not.
	 */

	public static String getProductOfferID(String offerID) {

		String offerValue = "null";

		switch (offerID) {
		case "AOM":
			offerValue = "1";
			break;
			
		case "OOM":
			offerValue = "2";
			break;
			
		case "PURCHASE":
			offerValue = "2";
			break;
		}
		return offerValue;
	}
	
	public static String getNullEndDate(String endDate,String startDate) {

		if(endDate==null)
			endDate=startDate;
		else if(endDate!=null)
		endDate=endDate;
		
		return endDate;
	}
		
	public static String getNullCode(String code) {

		if(code.equals("null")||code.equals("NA"))
			code="0";
		else if(code!=null)
			code=code;

		return code;
	}
	
	
	public static String convertYN(String value) {

		String convertedValue = null;
//Yet to check on this		
		if(value.equals("null") || value.equals("NA") || value.equalsIgnoreCase("false") )
			convertedValue="N";
		else if(value.equalsIgnoreCase("true")) 
			convertedValue="Y"; 
		else
			convertedValue=value;
		
		return convertedValue;
	}

	public static String getPaymentMech(String payment) {

		String paymentMechId = null;
		
		switch (payment) {
		
		case "BILL":
			paymentMechId = "1";
			break;
			
		case "CREDITCARD":
			paymentMechId = "2";
			break;
			
		case "TRADE_IN":
			paymentMechId = "5";
			break;

		case "BIB_TELUS_PENDING":
			paymentMechId = "6";
			break;
		
		case "TRADE_IN_PENDING":
			paymentMechId = "7";
			break;
		}
		
		return paymentMechId;
	}

	public static String getItemName(String ItemType) throws SQLException {

		String ItemTypeID = null;

		switch (ItemType) {
		case "HWDB":
			ItemTypeID = "1";
			break;
			
		case "BIBDB":
			ItemTypeID = "9";
			break;
			
		case "FINDB":
			ItemTypeID = "7";
			break;

		case "ACBDB":
			ItemTypeID = "4";
			break;

		}
		return ItemTypeID;
	}
	public static String getItemType(String ItemType) throws SQLException {

		String ItemTypeID = null;

		switch (ItemType) {
		case "HARDWARE":
			ItemTypeID = "1";
			break;
			
		case "ACTIVATIONBILLCREDIT":
			ItemTypeID = "4";
			break;

		case "FINANCE":
			ItemTypeID = "7";
			break;

		case "BIB":
			ItemTypeID = "9";
			break;

		case "PRECREDIT":
			ItemTypeID = "11";
			break;

		case "RENEWALBILLCREDIT":
			ItemTypeID = "12";
			break;
			
		case "TAB":
			ItemTypeID = "13";
			break;

		case "HWS":
			ItemTypeID = "14";
			break;

		case "TIASSETCREDIT":
			ItemTypeID = "15";
			break;

		case "TIPROMOCREDIT":
			ItemTypeID = "16";
			break;
			
	case "ACCESSORYFINANCE":
			ItemTypeID = "17";
			break;
		
		case "PRESOC":
			ItemTypeID="10";
			
		}
		return ItemTypeID;
	}

	public static String getReasonCD(String charReasonCdName) {
		String reasonCd = null;

		switch (charReasonCdName) {
		case "ACTIVATION_BALANCE":
			reasonCd = "1";
			break;
			
		case "RENEWAL_BALANCE":
			reasonCd = "101";
			break;
			
		case "BILL":
			reasonCd = "217";
			break;
		}
			return reasonCd;
		}


	public static String getStatusCd(String status) {

		String statusCd=null;
		
		switch (status) {
		case "ACTIVE":
			statusCd = "1";
			break;
		}

		return statusCd;
	}


	
public static String getHpaSubid(String rewardTypeID) throws SQLException {
		/*
		 * Tab - S - 8 Tab - M - 7 Tab - L - 6
		 */
	String subID=null;
		callCRDBConnect();
		Stmt = Conn.createStatement();
		Resultset = Stmt.executeQuery(
				"SELECT sub.subscription_id\r\n" + 
				"FROM REWARD_TXN tx, REWARD_TXN_RSN txrs , REWARD_TXN_TYP tp,\r\n" + 
				"   REWARD_TXN_TYP_DESC tpds, REWARD_RSN_TYP rs,\r\n" + 
				"   REWARD_RSN_TYP_DESC rsds,\r\n" + 
				"   subscription sub, client_account cas,\r\n" + 
				"   CLIENT_ACCOUNT cab , reward_account ra,reward_acct_detail rad\r\n" + 
				"WHERE tx.reward_txn_rsn_id = txrs.reward_txn_rsn_id\r\n" + 
				"and txrs.reward_txn_typ_id = tp.reward_txn_typ_id\r\n" + 
				"AND tp.reward_txn_typ_id = tpds.reward_txn_typ_id AND tpds.LANGUAGE_CD = 'EN'\r\n" + 
				"and txrs.reward_rsn_typ_id = rs.reward_rsn_typ_id\r\n" + 
				"AND rs.reward_rsn_typ_id = rsds.reward_rsn_typ_id AND rsds.LANGUAGE_CD = 'EN'\r\n" + 
				"and ra.REWARD_ACCOUNT_ID = tx.REWARD_ACCOUNT_ID\r\n" + 
				"and rad.REWARD_ACCOUNT_ID = ra.REWARD_ACCOUNT_ID\r\n" + 
				"and sysdate between rad.EFF_START_TS and rad.EFF_STOP_TS\r\n" + 
				"AND sub.client_account_id = cas.client_account_id\r\n" + 
				"AND cab.CLIENT_ACCOUNT_ID = cas.PARENT_CLIENT_ACCOUNT_ID\r\n" + 
				"and sub.subscription_id = ra.subscription_id\r\n" + 
				"and tp.TYP_CD='RDMP'\r\n" + 
				"and rs.typ_cd='ACTV'\r\n" + 
				"and sub.current_status_cd in ('A')--,'S')\r\n" + 
				"and tx.reward_program_typ_id="+rewardTypeID+ 
				" and rad.reward_program_typ_id="+rewardTypeID+ 
				" and ra.CURRENCY_BAL_AMT <> 0\r\n" + 
				"and rownum = 1");
		while (Resultset.next()) {
			subID=String.valueOf(Resultset.getString("SUBSCRIPTION_ID"));
		}
		Reporting.logReporter(Status.INFO, "Subscription ID picked from CR DB for "+rewardTypeID+" reward type : "+subID);
		callDBDisconnect();
		return subID;
	}

public static HashMap getHpaAllDetails(String rewardTypeID) throws SQLException {
	HashMap<String, String> hpaDetails = new HashMap<String, String>();
	callCRDBConnect();
	Stmt = Conn.createStatement();
	Resultset = Stmt.executeQuery(
			"SELECT cab.ban,sub.CELL_PHONE_NO,sub.subscription_id,ra.CURRENCY_BAL_AMT FROM REWARD_TXN tx, REWARD_TXN_RSN txrs , REWARD_TXN_TYP tp,\r\n"
					+ "   REWARD_TXN_TYP_DESC tpds, REWARD_RSN_TYP rs,\r\n" + "   REWARD_RSN_TYP_DESC rsds,\r\n"
					+ "   subscription sub, client_account cas,\r\n"
					+ "   CLIENT_ACCOUNT cab , reward_account ra,reward_acct_detail rad\r\n"
					+ "WHERE tx.reward_txn_rsn_id = txrs.reward_txn_rsn_id\r\n"
					+ "and txrs.reward_txn_typ_id = tp.reward_txn_typ_id\r\n"
					+ "AND tp.reward_txn_typ_id = tpds.reward_txn_typ_id AND tpds.LANGUAGE_CD = 'EN'\r\n"
					+ "and txrs.reward_rsn_typ_id = rs.reward_rsn_typ_id\r\n"
					+ "AND rs.reward_rsn_typ_id = rsds.reward_rsn_typ_id AND rsds.LANGUAGE_CD = 'EN'\r\n"
					+ "and ra.REWARD_ACCOUNT_ID = tx.REWARD_ACCOUNT_ID\r\n"
					+ "and rad.REWARD_ACCOUNT_ID = ra.REWARD_ACCOUNT_ID\r\n"
					+ "and sysdate between rad.EFF_START_TS and rad.EFF_STOP_TS\r\n"
					+ "AND sub.client_account_id = cas.client_account_id\r\n"
					+ "AND cab.CLIENT_ACCOUNT_ID = cas.PARENT_CLIENT_ACCOUNT_ID\r\n"
					+ "and sub.subscription_id = ra.subscription_id\r\n" + "and tp.TYP_CD='RDMP'\r\n"
					+ "and rs.typ_cd='ACTV'\r\n" + "and sub.current_status_cd in ('A')--,'S')\r\n"
					+ "and tx.reward_program_typ_id=" + rewardTypeID + " and rad.reward_program_typ_id="
					+ rewardTypeID + " and ra.CURRENCY_BAL_AMT='-300'\r\n" + "and rownum = 1");
	while (Resultset.next()) {
		hpaDetails.put("SUBSCRIPTION_ID", String.valueOf(Resultset.getString("SUBSCRIPTION_ID")));
		hpaDetails.put("CELL_PHONE_NO", String.valueOf(Resultset.getString("CELL_PHONE_NO")));
		hpaDetails.put("BAN", String.valueOf(Resultset.getString("BAN")));
		String amount = String.valueOf(Resultset.getString("CURRENCY_BAL_AMT"));
		if(amount.contains(".")) 
			amount = amount.split("\\.")[0];
		if(amount.contains("-"))
			amount = amount.split("\\-")[1];
		hpaDetails.put("CURRENCY_BAL_AMT", amount);
		
	}
	callDBDisconnect();
	return hpaDetails;
}
	

	
	public static Boolean DBAccountIDAvailability(String accountID) throws SQLException {
		Boolean DBAccIDAvailability = true;
		String DBaccID = null;
		callDBConnect();
		Stmt = Conn.createStatement();
		Resultset = Stmt.executeQuery(
				"SELECT DISTINCT BAN FROM CUSTOMER_SERVICE_AGREEMENT " + "WHERE BAN ='" + accountID + "'");
		while (Resultset.next()) {
		DBaccID = String.valueOf(Resultset.getInt("BAN"));
		}
		if(DBaccID==null)
		{
		DBAccIDAvailability = false;
		}
		return DBAccIDAvailability;
	}

	/*
	 * To verify whether particular SubscriptionID is available in DB or not.
	 */
	public static Boolean DBSubscriptionIDAvailability(String subscriptionID) throws SQLException {
		
		Boolean DBSubIDAvailability = true;
		String DBSubscriptionID = null;
		callDBConnect();
		Stmt = Conn.createStatement();
		Resultset = Stmt.executeQuery("SELECT DISTINCT SUBSCRIPTION_ID FROM CUSTOMER_SERVICE_AGREEMENT "
				+ "WHERE SUBSCRIPTION_ID ='" + subscriptionID + "'");
		while (Resultset.next()) {
		DBSubscriptionID = String.valueOf(Resultset.getInt("SUBSCRIPTION_ID"));
		}
		if(DBSubscriptionID==null)
		{
			DBSubIDAvailability = false;
		}
		return DBSubIDAvailability;
	}

	/*
	 * To verify whether particular SubscriptionNumber is available in DB or not.
	 */
	public static Boolean DBSubscriptionNumberAvailability(String subscriberNum) throws SQLException {
		Boolean DBSubNumAvailability = true;
		String DBsubscriberNumber = null;
		callDBConnect();
		Stmt = Conn.createStatement();
		Resultset = Stmt.executeQuery("SELECT DISTINCT subscriber_no FROM CUSTOMER_SERVICE_AGREEMENT "
				+ "WHERE SUBSCRIBER_NO ='" + subscriberNum + "'");
		while (Resultset.next()) {
		DBsubscriberNumber = String.valueOf(Resultset.getInt("SUBSCRIBER_NO"));
		}
		if(DBsubscriberNumber==null)
		{
			DBSubNumAvailability = false;
		}
		return DBSubNumAvailability;
	}

	/*
	 * To Convert Result Set to HashMAP
	 */
	public static Boolean convertResultSetToHashMap() throws SQLException {
		Boolean stat = true;
		List<Map<String, ?>> results = new ArrayList<Map<String, ?>>();

		callDBConnect();
		Stmt = Conn.createStatement();
		Resultset = Stmt.executeQuery("SELECT * FROM CUSTOMER_SERVICE_AGREEMENT where subscriber_no ='8499117748'");

		if (Resultset.next()) {
			Reporting.logReporter(Status.INFO, "");
		}

		try {

			ResultSetMetaData md = Resultset.getMetaData();
			int columns = md.getColumnCount();

			System.out.println(Resultset.getFetchSize());
			while (Resultset.next()) {

				for (int i = 1; i <= columns; i++) {
					if (i > 1)
						System.out.print(",  ");
					String columnValue = Resultset.getString(i);
					System.out.print(columnValue + " " + md.getColumnName(i));
				}

			}

		} catch (Exception e) {
			stat = false;

		}

		for (int i = 0; i < results.size(); i++) {
			System.out.print(" " + results.get(i).toString());
		}

		DBUtils.dbDisConnect();
		return stat;
	}

	public static void callDBConnect() throws SQLException {

		try {
			if (Conn.isClosed()) {
				dbConnect();
			}
		} catch (Exception e) {
			dbConnect();

		}

	}
	
	public static void callCRDBConnect() throws SQLException {

		try {
			if (Conn.isClosed()) {
				crDbConnect();
			}
		} catch (Exception e) {
			crDbConnect();

		}

	}

	public static void callDBDisconnect() throws SQLException {

		if (!Conn.isClosed()) {
			Conn.close();
		}
	}

}
