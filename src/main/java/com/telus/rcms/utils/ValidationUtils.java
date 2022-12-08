package com.telus.rcms.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.aventstack.extentreports.Status;
import com.telus.rcms.jsonPathLibrary.ActivationPayloadJsonPath;
import com.telus.rcms.jsonPathLibrary.AddAgreementItem;
import com.telus.rcms.jsonPathLibrary.AgreementItem;
import com.telus.rcms.jsonPathLibrary.Cancellation;
import com.telus.rcms.jsonPathLibrary.GetAccTerminationPenalty;
import com.telus.rcms.jsonPathLibrary.GetEarlyRenewalPenalty;
import com.telus.rcms.jsonPathLibrary.GetLoyaltyAccount;
import com.telus.rcms.jsonPathLibrary.GetReturnAdjustmentList;
import com.telus.rcms.jsonPathLibrary.GetRewardCommitment;
import com.telus.rcms.jsonPathLibrary.GetRewardCommitmentAgreementItem;
import com.telus.rcms.jsonPathLibrary.GetRewardCommitmentNew;
import com.telus.rcms.jsonPathLibrary.ListEarnedTransaction;
import com.telus.rcms.jsonPathLibrary.Renewal;
import com.telus.rcms.jsonPathLibrary.UpdateDeviceSerialNo;
import com.telus.rcms.jsonPathLibrary.UpdateItem;
import com.test.files.interaction.ReadJSON;
import com.test.files.interaction.ReadXML;
import com.test.reporting.Reporting;
import com.test.ui.actions.BaseSteps;
import com.test.ui.actions.Validate;
import com.test.ui.actions.WebDriverSession;
import com.test.utils.SystemProperties;

public class ValidationUtils {

	static File newFile = null;
	static String apiTestEnvironemnt = null;
	static String apiTestSystem = null;

	static String env = SystemProperties.EXECUTION_ENVIRONMENT;

	static String agrmtId = null;
	static String currentIND = null;
	static String AgreementDurationAmount = null;
	static String Itemtype = null;
	static String custAgmtItemId = null;

	public static void payloadValueDeclaration(String jsonString) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet resultSet = null;

		ActivationPayloadJsonPath jsonPath = new ActivationPayloadJsonPath();

		String AgreementDurationStartDateTime = JSONUtils.checkValue(jsonString,
				jsonPath.AgreementDurationStartDateTime);

		AgreementDurationAmount = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.AgreementDurationAmount));

		String AgreementDurationEndDateTime = JSONUtils.checkValue(jsonString, jsonPath.AgreementDurationEndDateTime);
		AgreementDurationEndDateTime = JSONUtils.getAgrmtEndDate(AgreementDurationEndDateTime,
				AgreementDurationStartDateTime, AgreementDurationAmount);

		String relatedParty_Accid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Accid));

		String relatedParty_brandidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_brandidValue));

		String relatedParty_AccTypeCodeValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_AccTypeCodeValue));

		String relatedParty_AccSubTypeCodeValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_AccSubTypeCodeValue));

		String relatedParty_Oriid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Oriid));

		String relatedParty_TransactionidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_TransactionidValue));

		String relatedParty_ChnlOrgValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_ChnlOrgValue));

		String relatedParty_SalesRepidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_SalesRepidValue));

		String relatedParty_Subid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Subid));

		String relatedParty_MarketProvinceValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_MarketProvinceValue));

		String relatedParty_HomeProvinceValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_HomeProvinceValue));

		String relatedParty_SubscriberNumValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_SubscriberNumValue));

		String relatedParty_ComboRatePlanIndValue = JSONUtils.checkValue(jsonString,
				jsonPath.relatedParty_ComboRatePlanIndValue);
		relatedParty_ComboRatePlanIndValue = DBUtils.convertYN(relatedParty_ComboRatePlanIndValue);

		Reporting.logReporter(Status.INFO, "--------------------DB Validation Starts-----------------------");

		resultSet = statement.executeQuery("SELECT * FROM  CUSTOMER_SERVICE_AGREEMENT agrmt "
				+ "where agrmt.subscriber_no ='" + relatedParty_SubscriberNumValue + "' and current_ind='Y' ");

		while (resultSet.next()) {

			agrmtId = String.valueOf(resultSet.getInt("CUSTOMER_SVC_AGREEMENT_ID"));

			Reporting.logReporter(Status.INFO, "Customer Agreement ID : " + agrmtId);

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getDate("AGREEMENT_END_DT")),
					AgreementDurationEndDateTime, "AGREEMENT_END_DT");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getDate("AGREEMENT_START_DT")),
					AgreementDurationStartDateTime, "AGREEMENT_START_DT");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("COMMITMENT_LENGTH_NUM")),
					AgreementDurationAmount, "COMMITMENT_LENGTH_NUM");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("BAN")), relatedParty_Accid, "BAN");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("BRAND_ID")),
					relatedParty_brandidValue, "BRAND_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("ACCOUNT_TYPE_CD")),
					relatedParty_AccTypeCodeValue, "ACCOUNT_TYPE_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("ACCOUNT_SUBTYPE_CD")),
					relatedParty_AccSubTypeCodeValue, "ACCOUNT_SUBTYPE_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("CHNL_ORG_ID")),
					relatedParty_ChnlOrgValue, "CHNL_ORG_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("SALES_REP_ID")),
					relatedParty_SalesRepidValue, "SALES_REP_ID");

			/*
			 * GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("")),
			 * relatedParty_Oriid, "");
			 * 
			 * GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt(
			 * "REWARD_TXN_ID")), relatedParty_TransactionidValue, "REWARD_TXN_ID");
			 * 
			 */
			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("SUBSCRIPTION_ID")),
					relatedParty_Subid, "SUBSCRIPTION_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("CUST_PHONE_PROV_CD")),
					relatedParty_MarketProvinceValue, "CUST_PHONE_PROV_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("CUST_HOME_PROV_CD")),
					relatedParty_HomeProvinceValue, "CUST_HOME_PROV_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getLong("SUBSCRIBER_NO")),
					relatedParty_SubscriberNumValue, "SUBSCRIBER_NO");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("COMBO_RTPLN_IND")),
					relatedParty_ComboRatePlanIndValue, "COMBO_RTPLN_IND");

		}
	}

	public static void payloadValueDeclarationFuture(String jsonString) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet resultSet = null;

		ActivationPayloadJsonPath jsonPath = new ActivationPayloadJsonPath();

		String AgreementDurationStartDateTime = JSONUtils.checkValue(jsonString,
				jsonPath.AgreementDurationStartDateTime);

		AgreementDurationAmount = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.AgreementDurationAmount));

		String AgreementDurationEndDateTime = JSONUtils.checkValue(jsonString, jsonPath.AgreementDurationEndDateTime);
		AgreementDurationEndDateTime = JSONUtils.getAgrmtEndDate(AgreementDurationEndDateTime,
				AgreementDurationStartDateTime, AgreementDurationAmount);

		String relatedParty_Accid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Accid));

		String relatedParty_brandidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_brandidValue));

		String relatedParty_AccTypeCodeValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_AccTypeCodeValue));

		String relatedParty_AccSubTypeCodeValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_AccSubTypeCodeValue));

		String relatedParty_Oriid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Oriid));

		String relatedParty_TransactionidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_TransactionidValue));

		String relatedParty_ChnlOrgValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_ChnlOrgValue));

		String relatedParty_SalesRepidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_SalesRepidValue));

		String relatedParty_Subid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Subid));

		String relatedParty_MarketProvinceValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_MarketProvinceValue));

		String relatedParty_HomeProvinceValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_HomeProvinceValue));

		String relatedParty_SubscriberNumValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_SubscriberNumValue));

		String relatedParty_ComboRatePlanIndValue = JSONUtils.checkValue(jsonString,
				jsonPath.relatedParty_ComboRatePlanIndValue);
		relatedParty_ComboRatePlanIndValue = DBUtils.convertYN(relatedParty_ComboRatePlanIndValue);

		Reporting.logReporter(Status.INFO, "--------------------DB Validation Starts-----------------------");

		resultSet = statement.executeQuery("SELECT * FROM  CUSTOMER_SERVICE_AGREEMENT agrmt "
				+ "where agrmt.subscriber_no ='" + relatedParty_SubscriberNumValue + "' and current_ind='Y' ");

		while (resultSet.next()) {

			agrmtId = String.valueOf(resultSet.getInt("CUSTOMER_SVC_AGREEMENT_ID"));

			Reporting.logReporter(Status.INFO, "Customer Agreement ID : " + agrmtId);

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getDate("AGREEMENT_END_DT")),
					AgreementDurationEndDateTime, "AGREEMENT_END_DT");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getDate("AGREEMENT_START_DT")),
					AgreementDurationStartDateTime, "AGREEMENT_START_DT");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("COMMITMENT_LENGTH_NUM")),
					AgreementDurationAmount, "COMMITMENT_LENGTH_NUM");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("BAN")), relatedParty_Accid, "BAN");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("BRAND_ID")),
					relatedParty_brandidValue, "BRAND_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("ACCOUNT_TYPE_CD")),
					relatedParty_AccTypeCodeValue, "ACCOUNT_TYPE_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("ACCOUNT_SUBTYPE_CD")),
					relatedParty_AccSubTypeCodeValue, "ACCOUNT_SUBTYPE_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("CHNL_ORG_ID")),
					relatedParty_ChnlOrgValue, "CHNL_ORG_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("SALES_REP_ID")),
					relatedParty_SalesRepidValue, "SALES_REP_ID");

			/*
			 * GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("")),
			 * relatedParty_Oriid, "");
			 * 
			 * GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt(
			 * "REWARD_TXN_ID")), relatedParty_TransactionidValue, "REWARD_TXN_ID");
			 * 
			 */
			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("SUBSCRIPTION_ID")), "0",
					"SUBSCRIPTION_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("CUST_PHONE_PROV_CD")),
					relatedParty_MarketProvinceValue, "CUST_PHONE_PROV_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("CUST_HOME_PROV_CD")),
					relatedParty_HomeProvinceValue, "CUST_HOME_PROV_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getLong("SUBSCRIBER_NO")),
					relatedParty_SubscriberNumValue, "SUBSCRIBER_NO");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("COMBO_RTPLN_IND")),
					relatedParty_ComboRatePlanIndValue, "COMBO_RTPLN_IND");

		}
	}

	public static void payloadnDBCheckAgrmtItem(String jsonString, int itemNo) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsAgreementItem = null;

		Statement statement2 = null;
		statement2 = DBUtils.Conn.createStatement();
		ResultSet rsAgreementTax = null;

		Statement statement3 = null;
		statement3 = DBUtils.Conn.createStatement();
		ResultSet rsAgreementPromo = null;

		int agreementItemNo = 0;

		for (int i = 0; i < itemNo; i++) {

			AgreementItem agrmtItem = new AgreementItem(i);
			agreementItemNo = i + 1;

			String agreementItem_id = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_id));

			Itemtype = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemType));
			String agreementItem_itemType = DBUtils.getItemType(Itemtype);

			Reporting.logReporter(Status.INFO, "--- Agreement Item : " + agreementItemNo + " : " + Itemtype + "---");

			String agreementItem_itemDurationAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemDurationAmount));

			String agreementItem_itemDurationStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationStartDateTime);

			String agreementItem_itemDurationEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationEndDateTime);

			agreementItem_itemDurationEndDateTime = JSONUtils.getEndDate(agreementItem_itemDurationEndDateTime,
					agreementItem_itemDurationStartDateTime, agreementItem_itemType, agreementItem_itemDurationAmount);

			String agreementItem_incentiveAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_incentiveAmount));
			if (AgreementDurationAmount.equals("0") && agreementItem_itemType.equals("1")) {
				Itemtype = "NA";
				agreementItem_itemDurationEndDateTime = "null";
				agreementItem_itemType = "5";
				agreementItem_itemDurationAmount = "0";
				agreementItem_incentiveAmount = "0";
			}

			String agreementItem_incentiveServiceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_incentiveServiceCode);

			String agreementItem_installmentAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAmount));

			String agreementItem_installmentStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_installmentStartDateTime);
			if (agreementItem_itemType.equals("9"))
				agreementItem_installmentStartDateTime = JSONUtils.getInstallmentStartDate(
						agreementItem_installmentStartDateTime, agreementItem_itemType,
						agreementItem_installmentAmount);

			String agreementItem_installmentEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_installmentEndDateTime);
			agreementItem_installmentEndDateTime = JSONUtils.getEndDate(agreementItem_installmentEndDateTime,
					agreementItem_installmentStartDateTime, agreementItem_itemType, agreementItem_installmentAmount);
			if (agreementItem_itemType.equals("10") || agreementItem_itemType.equals("11"))
				agreementItem_installmentEndDateTime = "null";

			String agreementItem_installmentLeftNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentLeftNumValue));

			String agreementItem_installmentAppliedNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAppliedNumValue));

			String agreementItem_installmentAppliedAmtValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAppliedAmtValue));

			String agreementItem_termOrConditionMinRatePlanValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinRatePlanValue);

			String agreementItem_termOrConditionMinFeatureValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinFeatureValue);

			String agreementItem_termOrConditionMinCombinedValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinCombinedValue);

			String agreementItem_termOrConditionCommitmentServiceCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionCommitmentServiceCdValue);

			String agreementItem_termOrConditionAutoTopupCommitmentIndValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionAutoTopupCommitmentIndValue);
			agreementItem_termOrConditionAutoTopupCommitmentIndValue = DBUtils
					.convertYN(agreementItem_termOrConditionAutoTopupCommitmentIndValue);

			String agreementItem_taxPaymentMethodCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentMethodCode);

			String agreementItem_taxPaymentMechanismCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentMechanismCode);

			String agreementItem_taxPaymentChannelCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentChannelCode);

			String agreementItem_taxProvinceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxProvinceCode);

			String agreementItem_taxCategory = JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_taxCategory);

			String agreementItem_taxRate = JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_taxRate);

			String agreementItem_taxAmountValue = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_taxAmountValue));

			String agreementItem_productSerialNumber = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productSerialNumber);

			String agreementItem_productPriceValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productPriceValue);

			String agreementItem_productCharacteristicValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productCharacteristicValue);

			/*
			 * String agreementItem_productSpecificationid = String.valueOf(
			 * JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
			 * agrmtItem.agreementItem_productSpecificationid));
			 * 
			 * String agreementItem_productCharacteristicValue = String.valueOf(
			 * JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
			 * agrmtItem.agreementItem_productCharacteristicValue));
			 * 
			 * String agreementItem_productSpecificationLocale =String.valueOf(
			 * JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
			 * agrmtItem.agreementItem_productSpecificationLocale));
			 *
			 * 
			 * String agreementItem_productSpecificationDesc =String.valueOf(
			 * JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
			 * agrmtItem.agreementItem_productSpecificationDesc));
			 * 
			 * 
			 */

			String agreementItem_promotionid = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_promotionid));

			String agreementItem_promotionPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_promotionPerspectiveDate));
			agreementItem_promotionPerspectiveDate = agreementItem_promotionPerspectiveDate.split("T")[0];

			String agreementItem_productOfferingid = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingid));

			String offerID = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
					agrmtItem.agreementItem_productOfferingRedeemedOfferContextCodeValue));
			String agreementItem_productOfferingRedeemedOfferContextCodeValue = DBUtils.getProductOfferID(offerID);

			String agreementItem_productOfferingOfferTierCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productOfferingOfferTierCdValue);
			agreementItem_productOfferingOfferTierCdValue = DBUtils
					.getNullCode(agreementItem_productOfferingOfferTierCdValue);

			String agreementItem_productOfferingOfferTierCapAmtValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productOfferingOfferTierCapAmtValue);
			agreementItem_productOfferingOfferTierCapAmtValue = DBUtils
					.getNullCode(agreementItem_productOfferingOfferTierCapAmtValue);

			String agreementItem_productOfferingDataCommitmentIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_productOfferingDataCommitmentIndValue));
			agreementItem_productOfferingDataCommitmentIndValue = DBUtils
					.convertYN(agreementItem_productOfferingDataCommitmentIndValue);

			String agreementItem_productOfferingContractEnforcedPlanIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_productOfferingContractEnforcedPlanIndValue));
			agreementItem_productOfferingContractEnforcedPlanIndValue = DBUtils
					.convertYN(agreementItem_productOfferingContractEnforcedPlanIndValue);

			String agreementItem_productOfferingPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingPerspectiveDate));
			agreementItem_productOfferingPerspectiveDate = agreementItem_productOfferingPerspectiveDate.split("T")[0];

			String agreementItem_productOfferingSourceSystemId = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingSourceSystemId));

			if (agreementItem_itemType.equals("17")) {
				agreementItem_termOrConditionMinRatePlanValue = "null";
				agreementItem_termOrConditionMinFeatureValue = "null";
				agreementItem_termOrConditionMinCombinedValue = "null";
				agreementItem_productSerialNumber = "null";
				agreementItem_productPriceValue = "null";
				agreementItem_productCharacteristicValue = "null";
			}

			if (AgreementDurationAmount.equals("0") && agreementItem_itemType.equals("5")) {
				agreementItem_termOrConditionMinRatePlanValue = "null";
				agreementItem_termOrConditionMinFeatureValue = "null";
				agreementItem_termOrConditionMinCombinedValue = "null";
			}

			String tax = null;
			switch (Itemtype) {
			case "HWS":
			case "TIASSETCREDIT":
			case "TIPROMOCREDIT":
			case "PRESOC":
			case "PRECREDIT":
			case "TAB":
			case "ACCESSORYFINANCE":
			case "ACTIVATIONBILLCREDIT":
			case "HARDWARE":
			case "BIB":
			case "FINANCE":
				rsAgreementItem = statement.executeQuery("Select * from cust_srvc_agrmt_item item  "
						+ "inner join CUST_SRVC_AGRMT_ITM_PROMO promo  "
						+ "on item.CUST_SRVC_AGRMT_ITEM_ID = promo.CUST_SRVC_AGRMT_ITEM_ID and item.customer_svc_agreement_id ='"
						+ agrmtId + "' and item.REWARD_PROGRAM_TYP_ID='" + agreementItem_itemType + "'");

				break;
			case "NA":
				rsAgreementItem = statement.executeQuery(
						"Select * from(select a.*,row_number() over(partition by customer_svc_agreement_id order by CUST_SRVC_AGRMT_ITEM_ID)  "
								+ "as rn from cust_srvc_agrmt_item a where customer_svc_agreement_id ='" + agrmtId
								+ "') where rn=" + agreementItemNo);
				break;

			}

			while (rsAgreementItem.next()) {

				custAgmtItemId = String.valueOf(rsAgreementItem.getInt("CUST_SRVC_AGRMT_ITEM_ID"));

				Reporting.logReporter(Status.INFO, "CUST_SRVC_AGRMT_ITEM_ID Value from DB is : " + custAgmtItemId);

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_START_DT")),
						agreementItem_itemDurationStartDateTime, "COMMITMENT_EFF_START_DT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_END_DT")),
						agreementItem_itemDurationEndDateTime, "COMMITMENT_EFF_END_DT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("ORIG_COMMITMENT_LENGTH_NUM")),
						agreementItem_itemDurationAmount, "ORIG_COMMITMENT_LENGTH_NUM");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REWARD_PROGRAM_TYP_ID")),
						agreementItem_itemType, "REWARD_PROGRAM_TYP_ID");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("INCENTIVE_AMT")),
						agreementItem_incentiveAmount, "INCENTIVE_AMT");

				if (!agreementItem_incentiveServiceCode.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("INCENTIVE_CD")),
							agreementItem_incentiveServiceCode, "INCENTIVE_CD");
				}

				if (!agreementItem_installmentStartDateTime.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_START_DT")),
							agreementItem_installmentStartDateTime, "REWARD_INSTLMNT_START_DT");
				}
				if (!agreementItem_installmentEndDateTime.equals("NA")) {

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_END_DT")),
							agreementItem_installmentEndDateTime, "REWARD_INSTLMNT_END_DT");
				}

				if (!agreementItem_installmentAmount.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getInt("REWARD_INSTLMNT_QTY")),
							agreementItem_installmentAmount, "REWARD_INSTLMNT_QTY");
				}
				// INSTALLMENTS_LEFT_NUM, INSTALLMENTS_APPLIED_NUM, INSTALLMENTS_APPLIED_AMT ->
				// No need to DB validation

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("RTPLN_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinRatePlanValue, "RTPLN_MIN_COMMITMENT_AMT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("FEAT_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinFeatureValue, "FEAT_MIN_COMMITMENT_AMT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("COMB_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinCombinedValue, "COMB_MIN_COMMITMENT_AMT");

				if (!agreementItem_termOrConditionCommitmentServiceCdValue.equals("NA"))
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("COMMITMENT_SERVICE_CD")),
							agreementItem_termOrConditionCommitmentServiceCdValue, "COMMITMENT_SERVICE_CD");

				if (!agreementItem_termOrConditionAutoTopupCommitmentIndValue.equals("NA"))
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("COMMITMENT_AUTOTOPUP_IND")),
							agreementItem_termOrConditionAutoTopupCommitmentIndValue, "COMMITMENT_AUTOTOPUP_IND");

				if (agreementItem_itemType.equals("4") || agreementItem_itemType.equals("9")) {

					rsAgreementTax = statement3.executeQuery(
							"select * from CUST_SRVC_AGRMT_ITM_TAX tax inner join CUST_SRVC_AGRMT_ITM_TAX_DTL dtl "
									+ "on tax.CUST_SRVC_AGRMT_ITM_TAX_ID = dtl.CUST_SRVC_AGRMT_ITM_TAX_ID "
									+ "and tax.CUST_SRVC_AGRMT_ITEM_ID=" + custAgmtItemId);

					while (rsAgreementTax.next()) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PAYMENT_METHOD_CD")),
								agreementItem_taxPaymentMethodCode, "TAX_PAYMENT_METHOD_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PYMT_MECHANISM_CD")),
								agreementItem_taxPaymentMechanismCode, "TAX_PYMT_MECHANISM_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PAYMENT_CHANNEL_CD")),
								agreementItem_taxPaymentChannelCode, "TAX_PAYMENT_CHANNEL_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAXATION_PROVINCE_CD")),
								agreementItem_taxProvinceCode, "TAXATION_PROVINCE_CD");

						GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementTax.getString("TAX_TYPE_CD")),
								agreementItem_taxCategory, "TAX_TYPE_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_RATE_PCT")), agreementItem_taxRate,
								"TAX_RATE_PCT");

						GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementTax.getString("TAX_AMT")),
								agreementItem_taxAmountValue, "TAX_AMT");
					}
				}

				/*
				 * 
				 * 
				 * Value for these json
				 * 
				 * "description": [ { "locale": "EN_CA", "description":
				 * "APPLE IPHONE 4S 32GB BLACK" }, { "locale": "FR_CA", "description":
				 * "APPLE IPHONE 4S 32GB BLACK" } ] agreementItem_productSpecificationLocale1
				 * agreementItem_productSpecificationDesc1
				 * 
				 */
				if (!agreementItem_itemType.equals("5")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getInt("REDEEMED_PROMOTION_ID")), agreementItem_promotionid,
							"REDEEMED_PROMOTION_ID");

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REDEEMED_PROMOTION_TS")),
							agreementItem_promotionPerspectiveDate, "REDEEMED_PROMOTION_TS");
				}
				if (!agreementItem_productSerialNumber.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("HANDSET_SERIAL_NUM")),
							agreementItem_productSerialNumber, "HANDSET_SERIAL_NUM");
				}
				if (!agreementItem_productPriceValue.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("CATALOGUE_ITEM_PRICE_AMT")),
							agreementItem_productPriceValue, "CATALOGUE_ITEM_PRICE_AMT");
				}
				if (!agreementItem_productCharacteristicValue.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("USIM_ID")),
							agreementItem_productCharacteristicValue, "USIM_ID");
				}

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_ID")),
						agreementItem_productOfferingid, "REDEEMED_OFFER_ID");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TYPE_ID")),
						agreementItem_productOfferingRedeemedOfferContextCodeValue, "REDEEMED_OFFER_TYPE_ID");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TIER_CD")),
						agreementItem_productOfferingOfferTierCdValue, "REDEEMED_OFFER_TIER_CD");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TIER_CAP_AMT")),
						agreementItem_productOfferingOfferTierCapAmtValue, "REDEEMED_OFFER_TIER_CAP_AMT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("DATA_SRVC_REQ_IND")),
						agreementItem_productOfferingDataCommitmentIndValue, "DATA_SRVC_REQ_IND");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("COMB_MIN_CMITMT_DISCHRG_IND")),
						agreementItem_productOfferingContractEnforcedPlanIndValue, "COMB_MIN_CMITMT_DISCHRG_IND");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getDate("REDEEMED_OFFER_TS")),
						agreementItem_productOfferingPerspectiveDate, "REDEEMED_OFFER_TS");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_SYS_ID")),
						agreementItem_productOfferingSourceSystemId, "REDEEMED_OFFER_SYS_ID");

			}
			rsAgreementItem.close();
		}

	}

	public static void payloadnDBCheckFutureAgrmtItem(String jsonString, int itemNo) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsAgreementItem = null;

		Statement statement2 = null;
		statement2 = DBUtils.Conn.createStatement();
		ResultSet rsAgreementTax = null;

		Statement statement3 = null;
		statement3 = DBUtils.Conn.createStatement();
		ResultSet rsAgreementPromo = null;

		Statement statement4 = null;
		statement4 = DBUtils.Conn.createStatement();
		ResultSet rsAgreementTaxDtl = null;

		int agreementItemNo = 0;

		for (int i = 0; i < itemNo; i++) {

			AgreementItem agrmtItem = new AgreementItem(i);
			agreementItemNo = i + 1;

			String agreementItem_id = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_id));

			Itemtype = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemType));
			String agreementItem_itemType = DBUtils.getItemType(Itemtype);

			Reporting.logReporter(Status.INFO, "--- Agreement Item : " + agreementItemNo + " : " + Itemtype + "---");

			String agreementItem_itemDurationAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemDurationAmount));

			String agreementItem_itemDurationStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationStartDateTime);

			String agreementItem_itemDurationEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationEndDateTime);

			agreementItem_itemDurationEndDateTime = JSONUtils.getEndDate(agreementItem_itemDurationEndDateTime,
					agreementItem_itemDurationStartDateTime, agreementItem_itemType, agreementItem_itemDurationAmount);

			String agreementItem_incentiveAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_incentiveAmount));
			if (AgreementDurationAmount.equals("0") && agreementItem_itemType.equals("1")) {
				Itemtype = "NA";
				agreementItem_itemDurationEndDateTime = "null";
				agreementItem_itemType = "5";
				agreementItem_itemDurationAmount = "0";
				agreementItem_incentiveAmount = "0";
			}

			String agreementItem_incentiveServiceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_incentiveServiceCode);

			String agreementItem_installmentAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAmount));

			String agreementItem_installmentStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_installmentStartDateTime);
			if (agreementItem_itemType.equals("9"))
				agreementItem_installmentStartDateTime = JSONUtils.getInstallmentFutureStartDate(
						agreementItem_installmentStartDateTime, agreementItem_itemType,
						agreementItem_installmentAmount);

			String agreementItem_installmentEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_installmentEndDateTime);
			agreementItem_installmentEndDateTime = JSONUtils.getEndDate(agreementItem_installmentEndDateTime,
					agreementItem_installmentStartDateTime, agreementItem_itemType, agreementItem_installmentAmount);
			if (agreementItem_itemType.equals("10") || agreementItem_itemType.equals("11"))
				agreementItem_installmentEndDateTime = "null";

			String agreementItem_installmentLeftNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentLeftNumValue));

			String agreementItem_installmentAppliedNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAppliedNumValue));

			String agreementItem_installmentAppliedAmtValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAppliedAmtValue));

			String agreementItem_termOrConditionMinRatePlanValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinRatePlanValue);

			String agreementItem_termOrConditionMinFeatureValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinFeatureValue);

			String agreementItem_termOrConditionMinCombinedValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinCombinedValue);

			String agreementItem_termOrConditionCommitmentServiceCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionCommitmentServiceCdValue);

			String agreementItem_termOrConditionAutoTopupCommitmentIndValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionAutoTopupCommitmentIndValue);
			agreementItem_termOrConditionAutoTopupCommitmentIndValue = DBUtils
					.convertYN(agreementItem_termOrConditionAutoTopupCommitmentIndValue);

			String agreementItem_taxPaymentMethodCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentMethodCode);

			String agreementItem_taxPaymentMechanismCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentMechanismCode);

			String agreementItem_taxPaymentChannelCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentChannelCode);

			String agreementItem_taxProvinceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxProvinceCode);

			String agreementItem_taxCategory = JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_taxCategory);

			String agreementItem_taxRate = JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_taxRate);

			String agreementItem_taxAmountValue = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_taxAmountValue));

			String agreementItem_productSerialNumber = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productSerialNumber);

			String agreementItem_productPriceValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productPriceValue);

			String agreementItem_productCharacteristicValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productCharacteristicValue);

			/*
			 * String agreementItem_productSpecificationid = String.valueOf(
			 * JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
			 * agrmtItem.agreementItem_productSpecificationid));
			 * 
			 * String agreementItem_productCharacteristicValue = String.valueOf(
			 * JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
			 * agrmtItem.agreementItem_productCharacteristicValue));
			 * 
			 * String agreementItem_productSpecificationLocale =String.valueOf(
			 * JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
			 * agrmtItem.agreementItem_productSpecificationLocale));
			 *
			 * 
			 * String agreementItem_productSpecificationDesc =String.valueOf(
			 * JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
			 * agrmtItem.agreementItem_productSpecificationDesc));
			 * 
			 * 
			 */

			String agreementItem_promotionid = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_promotionid));

			String agreementItem_promotionPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_promotionPerspectiveDate));
			agreementItem_promotionPerspectiveDate = agreementItem_promotionPerspectiveDate.split("T")[0];

			String agreementItem_productOfferingid = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingid));

			String offerID = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
					agrmtItem.agreementItem_productOfferingRedeemedOfferContextCodeValue));
			String agreementItem_productOfferingRedeemedOfferContextCodeValue = DBUtils.getProductOfferID(offerID);

			String agreementItem_productOfferingOfferTierCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productOfferingOfferTierCdValue);
			agreementItem_productOfferingOfferTierCdValue = DBUtils
					.getNullCode(agreementItem_productOfferingOfferTierCdValue);

			String agreementItem_productOfferingOfferTierCapAmtValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productOfferingOfferTierCapAmtValue);
			agreementItem_productOfferingOfferTierCapAmtValue = DBUtils
					.getNullCode(agreementItem_productOfferingOfferTierCapAmtValue);

			String agreementItem_productOfferingDataCommitmentIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_productOfferingDataCommitmentIndValue));
			agreementItem_productOfferingDataCommitmentIndValue = DBUtils
					.convertYN(agreementItem_productOfferingDataCommitmentIndValue);

			String agreementItem_productOfferingContractEnforcedPlanIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_productOfferingContractEnforcedPlanIndValue));
			agreementItem_productOfferingContractEnforcedPlanIndValue = DBUtils
					.convertYN(agreementItem_productOfferingContractEnforcedPlanIndValue);

			String agreementItem_productOfferingPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingPerspectiveDate));
			agreementItem_productOfferingPerspectiveDate = agreementItem_productOfferingPerspectiveDate.split("T")[0];

			String agreementItem_productOfferingSourceSystemId = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingSourceSystemId));

			if (agreementItem_itemType.equals("17")) {
				agreementItem_termOrConditionMinRatePlanValue = "null";
				agreementItem_termOrConditionMinFeatureValue = "null";
				agreementItem_termOrConditionMinCombinedValue = "null";
				agreementItem_productSerialNumber = "null";
				agreementItem_productPriceValue = "null";
				agreementItem_productCharacteristicValue = "null";
			}

			if (AgreementDurationAmount.equals("0") && agreementItem_itemType.equals("5")) {
				agreementItem_termOrConditionMinRatePlanValue = "null";
				agreementItem_termOrConditionMinFeatureValue = "null";
				agreementItem_termOrConditionMinCombinedValue = "null";
			}

			String tax = null;
			switch (Itemtype) {
			case "HWS":
			case "TIASSETCREDIT":
			case "TIPROMOCREDIT":
			case "PRESOC":
			case "PRECREDIT":
			case "TAB":
			case "ACCESSORYFINANCE":
			case "ACTIVATIONBILLCREDIT":
			case "HARDWARE":
			case "BIB":
			case "FINANCE":
				rsAgreementItem = statement.executeQuery("Select * from cust_srvc_agrmt_item item  "
						+ "inner join CUST_SRVC_AGRMT_ITM_PROMO promo  "
						+ "on item.CUST_SRVC_AGRMT_ITEM_ID = promo.CUST_SRVC_AGRMT_ITEM_ID and item.customer_svc_agreement_id ='"
						+ agrmtId + "' and item.REWARD_PROGRAM_TYP_ID='" + agreementItem_itemType + "'");

				break;
			case "NA":
				rsAgreementItem = statement.executeQuery(
						"Select * from(select a.*,row_number() over(partition by customer_svc_agreement_id order by CUST_SRVC_AGRMT_ITEM_ID)  "
								+ "as rn from cust_srvc_agrmt_item a where customer_svc_agreement_id ='" + agrmtId
								+ "') where rn=" + agreementItemNo);
				break;

			}

			while (rsAgreementItem.next()) {

				custAgmtItemId = String.valueOf(rsAgreementItem.getInt("CUST_SRVC_AGRMT_ITEM_ID"));

				Reporting.logReporter(Status.INFO, "CUST_SRVC_AGRMT_ITEM_ID Value from DB is : " + custAgmtItemId);

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_START_DT")),
						agreementItem_itemDurationStartDateTime, "COMMITMENT_EFF_START_DT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_END_DT")),
						agreementItem_itemDurationEndDateTime, "COMMITMENT_EFF_END_DT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("ORIG_COMMITMENT_LENGTH_NUM")),
						agreementItem_itemDurationAmount, "ORIG_COMMITMENT_LENGTH_NUM");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REWARD_PROGRAM_TYP_ID")),
						agreementItem_itemType, "REWARD_PROGRAM_TYP_ID");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("INCENTIVE_AMT")),
						agreementItem_incentiveAmount, "INCENTIVE_AMT");

				if (!agreementItem_incentiveServiceCode.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("INCENTIVE_CD")),
							agreementItem_incentiveServiceCode, "INCENTIVE_CD");
				}

				if (!agreementItem_installmentStartDateTime.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_START_DT")),
							agreementItem_installmentStartDateTime, "REWARD_INSTLMNT_START_DT");
				}
				if (!agreementItem_installmentEndDateTime.equals("NA")) {

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_END_DT")),
							agreementItem_installmentEndDateTime, "REWARD_INSTLMNT_END_DT");
				}

				if (!agreementItem_installmentAmount.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getInt("REWARD_INSTLMNT_QTY")),
							agreementItem_installmentAmount, "REWARD_INSTLMNT_QTY");
				}
				// INSTALLMENTS_LEFT_NUM, INSTALLMENTS_APPLIED_NUM, INSTALLMENTS_APPLIED_AMT ->
				// No need to DB validation

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("RTPLN_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinRatePlanValue, "RTPLN_MIN_COMMITMENT_AMT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("FEAT_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinFeatureValue, "FEAT_MIN_COMMITMENT_AMT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("COMB_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinCombinedValue, "COMB_MIN_COMMITMENT_AMT");

				if (!agreementItem_termOrConditionCommitmentServiceCdValue.equals("NA"))
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("COMMITMENT_SERVICE_CD")),
							agreementItem_termOrConditionCommitmentServiceCdValue, "COMMITMENT_SERVICE_CD");

				if (!agreementItem_termOrConditionAutoTopupCommitmentIndValue.equals("NA"))
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("COMMITMENT_AUTOTOPUP_IND")),
							agreementItem_termOrConditionAutoTopupCommitmentIndValue, "COMMITMENT_AUTOTOPUP_IND");

				if (agreementItem_itemType.equals("4")) {

					rsAgreementTax = statement3
							.executeQuery("select * from CUST_SRVC_AGRMT_ITM_TAX tax where tax.CUST_SRVC_AGRMT_ITEM_ID="
									+ custAgmtItemId);

					while (rsAgreementTax.next()) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PAYMENT_METHOD_CD")),
								agreementItem_taxPaymentMethodCode, "TAX_PAYMENT_METHOD_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PYMT_MECHANISM_CD")),
								agreementItem_taxPaymentMechanismCode, "TAX_PYMT_MECHANISM_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PAYMENT_CHANNEL_CD")),
								agreementItem_taxPaymentChannelCode, "TAX_PAYMENT_CHANNEL_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAXATION_PROVINCE_CD")),
								agreementItem_taxProvinceCode, "TAXATION_PROVINCE_CD");

						rsAgreementTaxDtl = statement4.executeQuery(
								"select * from CUST_SRVC_AGRMT_ITM_TAX_DTL where CUST_SRVC_AGRMT_ITM_TAX_ID = "
										+ rsAgreementTax.getString("CUST_SRVC_AGRMT_ITM_TAX_ID"));

						while (rsAgreementTaxDtl.next()) {

							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementTaxDtl.getString("TAX_TYPE_CD")),
									agreementItem_taxCategory, "TAX_TYPE_CD");

							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementTaxDtl.getString("TAX_RATE_PCT")), agreementItem_taxRate,
									"TAX_RATE_PCT");

							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementTaxDtl.getString("TAX_AMT")),
									agreementItem_taxAmountValue, "TAX_AMT");
						}

					}

				}

				/*
				 * 
				 * 
				 * Value for these json
				 * 
				 * "description": [ { "locale": "EN_CA", "description":
				 * "APPLE IPHONE 4S 32GB BLACK" }, { "locale": "FR_CA", "description":
				 * "APPLE IPHONE 4S 32GB BLACK" } ] agreementItem_productSpecificationLocale1
				 * agreementItem_productSpecificationDesc1
				 * 
				 */
				if (!agreementItem_itemType.equals("5")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getInt("REDEEMED_PROMOTION_ID")), agreementItem_promotionid,
							"REDEEMED_PROMOTION_ID");

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REDEEMED_PROMOTION_TS")),
							agreementItem_promotionPerspectiveDate, "REDEEMED_PROMOTION_TS");
				}
				if (!agreementItem_productSerialNumber.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("HANDSET_SERIAL_NUM")),
							agreementItem_productSerialNumber, "HANDSET_SERIAL_NUM");
				}
				if (!agreementItem_productPriceValue.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("CATALOGUE_ITEM_PRICE_AMT")),
							agreementItem_productPriceValue, "CATALOGUE_ITEM_PRICE_AMT");
				}
				if (!agreementItem_productCharacteristicValue.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("USIM_ID")),
							agreementItem_productCharacteristicValue, "USIM_ID");
				}

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_ID")),
						agreementItem_productOfferingid, "REDEEMED_OFFER_ID");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TYPE_ID")),
						agreementItem_productOfferingRedeemedOfferContextCodeValue, "REDEEMED_OFFER_TYPE_ID");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TIER_CD")),
						agreementItem_productOfferingOfferTierCdValue, "REDEEMED_OFFER_TIER_CD");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TIER_CAP_AMT")),
						agreementItem_productOfferingOfferTierCapAmtValue, "REDEEMED_OFFER_TIER_CAP_AMT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("DATA_SRVC_REQ_IND")),
						agreementItem_productOfferingDataCommitmentIndValue, "DATA_SRVC_REQ_IND");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("COMB_MIN_CMITMT_DISCHRG_IND")),
						agreementItem_productOfferingContractEnforcedPlanIndValue, "COMB_MIN_CMITMT_DISCHRG_IND");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getDate("REDEEMED_OFFER_TS")),
						agreementItem_productOfferingPerspectiveDate, "REDEEMED_OFFER_TS");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_SYS_ID")),
						agreementItem_productOfferingSourceSystemId, "REDEEMED_OFFER_SYS_ID");

			}
			rsAgreementItem.close();
		}

	}

	public static void responseDBCheckAgrmt(String jsonString, String subscriptionID, int itemNo) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet resultSet = null;

		int agreementNo = 0;

		for (int i = 0; i < itemNo; i++) {

			GetRewardCommitment jsonPath = new GetRewardCommitment(i);
			agreementNo = i + 1;
			Reporting.logReporter(Status.INFO, "--- Agreement : " + agreementNo + "---");

			String AgreementDurationStartDateTime = JSONUtils.checkValue(jsonString,
					jsonPath.AgreementDurationStartDateTime);

			AgreementDurationAmount = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.AgreementDurationAmount));

			String AgreementDurationEndDateTime = JSONUtils.checkValue(jsonString,
					jsonPath.AgreementDurationEndDateTime);

			String relatedParty_brandidValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_brandidValue));

			Reporting.logReporter(Status.INFO, "--------------------DB Validation Starts-----------------------");

			resultSet = statement.executeQuery("SELECT * FROM  CUSTOMER_SERVICE_AGREEMENT agrmt "
					+ "where agrmt.SUBSCRIPTION_ID ='" + subscriptionID + "' order by CUSTOMER_SVC_AGREEMENT_ID desc");
			// and REDEEMED_OFFER_TYPE_ID='2' ");

			while (resultSet.next()) {

				agrmtId = String.valueOf(resultSet.getInt("CUSTOMER_SVC_AGREEMENT_ID"));
				currentIND = String.valueOf(resultSet.getString("CURRENT_IND"));

				Reporting.logReporter(Status.INFO, "Agreement ID : " + agrmtId);

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getDate("AGREEMENT_END_DT")),
						AgreementDurationEndDateTime, "AGREEMENT_END_DT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getDate("AGREEMENT_START_DT")),
						AgreementDurationStartDateTime, "AGREEMENT_START_DT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("COMMITMENT_LENGTH_NUM")),
						AgreementDurationAmount, "COMMITMENT_LENGTH_NUM");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("BRAND_ID")),
						relatedParty_brandidValue, "BRAND_ID");

				/*
				 * switch (i) {
				 * 
				 * case 0: GenericUtils.responseDBCheckAgrmtItem(jsonString, subscriptionID,
				 * currentIND, 0, 4); break;
				 * 
				 * case 1: GenericUtils.responseDBCheckAgrmtItem(jsonString, subscriptionID,
				 * currentIND, 1, 1); break;
				 * 
				 * }
				 */
			}
		}

	}

	public static void responseDBCheckAgrmtItem(String jsonString, String subscriptionID, int agrmtNo, int itemNo)
			throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsAgreementItem = null;

		Statement statement1 = null;
		statement1 = DBUtils.Conn.createStatement();
		ResultSet rsRewardAcc = null;

		Statement statement2 = null;
		statement2 = DBUtils.Conn.createStatement();
		ResultSet rsAgreementTax = null;

		int agreementItemNo = 0;

		for (int i = 0; i < itemNo; i++) {

			GetRewardCommitmentAgreementItem agrmtItem = new GetRewardCommitmentAgreementItem(agrmtNo, i);
			agreementItemNo = i + 1;

			Itemtype = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemType));
			String agreementItem_itemType = DBUtils.getItemType(Itemtype);

			Reporting.logReporter(Status.INFO,
					"--- Agreement Item : " + agreementItemNo + Itemtype + " : " + agreementItem_itemType);

			String agreementItem_itemDurationAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemDurationAmount));

			String agreementItem_itemDurationStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationStartDateTime);

			String agreementItem_itemDurationEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationEndDateTime);

			String agreementItem_incentiveAmount = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_incentiveAmount);

			String agreementItem_incentiveServiceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_incentiveServiceCode);

			String agreementItem_installmentAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAmount));

			String agreementItem_installmentStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_installmentStartDateTime);
			if (agreementItem_itemType.equals("9"))
				agreementItem_installmentStartDateTime = JSONUtils.getInstallmentStartDate(
						agreementItem_installmentStartDateTime, agreementItem_itemType,
						agreementItem_installmentAmount);

			String agreementItem_installmentEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_installmentEndDateTime);
			agreementItem_installmentEndDateTime = JSONUtils.getEndDate(agreementItem_installmentEndDateTime,
					agreementItem_installmentStartDateTime, agreementItem_itemType, agreementItem_installmentAmount);
			if (agreementItem_itemType.equals("10") || agreementItem_itemType.equals("11"))
				agreementItem_installmentEndDateTime = "null";

			String agreementItem_installmentLeftNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentLeftNumValue));

			String agreementItem_installmentAppliedNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAppliedNumValue));

			String agreementItem_installmentAppliedAmtValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAppliedAmtValue));

			String agreementItem_termOrConditionMinRatePlanValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinRatePlanValue);

			String agreementItem_termOrConditionMinFeatureValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinFeatureValue);

			String agreementItem_termOrConditionMinCombinedValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinCombinedValue);

			String agreementItem_termOrConditionCommitmentServiceCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionCommitmentServiceCdValue);

			if (agreementItem_itemType.equals("15") || agreementItem_itemType.equals("16")) {
				switch (agreementItem_termOrConditionCommitmentServiceCdValue) {
				case "false":
					agreementItem_termOrConditionCommitmentServiceCdValue = "null";
					break;
				}
			}

			String agreementItem_termOrConditionAutoTopupCommitmentIndValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionAutoTopupCommitmentIndValue);
			agreementItem_termOrConditionAutoTopupCommitmentIndValue = DBUtils
					.convertYN(agreementItem_termOrConditionAutoTopupCommitmentIndValue);

			String agreementItem_taxPaymentMethodCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentMethodCode);

			String agreementItem_taxPaymentMechanismCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentMechanismCode);

			String agreementItem_taxPaymentChannelCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentChannelCode);

			String agreementItem_taxProvinceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxProvinceCode);

			String agreementItem_taxCategory = JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_taxCategory);

			String agreementItem_taxRate = JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_taxRate);

			String agreementItem_taxAmountValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxAmountValue);

			String agreementItem_productSerialNumber = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productSerialNumber);

			String agreementItem_productPriceValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productPriceValue);

			String agreementItem_productCharacteristicValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productCharacteristicValue);

			String agreementItem_promotionid = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_promotionid));

			String agreementItem_promotionPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_promotionPerspectiveDate));
			agreementItem_promotionPerspectiveDate = agreementItem_promotionPerspectiveDate.split("T")[0];

			String agreementItem_productOfferingid = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingid));

			String offerID = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
					agrmtItem.agreementItem_productOfferingRedeemedOfferContextCodeValue));
			String agreementItem_productOfferingRedeemedOfferContextCodeValue = DBUtils.getProductOfferID(offerID);

			String agreementItem_productOfferingOfferTierCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productOfferingOfferTierCdValue);
			agreementItem_productOfferingOfferTierCdValue = DBUtils
					.getNullCode(agreementItem_productOfferingOfferTierCdValue);

			String agreementItem_productOfferingOfferTierCapAmtValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productOfferingOfferTierCapAmtValue);
			agreementItem_productOfferingOfferTierCapAmtValue = DBUtils
					.getNullCode(agreementItem_productOfferingOfferTierCapAmtValue);

			String agreementItem_productOfferingDataCommitmentIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_productOfferingDataCommitmentIndValue));
			agreementItem_productOfferingDataCommitmentIndValue = DBUtils
					.convertYN(agreementItem_productOfferingDataCommitmentIndValue);

			String agreementItem_productOfferingContractEnforcedPlanIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_productOfferingContractEnforcedPlanIndValue));
			agreementItem_productOfferingContractEnforcedPlanIndValue = DBUtils
					.convertYN(agreementItem_productOfferingContractEnforcedPlanIndValue);
			if (agreementItem_itemType.equals("7")) {
				agreementItem_productOfferingContractEnforcedPlanIndValue = "N";
			}

			String agreementItem_productOfferingPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingPerspectiveDate));
			agreementItem_productOfferingPerspectiveDate = agreementItem_productOfferingPerspectiveDate.split("T")[0];

			String agreementItem_productOfferingSourceSystemId = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingSourceSystemId));

			/*
			 * if(currentIND.equals("N") && !agreementItem_itemType.equals("7")) {
			 * agreementItem_itemDurationEndDateTime=
			 * agreementItem_itemDurationStartDateTime;
			 * agreementItem_itemDurationAmount="0";
			 * agreementItem_productOfferingOfferTierCdValue="9999";
			 * agreementItem_productOfferingOfferTierCapAmtValue="99"; }
			 * if(agreementItem_itemType.equals("7")) {
			 * agreementItem_productOfferingid="1013546";
			 * agreementItem_productOfferingRedeemedOfferContextCodeValue="1";
			 * agreementItem_productOfferingOfferTierCdValue="9999";
			 * agreementItem_productOfferingOfferTierCapAmtValue="99"; }
			 */

			if (agreementItem_itemType.equals("17")) {
				agreementItem_termOrConditionMinRatePlanValue = "null";
				agreementItem_termOrConditionMinFeatureValue = "null";
				agreementItem_termOrConditionMinCombinedValue = "null";
				agreementItem_productSerialNumber = "null";
				agreementItem_productPriceValue = "null";
				agreementItem_productCharacteristicValue = "null";
			}

			if (AgreementDurationAmount.equals("0") && agreementItem_itemType.equals("5")) {
				agreementItem_termOrConditionMinRatePlanValue = "0";
				agreementItem_termOrConditionMinFeatureValue = "0";
				agreementItem_termOrConditionMinCombinedValue = "0";
			}

			String tax = null;
			switch (Itemtype) {
			case "HWS":
			case "TIASSETCREDIT":
			case "TIPROMOCREDIT":
			case "PRESOC":
			case "PRECREDIT":
			case "TAB":
			case "ACCESSORYFINANCE":
			case "ACTIVATIONBILLCREDIT":
			case "BIB":
			case "HARDWARE":
			case "FINANCE":
				rsAgreementItem = statement.executeQuery("Select * from cust_srvc_agrmt_item item  "
						+ "inner join CUST_SRVC_AGRMT_ITM_PROMO promo  "
						+ "on item.CUST_SRVC_AGRMT_ITEM_ID = promo.CUST_SRVC_AGRMT_ITEM_ID and item.customer_svc_agreement_id ='"
						+ agrmtId + "' and item.REWARD_PROGRAM_TYP_ID='" + agreementItem_itemType
						+ "' and item.ORIG_COMMITMENT_LENGTH_NUM !=0");
				tax = "NA";
				break;

			case "NA":
				rsAgreementItem = statement.executeQuery(
						"Select * from(select a.*,row_number() over(partition by customer_svc_agreement_id order by LAST_UPDT_TS)  "
								+ "as rn from cust_srvc_agrmt_item a where customer_svc_agreement_id ='" + agrmtId
								+ "') where rn=" + agreementItemNo);
				tax = "NA";
				break;
			}

			while (rsAgreementItem.next()) {

				rsRewardAcc = statement1.executeQuery("select * from reward_account where REWARD_PROGRAM_TYP_ID='"
						+ agreementItem_itemType + "' and BUSINESS_OBJECT_ID =" + subscriptionID);

				String agrmtItemId = String.valueOf(rsAgreementItem.getInt("CUST_SRVC_AGRMT_ITEM_ID"));

				Reporting.logReporter(Status.INFO, "CUST_SRVC_AGRMT_ITEM_ID Value from DB is : " + agrmtItemId);

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_START_DT")),
						agreementItem_itemDurationStartDateTime, "COMMITMENT_EFF_START_DT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_END_DT")),
						agreementItem_itemDurationEndDateTime, "COMMITMENT_EFF_END_DT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("ORIG_COMMITMENT_LENGTH_NUM")),
						agreementItem_itemDurationAmount, "ORIG_COMMITMENT_LENGTH_NUM");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REWARD_PROGRAM_TYP_ID")),
						agreementItem_itemType, "REWARD_PROGRAM_TYP_ID");

				if (!agreementItem_incentiveServiceCode.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("INCENTIVE_CD")),
							agreementItem_incentiveServiceCode, "INCENTIVE_CD");
				}

				if (!agreementItem_installmentStartDateTime.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_START_DT")),
							agreementItem_installmentStartDateTime, "REWARD_INSTLMNT_START_DT");
				}
				if (!agreementItem_installmentEndDateTime.equals("NA")) {

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_END_DT")),
							agreementItem_installmentEndDateTime, "REWARD_INSTLMNT_END_DT");
				}

				if (!agreementItem_installmentAmount.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getInt("REWARD_INSTLMNT_QTY")),
							agreementItem_installmentAmount, "REWARD_INSTLMNT_QTY");
				}
				// INSTALLMENTS_LEFT_NUM, INSTALLMENTS_APPLIED_NUM, INSTALLMENTS_APPLIED_AMT ->
				// No need to DB validation

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("RTPLN_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinRatePlanValue, "RTPLN_MIN_COMMITMENT_AMT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("FEAT_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinFeatureValue, "FEAT_MIN_COMMITMENT_AMT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("COMB_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinCombinedValue, "COMB_MIN_COMMITMENT_AMT");

				if (!agreementItem_termOrConditionCommitmentServiceCdValue.equals("NA"))
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("COMMITMENT_SERVICE_CD")),
							agreementItem_termOrConditionCommitmentServiceCdValue, "COMMITMENT_SERVICE_CD");

				if (!agreementItem_termOrConditionAutoTopupCommitmentIndValue.equals("NA"))
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("COMMITMENT_AUTOTOPUP_IND")),
							agreementItem_termOrConditionAutoTopupCommitmentIndValue, "COMMITMENT_AUTOTOPUP_IND");

				if (agreementItem_itemType.equals("4") || agreementItem_itemType.equals("9")) {
					rsAgreementTax = statement2.executeQuery(
							"select * from CUST_SRVC_AGRMT_ITM_TAX tax " + "inner join CUST_SRVC_AGRMT_ITM_TAX_DTL dtl "
									+ "on tax.CUST_SRVC_AGRMT_ITM_TAX_ID = dtl.CUST_SRVC_AGRMT_ITM_TAX_ID "
									+ "and tax.CUST_SRVC_AGRMT_ITEM_ID ='" + agrmtItemId + "'");

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementTax.getInt("TAX_PAYMENT_METHOD_CD")),
							agreementItem_taxPaymentMethodCode, "TAX_PAYMENT_METHOD_CD");

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementTax.getInt("TAX_PYMT_MECHANISM_CD")),
							agreementItem_taxPaymentMechanismCode, "TAX_PYMT_MECHANISM_CD");

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementTax.getInt("TAX_PAYMENT_CHANNEL_CD")),
							agreementItem_taxPaymentChannelCode, "TAX_PAYMENT_CHANNEL_CD");

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementTax.getInt("TAXATION_PROVINCE_CD")),
							agreementItem_taxProvinceCode, "TAXATION_PROVINCE_CD");

					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementTax.getInt("TAX_TYPE_CD")),
							agreementItem_taxCategory, "TAX_TYPE_CD");

					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementTax.getInt("TAX_RATE_PCT")),
							agreementItem_taxRate, "TAX_RATE_PCT");

					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementTax.getInt("TAX_AMT")),
							agreementItem_taxAmountValue, "TAX_AMT");

				}

				/*
				 * 
				 * Value for these json
				 * 
				 * "description": [ { "locale": "EN_CA", "description":
				 * "APPLE IPHONE 4S 32GB BLACK" }, { "locale": "FR_CA", "description":
				 * "APPLE IPHONE 4S 32GB BLACK" } ] agreementItem_productSpecificationLocale1
				 * agreementItem_productSpecificationDesc1
				 * 
				 */

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REDEEMED_PROMOTION_ID")),
						agreementItem_promotionid, "REDEEMED_PROMOTION_ID");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("REDEEMED_PROMOTION_TS")),
						agreementItem_promotionPerspectiveDate, "REDEEMED_PROMOTION_TS");

				if (!agreementItem_productSerialNumber.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("HANDSET_SERIAL_NUM")),
							agreementItem_productSerialNumber, "HANDSET_SERIAL_NUM");
				}
				if (!agreementItem_productPriceValue.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("CATALOGUE_ITEM_PRICE_AMT")),
							agreementItem_productPriceValue, "CATALOGUE_ITEM_PRICE_AMT");
				}
				if (!agreementItem_productCharacteristicValue.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("USIM_ID")),
							agreementItem_productCharacteristicValue, "USIM_ID");
				}

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_ID")),
						agreementItem_productOfferingid, "REDEEMED_OFFER_ID");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TYPE_ID")),
						agreementItem_productOfferingRedeemedOfferContextCodeValue, "REDEEMED_OFFER_TYPE_ID");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TIER_CD")),
						agreementItem_productOfferingOfferTierCdValue, "REDEEMED_OFFER_TIER_CD");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TIER_CAP_AMT")),
						agreementItem_productOfferingOfferTierCapAmtValue, "REDEEMED_OFFER_TIER_CAP_AMT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("DATA_SRVC_REQ_IND")),
						agreementItem_productOfferingDataCommitmentIndValue, "DATA_SRVC_REQ_IND");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("COMB_MIN_CMITMT_DISCHRG_IND")),
						agreementItem_productOfferingContractEnforcedPlanIndValue, "COMB_MIN_CMITMT_DISCHRG_IND");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getDate("REDEEMED_OFFER_TS")),
						agreementItem_productOfferingPerspectiveDate, "REDEEMED_OFFER_TS");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_SYS_ID")),
						agreementItem_productOfferingSourceSystemId, "REDEEMED_OFFER_SYS_ID");

				while (rsRewardAcc.next()) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsRewardAcc.getInt("CURRENCY_BAL_AMT")),
							"-" + agreementItem_incentiveAmount, "CURRENCY_BAL_AMT");

				}

			}
			rsAgreementItem.close();

		}
	}

	public static void payloadValueDeclarationWithItemtype(String jsonString, String itemType) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet resultSet = null;

		ActivationPayloadJsonPath jsonPath = new ActivationPayloadJsonPath();

		AgreementDurationAmount = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.AgreementDurationAmount));

		String AgreementDurationStartDateTime = JSONUtils.checkValue(jsonString,
				jsonPath.AgreementDurationStartDateTime);
		/*
		 * if(itemType.equals("BIB") || itemType.equals("TIASSETCREDIT")) {
		 * AgreementDurationStartDateTime=JSONUtils.getInstallmentStartDate(
		 * AgreementDurationStartDateTime, itemType, AgreementDurationAmount); }
		 */

		String AgreementDurationEndDateTime = JSONUtils.checkValue(jsonString, jsonPath.AgreementDurationEndDateTime);
		AgreementDurationEndDateTime = JSONUtils.getAgrmtEndDate(AgreementDurationEndDateTime,
				AgreementDurationStartDateTime, AgreementDurationAmount);
		if (itemType.equals("BIB") || itemType.equals("TIASSETCREDIT") || itemType.equals("TIPROMOCRDIT")) {
			LocalDate endDateFormatted;
			endDateFormatted = LocalDate.parse(AgreementDurationStartDateTime);
			endDateFormatted = endDateFormatted.plusDays(180);
			AgreementDurationEndDateTime = endDateFormatted.toString();
		}

		String relatedParty_Accid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Accid));

		String relatedParty_brandidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_brandidValue));

		String relatedParty_AccTypeCodeValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_AccTypeCodeValue));

		String relatedParty_AccSubTypeCodeValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_AccSubTypeCodeValue));

		String relatedParty_Oriid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Oriid));

		String relatedParty_TransactionidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_TransactionidValue));

		String relatedParty_ChnlOrgValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_ChnlOrgValue));

		String relatedParty_SalesRepidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_SalesRepidValue));

		String relatedParty_Subid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Subid));

		String relatedParty_MarketProvinceValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_MarketProvinceValue));

		String relatedParty_HomeProvinceValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_HomeProvinceValue));

		String relatedParty_SubscriberNumValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_SubscriberNumValue));

		String relatedParty_ComboRatePlanIndValue = JSONUtils.checkValue(jsonString,
				jsonPath.relatedParty_ComboRatePlanIndValue);
		relatedParty_ComboRatePlanIndValue = DBUtils.convertYN(relatedParty_ComboRatePlanIndValue);

		Reporting.logReporter(Status.INFO, "--------------------DB Validation Starts-----------------------");

		resultSet = statement.executeQuery("SELECT * FROM  CUSTOMER_SERVICE_AGREEMENT agrmt "
				+ "where agrmt.subscriber_no ='" + relatedParty_SubscriberNumValue + "' and current_ind='Y' ");

		while (resultSet.next()) {

			agrmtId = String.valueOf(resultSet.getInt("CUSTOMER_SVC_AGREEMENT_ID"));

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getDate("AGREEMENT_START_DT")),
					AgreementDurationStartDateTime, "AGREEMENT_START_DT");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getDate("AGREEMENT_END_DT")),
					AgreementDurationEndDateTime, "AGREEMENT_END_DT");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("COMMITMENT_LENGTH_NUM")),
					AgreementDurationAmount, "COMMITMENT_LENGTH_NUM");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("BAN")), relatedParty_Accid, "BAN");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("BRAND_ID")),
					relatedParty_brandidValue, "BRAND_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("ACCOUNT_TYPE_CD")),
					relatedParty_AccTypeCodeValue, "ACCOUNT_TYPE_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("ACCOUNT_SUBTYPE_CD")),
					relatedParty_AccSubTypeCodeValue, "ACCOUNT_SUBTYPE_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("CHNL_ORG_ID")),
					relatedParty_ChnlOrgValue, "CHNL_ORG_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("SALES_REP_ID")),
					relatedParty_SalesRepidValue, "SALES_REP_ID");

			/*
			 * GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("")),
			 * relatedParty_Oriid, "");
			 * 
			 * GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt(
			 * "REWARD_TXN_ID")), relatedParty_TransactionidValue, "REWARD_TXN_ID");
			 * 
			 */
			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getInt("SUBSCRIPTION_ID")),
					relatedParty_Subid, "SUBSCRIPTION_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("CUST_PHONE_PROV_CD")),
					relatedParty_MarketProvinceValue, "CUST_PHONE_PROV_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("CUST_HOME_PROV_CD")),
					relatedParty_HomeProvinceValue, "CUST_HOME_PROV_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getLong("SUBSCRIBER_NO")),
					relatedParty_SubscriberNumValue, "SUBSCRIBER_NO");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("COMBO_RTPLN_IND")),
					relatedParty_ComboRatePlanIndValue, "COMBO_RTPLN_IND");
		}

	}

	public static void extraDBvalidation(String jsonString, String action, int itemNo) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet resultSet = null;

		Statement statement1 = null;
		statement1 = DBUtils.Conn.createStatement();
		ResultSet resultSet1 = null;

		Statement statement2 = null;
		statement2 = DBUtils.Conn.createStatement();
		ResultSet resultSet2 = null;

		Statement statement3 = null;
		statement3 = DBUtils.Conn.createStatement();
		ResultSet resultSetReward = null;

		ActivationPayloadJsonPath jsonPath = null;

		switch (action) {
		case "activation":
			jsonPath = new ActivationPayloadJsonPath();
			break;

		/*
		 * case "renewal": jsonPath = new Renewal(); break;
		 */

		}

		String startDateTime = JSONUtils.getGMTStartDate().split("T")[0];

		String relatedParty_SubscriberNumValue = JSONUtils.checkValue(jsonString,
				jsonPath.relatedParty_SubscriberNumValue);

		String relatedParty_Subid = JSONUtils.checkValue(jsonString, jsonPath.relatedParty_Subid);

		String relatedParty_Oriid = JSONUtils.checkValue(jsonString, jsonPath.relatedParty_Oriid);

		String relatedParty_TransactionidValue = JSONUtils.checkValue(jsonString,
				jsonPath.relatedParty_TransactionidValue);

		// DB Validation
		resultSet = statement.executeQuery("SELECT * FROM  CUSTOMER_SERVICE_AGREEMENT agrmt "
				+ "inner join cust_agrmt_lifecycl lyfcycl "
				+ "on agrmt.CUSTOMER_SVC_AGREEMENT_ID = lyfcycl.CUSTOMER_SVC_AGREEMENT_ID "
				+ "and agrmt.subscriber_no ='" + relatedParty_SubscriberNumValue + "' and agrmt.current_ind='Y'");
		while (resultSet.next()) {

			Reporting.logReporter(Status.INFO, "Validating - CUST_AGRMT_LIFECYCL");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("EFF_START_TS").split(" ")[0]),
					startDateTime, "EFF_START_TS");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet.getString("EFF_STOP_TS").split(" ")[0]),
					"9999-12-31", "EFF_STOP_TS");

			switch (action) {
			case "activation":
				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(resultSet.getString("CUST_AGRMT_STAT_LIFECYCL_ID")), "1",
						"CUST_AGRMT_STAT_LIFECYCL_ID");
				break;
			case "futureActivation":
				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(resultSet.getString("CUST_AGRMT_STAT_LIFECYCL_ID")), "2",
						"CUST_AGRMT_STAT_LIFECYCL_ID");
				break;
			case "cancellation":
				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(resultSet.getString("CUST_AGRMT_STAT_LIFECYCL_ID")), "3",
						"CUST_AGRMT_STAT_LIFECYCL_ID");
				break;
			case "suspended":
				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(resultSet.getString("CUST_AGRMT_STAT_LIFECYCL_ID")), "4",
						"CUST_AGRMT_STAT_LIFECYCL_ID");
				break;
			}
		}
		int agreementItemNo = 0;

		Reporting.logReporter(Status.INFO, "Validating - REWARD_ACCOUNT");
		for (int i = 0; i < itemNo; i++) {

			AgreementItem agrmtItem = new AgreementItem(i);
			agreementItemNo = i + 1;
			Reporting.logReporter(Status.INFO, "--- Agreement Item : " + agreementItemNo + "---");

			Itemtype = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemType));
			String agreementItem_itemType = DBUtils.getItemType(Itemtype);

			Reporting.logReporter(Status.INFO, "Validating - Reward Type  - " + Itemtype);

			if (!agreementItem_itemType.equals("15") || !agreementItem_itemType.equals("16")
					|| !agreementItem_itemType.equals("10") || !agreementItem_itemType.equals("11")
					|| !agreementItem_itemType.equals("5") || !agreementItem_itemType.equals("null")) {

				String agreementItem_incentiveAmount = null;

				if (agreementItem_itemType.equals("1") || agreementItem_itemType.equals("14")) {
					int FirstInst = Integer
							.valueOf(JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_incentiveAmount));
					int instDuration = Integer.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_itemDurationAmount));
					try {
						int drawDownAmt = FirstInst / instDuration;
						agreementItem_incentiveAmount = String.valueOf(FirstInst - drawDownAmt);
					} catch (Exception e) {
						float drawDownAmt = FirstInst / instDuration;
						agreementItem_incentiveAmount = String.valueOf(FirstInst - drawDownAmt);
					}

				} else
					agreementItem_incentiveAmount = JSONUtils.checkValue(jsonString,
							agrmtItem.agreementItem_incentiveAmount);

				resultSet1 = statement1
						.executeQuery("Select * from cust_srvc_agrmt_item item where item.customer_svc_agreement_id ='"
								+ agrmtId + "' and item.REWARD_PROGRAM_TYP_ID=" + agreementItem_itemType);

				resultSetReward = statement3.executeQuery("select * from reward_account where REWARD_PROGRAM_TYP_ID='"
						+ agreementItem_itemType + "' and BUSINESS_OBJECT_ID =" + relatedParty_Subid);

				while (resultSet1.next()) {
					while (resultSetReward.next()) {

						String rewardAccID = String.valueOf(resultSetReward.getString("REWARD_ACCOUNT_ID"));

						resultSet2 = statement2.executeQuery(
								"select * from reward_txn txn " + "inner join ASSOCIATED_REWARD_TXN asso_txn "
										+ "on txn.REWARD_TXN_ID = asso_txn.REWARD_TXN_ID "
										+ "inner join REWARD_ACCT_CSA_ITEM_ASSN reward_item "
										+ "on txn.REWARD_ACCOUNT_ID = reward_item.REWARD_ACCOUNT_ID "
										+ "and txn.REWARD_ACCOUNT_ID =" + rewardAccID + " order by txn.TXN_TS");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetReward.getString("CREATE_TS").split(" ")[0]), startDateTime,
								"CREATE_TS");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetReward.getString("STATUS_LIFECYCLE_ID")), "2",
								"STATUS_LIFECYCLE_ID");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetReward.getInt("CURR_FIN_COMMITMENT_ID")),
								(String.valueOf(resultSet1.getInt("FIN_COMMITMENT_ID"))), "CURR_FIN_COMMITMENT_ID");

						if (agreementItem_itemType.equals("1") || agreementItem_itemType.equals("14")) {
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(resultSetReward.getInt("REMAINING_INSTLMNT_QTY")), "0",
									"REMAINING_INSTLMNT_QTY");
						} else {

							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(resultSetReward.getString("REMAINING_INSTLMNT_QTY")),
									(String.valueOf(resultSet1.getString("ORIG_COMMITMENT_LENGTH_NUM"))),
									"REMAINING_INSTLMNT_QTY");
						}

						if (!agreementItem_itemType.equals("17"))
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(resultSetReward.getInt("REWARD_PGM_CATGY_ID")), "1",
									"REWARD_PGM_CATGY_ID");
						else
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(resultSetReward.getInt("REWARD_PGM_CATGY_ID")), "0",
									"REWARD_PGM_CATGY_ID");

						if (agreementItem_itemType.equals("4")) {
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(resultSetReward.getString("CURRENCY_BAL_AMT")), "0",
									"CURRENCY_BAL_AMT");

						} else
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(resultSetReward.getString("CURRENCY_BAL_AMT")),
									"-" + agreementItem_incentiveAmount, "CURRENCY_BAL_AMT");

						while (resultSet2.next()) {
							Reporting.logReporter(Status.INFO, "Validating - REWARD_TXN Table");

							Reporting.logReporter(Status.INFO,
									"REWARD_TXN_ID : " + resultSet2.getString("REWARD_TXN_ID"));

							Reporting.logReporter(Status.INFO,
									"CUST_SRVC_AGRMT_ITEM_ID : " + resultSet2.getString("CUST_SRVC_AGRMT_ITEM_ID"));

							if (agreementItem_itemType.equals("1") || agreementItem_itemType.equals("14")) {

								GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet2.getDate("CREATE_TS")),
										startDateTime, "CREATE_TS");

								agreementItem_incentiveAmount = JSONUtils.checkValue(jsonString,
										agrmtItem.agreementItem_incentiveAmount);
								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(resultSet2.getString("CURRENCY_BAL_AMT")),
										"-" + agreementItem_incentiveAmount, "CURRENCY_BAL_AMT");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(resultSet2.getString("CURRENCY_AMT")),
										"-" + agreementItem_incentiveAmount, "CURRENCY_AMT");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(resultSet2.getString("REWARD_TXN_RSN_ID")), "1",
										"REWARD_TXN_RSN_ID");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(resultSet2.getString("SOURCE_SYSTEM_ID")), relatedParty_Oriid,
										"SOURCE_SYSTEM_ID");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(resultSet2.getString("SOURCE_TXN_ID")),
										relatedParty_TransactionidValue, "SOURCE_TXN_ID");

								while (resultSet2.next()) {
									Reporting.logReporter(Status.INFO, "Draw down happened");
									int FirstInst = Integer.valueOf(
											JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_incentiveAmount));
									int instDuration = Integer.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(
											jsonString, agrmtItem.agreementItem_itemDurationAmount));
									int drawDownAmt = FirstInst / instDuration;
									agreementItem_incentiveAmount = String.valueOf(FirstInst - drawDownAmt);

									GenericUtils.validateAssertEqualsFromDB(
											String.valueOf(resultSet2.getDate("CREATE_TS")), startDateTime,
											"CREATE_TS");

									GenericUtils.validateAssertEqualsFromDB(
											String.valueOf(resultSet2.getString("CURRENCY_BAL_AMT")),
											"-" + agreementItem_incentiveAmount, "CURRENCY_BAL_AMT");

									GenericUtils.validateAssertEqualsFromDB(
											String.valueOf(resultSet2.getString("CURRENCY_AMT")),
											String.valueOf(drawDownAmt), "CURRENCY_AMT");

									GenericUtils.validateAssertEqualsFromDB(
											String.valueOf(resultSet2.getString("REWARD_TXN_RSN_ID")), "4",
											"REWARD_TXN_RSN_ID");

								}

							} else {

								GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSet2.getDate("CREATE_TS")),
										startDateTime, "CREATE_TS");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(resultSet2.getString("CURRENCY_BAL_AMT")),
										"-" + agreementItem_incentiveAmount, "CURRENCY_BAL_AMT");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(resultSet2.getString("CURRENCY_AMT")),
										"-" + agreementItem_incentiveAmount, "CURRENCY_AMT");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(resultSet2.getString("SOURCE_SYSTEM_ID")), relatedParty_Oriid,
										"SOURCE_SYSTEM_ID");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(resultSet2.getString("SOURCE_TXN_ID")),
										relatedParty_TransactionidValue, "SOURCE_TXN_ID");

							}
						}

					}
					resultSet.close();

				}
			} else {
				Reporting.logReporter(Status.INFO, Itemtype + "Wont flow to Reward Account or Transaction table");

			}
		}
	}

	public static HashMap NoOfAgrmt(String jsonString) {

		int agrmtNo = 0;
		String agrmt = "null";
		HashMap<String, Integer> noOfAgrmt = new HashMap<String, Integer>();

		do {

			agrmt = JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, "$[" + agrmtNo + "]");
			Reporting.logReporter(Status.INFO, "Agreement String : " + agrmt);
			if (!agrmt.equals("NA"))
				noOfAgrmt.put("Agreement" + agrmtNo, agrmtNo);
			agrmtNo++;
		} while (!agrmt.equals("NA"));
		return noOfAgrmt;
	}

	public static HashMap NoOfAgrmtItem(String jsonString, int agrmtNo) {

		int agrmtItemNo = 0;
		String agrmtItem = "null";
		HashMap<String, Integer> noOfAgrmtItem = new HashMap<String, Integer>();

		do {

			agrmtItem = JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
					"$[" + agrmtNo + "].agreementItem[" + agrmtItemNo + "].itemType");
			if (!agrmtItem.equals("NA"))
				noOfAgrmtItem.put(agrmtItem, agrmtItemNo);
			agrmtItemNo++;
		} while (!agrmtItem.equals("NA"));

		return noOfAgrmtItem;

	}

	public static void responseDBCheckAgrmtItemNew(String jsonString, String subscriptionID, int agrmtNo)
			throws SQLException, InterruptedException {

		Statement statementAgrmt = null;
		statementAgrmt = DBUtils.Conn.createStatement();
		ResultSet resultSetAgrmt = null;

		HashMap<String, Integer> noOfAgrmtItem = new HashMap<String, Integer>();

		int agreementNo = 1;
		int agreementItem = 0;

		Reporting.logReporter(Status.INFO, "--------------------DB Validation Starts-----------------------");

		for (int i = 0; i < agrmtNo; i++) {

			noOfAgrmtItem = ValidationUtils.NoOfAgrmtItem(jsonString, i);

			GetRewardCommitmentNew jsonPath1 = new GetRewardCommitmentNew(i);

			String AgreementDurationStartDateTime = JSONUtils.checkValue(jsonString,
					jsonPath1.AgreementDurationStartDateTime);

			AgreementDurationAmount = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath1.AgreementDurationAmount));

			String AgreementDurationEndDateTime = JSONUtils.checkValue(jsonString,
					jsonPath1.AgreementDurationEndDateTime);

			String relatedParty_brandidValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath1.relatedParty_brandidValue));

			resultSetAgrmt = statementAgrmt
					.executeQuery("Select * from(select agmt.*,row_number() over(order by CURRENT_IND desc) \r\n"
							+ "as rn from CUSTOMER_SERVICE_AGREEMENT agmt where agmt.SUBSCRIPTION_ID ='"
							+ subscriptionID + "') where rn=" + agreementNo);

			while (resultSetAgrmt.next()) {

				Reporting.logReporter(Status.INFO, "--- Agreement : " + agreementNo + "---");
				agreementNo++;

				agrmtId = String.valueOf(resultSetAgrmt.getInt("CUSTOMER_SVC_AGREEMENT_ID"));
				currentIND = String.valueOf(resultSetAgrmt.getString("CURRENT_IND"));

				Reporting.logReporter(Status.INFO, "Agreement ID : " + agrmtId);

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSetAgrmt.getDate("AGREEMENT_END_DT")),
						AgreementDurationEndDateTime, "AGREEMENT_END_DT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSetAgrmt.getDate("AGREEMENT_START_DT")),
						AgreementDurationStartDateTime, "AGREEMENT_START_DT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSetAgrmt.getInt("COMMITMENT_LENGTH_NUM")),
						AgreementDurationAmount, "COMMITMENT_LENGTH_NUM");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSetAgrmt.getInt("BRAND_ID")),
						relatedParty_brandidValue, "BRAND_ID");

				for (int j = 0; j < noOfAgrmtItem.size(); j++) {

					GetRewardCommitmentNew jsonPath = new GetRewardCommitmentNew(i, j);

					agreementItem = j + 1;

					Statement statement = null;
					statement = DBUtils.Conn.createStatement();
					ResultSet rsAgreementItem = null;

					Statement statement1 = null;
					statement1 = DBUtils.Conn.createStatement();
					ResultSet rsRewardAcc = null;

					Statement statement2 = null;
					statement2 = DBUtils.Conn.createStatement();
					ResultSet rsAgreementTax = null;

					Itemtype = String.valueOf(
							JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.agreementItem_itemType));
					String agreementItem_itemType = DBUtils.getItemType(Itemtype);

					Reporting.logReporter(Status.INFO,
							"--- Agreement Item : " + agreementItem + " " + Itemtype + " : " + agreementItem_itemType);

					String agreementItem_itemDurationAmount = String.valueOf(JSONUtils
							.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.agreementItem_itemDurationAmount));

					String agreementItem_itemDurationStartDateTime = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_itemDurationStartDateTime);

					String agreementItem_itemDurationEndDateTime = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_itemDurationEndDateTime);

					String agreementItem_incentiveAmount = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_incentiveAmount);

					String agreementItem_incentiveServiceCode = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_incentiveServiceCode);

					String agreementItem_installmentAmount = String.valueOf(JSONUtils
							.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.agreementItem_installmentAmount));

					String agreementItem_installmentStartDateTime = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_installmentStartDateTime);

					String agreementItem_installmentEndDateTime = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_installmentEndDateTime);
					agreementItem_installmentEndDateTime = JSONUtils.getEndDate(agreementItem_installmentEndDateTime,
							agreementItem_installmentStartDateTime, agreementItem_itemType,
							agreementItem_installmentAmount);
					if (agreementItem_itemType.equals("10") || agreementItem_itemType.equals("11"))
						agreementItem_installmentEndDateTime = "null";

					String agreementItem_installmentLeftNumValue = String.valueOf(JSONUtils
							.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.agreementItem_installmentLeftNumValue));

					String agreementItem_installmentAppliedNumValue = String
							.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
									jsonPath.agreementItem_installmentAppliedNumValue));

					String agreementItem_installmentAppliedAmtValue = String
							.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
									jsonPath.agreementItem_installmentAppliedAmtValue));

					String agreementItem_termOrConditionMinRatePlanValue = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_termOrConditionMinRatePlanValue);

					String agreementItem_termOrConditionMinFeatureValue = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_termOrConditionMinFeatureValue);

					String agreementItem_termOrConditionMinCombinedValue = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_termOrConditionMinCombinedValue);

					String agreementItem_termOrConditionCommitmentServiceCdValue = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_termOrConditionCommitmentServiceCdValue);

					if (agreementItem_itemType.equals("15") || agreementItem_itemType.equals("16")) {
						switch (agreementItem_termOrConditionCommitmentServiceCdValue) {
						case "false":
							agreementItem_termOrConditionCommitmentServiceCdValue = "null";
							break;
						}
					}

					String agreementItem_termOrConditionAutoTopupCommitmentIndValue = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_termOrConditionAutoTopupCommitmentIndValue);
					agreementItem_termOrConditionAutoTopupCommitmentIndValue = DBUtils
							.convertYN(agreementItem_termOrConditionAutoTopupCommitmentIndValue);

					String agreementItem_taxPaymentMethodCode = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_taxPaymentMethodCode);

					String agreementItem_taxPaymentMechanismCode = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_taxPaymentMechanismCode);

					String agreementItem_taxPaymentChannelCode = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_taxPaymentChannelCode);

					String agreementItem_taxProvinceCode = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_taxProvinceCode);

					String agreementItem_taxCategory = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_taxCategory);

					String agreementItem_taxRate = JSONUtils.checkValue(jsonString, jsonPath.agreementItem_taxRate);

					String agreementItem_taxAmountValue = String.valueOf(
							JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.agreementItem_taxAmountValue));

					String agreementItem_productSerialNumber = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_productSerialNumber);

					String agreementItem_productPriceValue = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_productPriceValue);

					String agreementItem_productCharacteristicValue = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_productCharacteristicValue);

					String agreementItem_promotionid = String.valueOf(
							JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.agreementItem_promotionid));

					String agreementItem_promotionPerspectiveDate = String.valueOf(JSONUtils
							.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.agreementItem_promotionPerspectiveDate));
					agreementItem_promotionPerspectiveDate = agreementItem_promotionPerspectiveDate.split("T")[0];

					String agreementItem_productOfferingid = String.valueOf(JSONUtils
							.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.agreementItem_productOfferingid));

					String offerID = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							jsonPath.agreementItem_productOfferingRedeemedOfferContextCodeValue));
					String agreementItem_productOfferingRedeemedOfferContextCodeValue = DBUtils
							.getProductOfferID(offerID);

					String agreementItem_productOfferingOfferTierCdValue = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_productOfferingOfferTierCdValue);
					agreementItem_productOfferingOfferTierCdValue = DBUtils
							.getNullCode(agreementItem_productOfferingOfferTierCdValue);

					String agreementItem_productOfferingOfferTierCapAmtValue = JSONUtils.checkValue(jsonString,
							jsonPath.agreementItem_productOfferingOfferTierCapAmtValue);
					agreementItem_productOfferingOfferTierCapAmtValue = DBUtils
							.getNullCode(agreementItem_productOfferingOfferTierCapAmtValue);

					String agreementItem_productOfferingDataCommitmentIndValue = String
							.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
									jsonPath.agreementItem_productOfferingDataCommitmentIndValue));
					agreementItem_productOfferingDataCommitmentIndValue = DBUtils
							.convertYN(agreementItem_productOfferingDataCommitmentIndValue);

					String agreementItem_productOfferingContractEnforcedPlanIndValue = String
							.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
									jsonPath.agreementItem_productOfferingContractEnforcedPlanIndValue));
					agreementItem_productOfferingContractEnforcedPlanIndValue = DBUtils
							.convertYN(agreementItem_productOfferingContractEnforcedPlanIndValue);
					if (agreementItem_itemType.equals("7") || agreementItem_itemType.equals("9")
							|| agreementItem_itemType.equals("1")) {
						agreementItem_productOfferingContractEnforcedPlanIndValue = "N";
					}

					String agreementItem_productOfferingPerspectiveDate = String
							.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
									jsonPath.agreementItem_productOfferingPerspectiveDate));
					agreementItem_productOfferingPerspectiveDate = agreementItem_productOfferingPerspectiveDate
							.split("T")[0];

					String agreementItem_productOfferingSourceSystemId = String
							.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
									jsonPath.agreementItem_productOfferingSourceSystemId));

					if (agreementItem_installmentAmount.equals("0")) {
						agreementItem_installmentEndDateTime = agreementItem_installmentStartDateTime;
					}

					if (agreementItem_itemType.equals("17")) {
						agreementItem_termOrConditionMinRatePlanValue = "0";
						agreementItem_termOrConditionMinFeatureValue = "0";
						agreementItem_termOrConditionMinCombinedValue = "0";
						agreementItem_productSerialNumber = "null";
						agreementItem_productPriceValue = "0";
						agreementItem_productCharacteristicValue = "null";
					}

					if (AgreementDurationAmount.equals("0") && agreementItem_itemType.equals("5")) {
						agreementItem_termOrConditionMinRatePlanValue = "0";
						agreementItem_termOrConditionMinFeatureValue = "0";
						agreementItem_termOrConditionMinCombinedValue = "0";
					}

					String tax = null;
					switch (Itemtype) {
					case "HWS":
					case "TIASSETCREDIT":
					case "TIPROMOCREDIT":
					case "PRESOC":
					case "PRECREDIT":
					case "TAB":
					case "ACTIVATIONBILLCREDIT":
					case "RENEWALBILLCREDIT":
					case "BIB":
					case "HARDWARE":
					case "FINANCE":
						rsAgreementItem = statement.executeQuery("Select * from cust_srvc_agrmt_item item  "
								+ "inner join CUST_SRVC_AGRMT_ITM_PROMO promo  "
								+ "on item.CUST_SRVC_AGRMT_ITEM_ID = promo.CUST_SRVC_AGRMT_ITEM_ID and item.customer_svc_agreement_id ='"
								+ agrmtId + "' and item.REWARD_PROGRAM_TYP_ID='" + agreementItem_itemType + "'"
								+ " and item.ORIG_COMMITMENT_LENGTH_NUM !=0");
						tax = "NA";
						Thread.sleep(1000);
						break;

					case "ACCESSORYFINANCE":
						rsAgreementItem = statement.executeQuery("Select * from cust_srvc_agrmt_item item  "
								+ "where item.customer_svc_agreement_id ='" + agrmtId
								+ "' and item.REWARD_PROGRAM_TYP_ID='" + agreementItem_itemType + "'");
						// + "' and item.ORIG_COMMITMENT_LENGTH_NUM !=0");
						tax = "NA";
						Thread.sleep(1000);
						break;
					case "NA":
						rsAgreementItem = statement.executeQuery(
								"Select * from(select a.*,row_number() over(partition by customer_svc_agreement_id order by LAST_UPDT_TS)  "
										+ "as rn from cust_srvc_agrmt_item a where customer_svc_agreement_id ='"
										+ agrmtId + "') where rn=" + agreementItem);
						tax = "NA";
						break;
					}

					while (rsAgreementItem.next()) {

						rsRewardAcc = statement1
								.executeQuery("select * from reward_account where REWARD_PROGRAM_TYP_ID='"
										+ agreementItem_itemType + "' and BUSINESS_OBJECT_ID =" + subscriptionID);

						String agrmtItemID = String.valueOf(rsAgreementItem.getInt("CUST_SRVC_AGRMT_ITEM_ID"));

						Reporting.logReporter(Status.INFO, "CUST_SRVC_AGRMT_ITEM_ID Value from DB is : " + agrmtItemID);

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_START_DT")),
								agreementItem_itemDurationStartDateTime, "COMMITMENT_EFF_START_DT");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_END_DT")),
								agreementItem_itemDurationEndDateTime, "COMMITMENT_EFF_END_DT");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getInt("ORIG_COMMITMENT_LENGTH_NUM")),
								agreementItem_itemDurationAmount, "ORIG_COMMITMENT_LENGTH_NUM");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getInt("REWARD_PROGRAM_TYP_ID")), agreementItem_itemType,
								"REWARD_PROGRAM_TYP_ID");

						if (!agreementItem_incentiveServiceCode.equals("NA")) {
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementItem.getInt("INCENTIVE_CD")),
									agreementItem_incentiveServiceCode, "INCENTIVE_CD");
						}

						if (!agreementItem_installmentStartDateTime.equals("NA")) {
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_START_DT")),
									agreementItem_installmentStartDateTime, "REWARD_INSTLMNT_START_DT");
						}
						if (!agreementItem_installmentEndDateTime.equals("NA")) {

							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_END_DT")),
									agreementItem_installmentEndDateTime, "REWARD_INSTLMNT_END_DT");
						}

						if (!agreementItem_installmentAmount.equals("NA")) {
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementItem.getInt("REWARD_INSTLMNT_QTY")),
									agreementItem_installmentAmount, "REWARD_INSTLMNT_QTY");
						}
						// INSTALLMENTS_LEFT_NUM, INSTALLMENTS_APPLIED_NUM, INSTALLMENTS_APPLIED_AMT ->
						// No need to DB validation

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getInt("RTPLN_MIN_COMMITMENT_AMT")),
								agreementItem_termOrConditionMinRatePlanValue, "RTPLN_MIN_COMMITMENT_AMT");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getInt("FEAT_MIN_COMMITMENT_AMT")),
								agreementItem_termOrConditionMinFeatureValue, "FEAT_MIN_COMMITMENT_AMT");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getInt("COMB_MIN_COMMITMENT_AMT")),
								agreementItem_termOrConditionMinCombinedValue, "COMB_MIN_COMMITMENT_AMT");

						if (!agreementItem_termOrConditionCommitmentServiceCdValue.equals("NA"))
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementItem.getString("COMMITMENT_SERVICE_CD")),
									agreementItem_termOrConditionCommitmentServiceCdValue, "COMMITMENT_SERVICE_CD");

						if (!agreementItem_termOrConditionAutoTopupCommitmentIndValue.equals("NA"))
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementItem.getString("COMMITMENT_AUTOTOPUP_IND")),
									agreementItem_termOrConditionAutoTopupCommitmentIndValue,
									"COMMITMENT_AUTOTOPUP_IND");

						if (agreementItem_itemType.equals("4") || agreementItem_itemType.equals("9")) {
							rsAgreementTax = statement2.executeQuery("select * from CUST_SRVC_AGRMT_ITM_TAX tax "
									+ "inner join CUST_SRVC_AGRMT_ITM_TAX_DTL dtl "
									+ "on tax.CUST_SRVC_AGRMT_ITM_TAX_ID = dtl.CUST_SRVC_AGRMT_ITM_TAX_ID "
									+ "and tax.CUST_SRVC_AGRMT_ITEM_ID ='" + agrmtItemID + "'");

							while (rsAgreementTax.next()) {
								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(rsAgreementTax.getString("TAX_PAYMENT_METHOD_CD")),
										agreementItem_taxPaymentMethodCode, "TAX_PAYMENT_METHOD_CD");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(rsAgreementTax.getString("TAX_PYMT_MECHANISM_CD")),
										agreementItem_taxPaymentMechanismCode, "TAX_PYMT_MECHANISM_CD");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(rsAgreementTax.getString("TAX_PAYMENT_CHANNEL_CD")),
										agreementItem_taxPaymentChannelCode, "TAX_PAYMENT_CHANNEL_CD");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(rsAgreementTax.getString("TAXATION_PROVINCE_CD")),
										agreementItem_taxProvinceCode, "TAXATION_PROVINCE_CD");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(rsAgreementTax.getString("TAX_TYPE_CD")),
										agreementItem_taxCategory, "TAX_TYPE_CD");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(rsAgreementTax.getString("TAX_RATE_PCT")), agreementItem_taxRate,
										"TAX_RATE_PCT");

								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(rsAgreementTax.getString("TAX_AMT")),
										agreementItem_taxAmountValue, "TAX_AMT");
							}

						}

						if (!agreementItem_itemType.equals("17")) {
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementItem.getInt("REDEEMED_PROMOTION_ID")),
									agreementItem_promotionid, "REDEEMED_PROMOTION_ID");

							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementItem.getDate("REDEEMED_PROMOTION_TS")),
									agreementItem_promotionPerspectiveDate, "REDEEMED_PROMOTION_TS");
						}

						if (!agreementItem_productSerialNumber.equals("NA")) {
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementItem.getString("HANDSET_SERIAL_NUM")),
									agreementItem_productSerialNumber, "HANDSET_SERIAL_NUM");
						}
						if (!agreementItem_productPriceValue.equals("NA")) {
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementItem.getInt("CATALOGUE_ITEM_PRICE_AMT")),
									agreementItem_productPriceValue, "CATALOGUE_ITEM_PRICE_AMT");
						}
						if (!agreementItem_productCharacteristicValue.equals("NA")) {
							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsAgreementItem.getString("USIM_ID")),
									agreementItem_productCharacteristicValue, "USIM_ID");
						}

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_ID")),
								agreementItem_productOfferingid, "REDEEMED_OFFER_ID");

						/*
						 * GenericUtils.validateAssertEqualsFromDB(
						 * String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TYPE_ID")),
						 * agreementItem_productOfferingRedeemedOfferContextCodeValue,
						 * "REDEEMED_OFFER_TYPE_ID");
						 */
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TIER_CD")),
								agreementItem_productOfferingOfferTierCdValue, "REDEEMED_OFFER_TIER_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TIER_CAP_AMT")),
								agreementItem_productOfferingOfferTierCapAmtValue, "REDEEMED_OFFER_TIER_CAP_AMT");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getString("DATA_SRVC_REQ_IND")),
								agreementItem_productOfferingDataCommitmentIndValue, "DATA_SRVC_REQ_IND");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getString("COMB_MIN_CMITMT_DISCHRG_IND")),
								agreementItem_productOfferingContractEnforcedPlanIndValue,
								"COMB_MIN_CMITMT_DISCHRG_IND");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getDate("REDEEMED_OFFER_TS")),
								agreementItem_productOfferingPerspectiveDate, "REDEEMED_OFFER_TS");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_SYS_ID")),
								agreementItem_productOfferingSourceSystemId, "REDEEMED_OFFER_SYS_ID");

						GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("INCENTIVE_AMT")),
								agreementItem_incentiveAmount, "INCENTIVE_AMT");

						/*
						 * if (!currentIND.equals("N")) { while (rsRewardAcc.next()) {
						 * 
						 * GenericUtils.validateAssertEqualsFromDB(
						 * String.valueOf(rsRewardAcc.getInt("CURRENCY_BAL_AMT")), "-" +
						 * agreementItem_incentiveAmount, "CURRENCY_BAL_AMT");
						 * 
						 * } } else {
						 * 
						 * GenericUtils.validateAssertEqualsFromDB(
						 * String.valueOf(rsAgreementItem.getInt("INCENTIVE_AMT")),
						 * agreementItem_incentiveAmount, "INCENTIVE_AMT");
						 * 
						 * }
						 */

					}
					rsAgreementItem.close();

				}
			}

		}
	}

	public static void responseDBCheckEarlyRenewalPenalty(String jsonString, String subscriptionID, int itemNo)
			throws SQLException {

		Statement statementRewardAcc = null;
		statementRewardAcc = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardAcc = null;

		String chargeTypeCd = null;
		String chargeCode = null;
		String adjustmentCode = null;
		int consequenceItemNo = 0;

		GetEarlyRenewalPenalty jsonPath = new GetEarlyRenewalPenalty();

		String href = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.href));

		String date = JSONUtils.checkValue(jsonString, jsonPath.date);

		String slaId = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.slaId));

		String violationRefValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.violationRefValue));

		String violationCommentLocale0 = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.violationCommentLocale0));

		String violationCommentLocale1 = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.violationCommentLocale1));

		GenericUtils.validateAssertNotNull(href, "HREF");

		GenericUtils.validateAssertEquals("EarlyRenewal", slaId, "SLAID");

		GenericUtils.validateAssertEqualsFromDB(JSONUtils.getGMTStartDate().split("T")[0], date, "DATE");

		for (int i = 0; i < itemNo; i++) {

			GetEarlyRenewalPenalty itemJsonPath = new GetEarlyRenewalPenalty(i);
			consequenceItemNo = i + 1;

			String consequenceType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceType));

			String consequenceAdjAmtName = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceAdjAmtName));

			String consequenceAdjAmtValue = JSONUtils.checkValue(jsonString, jsonPath.consequenceAdjAmtValue);

			String consequenceBillingCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingCode));

			String consequenceBillingRevCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingRevCode));

			String consequenceLoyaltyType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceLoyaltyType));
			String itemID = DBUtils.getItemType(consequenceLoyaltyType);

			String consequenceAmt = JSONUtils.checkValue(jsonString, jsonPath.consequenceAmt);

			String consequenceTypeCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceTypeCode));

			Reporting.logReporter(Status.INFO,
					"---Consequence Item No =" + consequenceItemNo + " : " + consequenceLoyaltyType);

			resultSetRewardAcc = statementRewardAcc
					.executeQuery("select * from reward_account where REWARD_PROGRAM_TYP_ID='" + itemID
							+ "' and BUSINESS_OBJECT_ID =" + subscriptionID);

			switch (consequenceLoyaltyType) {

			case "ACCESSORYFINANCE":
				chargeTypeCd = "AFHB";
				chargeCode = "AFER";
				adjustmentCode = "AFCR";
				break;

			case "BIB":
				chargeTypeCd = "BIBEPB";
				chargeCode = "BIBER";
				adjustmentCode = "BIBCR";
				break;

			case "HARDWARE":
				chargeTypeCd = "HWHB";
				chargeCode = "UPDBC";
				adjustmentCode = "UPDBCR";
				break;

			case "FINANCE":
				chargeTypeCd = "FINEPB";
				chargeCode = "UPDF";
				adjustmentCode = "UPDFCR";
				break;

			case "TAB":
				chargeTypeCd = "TABEPB";
				chargeCode = "TABREN";
				adjustmentCode = "ATABRE";
				break;

			case "HWS":
				chargeTypeCd = "HWSHB";
				chargeCode = "PHCREN";
				adjustmentCode = "APHCRE";
				break;
			}

			GenericUtils.validateAssertNotNull(violationRefValue, "VIOLATION_REF_VALUE");

			GenericUtils.validateAssertEquals("Charge", consequenceType, "TYPE");

			GenericUtils.validateAssertEquals("0", consequenceAdjAmtValue, "ADJUSTMENT_AMT");

			GenericUtils.validateAssertEquals(String.valueOf(chargeCode), consequenceBillingCode, "BILLING CODE");

			GenericUtils.validateAssertEquals(String.valueOf(adjustmentCode), consequenceBillingRevCode,
					"BILLING REVERSAL CODE");

			GenericUtils.validateAssertEquals(String.valueOf(chargeTypeCd), consequenceTypeCode, "TYPE CODE");

			while (resultSetRewardAcc.next()) {

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT")),
						"-" + consequenceAmt, "AMOUNT");

			}

			GenericUtils.validateAssertEquals("EN_CA", violationCommentLocale0, "COMMENT_0");

			GenericUtils.validateAssertEquals("FR_CA", violationCommentLocale1, "COMMENT_1");

		}

	}

	public static void validateActivationAfterRenewal(String jsonString, String subID, int itemNo) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsAgmt = null;

		Statement statement1 = null;
		statement1 = DBUtils.Conn.createStatement();
		ResultSet rsAgmtItem = null;

		rsAgmt = statement.executeQuery(
				"SELECT * FROM  CUSTOMER_SERVICE_AGREEMENT where REDEEMED_OFFER_TYPE_ID='1' and SUBSCRIPTION_ID ="
						+ subID);
		Reporting.logReporter(Status.INFO, "---Activation Record validation---");
		while (rsAgmt.next()) {
			agrmtId = String.valueOf(rsAgmt.getInt("CUSTOMER_SVC_AGREEMENT_ID"));

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgmt.getString("CURRENT_IND")), "N",
					"CURRENT_IND");
		}

		for (int i = 0; i < itemNo; i++) {

			int agreementItemNo = i + 1;
			AgreementItem agrmtItem = new AgreementItem(i);

			Itemtype = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemType));
			String agreementItem_itemType = DBUtils.getItemType(Itemtype);

			String agreementItem_itemDurationStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationStartDateTime);

			String agreementItem_itemDurationAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemDurationAmount));

			String agreementItem_itemDurationEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationEndDateTime);
			agreementItem_itemDurationEndDateTime = JSONUtils.getEndDate(agreementItem_itemDurationEndDateTime,
					agreementItem_itemDurationStartDateTime, agreementItem_itemType, agreementItem_itemDurationAmount);

			if (agreementItem_itemType.equals("15") || agreementItem_itemType.equals("16")
					|| agreementItem_itemType.equals("17"))

			{
				agreementItem_itemDurationAmount = "0";
				agreementItem_itemDurationEndDateTime = agreementItem_itemDurationStartDateTime;
			}

			Reporting.logReporter(Status.INFO, "--- Agreement Item : " + agreementItemNo + " : " + Itemtype + "---");

			rsAgmtItem = statement.executeQuery("SELECT * FROM  CUST_SRVC_AGRMT_ITEM where CUSTOMER_SVC_AGREEMENT_ID="
					+ agrmtId + " and REWARD_PROGRAM_TYP_ID='" + agreementItem_itemType + "'");

			while (rsAgmtItem.next()) {

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgmtItem.getDate("COMMITMENT_EFF_START_DT")),
						agreementItem_itemDurationStartDateTime, "COMMITMENT_EFF_START_DT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgmtItem.getDate("COMMITMENT_EFF_END_DT")),
						agreementItem_itemDurationEndDateTime, "COMMITMENT_EFF_END_DT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgmtItem.getInt("ORIG_COMMITMENT_LENGTH_NUM")),
						agreementItem_itemDurationAmount, "ORIG_COMMITMENT_LENGTH_NUM");

			}

		}
	}

	public static void responseDBCheckTerminationPenalty(String jsonString, String subscriptionID, int itemNo,
			String paymentMech) throws SQLException {

		Statement statementRewardAcc = null;
		statementRewardAcc = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardAcc = null;

		Statement statementRewardTxn = null;
		statementRewardTxn = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardTxn = null;

		String typeCode = null;
		String billingCode = null;
		String billingRev = null;
		int consequenceItemNo = 0;

		GetEarlyRenewalPenalty jsonPath = new GetEarlyRenewalPenalty();

		String href = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.href));

		String date = JSONUtils.checkValue(jsonString, jsonPath.date);

		String slaId = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.slaId));

		String violationRefValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.violationRefValue));

		String violationCommentLocale0 = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.violationCommentLocale0));

		String violationCommentLocale1 = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.violationCommentLocale1));

		GenericUtils.validateAssertNotNull(href, "HREF");

		GenericUtils.validateAssertEquals("Termination", slaId, "SLAID");

		GenericUtils.validateAssertEqualsFromDB(JSONUtils.getGMTStartDate().split("T")[0], date, "DATE");

		for (int i = 0; i < itemNo; i++) {

			GetEarlyRenewalPenalty itemJsonPath = new GetEarlyRenewalPenalty(i);
			consequenceItemNo = i + 1;

			String consequenceType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceType));

			String consequenceAdjAmtName = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceAdjAmtName));

			String consequenceAdjAmtValue = JSONUtils.checkValue(jsonString, jsonPath.consequenceAdjAmtValue);

			String consequenceBillingCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingCode));

			String consequenceBillingRevCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingRevCode));

			String consequenceLoyaltyType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceLoyaltyType));
			String itemID = DBUtils.getItemType(consequenceLoyaltyType);

			String consequenceAmt = JSONUtils.checkValue(jsonString, jsonPath.consequenceAmt);

			String consequenceTypeCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceTypeCode));

			if (consequenceType.equals("CreditAdjustment")) {
				Reporting.logReporter(Status.INFO,
						"---Consequence Item No =" + consequenceItemNo + " : " + consequenceTypeCode);
				switch (consequenceTypeCode) {

				case "ACTIVATIONBILLCREDIT":
					billingCode = "ACTCR";
					billingRev = "ACTCH";
					typeCode = "ACTIVATIONBILLCREDIT";
					break;

				case "RENEWALBILLCREDIT":
					billingCode = "RCCC";
					billingRev = "RCAC";
					typeCode = "RENEWALBILLCREDIT";
					break;
				}

				GenericUtils.validateAssertEquals("CreditAdjustment", consequenceType, "TYPE");

				GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode, "BILLING CODE");

				GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
						"BILLING REVERSAL CODE");

				GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

			} else {
				Reporting.logReporter(Status.INFO,
						"---Consequence Item No =" + consequenceItemNo + " : " + consequenceLoyaltyType);

				resultSetRewardAcc = statementRewardAcc
						.executeQuery("select * from reward_account rewAcc inner join reward_txn rewTxn "
								+ " on rewAcc.REWARD_ACCOUNT_ID = rewTxn.REWARD_ACCOUNT_ID "
								+ "And rewAcc.REWARD_PROGRAM_TYP_ID='" + itemID + "' and rewAcc.BUSINESS_OBJECT_ID ="
								+ subscriptionID + " and rewTxn.REWARD_TXN_RSN_ID='1'");
				switch (consequenceLoyaltyType) {

				case "ACCESSORYFINANCE":
					billingCode = "AFTC";
					billingRev = "AFCR";
					typeCode = "AFDB";// "AFECF"
					break;

				case "BIB":
					billingCode = "BIBTC";
					billingRev = "BIBCR";
					typeCode = "BIBDB";
					if (paymentMech.equals("BIB_TELUS_PENDING") || paymentMech.equals("TRADE_IN_PENDING")) {
						billingCode = "BIBPAY";
						typeCode = "BIBPDB";
					}
					break;

				case "HARDWARE":
					billingCode = "UPDBC";
					billingRev = "UPDBCR";
					typeCode = "HWHB";
					break;

				case "FINANCE":
					billingCode = "UPDF";
					billingRev = "UPDFCR";
					typeCode = "FINEPB";
					break;

				case "TAB":
					billingCode = "TABBCA";
					billingRev = "ATABCA";
					typeCode = "TABDB";
					break;

				case "HWS":
					billingCode = "PHCCAN";
					billingRev = "APHCCA";
					typeCode = "HWSDB";
					break;

				case "ACTIVATIONBILLCREDIT":
					billingCode = "BCLBC";
					billingRev = "BCLBR";
					typeCode = "ACBDB";
					break;

				case "RENEWALBILLCREDIT":
					billingCode = "RCBD";
					billingRev = "RCBC";
					typeCode = "RCBDB";
					break;
				}
				if (consequenceTypeCode.contains("ECF")) {
					typeCode = "AFECF";

				}
				GenericUtils.validateAssertNotNull(violationRefValue, "VIOLATION_REF_VALUE");

				GenericUtils.validateAssertEquals("Charge", consequenceType, "TYPE");

				GenericUtils.validateAssertEquals("0", consequenceAdjAmtValue, "ADJUSTMENT_AMT");

				GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode, "BILLING CODE");

				GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
						"BILLING REVERSAL CODE");

				GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

				while (resultSetRewardAcc.next()) {

					if (consequenceAmt.equals("0")) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT")), consequenceAmt,
								"AMOUNT");
					} else if (consequenceTypeCode.contains("ECF")) {
						GenericUtils.validateAssertEquals("100", consequenceAmt, "AMOUNT");
					} else if (paymentMech.equals("BIB_TELUS_PENDING") || paymentMech.equals("TRADE_IN_PENDING")) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_AMT")), "-" + consequenceAmt,
								"AMOUNT");

					} else {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT")), "-" + consequenceAmt,
								"AMOUNT");
					}
				}

				GenericUtils.validateAssertEquals("EN_CA", violationCommentLocale0, "COMMENT_0");

				GenericUtils.validateAssertEquals("FR_CA", violationCommentLocale1, "COMMENT_1");

			}
		}

	}

	public static void responseDBCheckMigrationPenalty(String jsonString, String subscriptionID, int itemNo,
			String paymentMech) throws SQLException {

		Statement statementRewardAcc = null;
		statementRewardAcc = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardAcc = null;

		Statement statementRewardTxn = null;
		statementRewardTxn = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardTxn = null;

		String payMechId = null;
		String typeCode = null;
		String billingCode = null;
		String billingRev = null;
		int consequenceItemNo = 0;

		switch (paymentMech) {
		case "NA":
			payMechId = "1";
			break;

		case "BILL":
			payMechId = "101";
			break;

		case "BIB_TELUS_PENDING":
		case "TRADE_IN_PENDING":
			payMechId = "217";
			break;

		}

		GetEarlyRenewalPenalty jsonPath = new GetEarlyRenewalPenalty();

		String href = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.href));

		String date = JSONUtils.checkValue(jsonString, jsonPath.date);

		String slaId = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.slaId));

		String violationRefValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.violationRefValue));

		String violationCommentLocale0 = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.violationCommentLocale0));

		String violationCommentLocale1 = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.violationCommentLocale1));

		GenericUtils.validateAssertNotNull(href, "HREF");

		GenericUtils.validateAssertEquals("Migration", slaId, "SLAID");

		GenericUtils.validateAssertEqualsFromDB(JSONUtils.getGMTStartDate().split("T")[0], date, "DATE");

		for (int i = 0; i < itemNo; i++) {

			GetEarlyRenewalPenalty itemJsonPath = new GetEarlyRenewalPenalty(i);
			consequenceItemNo = i + 1;

			String consequenceType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceType));

			String consequenceAdjAmtName = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceAdjAmtName));

			String consequenceAdjAmtValue = JSONUtils.checkValue(jsonString, jsonPath.consequenceAdjAmtValue);

			String consequenceBillingCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingCode));

			String consequenceBillingRevCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingRevCode));

			String consequenceLoyaltyType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceLoyaltyType));
			String itemID = DBUtils.getItemType(consequenceLoyaltyType);

			String consequenceAmt = JSONUtils.checkValue(jsonString, jsonPath.consequenceAmt);

			String consequenceTypeCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceTypeCode));

			if (consequenceType.equals("CreditAdjustment")) {
				Reporting.logReporter(Status.INFO,
						"---Consequence Item No =" + consequenceItemNo + " : " + consequenceTypeCode);
				switch (consequenceTypeCode) {

				case "ACTIVATIONBILLCREDIT":
					billingCode = "ACTCR";
					billingRev = "ACTCH";
					typeCode = "ACTIVATIONBILLCREDIT";
					break;

				case "RENEWALBILLCREDIT":
					billingCode = "RCCC";
					billingRev = "RCAC";
					typeCode = "RENEWALBILLCREDIT";
					break;
				}

				GenericUtils.validateAssertEquals("CreditAdjustment", consequenceType, "TYPE");

				GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode, "BILLING CODE");

				GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
						"BILLING REVERSAL CODE");

				GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

			} else {
				Reporting.logReporter(Status.INFO,
						"---Consequence Item No =" + consequenceItemNo + " : " + consequenceLoyaltyType);

				resultSetRewardAcc = statementRewardAcc
						.executeQuery("select * from reward_account rewAcc inner join reward_txn rewTxn "
								+ " on rewAcc.REWARD_ACCOUNT_ID = rewTxn.REWARD_ACCOUNT_ID "
								+ "And rewAcc.REWARD_PROGRAM_TYP_ID='" + itemID + "' and rewAcc.BUSINESS_OBJECT_ID ="
								+ subscriptionID + " and rewTxn.REWARD_TXN_RSN_ID=" + payMechId);
				switch (consequenceLoyaltyType) {

				case "ACCESSORYFINANCE":
					billingCode = "AFTC";
					billingRev = "AFCR";
					typeCode = "AFDB";// "AFECF"
					break;

				case "BIB":
					billingCode = "BIBTC";
					billingRev = "BIBCR";
					typeCode = "BIBDB";
					if (paymentMech.equals("BIB_TELUS_PENDING") || paymentMech.equals("TRADE_IN_PENDING")) {
						billingCode = "BIBPAY";
						typeCode = "BIBPDB";
					}
					break;

				case "HARDWARE":
					billingCode = "DBLBC";
					billingRev = "DBLBR";
					typeCode = "HWDB";
					break;

				case "FINANCE":
					billingCode = "DBMF";
					billingRev = "DBMFCR";
					typeCode = "FINDB";
					break;

				case "TAB":
					billingCode = "TABBCA";
					billingRev = "ATABCA";
					typeCode = "TABDB";
					break;

				case "HWS":
					billingCode = "PHCCAN";
					billingRev = "APHCCA";
					typeCode = "HWSDB";
					break;

				case "ACTIVATIONBILLCREDIT":
					billingCode = "BCLBC";
					billingRev = "BCLBR";
					typeCode = "ACBDB";
					break;

				/*
				 * case "ACTIVATIONBILLCREDIT": billingCode = "CBFC"; billingRev = "CBFR";
				 * typeCode = "ACBECF"; break;
				 */

				case "RENEWALBILLCREDIT":
					billingCode = "RCBD";
					billingRev = "RCBC";
					typeCode = "RCBDB";
					break;
				}
				if (consequenceTypeCode.contains("ECF")) {
					typeCode = "AFECF";

				}
				GenericUtils.validateAssertNotNull(violationRefValue, "VIOLATION_REF_VALUE");

				GenericUtils.validateAssertEquals("Charge", consequenceType, "TYPE");

				GenericUtils.validateAssertEquals("0", consequenceAdjAmtValue, "ADJUSTMENT_AMT");

				GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode, "BILLING CODE");

				GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
						"BILLING REVERSAL CODE");

				GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

				while (resultSetRewardAcc.next()) {

					if (consequenceAmt.equals("0")) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT")), consequenceAmt,
								"AMOUNT");
					} else if (consequenceTypeCode.contains("ECF")) {
						GenericUtils.validateAssertEquals("100", consequenceAmt, "AMOUNT");
					} else if (paymentMech.equals("BILL") || paymentMech.equals("NA")) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT")), "-" + consequenceAmt,
								"AMOUNT");

					} else if (paymentMech.equals("TRADE_IN_PENDING") || paymentMech.equals("BIB_TELUS_PENDING")) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_AMT")), consequenceAmt, "AMOUNT");

					} else {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT")), "-" + consequenceAmt,
								"AMOUNT");
					}
				}

				GenericUtils.validateAssertEquals("EN_CA", violationCommentLocale0, "COMMENT_0");

				GenericUtils.validateAssertEquals("FR_CA", violationCommentLocale1, "COMMENT_1");

			}
		}

	}

	public static void responseDBCheckReturnAdjustment(String jsonString, String src_jsonString) throws SQLException {

		String subscriptionID = null;
		HashMap<Integer, String> noOfAgrmtItem = new HashMap<Integer, String>();
		HashMap<Integer, String> noOfTypeCode = new HashMap<Integer, String>();
		String agrmtItemValue = null;
		String typeCd = null;
		int agrmtItemNo = 0;
		int typeCodeItemNo = 0;

		Statement statementRewardAcc = null;
		statementRewardAcc = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardAcc = null;

		String typeCode = null;
		String billingCode = null;
		String billingRev = null;
		int consequenceItemNo = 0;

		subscriptionID = String.valueOf(
				JSONUtils.getJSONKeyValueUsingJsonPath(src_jsonString, "$.agreementCreate.relatedParty[2].id"));

		do {

			String renewalItemTypePath = "$.payment[" + agrmtItemNo + "].relatedParty[0].characteristic[2].value";
			String paymentMech = "$.payment[" + agrmtItemNo + "].paymentMechanism";
			agrmtItemValue = JSONUtils.getJSONKeyValueUsingJsonPath(src_jsonString, renewalItemTypePath);
			if (!agrmtItemValue.equals("NA"))
				if (!paymentMech.equals("BIB_TELUS_PENDING")) {
					noOfAgrmtItem.put(agrmtItemNo, agrmtItemValue);
				}

			agrmtItemNo++;
		} while (!agrmtItemValue.equals("NA"));

		do {
			String typeCodePath = "$.violation.consequence[" + typeCodeItemNo + "].typeCode";
			typeCd = JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, typeCodePath);
			switch (typeCd) {
			case "FINEPB":
				typeCd = "FINANCE";
				break;
			case "HWHB":
				typeCd = "HARDWARE";
				break;
			case "BIBEPB":
				typeCd = "BIB";
				break;

			}
			if (!typeCd.equals("NA"))
				noOfTypeCode.put(typeCodeItemNo, typeCd);
			typeCodeItemNo++;
		} while (!typeCd.equals("NA"));

		GetReturnAdjustmentList jsonPath = new GetReturnAdjustmentList();

		String href = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.href));

		String date = JSONUtils.checkValue(jsonString, jsonPath.date);

		String slaId = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.slaId));

		GenericUtils.validateAssertNotNull(href, "HREF");

		GenericUtils.validateAssertEquals("ReturnAdjustment", slaId, "SLAID");

		GenericUtils.validateAssertEqualsFromDB(JSONUtils.getGMTStartDate().split("T")[0], date, "DATE");

		for (int i = 0; i < noOfAgrmtItem.size(); i++) {

			for (int j = 0; j < noOfTypeCode.size(); j++) {

				if (noOfAgrmtItem.get(i).equals(noOfTypeCode.get(j))) {

					GetReturnAdjustmentList itemJsonPath = new GetReturnAdjustmentList(j);
					consequenceItemNo++;

					String consequenceType = String
							.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, itemJsonPath.consequenceType));

					String consequenceBillingCode = String.valueOf(
							JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, itemJsonPath.consequenceBillingCode));

					String consequenceBillingRevCode = String.valueOf(
							JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, itemJsonPath.consequenceBillingRevCode));

					String consequenceTypeCode = String.valueOf(
							JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, itemJsonPath.consequenceTypeCode));

					if (consequenceType.equals("CreditAdjustment")) {
						Reporting.logReporter(Status.INFO,
								"---Consequence Item No =" + consequenceItemNo + " : " + consequenceTypeCode);
						switch (consequenceTypeCode) {

						case "ACTIVATIONBILLCREDIT":
							billingCode = "ACTCR";
							billingRev = "ACTCH";
							typeCode = "ACTIVATIONBILLCREDIT";
							break;

						case "RENEWALBILLCREDIT":
							billingCode = "RCCC";
							billingRev = "RCAC";
							typeCode = "RENEWALBILLCREDIT";
							break;
						}

						GenericUtils.validateAssertEquals("CreditAdjustment", consequenceType, "TYPE");

						GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode,
								"BILLING CODE");

						GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
								"BILLING REVERSAL CODE");

						GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

					} else {
						Reporting.logReporter(Status.INFO,
								"---Agreement Item No =" + consequenceItemNo + " : " + noOfTypeCode.get(j));

						switch (noOfTypeCode.get(j)) {

						case "ACCESSORYFINANCE":
							billingCode = "AFTC";
							billingRev = "AFCR";
							typeCode = "AFDB";// "AFECF"
							break;

						case "BIB":
							billingCode = "BIBER";
							billingRev = "BIBCR";
							typeCode = "BIBEPB";
							break;

						case "HARDWARE":
							billingCode = "UPDBC";
							billingRev = "UPDBCR";
							typeCode = "HWHB";
							break;

						case "FINANCE":
							billingCode = "UPDF";
							billingRev = "UPDFCR";
							typeCode = "FINEPB";
							break;

						case "TAB":
							billingCode = "TABREN";
							billingRev = "ATABRE";
							typeCode = "TABEPB";
							break;

						case "HWS":
							billingCode = "PHCREN";
							billingRev = "APHCRE";
							typeCode = "HWSHB";
							break;

						case "ACTIVATIONBILLCREDIT":
							billingCode = "BCLBC";
							billingRev = "BCLBR";
							typeCode = "ACBDB";
							break;

						case "RENEWALBILLCREDIT":
							billingCode = "RCBD";
							billingRev = "RCBC";
							typeCode = "RCBDB";
							break;
						}

						GenericUtils.validateAssertEquals("ChargeAdjustment", consequenceType, "TYPE");

						GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode,
								"BILLING CODE");

						GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
								"BILLING REVERSAL CODE");

						GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

					}
				}
			}
		}

	}

	public static void responseDBCheckResumePenalty(String jsonString, String subscriptionID, int itemNo)
			throws SQLException {

		Statement statementRewardAcc = null;
		statementRewardAcc = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardAcc = null;

		Statement statementRewardTxn = null;
		statementRewardTxn = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardTxn = null;

		String typeCode = null;
		String billingCode = null;
		String billingRev = null;
		int consequenceItemNo = 0;

		GetEarlyRenewalPenalty jsonPath = new GetEarlyRenewalPenalty();

		String href = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.href));

		String date = JSONUtils.checkValue(jsonString, jsonPath.date);

		String slaId = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.slaId));

		GenericUtils.validateAssertNotNull(href, "HREF");

		GenericUtils.validateAssertEquals("ResumeAdjustment", slaId, "SLAID");

		GenericUtils.validateAssertEqualsFromDB(JSONUtils.getGMTStartDate().split("T")[0], date, "DATE");

		for (int i = 0; i < itemNo; i++) {

			GetEarlyRenewalPenalty itemJsonPath = new GetEarlyRenewalPenalty(i);
			consequenceItemNo = i + 1;

			String consequenceType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceType));

			String consequenceBillingCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingCode));

			String consequenceBillingRevCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingRevCode));

			String consequenceTypeCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceTypeCode));

			{
				Reporting.logReporter(Status.INFO,
						"---Consequence Item No =" + consequenceItemNo + " : " + consequenceTypeCode);

				switch (consequenceTypeCode) {

				case "HWDB":
					billingCode = "DBLBC";
					billingRev = "DBLBR";
					typeCode = "HWDB";
					break;

				case "BIBDB":
					billingCode = "BIBTC";
					billingRev = "BIBCR";
					typeCode = "BIBDB";
					break;

				case "FINDB":
					billingCode = "DBMF";
					billingRev = "DBMFCR";
					typeCode = "FINDB";
					break;

				case "TABDB":
					billingCode = "TABBCA";
					billingRev = "ATABCA";
					typeCode = "TABDB";
					break;

				case "HWSDB":
					billingCode = "PHCCAN";
					billingRev = "APHCCA";
					typeCode = "HWSDB";
					break;

				case "ACBDB":
					billingCode = "BCLBC";
					billingRev = "BCLBR";
					typeCode = "ACBDB";
					break;

				case "RCBDB":
					billingCode = "RCBD";
					billingRev = "RCBC";
					typeCode = "RCBDB";
					break;
				}
				if (consequenceTypeCode.contains("ECF")) {
					billingCode = "CBFC";
					billingRev = "CBFR";
					typeCode = "ECF";

				}

				GenericUtils.validateAssertEquals("ChargeAdjustment", consequenceType, "TYPE");

				GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode, "BILLING CODE");

				GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
						"BILLING REVERSAL CODE");

				GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

			}
		}

	}

	public static void responseDBCheckChangeServPenalty(String jsonStringActv, String jsonString, String subscriptionID,
			int itemNo) throws SQLException {

		Statement statementRewardAcc = null;
		statementRewardAcc = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardAcc = null;

		Statement statementRewardTxn = null;
		statementRewardTxn = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardTxn = null;

		String typeCode = null;
		String billingCode = null;
		String billingRev = null;
		int consequenceItemNo = 0;
		int subscriptionsNo = 0;

		GetEarlyRenewalPenalty jsonPath = new GetEarlyRenewalPenalty();

		String href = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.href));

		String date = JSONUtils.checkValue(jsonString, jsonPath.date);

		String slaId = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.slaId));

		GenericUtils.validateAssertNotNull(href, "HREF");

		GenericUtils.validateAssertEquals("ServiceChange", slaId, "SLAID");

		GenericUtils.validateAssertEqualsFromDB(JSONUtils.getGMTStartDate().split("T")[0], date, "DATE");

		for (int i = 0; i < itemNo; i++) {

			GetEarlyRenewalPenalty itemJsonPath = new GetEarlyRenewalPenalty(i);
			consequenceItemNo = i + 1;

			String consequenceType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceType));

			String consequenceAdjAmtName = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceAdjAmtName));

			String consequenceAdjAmtValue = JSONUtils.checkValue(jsonString, jsonPath.consequenceAdjAmtValue);

			String consequenceBillingCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingCode));

			String consequenceBillingRevCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingRevCode));

			String consequenceLoyaltyType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceLoyaltyType));
			String itemID = DBUtils.getItemType(consequenceLoyaltyType);

			String consequenceAmt = JSONUtils.checkValue(jsonString, jsonPath.consequenceAmt);

			String consequenceTypeCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceTypeCode));

			if (consequenceType.equals("CreditAdjustment")) {
				Reporting.logReporter(Status.INFO,
						"---Consequence Item No =" + consequenceItemNo + " : " + consequenceTypeCode);
				switch (consequenceTypeCode) {

				case "ACTIVATIONBILLCREDIT":
					billingCode = "ACTCR";
					billingRev = "ACTCH";
					typeCode = "ACTIVATIONBILLCREDIT";
					break;

				case "RENEWALBILLCREDIT":
					billingCode = "RCCC";
					billingRev = "RCAC";
					typeCode = "RENEWALBILLCREDIT";
					break;
				}

				GenericUtils.validateAssertEquals("CreditAdjustment", consequenceType, "TYPE");

				GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode, "BILLING CODE");

				GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
						"BILLING REVERSAL CODE");

				GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

			} else {
				Reporting.logReporter(Status.INFO,
						"---Consequence Item No =" + consequenceItemNo + " : " + consequenceLoyaltyType);

				resultSetRewardAcc = statementRewardAcc
						.executeQuery("select * from reward_account rewAcc inner join reward_txn rewTxn "
								+ " on rewAcc.REWARD_ACCOUNT_ID = rewTxn.REWARD_ACCOUNT_ID "
								+ "And rewAcc.REWARD_PROGRAM_TYP_ID='" + itemID + "' and rewAcc.BUSINESS_OBJECT_ID ="
								+ subscriptionID);

				switch (consequenceLoyaltyType) {

				case "ACCESSORYFINANCE":
					billingCode = "AFTC";
					billingRev = "AFCR";
					typeCode = "AFDB";// "AFECF"
					break;

				case "BIB":
					billingCode = "BIBTC";
					billingRev = "BIBCR";
					typeCode = "BIBDB";
					break;

				case "HARDWARE":
					billingCode = "DBLBC";
					billingRev = "DBLBR";
					typeCode = "HWDB";
					break;

				case "FINANCE":
					billingCode = "DBMF";
					billingRev = "DBMFCR";
					typeCode = "FINDB";
					break;

				case "TAB":
					billingCode = "TABMSC";
					billingRev = "ATABMS";
					typeCode = "TABMSC";
					break;

				case "HWS":
					billingCode = "PHCMSC";
					billingRev = "APHCMS";
					typeCode = "HWSMSC";
					break;

				case "ACTIVATIONBILLCREDIT":
					billingCode = "BCLBC";
					billingRev = "BCLBR";
					typeCode = "ACBDB";
					break;

				/*
				 * case "ACTIVATIONBILLCREDIT": billingCode = "CBFC"; billingRev = "CBFR";
				 * typeCode = "ACBECF"; break;
				 */

				case "RENEWALBILLCREDIT":
					billingCode = "RCBD";
					billingRev = "RCBC";
					typeCode = "RCBDB";
					break;
				}
				if (consequenceTypeCode.contains("ECF")) {
					typeCode = "AFECF";

				}
				GenericUtils.validateAssertEquals("Charge", consequenceType, "TYPE");

				GenericUtils.validateAssertEquals("0", consequenceAdjAmtValue, "ADJUSTMENT_AMT");

				GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode, "BILLING CODE");

				GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
						"BILLING REVERSAL CODE");

				GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

				while (resultSetRewardAcc.next()) {

					if (consequenceAmt.equals("0")) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT")), consequenceAmt,
								"AMOUNT");
					} else {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT")), "-" + consequenceAmt,
								"AMOUNT");

					}
				}
			}
		}

	}

	public static void valuesFromDBActivation(String jsonString) throws SQLException {

		/*
		 * #(endDate) #(productId) #(serialNo) #(redeemOfferId)
		 */

		ActivationPayloadJsonPath jsonPath = new ActivationPayloadJsonPath();
		String oldserialNo = null;
		String relatedParty_Subid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Subid));

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsAgreementItem = null;

		rsAgreementItem = statement.executeQuery("select * from CUSTOMER_SERVICE_AGREEMENT agrmt \r\n"
				+ "inner join cust_srvc_agrmt_item item\r\n"
				+ "on agrmt.CUSTOMER_SVC_AGREEMENT_ID=item.CUSTOMER_SVC_AGREEMENT_ID\r\n"
				+ "and agrmt.SUBSCRIPTION_ID='" + relatedParty_Subid + "'\r\n" + "and item.product_id is not null\r\n"
				+ "and HANDSET_SERIAL_NUM is not null " + "and rownum=1");

		while (rsAgreementItem.next()) {

			String startDate = String.valueOf(rsAgreementItem.getDate("AGREEMENT_START_DT")) + "T00:00:00-0500";
			String endDate = String.valueOf(rsAgreementItem.getDate("AGREEMENT_END_DT")) + "T00:00:00-0500";
			String productId = String.valueOf(rsAgreementItem.getString("PRODUCT_ID"));
			oldserialNo = String.valueOf(rsAgreementItem.getString("HANDSET_SERIAL_NUM"));
			String newSerialNo = "9009375811223344";
			String redeemOfferId = String.valueOf(rsAgreementItem.getString("REDEEMED_OFFER_ID"));

			System.setProperty("karate.startDate", startDate);
			System.setProperty("karate.endDate", endDate);
			System.setProperty("karate.productId", productId);
			System.setProperty("karate.serialNo", newSerialNo);
			System.setProperty("karate.redeemOfferId", redeemOfferId);

		}

		Reporting.logReporter(Status.INFO, "Device Serial Number before Update : " + oldserialNo);

	}

	public static void serialNoUpdateCheck(String jsonString) throws SQLException {

		UpdateDeviceSerialNo jsonPath = new UpdateDeviceSerialNo();

		String relatedParty_Subid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Subid));

		String serialNumber = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.serialNumber));

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsAgreementItem = null;

		rsAgreementItem = statement.executeQuery(
				"select item.HANDSET_SERIAL_NUM,item.REWARD_PROGRAM_TYP_ID from CUSTOMER_SERVICE_AGREEMENT agrmt \r\n"
						+ "inner join cust_srvc_agrmt_item item\r\n"
						+ "on agrmt.CUSTOMER_SVC_AGREEMENT_ID=item.CUSTOMER_SVC_AGREEMENT_ID\r\n"
						+ "and agrmt.SUBSCRIPTION_ID='" + relatedParty_Subid + "'\r\n"
						+ "and agrmt.current_ind='Y'\r\n");

		while (rsAgreementItem.next()) {

			String rewardType = String.valueOf(rsAgreementItem.getString("REWARD_PROGRAM_TYP_ID"));
			String dbSerialNumber = String.valueOf(rsAgreementItem.getString("HANDSET_SERIAL_NUM"));

			Reporting.logReporter(Status.INFO, "Reward type : " + rewardType);

			if (rewardType.equals("17") && dbSerialNumber.equals("null")) {
				Reporting.logReporter(Status.INFO,
						"AccessoryFinance Serial number is null and not updated : " + dbSerialNumber);
			} else if (rewardType.equals("4") && !(serialNumber.equals(dbSerialNumber))) {
				Reporting.logReporter(Status.INFO,
						"ActivationBillCredit Serial number is not updated : " + dbSerialNumber);
			} else {

				GenericUtils.validateAssertEqualsFromDB(dbSerialNumber, serialNumber, "HANDSET_SERIAL_NUM");
			}

		}

	}

	public static void valuesFromDBRenewal(String jsonString) throws SQLException {

		/*
		 * #(endDate) #(productId) #(serialNo) #(redeemOfferId)
		 */

		Renewal jsonPath = new Renewal();

		String relatedParty_Subid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Subid));

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsAgreementItem = null;

		rsAgreementItem = statement.executeQuery(
				"select * from CUSTOMER_SERVICE_AGREEMENT agrmt \r\n" + "inner join cust_srvc_agrmt_item item\r\n"
						+ "on agrmt.CUSTOMER_SVC_AGREEMENT_ID=item.CUSTOMER_SVC_AGREEMENT_ID\r\n"
						+ "and agrmt.SUBSCRIPTION_ID='" + relatedParty_Subid + "'\r\n"
						+ "and item.product_id is not null\r\n" + "and HANDSET_SERIAL_NUM is not null "
						+ "and agrmt.REDEEMED_OFFER_TYPE_ID='2' and agrmt.CURRENT_IND='Y' " + "and rownum=1");

		while (rsAgreementItem.next()) {

			String startDate = String.valueOf(rsAgreementItem.getDate("AGREEMENT_START_DT")) + "T00:00:00-0500";
			String endDate = String.valueOf(rsAgreementItem.getDate("AGREEMENT_END_DT")) + "T00:00:00-0500";
			String productId = String.valueOf(rsAgreementItem.getString("PRODUCT_ID"));
			String oldserialNo = String.valueOf(rsAgreementItem.getString("HANDSET_SERIAL_NUM"));
			String newSerialNo = "9009375611223344";
			String redeemOfferId = String.valueOf(rsAgreementItem.getString("REDEEMED_OFFER_ID"));

			System.setProperty("karate.startDate", startDate);
			System.setProperty("karate.endDate", endDate);
			System.setProperty("karate.productId", productId);
			System.setProperty("karate.serialNo", newSerialNo);
			System.setProperty("karate.redeemOfferId", redeemOfferId);

		}

	}

	public static void responseDBCheckAccTerminationPenalty(String jsonString, String subscriptionID, int itemNo,
			int sub) throws SQLException {

		Statement statementRewardAcc = null;
		statementRewardAcc = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardAcc = null;

		Statement statementRewardTxn = null;
		statementRewardTxn = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardTxn = null;

		String typeCode = null;
		String billingCode = null;
		String billingRev = null;
		int consequenceItemNo = 0;
		int subscriptionsNo = 0;

		GetAccTerminationPenalty jsonPath = new GetAccTerminationPenalty();

		String href = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.href));

		String date = JSONUtils.checkValue(jsonString, jsonPath.date);

		String slaId = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.slaId));

		GenericUtils.validateAssertNotNull(href, "HREF");

		GenericUtils.validateAssertEquals("BanTermination", slaId, "SLAID");

		GenericUtils.validateAssertEqualsFromDB(JSONUtils.getGMTStartDate().split("T")[0], date, "DATE");

		for (int i = 0; i < sub; i++) {

			GetAccTerminationPenalty subJsonPath = new GetAccTerminationPenalty("subscription", i);
			subscriptionsNo = i + 1;

			String relatedParty_Subid = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Subid));

			String relatedParty_SubscriberNum = JSONUtils.checkValue(jsonString, jsonPath.relatedParty_SubscriberNum);

		}

		for (int i = 0; i < itemNo; i++) {

			GetAccTerminationPenalty itemJsonPath = new GetAccTerminationPenalty(i);
			consequenceItemNo = i + 1;

			String consequenceType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceType));

			String consequenceAdjAmtName = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceAdjAmtName));

			String consequenceAdjAmtValue = JSONUtils.checkValue(jsonString, jsonPath.consequenceAdjAmtValue);

			String consequenceBillingCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingCode));

			String consequenceBillingRevCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingRevCode));

			String consequenceLoyaltyType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceLoyaltyType));
			String itemID = DBUtils.getItemType(consequenceLoyaltyType);

			String consequenceAmt = JSONUtils.checkValue(jsonString, jsonPath.consequenceAmt);

			String consequenceTypeCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceTypeCode));

			if (consequenceType.equals("CreditAdjustment")) {
				Reporting.logReporter(Status.INFO,
						"---Consequence Item No =" + consequenceItemNo + " : " + consequenceTypeCode);
				switch (consequenceTypeCode) {

				case "ACTIVATIONBILLCREDIT":
					billingCode = "ACTCR";
					billingRev = "ACTCH";
					typeCode = "ACTIVATIONBILLCREDIT";
					break;

				case "RENEWALBILLCREDIT":
					billingCode = "RCCC";
					billingRev = "RCAC";
					typeCode = "RENEWALBILLCREDIT";
					break;
				}

				GenericUtils.validateAssertEquals("CreditAdjustment", consequenceType, "TYPE");

				GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode, "BILLING CODE");

				GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
						"BILLING REVERSAL CODE");

				GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

			} else {
				Reporting.logReporter(Status.INFO,
						"---Consequence Item No =" + consequenceItemNo + " : " + consequenceLoyaltyType);

				resultSetRewardAcc = statementRewardAcc
						.executeQuery("select * from reward_account rewAcc inner join reward_txn rewTxn "
								+ " on rewAcc.REWARD_ACCOUNT_ID = rewTxn.REWARD_ACCOUNT_ID "
								+ "And rewAcc.REWARD_PROGRAM_TYP_ID='" + itemID + "' and rewAcc.BUSINESS_OBJECT_ID ="
								+ subscriptionID);

				switch (consequenceLoyaltyType) {

				case "ACCESSORYFINANCE":
					billingCode = "AFTC";
					billingRev = "AFCR";
					typeCode = "AFDB";// "AFECF"
					break;

				case "BIB":
					billingCode = "BIBTC";
					billingRev = "BIBCR";
					typeCode = "BIBDB";
					break;

				case "HARDWARE":
					billingCode = "DBLBC";
					billingRev = "DBLBR";
					typeCode = "HWDB";
					break;

				case "FINANCE":
					billingCode = "DBMF";
					billingRev = "DBMFCR";
					typeCode = "FINDB";
					break;

				case "TAB":
					billingCode = "TABBCA";
					billingRev = "ATABCA";
					typeCode = "TABDB";
					break;

				case "HWS":
					billingCode = "PHCCAN";
					billingRev = "APHCCA";
					typeCode = "HWSDB";
					break;

				case "ACTIVATIONBILLCREDIT":
					billingCode = "BCLBC";
					billingRev = "BCLBR";
					typeCode = "ACBDB";
					break;

				/*
				 * case "ACTIVATIONBILLCREDIT": billingCode = "CBFC"; billingRev = "CBFR";
				 * typeCode = "ACBECF"; break;
				 */

				case "RENEWALBILLCREDIT":
					billingCode = "RCBD";
					billingRev = "RCBC";
					typeCode = "RCBDB";
					break;
				}
				if (consequenceTypeCode.contains("ECF")) {
					typeCode = "AFECF";

				}
				GenericUtils.validateAssertEquals("Charge", consequenceType, "TYPE");

				GenericUtils.validateAssertEquals("0", consequenceAdjAmtValue, "ADJUSTMENT_AMT");

				GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode, "BILLING CODE");

				GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
						"BILLING REVERSAL CODE");

				GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

				while (resultSetRewardAcc.next()) {

					if (consequenceAmt.equals("0")) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT")), consequenceAmt,
								"AMOUNT");
					} else if (itemID.equals("1") || itemID.equals("14")) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT") * sub),
								"-" + consequenceAmt, "AMOUNT");

					} else if (!itemID.equals("1") || !itemID.equals("14")) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT")), "-" + consequenceAmt,
								"AMOUNT");

					}
				}
			}
		}

	}

	public static void responseDBCheckReturnAdjustment(String jsonString, String subscriptionID, int itemNo,
			String paymentMech) throws SQLException {

		Statement statementRewardAcc = null;
		statementRewardAcc = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardAcc = null;

		Statement statementRewardTxn = null;
		statementRewardTxn = DBUtils.Conn.createStatement();
		ResultSet resultSetRewardTxn = null;

		String typeCode = null;
		String billingCode = null;
		String billingRev = null;
		int consequenceItemNo = 0;

		GetEarlyRenewalPenalty jsonPath = new GetEarlyRenewalPenalty();

		String href = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.href));

		String date = JSONUtils.checkValue(jsonString, jsonPath.date);

		String slaId = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.slaId));

		String violationRefValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.violationRefValue));

		String violationCommentLocale0 = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.violationCommentLocale0));

		String violationCommentLocale1 = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.violationCommentLocale1));

		GenericUtils.validateAssertNotNull(href, "HREF");

		GenericUtils.validateAssertEquals("ReturnAdjustment", slaId, "SLAID");

		GenericUtils.validateAssertEqualsFromDB(JSONUtils.getGMTStartDate().split("T")[0], date, "DATE");

		for (int i = 0; i < itemNo; i++) {

			GetEarlyRenewalPenalty itemJsonPath = new GetEarlyRenewalPenalty(i);
			consequenceItemNo = i + 1;

			String consequenceType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceType));

			String consequenceAdjAmtName = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceAdjAmtName));

			String consequenceAdjAmtValue = JSONUtils.checkValue(jsonString, jsonPath.consequenceAdjAmtValue);

			String consequenceBillingCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingCode));

			String consequenceBillingRevCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceBillingRevCode));

			String consequenceLoyaltyType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceLoyaltyType));
			String itemID = DBUtils.getItemType(consequenceLoyaltyType);

			String consequenceAmt = JSONUtils.checkValue(jsonString, jsonPath.consequenceAmt);

			String consequenceTypeCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.consequenceTypeCode));

			if (consequenceType.equals("CreditAdjustment")) {
				Reporting.logReporter(Status.INFO,
						"---Consequence Item No =" + consequenceItemNo + " : " + consequenceTypeCode);
				switch (consequenceTypeCode) {

				case "ACTIVATIONBILLCREDIT":
					billingCode = "ACTCR";
					billingRev = "ACTCH";
					typeCode = "ACTIVATIONBILLCREDIT";
					break;

				case "RENEWALBILLCREDIT":
					billingCode = "RCCC";
					billingRev = "RCAC";
					typeCode = "RENEWALBILLCREDIT";
					break;
				}

				GenericUtils.validateAssertEquals("CreditAdjustment", consequenceType, "TYPE");

				GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode, "BILLING CODE");

				GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
						"BILLING REVERSAL CODE");

				GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

			} else {
				Reporting.logReporter(Status.INFO,
						"---Consequence Item No =" + consequenceItemNo + " : " + consequenceLoyaltyType);

				resultSetRewardAcc = statementRewardAcc
						.executeQuery("select * from reward_account rewAcc inner join reward_txn rewTxn "
								+ " on rewAcc.REWARD_ACCOUNT_ID = rewTxn.REWARD_ACCOUNT_ID "
//								+ "And rewAcc.REWARD_PROGRAM_TYP_ID='" + itemID
								+ "' and rewAcc.BUSINESS_OBJECT_ID =" + subscriptionID
								+ " and rewTxn.REWARD_TXN_RSN_ID=''");
				switch (consequenceLoyaltyType) {

				case "ACCESSORYFINANCE":
					billingCode = "AFTC";
					billingRev = "AFCR";
					typeCode = "AFDB";// "AFECF"
					break;

				case "BIB":
					billingCode = "BIBTC";
					billingRev = "BIBCR";
					typeCode = "BIBDB";
					if (paymentMech.equals("BIB_TELUS_PENDING") || paymentMech.equals("TRADE_IN_PENDING")) {
						billingCode = "BIBPAY";
						typeCode = "BIBPDB";
					}
					break;

				case "HARDWARE":
					billingCode = "DBLBC";
					billingRev = "DBLBR";
					typeCode = "HWDB";
					break;

				case "FINANCE":
					billingCode = "UPDF";
					billingRev = "UPDFCR";
					typeCode = "FINEPB";
					break;

				case "TAB":
					billingCode = "TABBCA";
					billingRev = "ATABCA";
					typeCode = "TABDB";
					break;

				case "HWS":
					billingCode = "PHCCAN";
					billingRev = "APHCCA";
					typeCode = "HWSDB";
					break;

				case "ACTIVATIONBILLCREDIT":
					billingCode = "BCLBC";
					billingRev = "BCLBR";
					typeCode = "ACBDB";
					break;

				/*
				 * case "ACTIVATIONBILLCREDIT": billingCode = "CBFC"; billingRev = "CBFR";
				 * typeCode = "ACBECF"; break;
				 */

				case "RENEWALBILLCREDIT":
					billingCode = "RCBD";
					billingRev = "RCBC";
					typeCode = "RCBDB";
					break;
				}
				if (consequenceTypeCode.contains("ECF")) {
					typeCode = "AFECF";

				}
				GenericUtils.validateAssertNotNull(violationRefValue, "VIOLATION_REF_VALUE");

				GenericUtils.validateAssertEquals("Charge", consequenceType, "TYPE");

				GenericUtils.validateAssertEquals("0", consequenceAdjAmtValue, "ADJUSTMENT_AMT");

				GenericUtils.validateAssertEquals(String.valueOf(billingCode), consequenceBillingCode, "BILLING CODE");

				GenericUtils.validateAssertEquals(String.valueOf(billingRev), consequenceBillingRevCode,
						"BILLING REVERSAL CODE");

				GenericUtils.validateAssertEquals(String.valueOf(typeCode), consequenceTypeCode, "TYPE CODE");

				while (resultSetRewardAcc.next()) {

					if (consequenceAmt.equals("0")) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT")), consequenceAmt,
								"AMOUNT");
					} else if (consequenceTypeCode.contains("ECF")) {
						GenericUtils.validateAssertEquals("100", consequenceAmt, "AMOUNT");
					} else if (paymentMech.equals("BIB_TELUS_PENDING") || paymentMech.equals("TRADE_IN_PENDING")
							|| paymentMech.equals("BILL")) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_AMT")), "-" + consequenceAmt,
								"AMOUNT");

					} else {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(resultSetRewardAcc.getInt("CURRENCY_BAL_AMT")), "-" + consequenceAmt,
								"AMOUNT");
					}
				}

				GenericUtils.validateAssertEquals("EN_CA", violationCommentLocale0, "COMMENT_0");

				GenericUtils.validateAssertEquals("FR_CA", violationCommentLocale1, "COMMENT_1");

			}
		}

	}

	public static void payloadDBCheckRenewalAgrmt(String jsonString) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsAgreement = null;

		Renewal jsonPath = new Renewal();

		String AgreementDurationStartDateTime = JSONUtils.checkValue(jsonString,
				jsonPath.AgreementDurationStartDateTime);

		AgreementDurationAmount = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.AgreementDurationAmount));

		String AgreementDurationEndDateTime = JSONUtils.checkValue(jsonString, jsonPath.AgreementDurationEndDateTime);
		AgreementDurationEndDateTime = JSONUtils.getAgrmtEndDate(AgreementDurationEndDateTime,
				AgreementDurationStartDateTime, AgreementDurationAmount);

		String relatedParty_Accid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Accid));

		String relatedParty_brandidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_brandidValue));

		String relatedParty_AccTypeCodeValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_AccTypeCodeValue));

		String relatedParty_AccSubTypeCodeValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_AccSubTypeCodeValue));

		String relatedParty_Oriid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Oriid));

		String relatedParty_TransactionidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_TransactionidValue));

		String relatedParty_TeamMemberidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_TeamMemberidValue));

		String relatedParty_ChnlOrgValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_ChnlOrgValue));

		String relatedParty_SalesRepidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_SalesRepidValue));

		String relatedParty_Subid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Subid));

		String relatedParty_MarketProvinceValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_MarketProvinceValue));

		String relatedParty_HomeProvinceValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_HomeProvinceValue));

		String relatedParty_SubscriberNumValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_SubscriberNumValue));

		String relatedParty_ComboRatePlanIndValue = JSONUtils.checkValue(jsonString,
				jsonPath.relatedParty_ComboRatePlanIndValue);
		relatedParty_ComboRatePlanIndValue = DBUtils.convertYN(relatedParty_ComboRatePlanIndValue);

		Reporting.logReporter(Status.INFO, "--------------------DB Validation Starts-----------------------");

		rsAgreement = statement.executeQuery("SELECT * FROM  CUSTOMER_SERVICE_AGREEMENT agrmt "
				+ "where agrmt.subscriber_no ='" + relatedParty_SubscriberNumValue + "' and current_ind='Y' ");

		while (rsAgreement.next()) {

			agrmtId = String.valueOf(rsAgreement.getInt("CUSTOMER_SVC_AGREEMENT_ID"));

			Reporting.logReporter(Status.INFO, "Customer Agreement ID : " + agrmtId);

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getDate("AGREEMENT_END_DT")),
					AgreementDurationEndDateTime, "AGREEMENT_END_DT");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getDate("AGREEMENT_START_DT")),
					AgreementDurationStartDateTime, "AGREEMENT_START_DT");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getInt("COMMITMENT_LENGTH_NUM")),
					AgreementDurationAmount, "COMMITMENT_LENGTH_NUM");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getInt("BAN")), relatedParty_Accid,
					"BAN");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getInt("BRAND_ID")),
					relatedParty_brandidValue, "BRAND_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getString("ACCOUNT_TYPE_CD")),
					relatedParty_AccTypeCodeValue, "ACCOUNT_TYPE_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getString("ACCOUNT_SUBTYPE_CD")),
					relatedParty_AccSubTypeCodeValue, "ACCOUNT_SUBTYPE_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getInt("CHNL_ORG_ID")),
					relatedParty_ChnlOrgValue, "CHNL_ORG_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getInt("SALES_REP_ID")),
					relatedParty_SalesRepidValue, "SALES_REP_ID");

			/*
			 * GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getInt("")
			 * ), relatedParty_Oriid, "");
			 * 
			 * GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getInt(
			 * "REWARD_TXN_ID")), relatedParty_TransactionidValue, "REWARD_TXN_ID");
			 * 
			 */
			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getInt("SUBSCRIPTION_ID")),
					relatedParty_Subid, "SUBSCRIPTION_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getString("CUST_PHONE_PROV_CD")),
					relatedParty_MarketProvinceValue, "CUST_PHONE_PROV_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getString("CUST_HOME_PROV_CD")),
					relatedParty_HomeProvinceValue, "CUST_HOME_PROV_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getLong("SUBSCRIBER_NO")),
					relatedParty_SubscriberNumValue, "SUBSCRIBER_NO");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreement.getString("COMBO_RTPLN_IND")),
					relatedParty_ComboRatePlanIndValue, "COMBO_RTPLN_IND");

		}

	}

	public static void payloadnDBCheckAgrmtItemRenewal(String jsonString, int itemNo) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsAgreementItem = null;

		Statement statement2 = null;
		statement2 = DBUtils.Conn.createStatement();
		ResultSet rsAgreementTax = null;

		Statement statement3 = null;
		statement3 = DBUtils.Conn.createStatement();
		ResultSet rsAgreementPromo = null;

		int agreementItemNo = 0;

		for (int i = 0; i < itemNo; i++) {

			Renewal agrmtItem = new Renewal(i);
			agreementItemNo = i + 1;

			String agreementItem_id = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_id));

			Itemtype = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemType));
			String agreementItem_itemType = DBUtils.getItemType(Itemtype);

			Reporting.logReporter(Status.INFO, "--- Agreement Item : " + agreementItemNo + " : " + Itemtype + "---");

			String agreementItem_itemDurationAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemDurationAmount));

			String agreementItem_itemDurationStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationStartDateTime);

			String agreementItem_itemDurationEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationEndDateTime);
			/*
			 * agreementItem_itemDurationEndDateTime =
			 * JSONUtils.getAgrmtEndDate(agreementItem_itemDurationEndDateTime,
			 * agreementItem_itemDurationStartDateTime, agreementItem_itemDurationAmount);
			 */
			agreementItem_itemDurationEndDateTime = JSONUtils.getEndDate(agreementItem_itemDurationEndDateTime,
					agreementItem_itemDurationStartDateTime, agreementItem_itemType, agreementItem_itemDurationAmount);

			/*
			 * if (Itemtype.equals("BIB") || Itemtype.equals("TIASSETCREDIT") ||
			 * Itemtype.equals("TIPROMOCRDIT")) { LocalDate endDateFormatted;
			 * endDateFormatted = LocalDate.parse(agreementItem_itemDurationStartDateTime);
			 * endDateFormatted = endDateFormatted.plusDays(180);
			 * agreementItem_itemDurationEndDateTime = endDateFormatted.toString(); }
			 */

			String agreementItem_incentiveAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_incentiveAmount));
			if (AgreementDurationAmount.equals("0") && agreementItem_itemType.equals("1")) {
				Itemtype = "NA";
				agreementItem_itemDurationEndDateTime = "null";
				agreementItem_itemType = "5";
				agreementItem_itemDurationAmount = "0";
				agreementItem_incentiveAmount = "0";
			}

			String agreementItem_incentiveServiceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_incentiveServiceCode);

			String agreementItem_installmentAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAmount));

			String agreementItem_installmentStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_installmentStartDateTime);
			if (agreementItem_itemType.equals("9"))
				agreementItem_installmentStartDateTime = JSONUtils.getInstallmentStartDate(
						agreementItem_installmentStartDateTime, agreementItem_itemType,
						agreementItem_installmentAmount);

			String agreementItem_installmentEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_installmentEndDateTime);
			agreementItem_installmentEndDateTime = JSONUtils.getEndDate(agreementItem_installmentEndDateTime,
					agreementItem_installmentStartDateTime, agreementItem_itemType, agreementItem_installmentAmount);
			if (agreementItem_itemType.equals("10") || agreementItem_itemType.equals("11"))
				agreementItem_installmentEndDateTime = "null";

			String agreementItem_installmentLeftNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentLeftNumValue));

			String agreementItem_installmentAppliedNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAppliedNumValue));

			String agreementItem_installmentAppliedAmtValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAppliedAmtValue));

			String agreementItem_termOrConditionMinRatePlanValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinRatePlanValue);

			String agreementItem_termOrConditionMinFeatureValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinFeatureValue);

			String agreementItem_termOrConditionMinCombinedValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinCombinedValue);

			String agreementItem_termOrConditionCommitmentServiceCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionCommitmentServiceCdValue);

			String agreementItem_termOrConditionAutoTopupCommitmentIndValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionAutoTopupCommitmentIndValue);
			agreementItem_termOrConditionAutoTopupCommitmentIndValue = DBUtils
					.convertYN(agreementItem_termOrConditionAutoTopupCommitmentIndValue);

			String agreementItem_taxPaymentMethodCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentMethodCode);

			String agreementItem_taxPaymentMechanismCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentMechanismCode);

			String agreementItem_taxPaymentChannelCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentChannelCode);

			String agreementItem_taxProvinceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxProvinceCode);

			String agreementItem_taxCategory = JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_taxCategory);

			String agreementItem_taxRate = JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_taxRate);

			String agreementItem_taxAmountValue = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_taxAmountValue));

			String agreementItem_productSerialNumber = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productSerialNumber);

			String agreementItem_productPriceValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productPriceValue);

			String agreementItem_productCharacteristicValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productCharacteristicValue);

			/*
			 * String agreementItem_productSpecificationid = String.valueOf(
			 * JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
			 * agrmtItem.agreementItem_productSpecificationid));
			 * 
			 * String agreementItem_productCharacteristicValue = String.valueOf(
			 * JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
			 * agrmtItem.agreementItem_productCharacteristicValue));
			 * 
			 * String agreementItem_productSpecificationLocale =String.valueOf(
			 * JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
			 * agrmtItem.agreementItem_productSpecificationLocale));
			 *
			 * 
			 * String agreementItem_productSpecificationDesc =String.valueOf(
			 * JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
			 * agrmtItem.agreementItem_productSpecificationDesc));
			 * 
			 * 
			 */

			String agreementItem_promotionid = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_promotionid));

			String agreementItem_promotionPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_promotionPerspectiveDate));
			agreementItem_promotionPerspectiveDate = agreementItem_promotionPerspectiveDate.split("T")[0];

			String agreementItem_productOfferingid = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingid));

			String offerID = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
					agrmtItem.agreementItem_productOfferingRedeemedOfferContextCodeValue));
			String agreementItem_productOfferingRedeemedOfferContextCodeValue = DBUtils.getProductOfferID(offerID);

			String agreementItem_productOfferingOfferTierCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productOfferingOfferTierCdValue);
			agreementItem_productOfferingOfferTierCdValue = DBUtils
					.getNullCode(agreementItem_productOfferingOfferTierCdValue);

			String agreementItem_productOfferingOfferTierCapAmtValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productOfferingOfferTierCapAmtValue);
			agreementItem_productOfferingOfferTierCapAmtValue = DBUtils
					.getNullCode(agreementItem_productOfferingOfferTierCapAmtValue);

			String agreementItem_productOfferingDataCommitmentIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_productOfferingDataCommitmentIndValue));
			/*
			 * agreementItem_productOfferingDataCommitmentIndValue = DBUtils
			 * .convertYN(agreementItem_productOfferingDataCommitmentIndValue); This will be
			 * always N irrespective of Payload
			 */
			agreementItem_productOfferingDataCommitmentIndValue = "N";

			String agreementItem_productOfferingContractEnforcedPlanIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_productOfferingContractEnforcedPlanIndValue));
			/*
			 * agreementItem_productOfferingContractEnforcedPlanIndValue = DBUtils
			 * .convertYN(agreementItem_productOfferingContractEnforcedPlanIndValue); This
			 * will be always N irrespective of Payload
			 */
			agreementItem_productOfferingContractEnforcedPlanIndValue = "N";

			String agreementItem_productOfferingPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingPerspectiveDate));
			agreementItem_productOfferingPerspectiveDate = agreementItem_productOfferingPerspectiveDate.split("T")[0];

			String agreementItem_productOfferingSourceSystemId = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingSourceSystemId));

			if (agreementItem_itemType.equals("17")) {
				agreementItem_termOrConditionMinRatePlanValue = "null";
				agreementItem_termOrConditionMinFeatureValue = "null";
				agreementItem_termOrConditionMinCombinedValue = "null";
				agreementItem_productSerialNumber = "null";
				agreementItem_productPriceValue = "null";
				agreementItem_productCharacteristicValue = "null";
			}

			if (AgreementDurationAmount.equals("0") && agreementItem_itemType.equals("5")) {
				agreementItem_termOrConditionMinRatePlanValue = "null";
				agreementItem_termOrConditionMinFeatureValue = "null";
				agreementItem_termOrConditionMinCombinedValue = "null";
			}

			String tax = null;
			switch (Itemtype) {
			case "HWS":
			case "TIASSETCREDIT":
			case "TIPROMOCREDIT":
			case "PRESOC":
			case "PRECREDIT":
			case "TAB":
			case "ACCESSORYFINANCE":
			case "ACTIVATIONBILLCREDIT":
			case "HARDWARE":
			case "BIB":
			case "FINANCE":
			case "RENEWALBILLCREDIT":
				rsAgreementItem = statement.executeQuery("Select * from cust_srvc_agrmt_item item  "
						+ "inner join CUST_SRVC_AGRMT_ITM_PROMO promo  "
						+ "on item.CUST_SRVC_AGRMT_ITEM_ID = promo.CUST_SRVC_AGRMT_ITEM_ID and item.customer_svc_agreement_id ='"
						+ agrmtId + "' and item.REWARD_PROGRAM_TYP_ID='" + agreementItem_itemType + "'");

				break;
			case "NA":
				rsAgreementItem = statement.executeQuery(
						"Select * from(select a.*,row_number() over(partition by customer_svc_agreement_id order by CUST_SRVC_AGRMT_ITEM_ID)  "
								+ "as rn from cust_srvc_agrmt_item a where customer_svc_agreement_id ='" + agrmtId
								+ "') where rn=" + agreementItemNo);
				break;

			}

			while (rsAgreementItem.next()) {

				custAgmtItemId = String.valueOf(rsAgreementItem.getInt("CUST_SRVC_AGRMT_ITEM_ID"));

				Reporting.logReporter(Status.INFO, "CUST_SRVC_AGRMT_ITEM_ID Value from DB is : " + custAgmtItemId);

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_START_DT")),
						agreementItem_itemDurationStartDateTime, "COMMITMENT_EFF_START_DT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_END_DT")),
						agreementItem_itemDurationEndDateTime, "COMMITMENT_EFF_END_DT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("ORIG_COMMITMENT_LENGTH_NUM")),
						agreementItem_itemDurationAmount, "ORIG_COMMITMENT_LENGTH_NUM");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REWARD_PROGRAM_TYP_ID")),
						agreementItem_itemType, "REWARD_PROGRAM_TYP_ID");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("INCENTIVE_AMT")),
						agreementItem_incentiveAmount, "INCENTIVE_AMT");

				if (!agreementItem_incentiveServiceCode.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("INCENTIVE_CD")),
							agreementItem_incentiveServiceCode, "INCENTIVE_CD");
				}

				if (!agreementItem_installmentStartDateTime.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_START_DT")),
							agreementItem_installmentStartDateTime, "REWARD_INSTLMNT_START_DT");
				}
				if (!agreementItem_installmentEndDateTime.equals("NA")) {

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_END_DT")),
							agreementItem_installmentEndDateTime, "REWARD_INSTLMNT_END_DT");
				}

				if (!agreementItem_installmentAmount.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getInt("REWARD_INSTLMNT_QTY")),
							agreementItem_installmentAmount, "REWARD_INSTLMNT_QTY");
				}
				// INSTALLMENTS_LEFT_NUM, INSTALLMENTS_APPLIED_NUM, INSTALLMENTS_APPLIED_AMT ->
				// No need to DB validation

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("RTPLN_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinRatePlanValue, "RTPLN_MIN_COMMITMENT_AMT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("FEAT_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinFeatureValue, "FEAT_MIN_COMMITMENT_AMT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("COMB_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinCombinedValue, "COMB_MIN_COMMITMENT_AMT");

				if (!agreementItem_termOrConditionCommitmentServiceCdValue.equals("NA"))
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("COMMITMENT_SERVICE_CD")),
							agreementItem_termOrConditionCommitmentServiceCdValue, "COMMITMENT_SERVICE_CD");

				if (!agreementItem_termOrConditionAutoTopupCommitmentIndValue.equals("NA"))
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("COMMITMENT_AUTOTOPUP_IND")),
							agreementItem_termOrConditionAutoTopupCommitmentIndValue, "COMMITMENT_AUTOTOPUP_IND");

				if (agreementItem_itemType.equals("4") || agreementItem_itemType.equals("9")) {

					rsAgreementTax = statement3.executeQuery(
							"select * from CUST_SRVC_AGRMT_ITM_TAX tax inner join CUST_SRVC_AGRMT_ITM_TAX_DTL dtl "
									+ "on tax.CUST_SRVC_AGRMT_ITM_TAX_ID = dtl.CUST_SRVC_AGRMT_ITM_TAX_ID "
									+ "and tax.CUST_SRVC_AGRMT_ITEM_ID=" + custAgmtItemId);

					while (rsAgreementTax.next()) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PAYMENT_METHOD_CD")),
								agreementItem_taxPaymentMethodCode, "TAX_PAYMENT_METHOD_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PYMT_MECHANISM_CD")),
								agreementItem_taxPaymentMechanismCode, "TAX_PYMT_MECHANISM_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PAYMENT_CHANNEL_CD")),
								agreementItem_taxPaymentChannelCode, "TAX_PAYMENT_CHANNEL_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAXATION_PROVINCE_CD")),
								agreementItem_taxProvinceCode, "TAXATION_PROVINCE_CD");

						GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementTax.getString("TAX_TYPE_CD")),
								agreementItem_taxCategory, "TAX_TYPE_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_RATE_PCT")), agreementItem_taxRate,
								"TAX_RATE_PCT");

						GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementTax.getString("TAX_AMT")),
								agreementItem_taxAmountValue, "TAX_AMT");
					}
				}

				/*
				 * 
				 * 
				 * Value for these json
				 * 
				 * "description": [ { "locale": "EN_CA", "description":
				 * "APPLE IPHONE 4S 32GB BLACK" }, { "locale": "FR_CA", "description":
				 * "APPLE IPHONE 4S 32GB BLACK" } ] agreementItem_productSpecificationLocale1
				 * agreementItem_productSpecificationDesc1
				 * 
				 */
				if (!agreementItem_itemType.equals("5")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getInt("REDEEMED_PROMOTION_ID")), agreementItem_promotionid,
							"REDEEMED_PROMOTION_ID");

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REDEEMED_PROMOTION_TS")),
							agreementItem_promotionPerspectiveDate, "REDEEMED_PROMOTION_TS");
				}
				if (!agreementItem_productSerialNumber.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("HANDSET_SERIAL_NUM")),
							agreementItem_productSerialNumber, "HANDSET_SERIAL_NUM");
				}
				if (!agreementItem_productPriceValue.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("CATALOGUE_ITEM_PRICE_AMT")),
							agreementItem_productPriceValue, "CATALOGUE_ITEM_PRICE_AMT");
				}
				if (!agreementItem_productCharacteristicValue.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("USIM_ID")),
							agreementItem_productCharacteristicValue, "USIM_ID");
				}

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_ID")),
						agreementItem_productOfferingid, "REDEEMED_OFFER_ID");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TYPE_ID")),
						agreementItem_productOfferingRedeemedOfferContextCodeValue, "REDEEMED_OFFER_TYPE_ID");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TIER_CD")),
						agreementItem_productOfferingOfferTierCdValue, "REDEEMED_OFFER_TIER_CD");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_TIER_CAP_AMT")),
						agreementItem_productOfferingOfferTierCapAmtValue, "REDEEMED_OFFER_TIER_CAP_AMT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("DATA_SRVC_REQ_IND")),
						agreementItem_productOfferingDataCommitmentIndValue, "DATA_SRVC_REQ_IND");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("COMB_MIN_CMITMT_DISCHRG_IND")),
						agreementItem_productOfferingContractEnforcedPlanIndValue, "COMB_MIN_CMITMT_DISCHRG_IND");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getDate("REDEEMED_OFFER_TS")),
						agreementItem_productOfferingPerspectiveDate, "REDEEMED_OFFER_TS");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("REDEEMED_OFFER_SYS_ID")),
						agreementItem_productOfferingSourceSystemId, "REDEEMED_OFFER_SYS_ID");

			}
			rsAgreementItem.close();
		}

	}

	public static void payloadnDBCheckPaymentRenewal(String jsonString, String jsonString_earlyPenalty, String subId,
			int itemNo) throws SQLException {

		String consequenceAdjAmtValue = null;
		String consequenceTypeCode = null;
		String consequenceLoyaltyType = null;
		String consequenceBillingRevCode = null;

		int paymentItemNo = 0;

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsPayment = null;

		Statement statement1 = null;
		statement1 = DBUtils.Conn.createStatement();
		ResultSet rsReward = null;

		for (int i = 0; i < itemNo; i++) {

			Renewal jsonPathPayment = new Renewal("payment", i);
			paymentItemNo = i + 1;

			String itemType = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPathPayment.payment_ItemTypeValue));
			String payment_ItemTypeValue = DBUtils.getItemType(itemType);

			String paymentAmt = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPathPayment.paymentAmt));

			String payment_paymentMachanism = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPathPayment.payment_paymentMachanism));
			payment_paymentMachanism = DBUtils.getPaymentMech(payment_paymentMachanism);

			String payment_relatedPartyRole = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPathPayment.payment_relatedPartyRole));

			String payment_FinanceCommitmentItemValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, jsonPathPayment.payment_FinanceCommitmentItemValue));

			String payment_ChargeTypeValue = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPathPayment.payment_ChargeTypeValue));

			String payment_waive_adjCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPathPayment.payment_waive_adjCode));

			String payment_waive_reasonCode = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPathPayment.payment_waive_reasonCode));

			String payment_waive_amt = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPathPayment.payment_waive_amt));

			Reporting.logReporter(Status.INFO,
					"--- Payment done for Item : " + paymentItemNo + " : " + itemType + "---");

			int j = 0;
			do {

				GetEarlyRenewalPenalty jsonPathEarlyPenalty = new GetEarlyRenewalPenalty(j);

				consequenceTypeCode = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString_earlyPenalty,
						jsonPathEarlyPenalty.consequenceTypeCode));

				consequenceLoyaltyType = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString_earlyPenalty,
						jsonPathEarlyPenalty.consequenceLoyaltyType));
				String itemID = DBUtils.getItemType(consequenceLoyaltyType);

				consequenceBillingRevCode = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(
						jsonString_earlyPenalty, jsonPathEarlyPenalty.consequenceBillingRevCode));

				j++;

			} while (!itemType.equals(consequenceLoyaltyType));

			rsReward = statement1.executeQuery("select * from reward_account rewAcc " + "inner join reward_txn txn "
					+ "on rewAcc.REWARD_ACCOUNT_ID = txn.REWARD_ACCOUNT_ID " + "and rewAcc.BUSINESS_OBJECT_ID=" + subId
					+ " and txn.REWARD_PYMT_MECHANISM_ID=" + payment_paymentMachanism + " and REWARD_PROGRAM_TYP_ID="
					+ payment_ItemTypeValue);

			while (rsReward.next()) {
				if (payment_waive_adjCode.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsReward.getString("CURRENCY_AMT")),
							paymentAmt, "CURRENCY_AMT");
				} else {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsReward.getString("CURRENCY_AMT")), "0",
							"CURRENCY_AMT");

					GenericUtils.validateAssertEquals(consequenceBillingRevCode, payment_waive_adjCode,
							"ADJUSTMENT_CODE");

					GenericUtils.validateAssertEquals("RENEWAL_OFFER_WAIVE", payment_waive_reasonCode,
							"RENEWAL_OFFER_WAIVE");

				}

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsReward.getString("REWARD_PYMT_MECHANISM_ID")),
						payment_paymentMachanism, "REWARD_PYMT_MECHANISM_ID");

				GenericUtils.validateAssertEquals("LOYALTY_AGREEMENT_ITEM", payment_relatedPartyRole, "ROLE");

				GenericUtils.validateAssertEquals(payment_FinanceCommitmentItemValue,
						payment_FinanceCommitmentItemValue, "FINANCE_COMMITMENT_ITEM_ID ->Yet to check");

				/*
				 * GenericUtils.validateAssertEqualsFromDB(
				 * String.valueOf(rsReward.getString("CURR_FIN_COMMITMENT_ID")),
				 * payment_FinanceCommitmentItemValue, "CURR_FIN_COMMITMENT_ID");
				 */
				GenericUtils.validateAssertEquals(consequenceTypeCode, payment_ChargeTypeValue, "CHARGE_TYPE");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsReward.getString("REWARD_PROGRAM_TYP_ID")),
						payment_ItemTypeValue, "ITEM_TYPE");

			}
		}

	}

	public static void payloadnDBCheckCancel(String jsonString, String subID) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsCancel = null;

		Cancellation jsonPath = new Cancellation();

		String charReasonCdValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.charReasonCdValue));

		String relatedParty_TransactionidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_TransactionidValue));

		String relatedParty_ChnlOrgValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_ChnlOrgValue));

		String relatedParty_SalesRepidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_SalesRepidValue));

		String relatedParty_Accid = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_Accid));

		String relatedParty_brandidValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_brandidValue));

		String relatedParty_AccTypeCodeValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_AccTypeCodeValue));

		String relatedParty_AccSubTypeCodeValue = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.relatedParty_AccSubTypeCodeValue));

		rsCancel = statement.executeQuery("SELECT * FROM  CUSTOMER_SERVICE_AGREEMENT agrmt "
				+ "where agrmt.SUBSCRIPTION_ID ='" + subID + "' and agrmt.current_ind='Y' ");

		while (rsCancel.next()) {

			GenericUtils.validateAssertEquals("CANCELLATION_PAYMENT", charReasonCdValue, "REASON_CODE");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancel.getInt("CHNL_ORG_ID")),
					relatedParty_ChnlOrgValue, "CHNL_ORG_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancel.getInt("SALES_REP_ID")),
					relatedParty_SalesRepidValue, "SALES_REP_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancel.getInt("BAN")), relatedParty_Accid, "BAN");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancel.getInt("BRAND_ID")),
					relatedParty_brandidValue, "BRAND_ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancel.getString("ACCOUNT_TYPE_CD")),
					relatedParty_AccTypeCodeValue, "ACCOUNT_TYPE_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancel.getString("ACCOUNT_SUBTYPE_CD")),
					relatedParty_AccSubTypeCodeValue, "ACCOUNT_SUBTYPE_CD");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancel.getInt("SUBSCRIPTION_ID")), subID,
					"SUBSCRIPTION_ID");
		}

	}

	public static void cancelDbCheck(String jsonString, String subID) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsCancelLyfcyc = null;

		String startDate = JSONUtils.getGMTStartDate().split("T")[0];

		rsCancelLyfcyc = statement.executeQuery(
				"select lyfcyc.CUSTOMER_SVC_AGREEMENT_ID, lyfcyc.CUST_AGRMT_STAT_LIFECYCL_ID, lyfcyc.EFF_START_TS, lyfcyc.EFF_STOP_TS, lyfcyc.CREATE_TS, lyfcyc.LAST_UPDT_TS\n"
						+ "from CUSTOMER_SERVICE_AGREEMENT agrmt\n" + "inner join CUST_AGRMT_LIFECYCL lyfcyc\n"
						+ "on agrmt.CUSTOMER_SVC_AGREEMENT_ID=lyfcyc.CUSTOMER_SVC_AGREEMENT_ID\n"
						+ "and agrmt.SUBSCRIPTION_ID='" + subID + "' order by lyfcyc.LAST_UPDT_TS asc");

		while (rsCancelLyfcyc.next()) {
			if (rsCancelLyfcyc.getString("CUST_AGRMT_STAT_LIFECYCL_ID").equals("1")) {
				Reporting.logReporter(Status.INFO, "Activation data in Lifecycle Table  " + "" + "---");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancelLyfcyc.getDate("EFF_START_TS")),
						startDate, "EFF_START_TS");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancelLyfcyc.getDate("EFF_STOP_TS")),
						startDate, "EFF_STOP_TS");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancelLyfcyc.getDate("CREATE_TS")), startDate,
						"CREATE_TS");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancelLyfcyc.getDate("LAST_UPDT_TS")),
						startDate, "LAST_UPDT_TS");

			} else if (rsCancelLyfcyc.getString("CUST_AGRMT_STAT_LIFECYCL_ID").equals("3")) {
				Reporting.logReporter(Status.INFO, "Cancellation data in Lifecycle Table  " + "" + "---");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancelLyfcyc.getDate("EFF_START_TS")),
						startDate, "EFF_START_TS");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancelLyfcyc.getDate("EFF_STOP_TS")),
						"9999-12-31", "EFF_STOP_TS");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancelLyfcyc.getDate("CREATE_TS")), startDate,
						"CREATE_TS");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancelLyfcyc.getDate("LAST_UPDT_TS")),
						startDate, "LAST_UPDT_TS");
			} else {
				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsCancelLyfcyc.getString("CUST_AGRMT_STAT_LIFECYCL_ID")), "3",
						"CUST_AGRMT_STAT_LIFECYCL_ID");
			}
		}

		Statement statement1 = null;
		statement1 = DBUtils.Conn.createStatement();
		ResultSet rsCancelRewardAcc = null;

		rsCancelRewardAcc = statement.executeQuery(
				"select * from reward_account where BUSINESS_OBJECT_ID ='" + subID + "' order by LAST_UPDT_TS asc");

		while (rsCancelRewardAcc.next()) {
			String rewAccID = String.valueOf(rsCancelRewardAcc.getString("REWARD_ACCOUNT_ID"));

			Reporting.logReporter(Status.INFO, "Cancelled Reward type : "
					+ (String.valueOf(rsCancelRewardAcc.getString("REWARD_PROGRAM_TYP_ID"))));

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancelRewardAcc.getString("CURRENCY_BAL_AMT")),
					"0", "CURRENCY_BAL_AMT");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsCancelRewardAcc.getString("STATUS_LIFECYCLE_ID")),
					"3", "STATUS_LIFECYCLE_ID");

			Statement statement2 = null;
			statement2 = DBUtils.Conn.createStatement();
			ResultSet rsCancelRewardTxn = null;

			rsCancelRewardTxn = statement2.executeQuery(
					"select * from reward_txn where REWARD_ACCOUNT_ID in (" + rewAccID + ") order by LAST_UPDT_TS asc");

			while (rsCancelRewardTxn.next()) {

			}

		}

	}

	public static void payloadnDBCheckReturn1(String subID) throws SQLException {

		String agrmtId = null;

		Statement statement1 = null;
		statement1 = DBUtils.Conn.createStatement();
		ResultSet rsAgrmt = null;

		rsAgrmt = statement1.executeQuery(
				"SELECT * FROM  CUSTOMER_SERVICE_AGREEMENT agrmt  \r\n" + "inner join cust_agrmt_lifecycl lyfcyc\r\n"
						+ "on agrmt.CUSTOMER_SVC_AGREEMENT_ID=lyfcyc.CUSTOMER_SVC_AGREEMENT_ID\r\n"
						+ "and agrmt.SUBSCRIPTION_ID =" + subID + "\r\n" + "and lyfcyc.CUST_AGRMT_STAT_LIFECYCL_ID=4");

		while (rsAgrmt.next()) {
			agrmtId = rsAgrmt.getString("CUSTOMER_SVC_AGREEMENT_ID");

			Reporting.logReporter(Status.INFO, "CUSTOMER_SVC_AGREEMENT_ID = " + agrmtId);

			GenericUtils.validateAssertEquals(String.valueOf(rsAgrmt.getString("CURRENT_IND")), "N", "CURRENT_IND");

			GenericUtils.validateAssertEquals(String.valueOf(rsAgrmt.getString("CUST_AGRMT_STAT_LIFECYCL_ID")), "4",
					"CUST_AGRMT_STAT_LIFECYCL_ID");

		}

		Statement statement2 = null;
		statement2 = DBUtils.Conn.createStatement();
		ResultSet rsRewardAcc = null;

		Statement statement3 = null;
		statement3 = DBUtils.Conn.createStatement();
		ResultSet rsRewardTxn = null;

		rsRewardAcc = statement2.executeQuery(
				"SELECT * FROM  REWARD_ACCOUNT " + "WHERE BUSINESS_OBJECT_ID ='" + subID + "' and CURRENCY_BAL_AMT=0");

		while (rsRewardAcc.next()) {
			Reporting.logReporter(Status.INFO, "Reward type ID = " + rsRewardAcc.getString("REWARD_PROGRAM_TYP_ID"));

			GenericUtils.validateAssertEquals(String.valueOf(rsRewardAcc.getString("CURRENCY_BAL_AMT")), "0",
					"CURRENCY_BAL_AMT");

			GenericUtils.validateAssertEquals(String.valueOf(rsRewardAcc.getString("STATUS_LIFECYCLE_ID")),
					rsRewardAcc.getString("STATUS_LIFECYCLE_ID"), "STATUS_LIFECYCLE_ID");

			rsRewardTxn = statement3.executeQuery(
					"select RA.BUSINESS_OBJECT_ID,ra.reward_account_id,ra.REWARD_PROGRAM_TYP_ID,rt.REWARD_PYMT_MECHANISM_ID,rt.CURRENCY_AMT,rt.CURRENCY_BAL_AMT,\r\n"
							+ "rt.txn_ts,rt.reward_txn_rsn_id,rsn.TYP_CD ,RTTR.REWARD_TXN_TYP_ID,rt.RELATED_TXN_ID, rt.DEVICE_DUEDATE \r\n"
							+ "from reward_account ra, reward_txn rt, reward_txn_rsn rttr, reward_txn_typ txn, reward_rsn_typ rsn\r\n"
							+ "where RA.BUSINESS_OBJECT_ID in ('" + subID + "') and \r\n"
							+ "rt.CURRENCY_BAL_AMT=0 and rt.reward_txn_rsn_id=220 and ra.REWARD_PROGRAM_TYP_ID="
							+ rsRewardAcc.getString("REWARD_PROGRAM_TYP_ID") + " and \r\n"
							+ "RA.REWARD_ACCOUNT_ID = RT.REWARD_ACCOUNT_ID and\r\n"
							+ "RT.REWARD_TXN_RSN_ID = RTTR.REWARD_TXN_RSN_ID and\r\n"
							+ "RTTR.REWARD_TXN_TYP_ID = TXN.REWARD_TXN_TYP_ID and\r\n"
							+ "RTTR.REWARD_RSN_TYP_ID = RSN.REWARD_RSN_TYP_ID\r\n"
							+ "ORDER  BY rt.txn_ts desc, rt.reward_txn_id desc");

			while (rsRewardTxn.next()) {
				GenericUtils.validateAssertEquals(String.valueOf(rsRewardTxn.getString("TYP_CD")), "RETURN_REVERSAL",
						"TYP_CD");
			}

		}

	}

	public static void payloadnDBCheckReturn(String subID) throws SQLException {

		String agrmtId = null;

		Statement statement1 = null;
		statement1 = DBUtils.Conn.createStatement();
		ResultSet rsAgrmt = null;

		rsAgrmt = statement1.executeQuery(
				"SELECT * FROM  CUSTOMER_SERVICE_AGREEMENT agrmt  \r\n" + "inner join cust_agrmt_lifecycl lyfcyc\r\n"
						+ "on agrmt.CUSTOMER_SVC_AGREEMENT_ID=lyfcyc.CUSTOMER_SVC_AGREEMENT_ID\r\n"
						+ "and agrmt.SUBSCRIPTION_ID =" + subID + "\r\n" + "and lyfcyc.CUST_AGRMT_STAT_LIFECYCL_ID=4");

		while (rsAgrmt.next()) {
			agrmtId = rsAgrmt.getString("CUSTOMER_SVC_AGREEMENT_ID");

			Reporting.logReporter(Status.INFO, "CUSTOMER_SVC_AGREEMENT_ID = " + agrmtId);

			GenericUtils.validateAssertEquals(String.valueOf(rsAgrmt.getString("CURRENT_IND")), "N", "CURRENT_IND");

			GenericUtils.validateAssertEquals(String.valueOf(rsAgrmt.getString("CUST_AGRMT_STAT_LIFECYCL_ID")), "4",
					"CUST_AGRMT_STAT_LIFECYCL_ID");

		}

		Statement statement2 = null;
		statement2 = DBUtils.Conn.createStatement();
		ResultSet rsRewardAcc = null;

		rsRewardAcc = statement2.executeQuery(
				"select RA.BUSINESS_OBJECT_ID,ra.reward_account_id,ra.REWARD_PROGRAM_TYP_ID,rt.REWARD_PYMT_MECHANISM_ID,rt.CURRENCY_AMT,rt.CURRENCY_BAL_AMT,\r\n"
						+ "rt.txn_ts,rt.reward_txn_rsn_id,rsn.TYP_CD,ra.STATUS_LIFECYCLE_ID,RTTR.REWARD_TXN_TYP_ID,txn.TYP_CD,rt.RELATED_TXN_ID, rt.DEVICE_DUEDATE \r\n"
						+ "from reward_account ra, reward_txn rt, reward_txn_rsn rttr, reward_txn_typ txn, reward_rsn_typ rsn\r\n"
						+ "where RA.BUSINESS_OBJECT_ID = '" + subID + "' and \r\n" + "rt.REWARD_TXN_RSN_ID=220 and \r\n"
						+ "RA.REWARD_ACCOUNT_ID = RT.REWARD_ACCOUNT_ID and\r\n"
						+ "RT.REWARD_TXN_RSN_ID = RTTR.REWARD_TXN_RSN_ID and\r\n"
						+ "RTTR.REWARD_TXN_TYP_ID = TXN.REWARD_TXN_TYP_ID and\r\n"
						+ "RTTR.REWARD_RSN_TYP_ID = RSN.REWARD_RSN_TYP_ID\r\n"
						+ "ORDER  BY rt.txn_ts desc, rt.reward_txn_id desc");

		while (rsRewardAcc.next()) {
			Reporting.logReporter(Status.INFO, "Reward type ID = " + rsRewardAcc.getString("REWARD_PROGRAM_TYP_ID"));

			GenericUtils.validateAssertEquals(String.valueOf(rsRewardAcc.getString("CURRENCY_BAL_AMT")), "0",
					"CURRENCY_BAL_AMT");

			GenericUtils.validateAssertEquals(String.valueOf(rsRewardAcc.getString("STATUS_LIFECYCLE_ID")),
					rsRewardAcc.getString("STATUS_LIFECYCLE_ID"), "STATUS_LIFECYCLE_ID");

			GenericUtils.validateAssertEquals(String.valueOf(rsRewardAcc.getString("TYP_CD")), "RETURN_REVERSAL",
					"TYP_CD");
		}

	}

	public static void responseDBCheckListEarnedTransaction(String jsonResponse, String type, String subID)
			throws SQLException {

		String ItemTypeID = DBUtils.getItemType(type);
		int rowCount = 0;

		Statement statement1 = null;
		statement1 = DBUtils.Conn.createStatement();
		ResultSet rsRewardTxn = null;

		String charTotalHistoryRecordNo = String
				.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, "$.[0].characteristic[0].value"));

		rsRewardTxn = statement1.executeQuery("select * from reward_txn where REWARD_ACCOUNT_ID in"
				+ "(select REWARD_ACCOUNT_ID from reward_account where BUSINESS_OBJECT_ID =" + subID
				+ " and REWARD_PROGRAM_TYP_ID=" + ItemTypeID + ") order by REWARD_TXN_ID desc");

		while (rsRewardTxn.next()) {

			ListEarnedTransaction jsonPath = new ListEarnedTransaction(rowCount);

			rowCount = rowCount + 1;

			String id = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, jsonPath.id));

			String quantity = JSONUtils.checkValue(jsonResponse, jsonPath.quantity);

			String closeBal = JSONUtils.checkValue(jsonResponse, jsonPath.closeBal);

			String dateTime = JSONUtils.checkValue(jsonResponse, jsonPath.dateTime);

			String descEN = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, jsonPath.descEN));

			String descFR = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, jsonPath.descFR));

			String charTypeCdName = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, jsonPath.charTypeCd));

			String charUnitOfMeasureCd = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, jsonPath.charUnitOfMeasureCd));

			String charReasonCode = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, jsonPath.charReasonCode));
			String reasonCD = DBUtils.getReasonCD(charReasonCode);

			String loyaltyPgmProductID = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, jsonPath.loyaltyPgmProductID));

			// GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsRewardTxn.getString("REWARD_TXN_ID")),
			// id, "ID");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsRewardTxn.getString("CURRENCY_AMT")), quantity,
					"QUANTITY");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsRewardTxn.getString("CURRENCY_BAL_AMT")), closeBal,
					"CLOSING_BALANCE");

			GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsRewardTxn.getDate("LAST_UPDT_TS")), dateTime,
					"DATE_TIME");

			GenericUtils.validateAssertNotNull(descEN, "DESC_LOCALE_EN");

			GenericUtils.validateAssertNotNull(descFR, "DESC_LOCALE_FR");

			GenericUtils.validateAssertEquals(charTypeCdName, charTypeCdName, "TYPE_CD");

			GenericUtils.validateAssertEquals("CAD", charUnitOfMeasureCd, "UNIT_OF_MEASURE_CD");

			GenericUtils.validateAssertEqualsFromDB(reasonCD, reasonCD, "REASON_CODE");

			GenericUtils.validateAssertEquals(type, loyaltyPgmProductID, "LOYALTY_PROGRAM_PRODUCT");

		}
		GenericUtils.validateAssertEquals(String.valueOf(rowCount), charTotalHistoryRecordNo,
				"TOTAL_HISTORY_RECORD_NUMcharTotalHistoryRecordNo");

	}

	public static void responseDBCheckLoyaltyAccount(String jsonResponse, String subID) throws SQLException {

		Statement statement1 = null;
		statement1 = DBUtils.Conn.createStatement();
		ResultSet rsRewardAcc = null;

		int itemIndex = 0;
		String itemTypeName = null;
		String loyaltyPgmProductId = null;
		List<String> rewardItemOrder = new ArrayList<String>();
		String charName = null;

		String charMonthlyDrawdownAmt = null;
		String charFinanceCommItemId = null;
		String charInstallmentLeftNum = null;
		String charPaidAmt = null;
		String charCatlogueItemCatCd = null;
		String charCatlogueItemCatTypeCd = null;

		do {

			String itemJsonPath = "$.[" + itemIndex + "].loyaltyProgramProduct.name";
			itemTypeName = JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, itemJsonPath);
			if (!itemTypeName.equals("NA"))
				rewardItemOrder.add(DBUtils.getItemType(itemTypeName));
			itemIndex++;
		} while (!itemTypeName.equals("NA"));

		int sizee = rewardItemOrder.size();
		for (int i = 0; i < sizee; i++) {
			loyaltyPgmProductId = rewardItemOrder.get(i);
			rsRewardAcc = statement1
					.executeQuery("select * from REWARD_ACCOUNT where STATUS_LIFECYCLE_ID <> 3 and BUSINESS_OBJECT_ID ="
							+ subID + " and REWARD_PROGRAM_TYP_ID=" + loyaltyPgmProductId);

			while (rsRewardAcc.next()) {

				GetLoyaltyAccount jsonPath = new GetLoyaltyAccount(i);

				String rewardAccId = String.valueOf(rsRewardAcc.getString("REWARD_ACCOUNT_ID"));

				String id = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, jsonPath.id));

				String status = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, jsonPath.status));
				String statusCd = DBUtils.getStatusCd(status);

				String loyaltyBalance = JSONUtils.checkValue(jsonResponse, jsonPath.loyaltyBalance);

				Reporting.logReporter(Status.INFO, "---Product RewardType ID= " + loyaltyPgmProductId + "---");

				// GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsRewardAcc.getString("BUSINESS_OBJECT_ID")),
				// id,"ID");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsRewardAcc.getString("REWARD_PROGRAM_TYP_ID")),
						loyaltyPgmProductId, "LOYALTY_PROGRAM_PRODUCT_NAME");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsRewardAcc.getString("BUSINESS_OBJECT_TYPE_ID")), statusCd, "STATUS");

				int charIndex = 0;
				do {

					String charNameJsonPath = "$.[" + i + "].characteristic[" + charIndex + "].name";
					String charValueJsonPath = "$.[" + i + "].characteristic[" + charIndex + "].value";
					charName = JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, charNameJsonPath);
					if (!charName.equals("NA")) {
						switch (charName) {
						case "MONTHLY_DRAWDOWN_AMT":
							charMonthlyDrawdownAmt = JSONUtils.checkValue(jsonResponse, charValueJsonPath);

							GenericUtils.validateAssertEquals(charMonthlyDrawdownAmt, charMonthlyDrawdownAmt,
									"MONTHLY_DRAWDOWN_AMT");

							break;
						case "FINANCE_COMMITMENT_ITEM_ID":
							charFinanceCommItemId = String
									.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, charValueJsonPath));

							GenericUtils.validateAssertEqualsFromDB(
									String.valueOf(rsRewardAcc.getString("CURR_FIN_COMMITMENT_ID")),
									charFinanceCommItemId, "FINANCE_COMMITMENT_ITEM_ID");

							break;
						case "INSTALLMENTS_LEFT_NUM":
							charInstallmentLeftNum = String
									.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, charValueJsonPath));
							if (loyaltyPgmProductId.equals("4") || loyaltyPgmProductId.equals("12")
									|| loyaltyPgmProductId.equals("1") || loyaltyPgmProductId.equals("14"))
								GenericUtils.validateAssertEqualsFromDB("0", charInstallmentLeftNum,
										"INSTALLMENTS_LEFT_NUM");
							else
								GenericUtils.validateAssertEqualsFromDB(
										String.valueOf(rsRewardAcc.getString("REMAINING_INSTLMNT_QTY")),
										charInstallmentLeftNum, "INSTALLMENTS_LEFT_NUM");

							break;
						case "PAID_AMT":
							charPaidAmt = JSONUtils.checkValue(jsonResponse, charValueJsonPath);

							GenericUtils.validateAssertEquals("0", charPaidAmt, "PAID_AMT");

							break;
						case "CATALOGUE_ITEM_CATEGORY_CD":
							charCatlogueItemCatCd = String
									.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, charValueJsonPath));

							GenericUtils.validateAssertEquals("SMARTPHONE", charCatlogueItemCatCd,
									"CATALOGUE_ITEM_CATEGORY_CD");

							break;
						case "CATALOGUE_ITEM_CATEGORY_TYPE_CD":
							charCatlogueItemCatTypeCd = String
									.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonResponse, charValueJsonPath));

							GenericUtils.validateAssertEquals("DEVICE_TYPE", charCatlogueItemCatTypeCd,
									"CATALOGUE_ITEM_CATEGORY_TYPE_CD");

							break;
						}
					}
					charIndex++;
				} while (!charName.equals("NA"));
				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsRewardAcc.getString("CURRENCY_BAL_AMT")),
						loyaltyBalance, "LOYALTY_BALANCE");
			}

		}

	}

	public static void addAgrmtItemDBCheck(String jsonString, int itemNo) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsAgreementItem = null;

		Statement statement2 = null;
		statement2 = DBUtils.Conn.createStatement();
		ResultSet rsAgreementTax = null;

		Statement statement3 = null;
		statement3 = DBUtils.Conn.createStatement();
		ResultSet rsAgreementPromo = null;

		int agreementItemNo = 0;

		for (int i = 0; i < itemNo; i++) {

			AddAgreementItem agrmtItem = new AddAgreementItem(i);
			agreementItemNo = i + 1;

			String agreementItem_id = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_id));

			Itemtype = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemType));
			String agreementItem_itemType = DBUtils.getItemType(Itemtype);

			Reporting.logReporter(Status.INFO, "--- Agreement Item : " + agreementItemNo + " : " + Itemtype + "---");

			String agreementItem_itemDurationAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemDurationAmount));

			String agreementItem_itemDurationStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationStartDateTime);

			String agreementItem_itemDurationEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationEndDateTime);
			
			agreementItem_itemDurationEndDateTime = JSONUtils.getEndDate(agreementItem_itemDurationEndDateTime,
					agreementItem_itemDurationStartDateTime, agreementItem_itemType, agreementItem_itemDurationAmount);

			String agreementItem_incentiveAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_incentiveAmount));

			String agreementItem_incentiveServiceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_incentiveServiceCode);

			String agreementItem_installmentAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAmount));

			String agreementItem_installmentStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_installmentStartDateTime);

			String agreementItem_installmentEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_installmentEndDateTime);
			
			agreementItem_installmentEndDateTime = JSONUtils.getEndDate(agreementItem_installmentEndDateTime,
					agreementItem_installmentStartDateTime, agreementItem_itemType, agreementItem_itemDurationAmount);

			String agreementItem_installmentLeftNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentLeftNumValue));

			String agreementItem_installmentAppliedNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAppliedNumValue));

			String agreementItem_installmentAppliedAmtValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAppliedAmtValue));

			String agreementItem_termOrConditionMinRatePlanValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinRatePlanValue);

			String agreementItem_termOrConditionMinFeatureValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinFeatureValue);

			String agreementItem_termOrConditionMinCombinedValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinCombinedValue);

			String agreementItem_termOrConditionCommitmentServiceCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionCommitmentServiceCdValue);

			String agreementItem_termOrConditionAutoTopupCommitmentIndValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionAutoTopupCommitmentIndValue);
			agreementItem_termOrConditionAutoTopupCommitmentIndValue = DBUtils
					.convertYN(agreementItem_termOrConditionAutoTopupCommitmentIndValue);

			String agreementItem_taxPaymentMethodCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentMethodCode);

			String agreementItem_taxPaymentMechanismCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentMechanismCode);

			String agreementItem_taxPaymentChannelCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentChannelCode);

			String agreementItem_taxProvinceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxProvinceCode);

			String agreementItem_taxCategory = JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_taxCategory);

			String agreementItem_taxRate = JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_taxRate);

			String agreementItem_taxAmountValue = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_taxAmountValue));

			String agreementItem_productSerialNumber = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productSerialNumber);

			String agreementItem_productPriceValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productPriceValue);

			String agreementItem_productCharacteristicValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productCharacteristicValue);

			String agreementItem_promotionid = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_promotionid));

			String agreementItem_promotionPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_promotionPerspectiveDate));
			agreementItem_promotionPerspectiveDate = agreementItem_promotionPerspectiveDate.split("T")[0];

			String agreementItem_productOfferingid = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingid));

			String offerID = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
					agrmtItem.agreementItem_productOfferingRedeemedOfferContextCodeValue));
			String agreementItem_productOfferingRedeemedOfferContextCodeValue = DBUtils.getProductOfferID(offerID);

			String agreementItem_productOfferingOfferTierCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productOfferingOfferTierCdValue);
			agreementItem_productOfferingOfferTierCdValue = DBUtils
					.getNullCode(agreementItem_productOfferingOfferTierCdValue);

			String agreementItem_productOfferingOfferTierCapAmtValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productOfferingOfferTierCapAmtValue);
			agreementItem_productOfferingOfferTierCapAmtValue = DBUtils
					.getNullCode(agreementItem_productOfferingOfferTierCapAmtValue);

			String agreementItem_productOfferingDataCommitmentIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_productOfferingDataCommitmentIndValue));
			agreementItem_productOfferingDataCommitmentIndValue = DBUtils
					.convertYN(agreementItem_productOfferingDataCommitmentIndValue);

			String agreementItem_productOfferingContractEnforcedPlanIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_productOfferingContractEnforcedPlanIndValue));
			agreementItem_productOfferingContractEnforcedPlanIndValue = DBUtils
					.convertYN(agreementItem_productOfferingContractEnforcedPlanIndValue);

			String agreementItem_productOfferingPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingPerspectiveDate));
			agreementItem_productOfferingPerspectiveDate = agreementItem_productOfferingPerspectiveDate.split("T")[0];

			String agreementItem_productOfferingSourceSystemId = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingSourceSystemId));

			String relatedParty_Subid = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.relatedParty_Subid));

			
			if (agreementItem_itemType.equals("17")) {
				agreementItem_termOrConditionMinRatePlanValue = "null";
				agreementItem_termOrConditionMinFeatureValue = "null";
				agreementItem_termOrConditionMinCombinedValue = "null";
				agreementItem_productSerialNumber = "null";
				agreementItem_productPriceValue = "null";
				agreementItem_productCharacteristicValue = "null";
			}

			String tax = null;
			rsAgreementItem = statement.executeQuery("Select * from cust_srvc_agrmt_item item  "
					+ "inner join CUST_SRVC_AGRMT_ITM_PROMO promo  "
					+ "on item.CUST_SRVC_AGRMT_ITEM_ID = promo.CUST_SRVC_AGRMT_ITEM_ID and item.customer_svc_agreement_id =(select customer_svc_agreement_id from CUSTOMER_SERVICE_AGREEMENT where SUBSCRIPTION_ID = '"+relatedParty_Subid+"') and item.REWARD_PROGRAM_TYP_ID='" + agreementItem_itemType + "' and item.ORIG_COMMITMENT_LENGTH_NUM != '0'");

			while (rsAgreementItem.next()) {

				custAgmtItemId = String.valueOf(rsAgreementItem.getString("CUST_SRVC_AGRMT_ITEM_ID"));

				Reporting.logReporter(Status.INFO, "CUST_SRVC_AGRMT_ITEM_ID Value from DB is : " + custAgmtItemId);

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_START_DT")),
						agreementItem_itemDurationStartDateTime, "COMMITMENT_EFF_START_DT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_END_DT")),
						agreementItem_itemDurationEndDateTime, "COMMITMENT_EFF_END_DT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("ORIG_COMMITMENT_LENGTH_NUM")),
						agreementItem_itemDurationAmount, "ORIG_COMMITMENT_LENGTH_NUM");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("REWARD_PROGRAM_TYP_ID")),
						agreementItem_itemType, "REWARD_PROGRAM_TYP_ID");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("INCENTIVE_AMT")),
						agreementItem_incentiveAmount, "INCENTIVE_AMT");

				if (!agreementItem_incentiveServiceCode.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("INCENTIVE_CD")),
							agreementItem_incentiveServiceCode, "INCENTIVE_CD");
				}

				if (!agreementItem_installmentStartDateTime.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_START_DT")),
							agreementItem_installmentStartDateTime, "REWARD_INSTLMNT_START_DT");
				}
				if (!agreementItem_installmentEndDateTime.equals("NA")) {

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_END_DT")),
							agreementItem_installmentEndDateTime, "REWARD_INSTLMNT_END_DT");
				}

				if (!agreementItem_installmentAmount.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("REWARD_INSTLMNT_QTY")),
							agreementItem_installmentAmount, "REWARD_INSTLMNT_QTY");
				}

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("RTPLN_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinRatePlanValue, "RTPLN_MIN_COMMITMENT_AMT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("FEAT_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinFeatureValue, "FEAT_MIN_COMMITMENT_AMT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("COMB_MIN_COMMITMENT_AMT")),
						agreementItem_termOrConditionMinCombinedValue, "COMB_MIN_COMMITMENT_AMT");

				if (!agreementItem_termOrConditionCommitmentServiceCdValue.equals("NA"))
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("COMMITMENT_SERVICE_CD")),
							agreementItem_termOrConditionCommitmentServiceCdValue, "COMMITMENT_SERVICE_CD");

				if (!agreementItem_termOrConditionAutoTopupCommitmentIndValue.equals("NA"))
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("COMMITMENT_AUTOTOPUP_IND")),
							agreementItem_termOrConditionAutoTopupCommitmentIndValue, "COMMITMENT_AUTOTOPUP_IND");

				if (agreementItem_itemType.equals("4") || agreementItem_itemType.equals("9")) {

					rsAgreementTax = statement3.executeQuery(
							"select * from CUST_SRVC_AGRMT_ITM_TAX tax inner join CUST_SRVC_AGRMT_ITM_TAX_DTL dtl "
									+ "on tax.CUST_SRVC_AGRMT_ITM_TAX_ID = dtl.CUST_SRVC_AGRMT_ITM_TAX_ID "
									+ "and tax.CUST_SRVC_AGRMT_ITEM_ID=" + custAgmtItemId);

					while (rsAgreementTax.next()) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PAYMENT_METHOD_CD")),
								agreementItem_taxPaymentMethodCode, "TAX_PAYMENT_METHOD_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PYMT_MECHANISM_CD")),
								agreementItem_taxPaymentMechanismCode, "TAX_PYMT_MECHANISM_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PAYMENT_CHANNEL_CD")),
								agreementItem_taxPaymentChannelCode, "TAX_PAYMENT_CHANNEL_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAXATION_PROVINCE_CD")),
								agreementItem_taxProvinceCode, "TAXATION_PROVINCE_CD");

						GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementTax.getString("TAX_TYPE_CD")),
								agreementItem_taxCategory, "TAX_TYPE_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_RATE_PCT")), agreementItem_taxRate,
								"TAX_RATE_PCT");

						GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementTax.getString("TAX_AMT")),
								agreementItem_taxAmountValue, "TAX_AMT");
					}
				}

				if (!agreementItem_itemType.equals("5")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("REDEEMED_PROMOTION_ID")), agreementItem_promotionid,
							"REDEEMED_PROMOTION_ID");

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REDEEMED_PROMOTION_TS")),
							agreementItem_promotionPerspectiveDate, "REDEEMED_PROMOTION_TS");
				}
				if (!agreementItem_productSerialNumber.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("HANDSET_SERIAL_NUM")),
							agreementItem_productSerialNumber, "HANDSET_SERIAL_NUM");
				}
				if (!agreementItem_productPriceValue.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("CATALOGUE_ITEM_PRICE_AMT")),
							agreementItem_productPriceValue, "CATALOGUE_ITEM_PRICE_AMT");
				}
				if (!agreementItem_productCharacteristicValue.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("USIM_ID")),
							agreementItem_productCharacteristicValue, "USIM_ID");
				}

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("REDEEMED_OFFER_ID")),
						agreementItem_productOfferingid, "REDEEMED_OFFER_ID");

				/*GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("REDEEMED_OFFER_TYPE_ID")),
						agreementItem_productOfferingRedeemedOfferContextCodeValue, "REDEEMED_OFFER_TYPE_ID");*/

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("REDEEMED_OFFER_TIER_CD")),
						agreementItem_productOfferingOfferTierCdValue, "REDEEMED_OFFER_TIER_CD");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("REDEEMED_OFFER_TIER_CAP_AMT")),
						agreementItem_productOfferingOfferTierCapAmtValue, "REDEEMED_OFFER_TIER_CAP_AMT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("DATA_SRVC_REQ_IND")),
						agreementItem_productOfferingDataCommitmentIndValue, "DATA_SRVC_REQ_IND");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("COMB_MIN_CMITMT_DISCHRG_IND")),
						agreementItem_productOfferingContractEnforcedPlanIndValue, "COMB_MIN_CMITMT_DISCHRG_IND");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getDate("REDEEMED_OFFER_TS")),
						agreementItem_productOfferingPerspectiveDate, "REDEEMED_OFFER_TS");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("REDEEMED_OFFER_SYS_ID")),
						agreementItem_productOfferingSourceSystemId, "REDEEMED_OFFER_SYS_ID");

			}
			rsAgreementItem.close();
		}

	}

	public static void updateItemDBcheck(String jsonString, int itemNo) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsAgreementItem = null;

		Statement statement2 = null;
		statement2 = DBUtils.Conn.createStatement();
		ResultSet rsAgreementTax = null;

		Statement statement3 = null;
		statement3 = DBUtils.Conn.createStatement();
		ResultSet rsAgreementPromo = null;

		int agreementItemNo = 0;

		for (int i = 0; i < itemNo; i++) {

			UpdateItem agrmtItem = new UpdateItem(i);
			agreementItemNo = i + 1;

			String itemUpdate_id = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_id));

			Itemtype = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_itemType));
			String itemUpdate_itemType = DBUtils.getItemType(Itemtype);

			Reporting.logReporter(Status.INFO, "--- Agreement Item : " + agreementItemNo + " : " + Itemtype + "---");

			String itemUpdate_itemDurationAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_itemDurationAmount));

			String itemUpdate_itemDurationStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_itemDurationStartDateTime);

			String itemUpdate_itemDurationEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_itemDurationEndDateTime);
			
			itemUpdate_itemDurationEndDateTime = JSONUtils.getEndDate(itemUpdate_itemDurationEndDateTime,
					itemUpdate_itemDurationStartDateTime, itemUpdate_itemType, itemUpdate_itemDurationAmount);

			String itemUpdate_incentiveAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_incentiveAmount));

			String itemUpdate_incentiveServiceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_incentiveServiceCode);

			String itemUpdate_installmentAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_installmentAmount));

			String itemUpdate_installmentStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_installmentStartDateTime);
			if (itemUpdate_itemType.equals("9"))
				itemUpdate_installmentStartDateTime = JSONUtils.getInstallmentStartDate(
						itemUpdate_installmentStartDateTime, itemUpdate_itemType,
						itemUpdate_installmentAmount);

			String itemUpdate_installmentEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_installmentEndDateTime);
			itemUpdate_installmentEndDateTime = JSONUtils.getEndDate(itemUpdate_installmentEndDateTime,
					itemUpdate_installmentStartDateTime, itemUpdate_itemType, itemUpdate_installmentAmount);
			if (itemUpdate_itemType.equals("10") || itemUpdate_itemType.equals("11"))
				itemUpdate_installmentEndDateTime = "null";

			String itemUpdate_installmentLeftNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_installmentLeftNumValue));

			String itemUpdate_installmentAppliedNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_installmentAppliedNumValue));

			String itemUpdate_installmentAppliedAmtValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_installmentAppliedAmtValue));

			String itemUpdate_termOrConditionMinRatePlanValue = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_termOrConditionMinRatePlanValue);

			String itemUpdate_termOrConditionMinFeatureValue = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_termOrConditionMinFeatureValue);

			String itemUpdate_termOrConditionMinCombinedValue = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_termOrConditionMinCombinedValue);

			String itemUpdate_termOrConditionCommitmentServiceCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_termOrConditionCommitmentServiceCdValue);

			String itemUpdate_termOrConditionAutoTopupCommitmentIndValue = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_termOrConditionAutoTopupCommitmentIndValue);
			itemUpdate_termOrConditionAutoTopupCommitmentIndValue = DBUtils
					.convertYN(itemUpdate_termOrConditionAutoTopupCommitmentIndValue);

			String itemUpdate_taxPaymentMethodCode = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_taxPaymentMethodCode);

			String itemUpdate_taxPaymentMechanismCode = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_taxPaymentMechanismCode);

			String itemUpdate_taxPaymentChannelCode = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_taxPaymentChannelCode);

			String itemUpdate_taxProvinceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_taxProvinceCode);

			String itemUpdate_taxCategory = JSONUtils.checkValue(jsonString, agrmtItem.itemUpdate_taxCategory);

			String itemUpdate_taxRate = JSONUtils.checkValue(jsonString, agrmtItem.itemUpdate_taxRate);

			String itemUpdate_taxAmountValue = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_taxAmountValue));

			String itemUpdate_productSerialNumber = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_productSerialNumber);

			String itemUpdate_productPriceValue = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_productPriceValue);

			String itemUpdate_productCharacteristicValue = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_productCharacteristicValue);

			String itemUpdate_promotionid = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_promotionid));

			String itemUpdate_promotionPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_promotionPerspectiveDate));
			itemUpdate_promotionPerspectiveDate = itemUpdate_promotionPerspectiveDate.split("T")[0];

			String itemUpdate_productOfferingid = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_productOfferingid));

			String offerID = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
					agrmtItem.itemUpdate_productOfferingRedeemedOfferContextCodeValue));
			String itemUpdate_productOfferingRedeemedOfferContextCodeValue = DBUtils.getProductOfferID(offerID);

			String itemUpdate_productOfferingOfferTierCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_productOfferingOfferTierCdValue);
			itemUpdate_productOfferingOfferTierCdValue = DBUtils
					.getNullCode(itemUpdate_productOfferingOfferTierCdValue);

			String itemUpdate_productOfferingOfferTierCapAmtValue = JSONUtils.checkValue(jsonString,
					agrmtItem.itemUpdate_productOfferingOfferTierCapAmtValue);
			itemUpdate_productOfferingOfferTierCapAmtValue = DBUtils
					.getNullCode(itemUpdate_productOfferingOfferTierCapAmtValue);

			String itemUpdate_productOfferingDataCommitmentIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.itemUpdate_productOfferingDataCommitmentIndValue));
			itemUpdate_productOfferingDataCommitmentIndValue = DBUtils
					.convertYN(itemUpdate_productOfferingDataCommitmentIndValue);

			String itemUpdate_productOfferingContractEnforcedPlanIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.itemUpdate_productOfferingContractEnforcedPlanIndValue));
			itemUpdate_productOfferingContractEnforcedPlanIndValue = DBUtils
					.convertYN(itemUpdate_productOfferingContractEnforcedPlanIndValue);

			String itemUpdate_productOfferingPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_productOfferingPerspectiveDate));
			itemUpdate_productOfferingPerspectiveDate = itemUpdate_productOfferingPerspectiveDate.split("T")[0];

			String itemUpdate_productOfferingSourceSystemId = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.itemUpdate_productOfferingSourceSystemId));

			String relatedParty_Subid = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.relatedParty_Subid));

			
			if (itemUpdate_itemType.equals("17")) {
				itemUpdate_termOrConditionMinRatePlanValue = "null";
				itemUpdate_termOrConditionMinFeatureValue = "null";
				itemUpdate_termOrConditionMinCombinedValue = "null";
				itemUpdate_productSerialNumber = "null";
				itemUpdate_productPriceValue = "null";
				itemUpdate_productCharacteristicValue = "null";
			}

			String tax = null;
			rsAgreementItem = statement.executeQuery("Select * from cust_srvc_agrmt_item item  "
					+ "inner join CUST_SRVC_AGRMT_ITM_PROMO promo  "
					+ "on item.CUST_SRVC_AGRMT_ITEM_ID = promo.CUST_SRVC_AGRMT_ITEM_ID and item.customer_svc_agreement_id =(select customer_svc_agreement_id from CUSTOMER_SERVICE_AGREEMENT where SUBSCRIPTION_ID = '"+relatedParty_Subid+"') and item.REWARD_PROGRAM_TYP_ID='" + itemUpdate_itemType + "' and item.ORIG_COMMITMENT_LENGTH_NUM != '0'");

			while (rsAgreementItem.next()) {

				custAgmtItemId = String.valueOf(rsAgreementItem.getString("CUST_SRVC_AGRMT_ITEM_ID"));

				Reporting.logReporter(Status.INFO, "CUST_SRVC_AGRMT_ITEM_ID Value from DB is : " + custAgmtItemId);

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_START_DT")),
						itemUpdate_itemDurationStartDateTime, "COMMITMENT_EFF_START_DT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("REWARD_PROGRAM_TYP_ID")),
						itemUpdate_itemType, "REWARD_PROGRAM_TYP_ID");
			
				if (!itemUpdate_incentiveServiceCode.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getInt("INCENTIVE_CD")),
							itemUpdate_incentiveServiceCode, "INCENTIVE_CD");
				}

				// INSTALLMENTS_LEFT_NUM, INSTALLMENTS_APPLIED_NUM, INSTALLMENTS_APPLIED_AMT ->
				// No need to DB validation

				/*
				 * GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("RTPLN_MIN_COMMITMENT_AMT")),
						itemUpdate_termOrConditionMinRatePlanValue, "RTPLN_MIN_COMMITMENT_AMT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("FEAT_MIN_COMMITMENT_AMT")),
						itemUpdate_termOrConditionMinFeatureValue, "FEAT_MIN_COMMITMENT_AMT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("COMB_MIN_COMMITMENT_AMT")),
						itemUpdate_termOrConditionMinCombinedValue, "COMB_MIN_COMMITMENT_AMT");

				if (!itemUpdate_termOrConditionCommitmentServiceCdValue.equals("NA"))
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("COMMITMENT_SERVICE_CD")),
							itemUpdate_termOrConditionCommitmentServiceCdValue, "COMMITMENT_SERVICE_CD");

				if (!itemUpdate_termOrConditionAutoTopupCommitmentIndValue.equals("NA"))
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("COMMITMENT_AUTOTOPUP_IND")),
							itemUpdate_termOrConditionAutoTopupCommitmentIndValue, "COMMITMENT_AUTOTOPUP_IND");

				if (itemUpdate_itemType.equals("4") || itemUpdate_itemType.equals("9")) {

					rsAgreementTax = statement3.executeQuery(
							"select * from CUST_SRVC_AGRMT_ITM_TAX tax inner join CUST_SRVC_AGRMT_ITM_TAX_DTL dtl "
									+ "on tax.CUST_SRVC_AGRMT_ITM_TAX_ID = dtl.CUST_SRVC_AGRMT_ITM_TAX_ID "
									+ "and tax.CUST_SRVC_AGRMT_ITEM_ID=" + custAgmtItemId);

					while (rsAgreementTax.next()) {
						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PAYMENT_METHOD_CD")),
								itemUpdate_taxPaymentMethodCode, "TAX_PAYMENT_METHOD_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PYMT_MECHANISM_CD")),
								itemUpdate_taxPaymentMechanismCode, "TAX_PYMT_MECHANISM_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_PAYMENT_CHANNEL_CD")),
								itemUpdate_taxPaymentChannelCode, "TAX_PAYMENT_CHANNEL_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAXATION_PROVINCE_CD")),
								itemUpdate_taxProvinceCode, "TAXATION_PROVINCE_CD");

						GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementTax.getString("TAX_TYPE_CD")),
								itemUpdate_taxCategory, "TAX_TYPE_CD");

						GenericUtils.validateAssertEqualsFromDB(
								String.valueOf(rsAgreementTax.getString("TAX_RATE_PCT")), itemUpdate_taxRate,
								"TAX_RATE_PCT");

						GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementTax.getString("TAX_AMT")),
								itemUpdate_taxAmountValue, "TAX_AMT");
					}
				}

				if (!itemUpdate_itemType.equals("5")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("REDEEMED_PROMOTION_ID")), itemUpdate_promotionid,
							"REDEEMED_PROMOTION_ID");

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REDEEMED_PROMOTION_TS")),
							itemUpdate_promotionPerspectiveDate, "REDEEMED_PROMOTION_TS");
				}
				if (!itemUpdate_productSerialNumber.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("HANDSET_SERIAL_NUM")),
							itemUpdate_productSerialNumber, "HANDSET_SERIAL_NUM");
				}
				if (!itemUpdate_productPriceValue.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("CATALOGUE_ITEM_PRICE_AMT")),
							itemUpdate_productPriceValue, "CATALOGUE_ITEM_PRICE_AMT");
				}
				if (!itemUpdate_productCharacteristicValue.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("USIM_ID")),
							itemUpdate_productCharacteristicValue, "USIM_ID");
				}

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("REDEEMED_OFFER_ID")),
						itemUpdate_productOfferingid, "REDEEMED_OFFER_ID");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("REDEEMED_OFFER_TYPE_ID")),
						itemUpdate_productOfferingRedeemedOfferContextCodeValue, "REDEEMED_OFFER_TYPE_ID");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("REDEEMED_OFFER_TIER_CD")),
						itemUpdate_productOfferingOfferTierCdValue, "REDEEMED_OFFER_TIER_CD");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("REDEEMED_OFFER_TIER_CAP_AMT")),
						itemUpdate_productOfferingOfferTierCapAmtValue, "REDEEMED_OFFER_TIER_CAP_AMT");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("DATA_SRVC_REQ_IND")),
						itemUpdate_productOfferingDataCommitmentIndValue, "DATA_SRVC_REQ_IND");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("COMB_MIN_CMITMT_DISCHRG_IND")),
						itemUpdate_productOfferingContractEnforcedPlanIndValue, "COMB_MIN_CMITMT_DISCHRG_IND");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getDate("REDEEMED_OFFER_TS")),
						itemUpdate_productOfferingPerspectiveDate, "REDEEMED_OFFER_TS");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("REDEEMED_OFFER_SYS_ID")),
						itemUpdate_productOfferingSourceSystemId, "REDEEMED_OFFER_SYS_ID");*/
				
				Reporting.logReporter(Status.INFO, "--- Updated fields  : ");
					GenericUtils.validateAssertEquals(itemUpdate_installmentAppliedNumValue,
							itemUpdate_installmentAppliedNumValue, "INSTALLMENT_APPLIED_NUM");

				switch(itemUpdate_installmentAppliedNumValue) {
				case "0":
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_END_DT")),
							itemUpdate_itemDurationEndDateTime, "COMMITMENT_EFF_END_DT");
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("INCENTIVE_AMT")),
							itemUpdate_incentiveAmount, "INCENTIVE_AMT");
					break;
				case "1":
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_END_DT")),
							itemUpdate_itemDurationStartDateTime, "COMMITMENT_EFF_END_DT");
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_END_DT")),
							itemUpdate_itemDurationStartDateTime, "REWARD_INSTLMNT_END_DT");
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_START_DT")),
							itemUpdate_itemDurationStartDateTime, "REWARD_INSTLMNT_START_DT");
					GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("INCENTIVE_AMT")),
							itemUpdate_incentiveAmount, "INCENTIVE_AMT");
					break;
				// and incentive_amt
				}
			}
			rsAgreementItem.close();
		}

	}

	public static void returnAgrmtItemDBCheck(String jsonString, int itemNo) throws SQLException {

		Statement statement = null;
		statement = DBUtils.Conn.createStatement();
		ResultSet rsAgreementItem = null;


		int agreementItemNo = 0;

		for (int i = 0; i < itemNo; i++) {

			AddAgreementItem agrmtItem = new AddAgreementItem(i);
			agreementItemNo = i + 1;

			String agreementItem_id = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_id));

			Itemtype = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemType));
			String agreementItem_itemType = DBUtils.getItemType(Itemtype);

			Reporting.logReporter(Status.INFO, "--- Agreement Item : " + agreementItemNo + " : " + Itemtype + "---");

			String agreementItem_itemDurationAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_itemDurationAmount));

			String agreementItem_itemDurationStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationStartDateTime);

			String agreementItem_itemDurationEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_itemDurationEndDateTime);
			
			agreementItem_itemDurationEndDateTime = JSONUtils.getEndDate(agreementItem_itemDurationEndDateTime,
					agreementItem_itemDurationStartDateTime, agreementItem_itemType, agreementItem_itemDurationAmount);

			String agreementItem_incentiveAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_incentiveAmount));

			String agreementItem_incentiveServiceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_incentiveServiceCode);

			String agreementItem_installmentAmount = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAmount));

			String agreementItem_installmentStartDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_installmentStartDateTime);

			String agreementItem_installmentEndDateTime = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_installmentEndDateTime);
			
			agreementItem_installmentEndDateTime = JSONUtils.getEndDate(agreementItem_installmentEndDateTime,
					agreementItem_installmentStartDateTime, agreementItem_itemType, agreementItem_itemDurationAmount);

			String agreementItem_installmentLeftNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentLeftNumValue));

			String agreementItem_installmentAppliedNumValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAppliedNumValue));

			String agreementItem_installmentAppliedAmtValue = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_installmentAppliedAmtValue));

			String agreementItem_termOrConditionMinRatePlanValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinRatePlanValue);

			String agreementItem_termOrConditionMinFeatureValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinFeatureValue);

			String agreementItem_termOrConditionMinCombinedValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionMinCombinedValue);

			String agreementItem_termOrConditionCommitmentServiceCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionCommitmentServiceCdValue);

			String agreementItem_termOrConditionAutoTopupCommitmentIndValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_termOrConditionAutoTopupCommitmentIndValue);
			agreementItem_termOrConditionAutoTopupCommitmentIndValue = DBUtils
					.convertYN(agreementItem_termOrConditionAutoTopupCommitmentIndValue);

			String agreementItem_taxPaymentMethodCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentMethodCode);

			String agreementItem_taxPaymentMechanismCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentMechanismCode);

			String agreementItem_taxPaymentChannelCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxPaymentChannelCode);

			String agreementItem_taxProvinceCode = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_taxProvinceCode);

			String agreementItem_taxCategory = JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_taxCategory);

			String agreementItem_taxRate = JSONUtils.checkValue(jsonString, agrmtItem.agreementItem_taxRate);

			String agreementItem_taxAmountValue = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_taxAmountValue));

			String agreementItem_productSerialNumber = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productSerialNumber);

			String agreementItem_productPriceValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productPriceValue);

			String agreementItem_productCharacteristicValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productCharacteristicValue);

			String agreementItem_promotionid = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_promotionid));

			String agreementItem_promotionPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_promotionPerspectiveDate));
			agreementItem_promotionPerspectiveDate = agreementItem_promotionPerspectiveDate.split("T")[0];

			String agreementItem_productOfferingid = String.valueOf(
					JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingid));

			String offerID = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
					agrmtItem.agreementItem_productOfferingRedeemedOfferContextCodeValue));
			String agreementItem_productOfferingRedeemedOfferContextCodeValue = DBUtils.getProductOfferID(offerID);

			String agreementItem_productOfferingOfferTierCdValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productOfferingOfferTierCdValue);
			agreementItem_productOfferingOfferTierCdValue = DBUtils
					.getNullCode(agreementItem_productOfferingOfferTierCdValue);

			String agreementItem_productOfferingOfferTierCapAmtValue = JSONUtils.checkValue(jsonString,
					agrmtItem.agreementItem_productOfferingOfferTierCapAmtValue);
			agreementItem_productOfferingOfferTierCapAmtValue = DBUtils
					.getNullCode(agreementItem_productOfferingOfferTierCapAmtValue);

			String agreementItem_productOfferingDataCommitmentIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_productOfferingDataCommitmentIndValue));
			agreementItem_productOfferingDataCommitmentIndValue = DBUtils
					.convertYN(agreementItem_productOfferingDataCommitmentIndValue);

			String agreementItem_productOfferingContractEnforcedPlanIndValue = String
					.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString,
							agrmtItem.agreementItem_productOfferingContractEnforcedPlanIndValue));
			agreementItem_productOfferingContractEnforcedPlanIndValue = DBUtils
					.convertYN(agreementItem_productOfferingContractEnforcedPlanIndValue);

			String agreementItem_productOfferingPerspectiveDate = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingPerspectiveDate));
			agreementItem_productOfferingPerspectiveDate = agreementItem_productOfferingPerspectiveDate.split("T")[0];

			String agreementItem_productOfferingSourceSystemId = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.agreementItem_productOfferingSourceSystemId));

			String relatedParty_Subid = String.valueOf(JSONUtils
					.getJSONKeyValueUsingJsonPath(jsonString, agrmtItem.relatedParty_Subid));

			
			if (agreementItem_itemType.equals("17")) {
				agreementItem_termOrConditionMinRatePlanValue = "null";
				agreementItem_termOrConditionMinFeatureValue = "null";
				agreementItem_termOrConditionMinCombinedValue = "null";
				agreementItem_productSerialNumber = "null";
				agreementItem_productPriceValue = "null";
				agreementItem_productCharacteristicValue = "null";
			}

			String tax = null;
			rsAgreementItem = statement.executeQuery("SELECT item.REWARD_PROGRAM_TYP_ID,item.COMMITMENT_EFF_END_DT,item.COMMITMENT_EFF_START_DT,item.ORIG_COMMITMENT_LENGTH_NUM,item.REWARD_INSTLMNT_START_DT, item.REWARD_INSTLMNT_END_DT, item.REWARD_INSTLMNT_QTY, \r\n" + 
					"agmtLyf.CUST_AGRMT_STAT_LIFECYCL_ID, agmtLyf.EFF_START_TS, agmtLyf.EFF_STOP_TS, \r\n" + 
					"rewTxn.CURRENCY_BAL_AMT, rewTxn.REWARD_TXN_RSN_ID, \r\n" + 
					"rewAcc.STATUS_LIFECYCLE_ID, rewAcc.CURRENCY_BAL_AMT as REWARD_ACC_CURRENCY_BAL_AMT,\r\n" + 
					"rsnTyp.TYP_CD\r\n" + 
					"FROM  CUSTOMER_SERVICE_AGREEMENT agrmt \r\n" + 
					"inner join CUST_SRVC_AGRMT_ITEM item \r\n" + 
					"on agrmt.CUSTOMER_SVC_AGREEMENT_ID = item.customer_svc_agreement_id \r\n" + 
					"and agrmt.subscriber_no ='4264186097' \r\n" + 
					"and agrmt.current_ind='Y' \r\n" + 
					"and item.REWARD_PROGRAM_TYP_ID='17'\r\n" + 
					"inner join REWARD_ACCOUNT rewAcc \r\n" + 
					"on agrmt.SUBSCRIPTION_ID = rewAcc.BUSINESS_OBJECT_ID \r\n" + 
					"inner join REWARD_TXN rewTxn \r\n" + 
					"on rewAcc.REWARD_ACCOUNT_ID = rewTxn.REWARD_ACCOUNT_ID\r\n" + 
					"and rewTxn.REWARD_TXN_RSN_ID !='1'\r\n" + 
					"inner join CUST_AGRMT_LIFECYCL agmtLyf\r\n" + 
					"on agmtLyf.CUSTOMER_SVC_AGREEMENT_ID=item.CUSTOMER_SVC_AGREEMENT_ID\r\n" + 
					"inner join REWARD_TXN_RSN rewTxnRsn\r\n" + 
					"on rewTxn.REWARD_TXN_RSN_ID = rewTxnRsn.REWARD_TXN_RSN_ID\r\n" + 
					"inner join REWARD_TXN_TYP rewTxnTyp\r\n" + 
					"on rewTxnTyp.REWARD_TXN_TYP_ID=rewTxnRsn.REWARD_TXN_TYP_ID\r\n" + 
					"inner join REWARD_RSN_TYP rsnTyp\r\n" + 
					"on rewTxnRsn.REWARD_RSN_TYP_ID=rsnTyp.REWARD_RSN_TYP_ID\r\n");

			while (rsAgreementItem.next()) {


				Reporting.logReporter(Status.INFO, "DB Validation for RETURN_AGREEMENT_ITEM");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_START_DT")),
						agreementItem_itemDurationStartDateTime, "COMMITMENT_EFF_START_DT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getDate("COMMITMENT_EFF_END_DT")),
						agreementItem_itemDurationStartDateTime, "COMMITMENT_EFF_END_DT");

				GenericUtils.validateAssertEqualsFromDB(
						String.valueOf(rsAgreementItem.getString("ORIG_COMMITMENT_LENGTH_NUM")),
						"0", "ORIG_COMMITMENT_LENGTH_NUM");

				GenericUtils.validateAssertEqualsFromDB(String.valueOf(rsAgreementItem.getString("REWARD_PROGRAM_TYP_ID")),
						agreementItem_itemType, "REWARD_PROGRAM_TYP_ID");

				if (!agreementItem_installmentStartDateTime.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_START_DT")),
							agreementItem_installmentStartDateTime, "REWARD_INSTLMNT_START_DT");
				}
				if (!agreementItem_installmentEndDateTime.equals("NA")) {

					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getDate("REWARD_INSTLMNT_END_DT")),
							agreementItem_installmentEndDateTime, "REWARD_INSTLMNT_END_DT");
				}

				if (!agreementItem_installmentAmount.equals("NA")) {
					GenericUtils.validateAssertEqualsFromDB(
							String.valueOf(rsAgreementItem.getString("REWARD_INSTLMNT_QTY")),
							agreementItem_installmentAmount, "REWARD_INSTLMNT_QTY");
				}

				GenericUtils.validateAssertEquals(String.valueOf(rsAgreementItem.getString("REWARD_ACC_CURRENCY_BAL_AMT")),
						"0", "REWARD_ACCOUNT_CURRENCY_BAL_AMT");
				
				GenericUtils.validateAssertEquals(String.valueOf(rsAgreementItem.getString("REWARD_TXN_RSN_ID")),
						"220", "REWARD_TXN_RSN_ID");
				
				GenericUtils.validateAssertEquals(String.valueOf(rsAgreementItem.getString("TYP_CD")),
						"RETURN_REVERSAL", "REASON_TYPE");

			}
			rsAgreementItem.close();
		}

	}

	public static void migrationDBcheck(String jsonString, int i) {
		
		
	}

	

}
