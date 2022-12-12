Feature: TC02 Telus Sub with DB+DF+BIB+ACB+TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE_Update AF_Adjustment reason LUMPSUM_PAYMENT

  Scenario: Verify AF Balance Adjustment with reason code LUMPSUM_PAYMENT
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC2.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    ##Then match apiDetailsStatus == 200