Feature: TC01 Telus Sub with DF+ACCESSORYFINANCE_Update AF_Adjustment reason CANCELLATION_PAYMENT


  Scenario: Verify AF Adjust Reward Balance with reason code CANCELLATION_PAYMENT
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario1/callAdjustBalanceTC1.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 200
   