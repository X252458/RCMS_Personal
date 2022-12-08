@RCMSactivation
Feature: TC07 Activate Telus Subscriber with DF_DB_BIB_ACB

  Scenario: Verify Activation Telus Subscriber with DF_DB_BIB_ACB
    #Operation 1
    When def tc08ActivateTelusSubwithDF = call read(PATH_API_OPS+'Activation/Others/callActivationTC8.feature')
    #Request
    * json tc08ActivateTelusSubwithDFRequest = tc08ActivateTelusSubwithDF.payload
    #Status
    * def tc08ActivateTelusSubwithDFStatus = tc08ActivateTelusSubwithDF.responseStatus
    #Validation
    Then match tc08ActivateTelusSubwithDFStatus == 200
