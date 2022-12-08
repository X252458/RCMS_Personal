Feature: TC05 EAR Perform Renewal for a customer having  DB+AF+TIPROMOCREDIT + TIASSETCREDIT to DB + TIPROMOASSET +TIASSETCREDIT and perform exchange to DF +TIPROMOCREDIT+TIASSETCREDIT

  Scenario: Calling API feature file
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Renewal/offerExchange/callRenewalTC5.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
