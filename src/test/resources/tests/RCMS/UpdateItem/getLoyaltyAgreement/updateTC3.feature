Feature: Updating All Device return

  Scenario: Verify update on installment date
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'UpdateItem/getLoyaltyAgreement/callUpdateTC3.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
