Feature: TC04 Telus Sub with DB+DF+BIB+ACB+TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE_Update AF_Adjustment reason as RESTORE_RETURN when AF BALANCE is NOT_0

  Scenario: Verify AF Balance Adjustment with reason code RESTORE_RETURN when AF BALANCE is NOT_0
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC4.feature')
    #Request
    * json apiDetailsResponse = apiDetails.response
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 500