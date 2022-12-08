Feature: TC03 EAA Telus Subscriber with DB+ACB+TIASSETCREDIT+TIPROMOCREDIT +AF+DF exchanged to DF+ACB+TIASSETCREDIT+TIPROMOCREDIT +DB  +AF

  Scenario: TC03 EAA Telus Subscriber with DB+ACB+TIASSETCREDIT+TIPROMOCREDIT +AF+DF exchanged to DF+ACB+TIASSETCREDIT+TIPROMOCREDIT +DB  +AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Exchange/callExchangeTC3.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
