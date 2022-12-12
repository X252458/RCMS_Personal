Feature: TC09 Perform Status change from Cancelled to Active for Telus Sub with DF+ACCESSORYFINANCE


  Scenario: Verify change from Cancelled to Active for Telus Sub
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario2/callStatusChangeTC9.feature')
    #Response
    #* json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 200
   