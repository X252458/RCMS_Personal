@Ignore
Feature: RCMS New Adjust Reward Amount API - TC07 Perform adjustBalance – reasonCode=RESTORE_RETURN (amount=100) and Return Agreemnet item with full payment

Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token']
    * def startDate = karate.properties['karate.startDate']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def payload = read(PATH_API_PAYLOAD + 'NewAdjustBalance/Scenario1/TC07_Telus_DF_AF_adjustBalance_ReturnAgreement.json')
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