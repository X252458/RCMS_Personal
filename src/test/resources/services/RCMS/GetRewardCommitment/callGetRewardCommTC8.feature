@Ignore
Feature: RCMS Activation API - TC08 Call getRewardCommitment operation for koodo Subscriber having HWS+TAB

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token']
    * def subID = karate.properties['karate.subID']
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param subscriptionId = subID;
		* param brandId = '3' 
		

  Scenario: Firing API for getRewardCommitment API
    #Set endpoint url
    Given url ENDPOINT_REWARD_COMMITMENT
    #		When REST operation GET
    When method get
