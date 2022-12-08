Feature: TC03 Perform Device Return – notifySubscriptionReturn which makes DF=0 

  Scenario: Verify Notify Subscription Return which makes DF=0 
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario1/callNotifySubscriptionReturnTC3.feature')
    #Response
    #* json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 200
   