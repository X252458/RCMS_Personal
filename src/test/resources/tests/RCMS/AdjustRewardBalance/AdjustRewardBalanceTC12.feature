Feature: TC12 Koodo Subscriber active in RCMS DB having HWS - Adjustment with Retail Tab Credits for HWS to make amount to -ve from 0 

  Scenario: Verify Adjust Reward Balance for HWS with Retail Tab Credits for HWS to make amount to -ve from 0 
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC12.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 200