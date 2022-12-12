@RCMSactivation
Feature: TC02 Activate Telus Subscriber with DF

  Scenario: Verify Activation Telus Subscriber with DF
    #Operation 1
    When def tc03ActivateDB_DF_RCB_AF = call read(PATH_API_OPS+'Activation/Others/callActivationTC3.feature')
    #Request
    * json tc03ActivateDB_DF_RCB_AFRequest = tc03ActivateDB_DF_RCB_AF.payload
    #Status
    * def tc03ActivateDB_DF_RCB_AFStatus = tc03ActivateDB_DF_RCB_AF.responseStatus
    #Validation
    #Then match tc03ActivateDB_DF_RCB_AFStatus == 200
