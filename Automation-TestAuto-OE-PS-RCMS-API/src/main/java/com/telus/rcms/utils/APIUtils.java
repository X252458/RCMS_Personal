package com.telus.rcms.utils;

import java.util.Map;

import com.aventstack.extentreports.Status;
import com.telus.api.test.utils.APIJava;
import com.test.reporting.Reporting;
import com.test.ui.actions.WebDriverSteps;

public class APIUtils {

	public static String getAccessToken(String environment, String authName) {

		Reporting.logReporter(Status.INFO, "STEP ===== > OAuth2 API Call");
		String token = null;
		Map<String, Object> apiOperation = null;

		try {
			switch (authName) {
			case "rewardService":
				apiOperation = APIJava.runKarateFeature(environment,
						"classpath:tests/RCMS/OAuthAuthentication/generateOauth2TokenRewardService.feature");
				break;
			case "management":
				apiOperation = APIJava.runKarateFeature(environment,
						"classpath:tests/RCMS/OAuthAuthentication/generateOauth2TokenManagement.feature");
				break;
			case "violation":
				apiOperation = APIJava.runKarateFeature(environment,
						"classpath:tests/RCMS/OAuthAuthentication/generateOauth2TokenViolation.feature");
				break;
			}
			Reporting.logReporter(Status.INFO, "API Operation status: " + apiOperation.get("accessTokenStatus"));
//			Reporting.logReporter(Status.INFO, "API Operation Response: " + apiOperation.get("accessTokenResponse"));
//			Reporting.logReporter(Status.INFO, "Oauth2 Access Token: " + apiOperation.get("access_token"));
			token = apiOperation.get("access_token").toString();

		} catch (Exception e) {
			Reporting.logReporter(Status.INFO, "Unable to generate the Access Token" + e.getMessage());
		}

		return token;
	}
	
}
