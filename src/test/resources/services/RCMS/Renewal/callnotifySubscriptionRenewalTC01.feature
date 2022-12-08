Feature: RCMS Activation API - TC01_Telus_Subscriber_having_DF_AF_TIASSETCREDIT_TIPROMOCREDIT_Renewed_DF_Payment_BILL

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token_reward = karate.properties['karate.auth_token_reward']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def startDate = karate.properties['karate.startDate']
    * def payload = read(PATH_API_PAYLOAD + 'Renewal/TC01_Telus_DF_AF_TIA_TIP_Renewal_DF_Pay_BILL.json')
    * header Authorization = 'Bearer ' + auth_token_reward
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
    #print response
