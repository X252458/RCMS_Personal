Feature: TC19 Perform Accessory Partial Return = ExchangeAgreementItem with amount=100 ( keep 200 dollar Accessory)
Client wants to partially return accessories worth 100 dollars and keep $200 worth.

  Scenario: Verify  Accessory Partial Return
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario3/callExchangeAgreementItemTC19.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 500
   