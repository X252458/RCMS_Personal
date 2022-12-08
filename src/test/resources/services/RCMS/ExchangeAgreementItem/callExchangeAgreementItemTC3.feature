@Ignore
Feature: RCMS Exchange Agreement Item API- TC03 Perform exchangeAgreementItem operation for Telus subscriber with DB_ACB_TIA_TIP_AF_DF doing  exchangeAgreementItem for AF 

Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token']
    * def startDate = karate.properties['karate.startDate']
    * def endDate = karate.properties['karate.endDate']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def payload = read(PATH_API_PAYLOAD + 'ExchangeAgreementItem/TC03_Telus_DB_ACB_TIA_TIP_AF_DF_ExAgmtItem.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'ExchangeAgreementItem'
    
Scenario: Retrieve SPID from the LSMS
    #Set endpoint url
    Given url ENDPOINT_EXCHANGE_AGREEMENT_ITEM
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method patch