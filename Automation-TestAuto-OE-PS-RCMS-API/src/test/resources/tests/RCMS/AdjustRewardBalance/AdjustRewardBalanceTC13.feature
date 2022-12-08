Feature: TC13 Telus Sub with DB + DF + BIB + ACB_Adjustment reason CORRECTION_Update DF balance to -ve from 0 

  Scenario: Verify DF Adjust Reward Balance -ve from 0 with reason code CORRECTION
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC13.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 200