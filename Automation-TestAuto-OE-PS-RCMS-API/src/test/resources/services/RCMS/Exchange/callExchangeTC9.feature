@Ignore
Feature: TC09 EAR Perform Renewal for a customer havingTAB to HWS and perform exchange to TAB+HWS 

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token_reward']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def startDate = karate.properties['karate.startDate']
    * def payload = read(PATH_API_PAYLOAD + 'Exchange/TC09_Telus_TAB_Renewal_HWS_Exchange_TAB_HWS.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'EXCHANGE'

  Scenario: Firing API
    #Set endpoint url
    Given url ENDPOINT_OFFER_EXCHANGE
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method patch
