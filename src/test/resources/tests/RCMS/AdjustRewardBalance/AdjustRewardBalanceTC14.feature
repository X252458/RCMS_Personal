Feature: TC14 Telus Sub with DB + DF + BIB + ACB_Adjustment reason CORRECTION__Update DB balance to -ve from 0  

  Scenario: Verify DB Adjust Reward Balance -ve from 0 with reason code CORRECTION
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC14.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 200