@Ignore
Feature: RCMS Adjust Reward Amount API - TC13Telus Sub with DB + DF + BIB + ACB_Adjustment reason CORRECTION_Update DF balance to -ve from 0 

Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token_management']
    * def subID = karate.properties['karate.subID']
    #* def subNum = karate.properties['karate.subNum']
    * def payload = read(PATH_API_PAYLOAD + 'AdjustRewardBalance/TC13-15_Telus_DB_DF_BIB_ACB_AdRewardBalance_CORRECTION.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * path  karate.properties['karate.itemType']
    
Scenario: Retrieve SPID from the LSMS
    #Set endpoint url
    Given url ENDPOINT_ADJUST_REWARD_BALANCE
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method patch
    