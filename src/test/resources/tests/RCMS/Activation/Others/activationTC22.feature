@RCMSactivation
Feature: TC02 Activate Telus Subscriber with DF

  Scenario: Verify Activation Telus Subscriber with DF
    #Operation 1
    When def tc22ActivateDB_DF_RCB = call read(PATH_API_OPS+'Activation/Others/callActivationTC22.feature')
    #Request
    * json tc22ActivateDB_DF_RCBRequest = tc22ActivateDB_DF_RCB.payload
    #Status
    * def tc22ActivateDB_DF_RCBStatus = tc22ActivateDB_DF_RCB.responseStatus
    #Validation
    ##Then match tc22ActivateDB_DF_RCBStatus == 200
