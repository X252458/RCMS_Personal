Feature: TC01 Telus Subscriber having ACCESSORYFINANCE Renewed to ACCESSORYFINANCE

  Scenario: Verify Renewal with AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Renewal/getMigrationPenalty/callRenewalTC2.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 200
