Feature: TC11 Koodo Subscriber active in RCMS DB having TAB - Adjustment with Goodwill to make the amount to -ve from 0 

  Scenario: Verify Adjust Reward Balance for TAB with Goodwill to make the amount to -ve from 0 
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC11.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 500