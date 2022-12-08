@Ignore
Feature: RCMS Activation API - TC02 Call getBillingAccountTerminationPenaltyfor a MULTIPLE subscribers with Sub1 on DB+BIB+RCB+AF and Sub 2 on DB+DF+RCB+BIB+AF

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token']
    * def accID = karate.properties['karate.accID']
    * def subID = karate.properties['karate.subID']
    * def subNum = karate.properties['karate.subNum']
    * def startDate = karate.properties['karate.startDate']
    * def payload = read(PATH_API_PAYLOAD + 'Renewal/getAccTerminationPenalty/TC02_Telus_DF_Renewal_DB_AF_BIB_RCB_Pay_CC.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'RENEWAL'

  Scenario: Retrieve SPID from the LSMS
    #Set endpoint url
    Given url ENDPOINT_RENEWAL
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method patch
