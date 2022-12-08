Feature: RCMS Activation API - TC01 Call getTerminationPenalty operation for Telus Subscriber having ACCESSORYFINANCE

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token_violation']
    * def subID = karate.properties['karate.subID']
    * header Authorization = 'Bearer ' + auth_token
    * def payload = read(PATH_API_PAYLOAD +'TerminationPenalty/CommonTerminationPenalty_with_LoyaltyType.json')
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
 
	
  Scenario: Firing GetEarlyRenewalPenalty API

    Given url ENDPOINT_TERMINATION_PENALTY
    And request payload
    When method post
