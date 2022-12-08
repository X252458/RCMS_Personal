Feature: TC02 Perform exchangeAgreementItem for Telus subscriber with DF + DB doing exchangeAgreementItem to AF


  Scenario: Verify Exchange Agreement Item for Telus Subscriber
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'ExchangeAgreementItem/callExchangeAgreementItemTC2.feature')
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 500
