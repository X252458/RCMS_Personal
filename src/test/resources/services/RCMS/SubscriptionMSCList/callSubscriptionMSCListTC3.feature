Feature: RCMS Activation API - TC01 Perform Update Device Serial Number operation on customer having DB+DF+BIB+ACB+TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token_violation = karate.properties['karate.auth_token_violation']
    * def startDate = karate.properties['karate.startDate']
    * def subID = karate.properties['karate.subID']
    * def payload = read(PATH_API_PAYLOAD + 'SubscriptionMSCList/TC03_DB_ACB_RCB_MSC_NotBroken.json')
    * header Authorization = 'Bearer ' + auth_token_violation
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'

  Scenario: Firing API
  
    #Set endpoint url
    Given url ENDPOINT_VALIDATION_SUBSCRIPTION_MSC_LIST
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method post
