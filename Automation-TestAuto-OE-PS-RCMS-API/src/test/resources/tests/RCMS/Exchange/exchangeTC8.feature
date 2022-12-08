Feature: TC08 EAR(REGRESSION in store) Perform Renewal for a customer having DF+DB +BIB to BIB+DB and perform exchange to DF+DB

  Scenario: TC08 EAR(REGRESSION in store) Perform Renewal for a customer having DF+DB +BIB to BIB+DB and perform exchange to DF+DB
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Exchange/callExchangeTC8.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
