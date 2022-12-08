Feature: TC01_EAA_ Telus subscriber with DB+ TIASSETCREDIT +AF  exchanged to DB +DF

  Scenario: TC01_EAA_ Telus subscriber with DB+ TIASSETCREDIT +AF  exchanged to DB +DF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Exchange/callExchangeTC1.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
