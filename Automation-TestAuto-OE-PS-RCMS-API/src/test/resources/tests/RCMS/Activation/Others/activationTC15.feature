@RCMSactivation
Feature: TC15 Activate Telus Subscriber with DB+TIA+AF

  Scenario: Verify Activation Telus Subscriber with DB+TIA+AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Activation/Others/callActivationTC15.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
