@Ignore
Feature: RCMS New Adjust Reward Amount API - TC06 Perform Accessory Partial Return = ExchangeAgreementItem with amount=100 ( keep 100 dollar Accessory)
and Client wants to partially return accessories worth 100 dollars and keep $100 worth.

Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token']
    * def startDate = karate.properties['karate.startDate']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def payload = read(PATH_API_PAYLOAD + 'NewAdjustBalance/Scenario1/TC06_Telus_DF_AF_ExchangeAgreementItem.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'ExchangeAgreementItem'
    
Scenario: Retrieve SPID from the LSMS
    #Set endpoint url
    Given url 'https://apigw-st.tsl.telus.com/customer/loyaltyAgreement/v1/loyaltyAgreement/1'
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method patch