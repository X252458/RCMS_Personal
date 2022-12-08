@Ignore
Feature: RCMS GetLoyaltyAccount API - TC01 Call GetLoyaltyAccount for different reward types of subscribers

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token_management = karate.properties['karate.auth_token_management']
    * def subID = karate.properties['karate.subID']
    * def URL = ENDPOINT_GET_LOYALTY_ACCOUNT+subID+'/loyaltyAccount'
    * header Authorization = 'Bearer ' + auth_token_management
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
 
	
  Scenario: Firing GetLoyaltyAccount API

    Given url URL
    When method GET
