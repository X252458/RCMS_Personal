@Ignore
Feature: RCMS Activation API - UpdateItem API 

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token_reward']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def startDate = karate.properties['karate.startDate']
    * def endDate = karate.properties['karate.endDate']
    * def payload = read(PATH_API_PAYLOAD + 'UpdateItem/TC04_Telus_DB_DF_BIB_ACB_TIA_TIP_Update_Return_complete.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'ItemUpdate' 

  Scenario: Firing UpdateItem API
    #Set endpoint url
    Given url ENDPOINT_UPDATE_COMMITMENT_ITEM
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method patch
