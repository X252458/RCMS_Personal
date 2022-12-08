Feature: TC01 Telus Subscriber having DF Renewed to DB,RCB,AF

  Scenario: Verify Renewal with DB,RCB, AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Renewal/getAccTerminationPenalty/callRenewalTC2.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
