Feature: TC07 Perform adjustBalance – reasonCode=RESTORE_RETURN (amount=100) and Return Agreemnet Item after full payment

  Scenario: Verify Adjust Balance with reasonCode RESTORE_RETURN and Return Agreemnet Item after full payment
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario2/callReturnAgreementItemTC7.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 500
   