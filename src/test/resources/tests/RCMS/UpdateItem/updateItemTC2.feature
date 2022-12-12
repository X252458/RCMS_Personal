Feature: RCMS API - Update Item API 

  Scenario: TC02
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'UpdateItem/callupdateItemTC2.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 200
