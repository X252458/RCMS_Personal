Feature: RCMS Activation API - TC02_Telus_Subscriber_DB_DF_BIB_ACB_TIASSETCREDIT_TIPROMOCREDIT_Renewal_DB_AF_Payment_Method_as_BIB_TELUS_PENDING

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token_Mgmt = karate.properties['karate.auth_token_Mgmt']
    * def subID = karate.properties['karate.subID']
    * def type = karate.properties['karate.type']
    * def startDate = karate.properties['karate.startDate']
    * def URL = ENDPOINT_LIST_EARNED_TRANSACTIONS+subID+'/loyaltyBalance/'+type+'/loyaltyEarn' 
    * header Authorization = 'Bearer ' + auth_token_Mgmt
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'

  Scenario: Firing API
    #Set endpoint url
    Given url URL
    When method GET
