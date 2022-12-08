Feature: Updating TIAssetCredit and TIPromoCredit installment date

  Scenario: Verify return on installment date
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Return/callReturnTC3.feature')
    #Request
    * json apiResponse = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
