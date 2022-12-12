Feature: TC18 Telus Sub with DF + DB+RCB_Adjustment reason  'RCB_MANUAL_INSTALLMENT'_Update RCB 

  Scenario: Verify RCB Adjust Reward Balance  with reason code RCB_MANUAL_INSTALLMENT
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'AdjustRewardBalance/callAdjustRewardBalanceTC18.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    ##Then match apiDetailsStatus == 200