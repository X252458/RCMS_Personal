Feature: TC15  Telus Sub with DB + DF + BIB + ACB_Adjustment reason CORRECTION_Update BIB  balance to -ve from 0
 

  Scenario: Verify BIB Adjust Reward Balance -ve from 0 with reason code CORRECTION
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC15.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Status
    ##Then match apiDetailsStatus == 200