@RCMSactivation
Feature: TC09 Activate Telus Subscriber with PRESOC

  Scenario: Verify Activation Telus Subscriber with PRESOC
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Activation/Others/callActivationTC10.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 200
