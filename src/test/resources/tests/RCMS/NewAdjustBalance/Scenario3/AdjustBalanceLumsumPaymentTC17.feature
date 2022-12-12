Feature: TC17 Telus Sub with DF+ACCESSORYFINANCE_Update AF_Adjustment reason =LUMPSUM PAYMENT
 

  Scenario:  Verify AF Adjust Reward Balance with reason code LUMPSUM PAYMENT
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario3/callAdjustBalanceLumsumPaymentTC17.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    ##Then match apiDetailsStatus == 200
   