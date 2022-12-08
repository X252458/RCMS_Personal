@Ignore
Feature: RCMS ChangeServiceCommitmentPenalty API - TC01 Call getChangeServiceCommitmentPenalty operation for Koodo sub with TAB+HWS_MSC Broken Already provided the Payload during Activation automation     

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token_violation = karate.properties['karate.auth_token_violation']
    * def subID = karate.properties['karate.subID']
    * def startDate = karate.properties['karate.startDate']
    * header Authorization = 'Bearer ' + auth_token_violation
    * def payload = read(PATH_API_PAYLOAD +'GetChangeServCommitmentPenalty/TC01_Koodo_TAB_HWS_MSC_Broken.json')
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
 
	
  Scenario: Firing GetChangeServiceCommitmentPenalty API

    Given url ENDPOINT_GET_CHANGE_SERVICE_COMMITMENT_PENALTY    
    And request payload
    When method post
