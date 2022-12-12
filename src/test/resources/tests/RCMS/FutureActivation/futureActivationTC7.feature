@RCMSactivation
Feature: TC01 Activate Telus Subscriber with TAB+HWS+DB+DF+BIB

  Scenario: Verify Activation Telus Subscriber with TAB+HWS+DB+DF+BIB
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'FutureActivation/callFutureActivationTC7.feature')
    #Request
    * json apiResponse = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 200
