@RCMSactivation
Feature: TC05 Activate Telus Subscriber with AF_DB

  Scenario: Verify Activation Telus Subscriber with AF_DB
    #Operation 1
    When def tc05ActivateTelusSubwithAF_DB = call read(PATH_API_OPS+'Activation/Others/callActivationTC5.feature')
    #Request
    * json tc05ActivateTelusSubwithAF_DBRequest = tc05ActivateTelusSubwithAF_DB.payload
    #Status
    * def tc05ActivateTelusSubwithAF_DBStatus = tc05ActivateTelusSubwithAF_DB.responseStatus
    #Validation
    Then match tc05ActivateTelusSubwithAF_DBStatus == 200
