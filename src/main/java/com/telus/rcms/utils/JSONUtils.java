package com.telus.rcms.utils;

import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.aventstack.extentreports.Status;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.telus.rcms.jsonPathLibrary.ActivationPayloadJsonPath;
import com.telus.rcms.jsonPathLibrary.AgreementItem;
import com.telus.rcms.jsonPathLibrary.ExchangeAgreementItem;
import com.telus.rcms.jsonPathLibrary.GetRewardCommitmentAgreementItem;
import com.test.reporting.Reporting;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 ****************************************************************************
 * > DESCRIPTION: This Class contains the common methods for handling the json
 * files and json objects
 ****************************************************************************
 */
public class JSONUtils {

	static String AgreementDurationAmount;
	static String agrmtId = null;

	/**
	 * Description: This method return the value for the json key based on the
	 * jsonPath
	 * 
	 * @param jsonPath
	 * @return
	 */
	public static String getJSONKeyValueUsingJsonPath(String jsonStringInput, String jsonPath) {

		Object ob = null;
		String keyValue = null;

		try {
			ob = JsonPath.read(jsonStringInput, jsonPath);
			keyValue = ob.toString();
		} catch (Exception e) {
			if (e instanceof NullPointerException)
				return null;
			else if (e instanceof PathNotFoundException)
				return "NA";
		}
		return keyValue;

	}

	public static String readJsonAsString(String filePath) {

		String jsonString = null;
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(filePath));
			jsonString = jsonObject.toString();
		} catch (Exception e) {
			Reporting.logReporter(Status.INFO, "Unable to read JSON file as String");
		}

		return jsonString;

	}

	public static String checkValue(String jsonFile, String jsonPath) {
		String varName = null;
		try {
			varName = String.valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonFile, jsonPath));
			if (varName.contains(":")) {
				varName = varName.split("T")[0];
			} else if (varName.contains(".")) {
				varName = varName.split("\\.")[0];
				if (varName.equals("null"))
					varName = "0";
			}
			
		} catch (NullPointerException e) {
			varName = null;
		}
		return varName;
	}

	public static String getStartDate() {
		String startDate = null;
		startDate = LocalDate.now().toString().concat("T00:00:00-0500");		
		return startDate;

	}
	public static String getGMTStartDate() {
		
		/*
		 * Instant instant = Instant.now(); ZonedDateTime zdtNewYork =
		 * instant.atZone(ZoneId.of("UTC-04:00")); DateTimeFormatter formatter =
		 * DateTimeFormatter.ofPattern("yyyy-MM-dd"); String startDate =
		 * zdtNewYork.format(formatter).toString().concat("T00:00:00-0400"); return
		 * startDate;
		 */
		
		Instant instant = Instant.now();
        ZonedDateTime zdtNewYork = instant.atZone(ZoneId.of("EST", ZoneId.SHORT_IDS));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = zdtNewYork.format(formatter).toString();
        startDate=startDate.concat("T00:00:00-0500");
        return startDate;
		
	}
	
public static String getNewGMTEndDate(Integer duration) {
				
		Instant instant = Instant.now();
        ZonedDateTime zdtNewYork = instant.atZone(ZoneId.of("EST", ZoneId.SHORT_IDS));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = zdtNewYork.format(formatter).toString();
        
        LocalDate endDateFormatted;
		endDateFormatted = LocalDate.parse(startDate);
		endDateFormatted = endDateFormatted.plusMonths(duration);
		String futureEndDate = endDateFormatted.toString();
		futureEndDate = futureEndDate.concat("T00:00:00-0500");
		return futureEndDate;
		
	}
	
	public static String getGMTFutureStartDate() {

		Instant instant = Instant.now();
		ZonedDateTime zdtNewYork = instant.atZone(ZoneId.of("EST", ZoneId.SHORT_IDS));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String startDate = zdtNewYork.format(formatter).toString();

		LocalDate endDateFormatted;
		endDateFormatted = LocalDate.parse(startDate);
		endDateFormatted = endDateFormatted.plusMonths(1);
		String futureEndDate = endDateFormatted.toString();
		futureEndDate = futureEndDate.concat("T00:00:00-0500");

		return futureEndDate;

	}
	
	public static String getInstallmentFutureStartDate(String startDate, String ItemType, String duration) {

		LocalDate startDateFormatted;
		int monthDuration = Integer.parseInt(duration);

		switch (ItemType) {
		case "BIB":
		case "9":
			startDateFormatted = LocalDate.parse(startDate);
            startDateFormatted = startDateFormatted.plusMonths(monthDuration + 1).minusDays(1);
//			startDateFormatted = startDateFormatted.plusMonths(monthDuration + 1);
			startDate = startDateFormatted.toString();
			break;
		case "TIASSETCREDIT":
			startDateFormatted = LocalDate.parse(startDate);
			startDateFormatted = startDateFormatted.plusDays(180);
			startDate = startDateFormatted.toString();
			break;
		}
		return startDate;

	}

	public static String getAgrmtEndDate(String endDate, String startDate, String duration) {

		if (endDate == null || startDate.equals(endDate) || endDate.equals("null")) {
			LocalDate endDateFormatted;
			int monthDuration = Integer.parseInt(duration);
			endDateFormatted = LocalDate.parse(startDate);
			endDateFormatted = endDateFormatted.plusMonths(monthDuration);
			endDate = endDateFormatted.toString();

		}
		

		return endDate;
	}

	/*
	 * public static String getItemDuration(String itemType, String duration) {
	 * 
	 * switch (itemType) { case "13": case "4": case "9": case "14": case "17":
	 * duration=duration; break;
	 * 
	 * case "10": case "11": duration=duration; break;
	 * 
	 * case "5": duration="0"; break;
	 * 
	 * case "15": case "16": duration="6"; break;
	 * 
	 * } return duration; }
	 */

	public static String getInstallmentStartDate(String startDate, String ItemType, String duration) {

		LocalDate startDateFormatted;
		int monthDuration = Integer.parseInt(duration);

		switch (ItemType) {
		case "BIB":
		case "9":
			startDateFormatted = LocalDate.parse(startDate);
          startDateFormatted = startDateFormatted.plusMonths(monthDuration + 1).minusDays(1);
//        startDateFormatted = startDateFormatted.plusMonths(monthDuration + 1);
          startDate = startDateFormatted.toString();
          break;
		case "TIASSETCREDIT":
			startDateFormatted = LocalDate.parse(startDate);
			startDateFormatted = startDateFormatted.plusDays(180);
			startDate = startDateFormatted.toString();
			break;
		}
		return startDate;

	}

	public static String getEndDate(String endDate, String startDate, String ItemType, String duration) {

		try {
			if (endDate.equalsIgnoreCase("NA"))
				return "NA";

			if (endDate == null || startDate.equals(endDate) || endDate.equals("null")) {

				LocalDate endDateFormatted;
				int monthDuration = Integer.parseInt(duration);

				switch (ItemType) {
				case "1":
				case "4":
				case "7":
				case "9":
				case "12":
				case "13":
				case "14":
				case "17":
					endDateFormatted = LocalDate.parse(startDate);
					endDateFormatted = endDateFormatted.plusMonths(monthDuration);
					endDate = endDateFormatted.toString();
					break;
				case "10":
				case "11":
				case "5":
					endDate = startDate;
					break;
				case "15":
				case "16":
					endDateFormatted = LocalDate.parse(startDate);
					endDateFormatted = endDateFormatted.plusDays(180);
					endDate = endDateFormatted.toString();
					break;

				}
			} else if (endDate != null)
				endDate = endDate;
		} catch (Exception e) {
			Reporting.logReporter(Status.ERROR, "Unable to get EndDate");
			e.printStackTrace();
		}
		return String.valueOf(endDate);
	}
	


	public static void main(String[] args) {

		String filePath = "D:\\RCMS_REPO\\src\\test\\resources\\testSpecs\\RCMS\\test.json";
		try {
			String jsonStringReader = readJsonAsString(filePath);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static String getGMTEndDate(String jsonString) {
		ExchangeAgreementItem jsonPath = new ExchangeAgreementItem(0);

        String AgreementDurationStartDateTime = JSONUtils.checkValue(jsonString,
                jsonPath.AgreementDurationStartDateTime);
        
        String AgreementDurationEndDateTime = JSONUtils.checkValue(jsonString,
                jsonPath.AgreementDurationEndDateTime);

        String AgreementDurationAmount = String
                .valueOf(JSONUtils.getJSONKeyValueUsingJsonPath(jsonString, jsonPath.AgreementDurationAmount));
        
        if (AgreementDurationEndDateTime.equals("tempDate")) {
            LocalDate endDateFormatted;
            int monthDuration = Integer.parseInt(AgreementDurationAmount);
            endDateFormatted = LocalDate.parse(AgreementDurationStartDateTime);
            endDateFormatted = endDateFormatted.plusMonths(monthDuration);
            AgreementDurationEndDateTime = endDateFormatted.toString().concat("T00:00:00-0400");
        }
		return AgreementDurationEndDateTime;
        
        }

}
