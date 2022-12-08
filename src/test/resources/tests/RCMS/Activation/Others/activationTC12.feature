@RCMSactivation
Feature: TC12 Activate Telus Subscriber with DB+ACB

  Scenario: Verify Activation Telus Subscriber with DB+ACB
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Activation/Others/callActivationTC12.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
