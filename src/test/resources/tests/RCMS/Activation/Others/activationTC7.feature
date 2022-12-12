@RCMSactivation
Feature: TC07 Activate Telus Subscriber with DF_DB_BIB_ACB

  Scenario: Verify Activation Telus Subscriber with DF_DB_BIB_ACB
    #Operation 1
    When def tc07ActivateTelusSubwithDF_DB_BIB_ACB = call read(PATH_API_OPS+'Activation/Others/callActivationTC7.feature')
    #Request
    * json tc07ActivateTelusSubwithDF_DB_BIB_ACBRequest = tc07ActivateTelusSubwithDF_DB_BIB_ACB.payload
    #Status
    * def tc07ActivateTelusSubwithDF_DB_BIB_ACBStatus = tc07ActivateTelusSubwithDF_DB_BIB_ACB.responseStatus
    #Validation
    #Then match tc07ActivateTelusSubwithDF_DB_BIB_ACBStatus == 200
