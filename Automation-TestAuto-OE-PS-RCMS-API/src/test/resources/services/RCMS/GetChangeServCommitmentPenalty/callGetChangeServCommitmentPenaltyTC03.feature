@Ignore
Feature: RCMS ChangeServiceCommitmentPenalty API - TC03  Call getChangeServiceCommitmentPenalty operation for renewed customer having DB + DF + BIB + RCB+AF [CMT Broken]     

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token_violation = karate.properties['karate.auth_token_violation']
    * def subID = karate.properties['karate.subID']
    * def startDate = karate.properties['karate.startDate']
    * header Authorization = 'Bearer ' + auth_token_violation
    * def payload = read(PATH_API_PAYLOAD +'GetChangeServCommitmentPenalty/TC03_Telus_DB_DF_BIB_RCB_AF_CMT_Broken.json')
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
 
	
  Scenario: Firing GetChangeServiceCommitmentPenalty API

    Given url ENDPOINT_GET_CHANGE_SERVICE_COMMITMENT_PENALTY
    And request payload
    When method post
       