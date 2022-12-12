Feature: TC06 Perform Accessory Partial Return = ExchangeAgreementItem with amount=100 ( keep 100 dollar Accessory)
and Client wants to partially return accessories worth 100 dollars and keep $100 worth 

  Scenario: Verify Accessory Partial Return 
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario1/callExchangeAgreementItemTC6.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 500
   