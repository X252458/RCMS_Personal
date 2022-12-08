@RCMSactivation
Feature: TC01 Activate Telus Subscriber with TAB+HWS

  Scenario: Verify Activation Telus Subscriber with All
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'FutureActivation/callFutureActivationTC3.feature')
    #Request
    * json apiResponse = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
