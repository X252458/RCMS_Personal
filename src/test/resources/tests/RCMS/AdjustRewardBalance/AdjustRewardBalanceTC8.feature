Feature: TC08 Koodo Subscriber active in RCMS DB having TAB and HWS - Adjustment with Retail Tab Credits for Tab

  Scenario: Verify Adjust Reward Balance for TAB and HWS with Retail Tab Credits for Tab and Hws
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC8.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 200