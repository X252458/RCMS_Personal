@Ignore
Feature: RCMS Activation API - TC01 Activate Telus Subscriber with DB+DF+BIB+ACB+TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def startDate = karate.properties['karate.startDate']
    * def payload = read(PATH_API_PAYLOAD + 'FutureActivation/TC07_Telus_TAB_HWS_DB_DF_BIB.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'FUTUREACTIVATION'

  Scenario: Retrieve SPID from the LSMS
    #Set endpoint url
    Given url ENDPOINT_ACTIVATION
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method post
