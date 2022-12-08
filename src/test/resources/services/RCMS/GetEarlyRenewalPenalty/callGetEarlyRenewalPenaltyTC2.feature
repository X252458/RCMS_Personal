@Ignore
Feature: TC02 Call getEarlyRenewalPenalty for Telus  subscriber with TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token_violation = karate.properties['karate.auth_token_violation']
    * def subID = karate.properties['karate.subID']
    * header Authorization = 'Bearer ' + auth_token_violation
    * def payload = read(PATH_API_PAYLOAD +'EarlyRenewalPenalty/CommonEarlyRenewalPenalty_with_LoyaltyType.json')
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
 
	
  Scenario: Retrieve SPID from the LSMS

    Given url ENDPOINT_EARLY_RENEWAL_PENALTY
    And request payload
    When method post
