Feature: TC12 Perform Device Return – notifySubscriptionReturn makes DF=0 

  Scenario: Verify Device Return – notifySubscriptionReturn makes DF=0 
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario2/callNotifySubscriptionReturnTC12.feature')
    #Response
    #* json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 200
   