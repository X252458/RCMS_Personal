Feature: TC05 Perform exchangeAgreementItem for Telus Subscriber with DB+ACB+TIASSETCREDIT+TIPROMOCREDIT DF doing exchangeAgreementItem to AF 

  Scenario: Verify Exchange Agreement Item for Telus Subscriber
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'ExchangeAgreementItem/callExchangeAgreementItemTC5.feature')
    #Request
    * json apiDetailsRequest = apiDetails.response
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 500
