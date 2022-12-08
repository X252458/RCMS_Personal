@Ignore
Feature: TC04  EAR Perform Renewal for a customer having DF  to DB + TIASSETCREDIT +AF and perform exchange to DF + DB  + TIASSETCREDIT + TIPROMOCREDIT 

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token_reward']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def startDate = karate.properties['karate.startDate']
    * def payload = read(PATH_API_PAYLOAD + 'Exchange/TC04_Telus_DF_Renewal_DB_TIA_AF_Exchange_DF_DB_TIA_TIP.json')
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
