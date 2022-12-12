Feature: TC01_Perform exchangeAgreementItem operation for Telus subscriber with DB+ TIASSETCREDIT +AF  doing  exchangeAgreementItem for AF


  Scenario: Verify Exchange Agreement Item for Telus Subscriber
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'ExchangeAgreementItem/callExchangeAgreementItemTC1.feature')
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 200
