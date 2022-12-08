@RCMSactivation
Feature: TC16 Activate Telus Subscriber with DF + DB

  Scenario: Verify Activation Telus Subscriber with DF+DB
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Activation/Others/callActivationTC16.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200