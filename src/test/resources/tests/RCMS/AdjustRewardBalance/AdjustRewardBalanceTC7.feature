Feature: TC07 Koodo Subscriber active in RCMS DB having TAB and HWS - Adjustment with Goodwill for TAB and HWS

  Scenario: Verify Adjust Reward Balance for TAB and HWS with Goodwill
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC7.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 200