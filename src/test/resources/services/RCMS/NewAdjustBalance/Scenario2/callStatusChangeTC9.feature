@Ignore
Feature: RCMS New Adjust Reward Amount API - TC09 Perform Status change from Cancelled to Active for Telus Sub with DF+ACCESSORYFINANCE


Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def payload = read(PATH_API_PAYLOAD + 'NewAdjustBalance/Scenario2/TC09_Telus_DF_AF_StatusChange.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'StatusChange'
    
Scenario: Retrieve SPID from the LSMS
    #Set endpoint url
    Given url 'https://apigw-st.tsl.telus.com/customer/loyaltyAgreement/v1/loyaltyAgreement/123'
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method patch