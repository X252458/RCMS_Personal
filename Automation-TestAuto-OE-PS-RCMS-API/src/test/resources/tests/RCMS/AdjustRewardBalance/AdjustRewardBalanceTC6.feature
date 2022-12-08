Feature: TC06 Telus Sub with DB+DF+BIB+ACB+TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE_Update AF_Adjustment reason LUMPSUM_PAYMENT when AF Balance is 0


  Scenario: Verify AF Balance Adjustment with reason code LUMPSUM_PAYMENT when AF Balance is 0
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC6.feature')
    #Request
    * json apiDetailsRequest = apiDetails.response
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 500