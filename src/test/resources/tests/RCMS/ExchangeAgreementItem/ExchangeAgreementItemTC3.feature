Feature: TC03 Perform exchangeAgreementItem for Telus Subscriber with DB+ACB+TIASSETCREDIT+TIPROMOCREDIT +AF+DF doing exchangeAgreementItem to AF 

  Scenario: Verify Exchange Agreement Item for Telus Subscriber
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'ExchangeAgreementItem/callExchangeAgreementItemTC3.feature')
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 200
