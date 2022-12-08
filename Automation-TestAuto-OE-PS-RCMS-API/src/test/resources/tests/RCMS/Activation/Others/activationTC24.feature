@RCMSactivation
Feature: TC12 Activate Telus Subscriber with TAB

  Scenario: Verify Activation Telus Subscriber with TAB
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Activation/Others/callActivationTC24.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200