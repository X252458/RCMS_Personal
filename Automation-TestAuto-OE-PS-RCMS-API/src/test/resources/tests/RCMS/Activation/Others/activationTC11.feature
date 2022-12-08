@RCMSactivation
Feature: TC09 Activate Telus Subscriber with DB+DF+BIB+ACB+AF

  Scenario: Verify Activation Telus Subscriber with DB+DF+BIB+ACB+AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Activation/Others/callActivationTC11.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
