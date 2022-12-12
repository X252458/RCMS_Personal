Feature: TC01 Telus Sub with DB+DF+BIB+ACB+TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE_Update AF_Adjustment reason CORRECTION

  Scenario: Verify AF Balance Adjustment with reason code CORRECTION
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC1.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    ##Then match apiDetailsStatus == 200