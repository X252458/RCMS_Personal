Feature: TC04 Telus Subscriber having DF Renewed to DB,DF,RCB,AF

  Scenario: Verify Renewal with DB,DF,RCB, AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Renewal/getEarlyRenewalPenalty/callRenewalTC4.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 200
