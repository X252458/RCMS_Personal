Feature: TC17.b Perform Accessory return - ReturnAgreementItem with amount=300. (full amount): 

  Scenario: Verify Return Agreement Item with full amount
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario3/callReturnAgreementItemTC17.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 500
   