Feature: TC04  EAR Perform Renewal for a customer having DF  to DB + TIASSETCREDIT +AF and perform exchange to DF + DB  + TIASSETCREDIT + TIPROMOCREDIT

  Scenario: TC04  EAR Perform Renewal for a customer having DF  to DB + TIASSETCREDIT +AF and perform exchange to DF + DB  + TIASSETCREDIT + TIPROMOCREDIT
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Exchange/callExchangeTC4.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 200
