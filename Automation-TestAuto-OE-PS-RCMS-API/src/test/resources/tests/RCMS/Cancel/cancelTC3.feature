Feature: TC01 Telus Subscriber having ACCESSORYFINANCE Renewed to ACCESSORYFINANCE

  Scenario: Verify Renewal with AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Cancel/callCancelTC3.feature')
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 200
