@Ignore
Feature: RCMS New Adjust Reward Amount API - 

Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token']
    * def startDate = karate.properties['karate.startDate']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def payload = read(PATH_API_PAYLOAD + 'NewAdjustBalance/Scenario3/TC17_Telus_DF_AF_ ReturnAgreementItem.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'ReturnAgreementItem'
    
Scenario: Retrieve SPID from the LSMS
    #Set endpoint url
    Given url 'https://apigw-st.tsl.telus.com/customer/loyaltyAgreement/v1/loyaltyAgreement/1'
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method patch
