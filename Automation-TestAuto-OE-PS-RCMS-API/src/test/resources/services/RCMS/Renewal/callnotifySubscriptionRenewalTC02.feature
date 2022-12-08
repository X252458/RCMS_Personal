Feature: RCMS Activation API - TC02_Telus_Subscriber_DB_DF_BIB_ACB_TIASSETCREDIT_TIPROMOCREDIT_Renewal_DB_AF_Payment_Method_as_BIB_TELUS_PENDING

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token_reward']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def startDate = karate.properties['karate.startDate']
    * def payload = read(PATH_API_PAYLOAD + 'Renewal/TC02_Telus_DB_DF_BIB_ACB_TIA_TIP_Renewal_DB_AF_Pay_BIB_TELUS_PENDING.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'RENEWAL'

  Scenario: Firing API
    #Set endpoint url
    Given url ENDPOINT_RENEWAL
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method patch
    Then print response
