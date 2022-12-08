@RCMSactivation
Feature: TC06 Activate Telus Subscriber with DF_DB_BIB

  Scenario: Verify Activation Telus Subscriber with DF_DB_BIB
    #Operation 1
    When def tc06ActivateTelusSubwithDF_DB_BIB = call read(PATH_API_OPS+'Activation/Others/callActivationTC6.feature')
    #Request
    * json tc06ActivateTelusSubwithDF_DB_BIBRequest = tc06ActivateTelusSubwithDF_DB_BIB.payload
    #Status
    * def tc06ActivateTelusSubwithDF_DB_BIBStatus = tc06ActivateTelusSubwithDF_DB_BIB.responseStatus
    #Validation
    Then match tc06ActivateTelusSubwithDF_DB_BIBStatus == 200
