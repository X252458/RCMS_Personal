@Ignore
Feature: RCMS Log Event API - TC02 Telus Subscriber having DB+DF+BIB renewed to DB  using Payment Method TRADE_IN_PENDING_ LOG Event_RETURN_COMPLETE when device is received in good condition

Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token_management']
    * def subID = karate.properties['karate.subID']
    * def startDate = karate.properties['karate.startDate']
    * def payload = read(PATH_API_PAYLOAD + 'LogEvent/TC01_TC02_Telus_DB_DF_BIB_Renewal_DB_LOG_Event_RETURN_COMPLETE.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    
Scenario: Retrieve SPID from the LSMS
    #Set endpoint url
    Given url ENDPOINT_LOG_EVENT
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method post
    