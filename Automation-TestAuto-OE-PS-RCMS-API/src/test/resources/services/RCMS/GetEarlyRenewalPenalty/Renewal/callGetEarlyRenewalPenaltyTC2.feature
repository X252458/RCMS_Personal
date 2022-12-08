@Ignore
Feature: RCMS Activation API - TC01 Call getRewardCommitment operation for Telus Subscriber having ACCESSORYFINANCE Renewed to ACCESSORYFINANCE

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token_violation']
    * def subID = karate.properties['karate.subID']
    * header Authorization = 'Bearer ' + auth_token
    * def payload = read(PATH_API_PAYLOAD +'EarlyRenewalPenalty/CommonEarlyRenewalPenalty.json')
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
 
	
  Scenario: Firing GetEarlyRenewalPenalty API

    Given url ENDPOINT_EARLY_RENEWAL_PENALTY
    And request payload
    When method post
