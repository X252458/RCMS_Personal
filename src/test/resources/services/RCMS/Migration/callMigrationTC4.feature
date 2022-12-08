Feature: RCMS Migration API

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token_reward']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def startDate = karate.properties['karate.startDate']
    * def payload = read(PATH_API_PAYLOAD + 'Migration/TC04_Pre_PRESOC_PRECREDIT_to_Post_DB_DF_ACB_RCB_AF.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'Migration' 

  Scenario: Firing UpdateItem API
    #Set endpoint url
    Given url ENDPOINT_MIGRATION
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method patch
