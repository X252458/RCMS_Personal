@Ignore
Feature: RCMS Activation API - TC06 Activate TELUS subscriber with no reward account SIM ONLY

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def startDate = karate.properties['karate.startDate']
    * def payload = read(PATH_API_PAYLOAD + 'Activation/TC06_Telus_with_no_reward_acc_SIM_ONLY.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'ACTIVATION'

  Scenario: Retrieve SPID from the LSMS
    #Set endpoint url
    Given url ENDPOINT_ACTIVATION
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method post
