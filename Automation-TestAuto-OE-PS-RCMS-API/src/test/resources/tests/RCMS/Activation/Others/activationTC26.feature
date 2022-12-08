Feature: Activate Telus Subscriber with TIA

  Scenario: Verify Activation Telus Subscriber with TIA
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Activation/Others/callActivationTC26.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200