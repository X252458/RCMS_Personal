@Ignore
Feature: RCMS Activation API - Telus Subscriber with HARWARE_ACCESSORYFINANCE


  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token_reward = karate.properties['karate.auth_token_reward']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def startDate = karate.properties['karate.startDate']
    * def payload = read(PATH_API_PAYLOAD + 'Activation/Others/Activation_DB_DF_BIB.json')
    * header Authorization = 'Bearer ' + auth_token_reward
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'ACTIVATION'

  Scenario: Firing API
    #Set endpoint url
    Given url ENDPOINT_ACTIVATION
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method post
