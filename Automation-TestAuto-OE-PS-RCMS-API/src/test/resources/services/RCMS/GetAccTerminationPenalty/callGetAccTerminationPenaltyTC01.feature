@Ignore
Feature: RCMS AccTerminationPeanlty API - TC01 Call getBillingAccountTerminationPenalty for a Single subscriber with DB+DF+BIB+ACB+AF

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token_violation = karate.properties['karate.auth_token_violation']
    * def accID = karate.properties['karate.accID']
    * header Authorization = 'Bearer ' + auth_token_violation
    * def payload = read(PATH_API_PAYLOAD +'GetAccTerminationPenalty/CommonAccTerminationPenalty.json')
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
 
	
  Scenario: Firing GetBillingAccTerminationPenalty API

    Given url ENDPOINT_GET_BILLING_ACCOUNT_TERMINATION_PENALTY
    And request payload
    When method post
