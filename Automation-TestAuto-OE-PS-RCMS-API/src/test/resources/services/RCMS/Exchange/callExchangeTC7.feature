@Ignore
Feature: TC07 EAR Perform Renewal(Paid with Trade IN ) for a customer having DF+DB +BIB to DB+ RCB + TIASSETCREDIT and perform exchange to DF+AF + TIASSETCREDIT 

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token_reward']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def startDate = karate.properties['karate.startDate']
    * def payload = read(PATH_API_PAYLOAD + 'Exchange/TC07_Telus_DF_DB_BIB_Renewal_DB_RCB_TIA_Pay_TIP_Exchange_DF_AF_TIA.json')
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
