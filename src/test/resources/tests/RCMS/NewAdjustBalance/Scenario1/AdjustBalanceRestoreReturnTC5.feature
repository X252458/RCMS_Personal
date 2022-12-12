Feature: TC05 Perform adjustBalance  with reasonCode=RESTORE_RETURN (amount=100) 

  Scenario: Verify Adjust Balance with reasonCode RESTORE_RETURN
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario3/callAdjustBalanceRestoreReturnTC20.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    ##Then match apiDetailsStatus == 200
   