Feature: TC10 Koodo Subscriber active in RCMS DB having TAB and HWS - Adjustment with Correction for TAB and HWS
 

  Scenario: Verify Adjust Reward Balance for TAB and HWS with Correction for TAB and HWS
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC10.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    ##Then match apiDetailsStatus == 200