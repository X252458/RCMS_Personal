Feature: TC02_EAA_Activate Telus subscriber with DF + DB exchanged to DF + TIASSETCREDIT  +AF

  Scenario: TC02_EAA_Activate Telus subscriber with DF + DB exchanged to DF + TIASSETCREDIT  +AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Exchange/callExchangeTC2.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
