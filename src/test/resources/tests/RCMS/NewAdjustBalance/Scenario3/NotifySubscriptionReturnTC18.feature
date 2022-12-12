Feature: TC18. Perform Device Return – notifySubscriptionReturn which makes DF=0

  Scenario: Verify Notify Subscription Return which makes DF=0
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario3/callNotifySubscriptionReturnTC18.feature')
    #Response
    #* json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    ##Then match apiDetailsStatus == 200
   