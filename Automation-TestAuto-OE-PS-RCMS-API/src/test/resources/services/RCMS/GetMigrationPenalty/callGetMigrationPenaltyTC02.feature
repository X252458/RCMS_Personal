@Ignore
Feature: RCMS Migration API - TC02 Call getMigrationPenalty for a subscriber who wants to Migrate from Postpaid to Prepaid - From DB + DF + BIB + ACB + RCB to PRECREDIT

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token_violation = karate.properties['karate.auth_token_violation']
    * def subID = karate.properties['karate.subID']
    * header Authorization = 'Bearer ' + auth_token_violation
    * def payload = read(PATH_API_PAYLOAD +'MigrationPenalty/CommonMigrationPenalty_Post_to_Pre.json')
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
 
	
  Scenario: Firing GetEarlyRenewalPenalty API

    Given url ENDPOINT_MIGRATION_PENALTY
    And request payload
    When method post
