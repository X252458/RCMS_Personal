Feature: TC08 Perform Notify Cancellation for Telus Sub with DF+ACCESSORYFINANCE

  Scenario: Verify  Notify Cancellation for Telus Sub
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario2/callNotifyCancellationTC8.feature')
    #Response
    #* json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    ##Then match apiDetailsStatus == 200
   