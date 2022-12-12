Feature: TC07 EAR Perform Renewal(Paid with Trade IN ) for a customer having DF+DB +BIB to DB+ RCB + TIASSETCREDIT and perform exchange to DF+AF + TIASSETCREDIT

  Scenario: TC07 EAR Perform Renewal(Paid with Trade IN ) for a customer having DF+DB +BIB to DB+ RCB + TIASSETCREDIT and perform exchange to DF+AF + TIASSETCREDIT
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Exchange/callExchangeTC7.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 200
