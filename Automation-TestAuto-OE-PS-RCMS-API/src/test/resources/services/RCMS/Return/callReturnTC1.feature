@Ignore
Feature: RCMS Activation API - TC01 Return API fro renewed DF, AF, TIASSETCREDIT, TIPROMOCREDIT

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def startDate = karate.properties['karate.startDate']
    * def payload = read(PATH_API_PAYLOAD + 'Return/TC01_Telus_AF_Return.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'RETURN'

  Scenario: Firing API
    #Set endpoint url
    Given url ENDPOINT_RETURN
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method patch

    
    
 