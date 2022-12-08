@Ignore
Feature: RCMS Migration API - TC01 Call getMigrationPenalty for a subscriber who wants to Migrate from Prepaid account with PRESOC to Postpaid (DB+DF)

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token_violation = karate.properties['karate.auth_token_violation']
    * def subID = karate.properties['karate.subID']
    * header Authorization = 'Bearer ' + auth_token_violation
    * def payload = read(PATH_API_PAYLOAD +'MigrationPenalty/CommonMigrationPenalty_Pre_to_Post.json')
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
 
	
  Scenario: Firing GetEarlyRenewalPenalty API

    Given url ENDPOINT_MIGRATION_PENALTY
    And request payload
    When method post
