Feature: TC17 Telus Sub with DB + DF + BIB + ACB_Adjustment reason 'ACB_MANUAL_INSTALLMENT'_Update ACB  

  Scenario: Verify ACB Adjust Reward Balance  with reason code ACB_MANUAL_INSTALLMENT
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC17.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    ##Then match apiDetailsStatus == 200