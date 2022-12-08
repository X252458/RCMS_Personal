Feature: TC10 Perform Addagreementitem to add ACCESSORYFINANCE to above subscriber


  Scenario: Verify Add Agreement Item to add ACCESSORYFINANCE
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario2/callAddAgreementItemTC10.feature')
    #Response
    #* json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 200
   