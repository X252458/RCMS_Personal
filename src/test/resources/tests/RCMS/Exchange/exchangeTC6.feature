Feature: TC06 EAR Perform Renewal(Paid with BIB) for a customer having DF+DB +BIB to DB +AF and perform exchange to AF+DF+DB+BIB

  Scenario: TC06 EAR Perform Renewal(Paid with BIB) for a customer having DF+DB +BIB to DB +AF and perform exchange to AF+DF+DB+BIB
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Exchange/callExchangeTC6.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
