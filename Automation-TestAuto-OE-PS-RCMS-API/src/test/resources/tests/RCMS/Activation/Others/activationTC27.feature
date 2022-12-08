Feature: Activate Telus Subscriber with DB+DF+AF balance 0

  Scenario: Verify Activation Telus Subscriber with DB+DF+AF balance 0
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Activation/Others/callActivationTC27.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200