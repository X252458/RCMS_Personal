Feature: RCMS API - Update Item API 

  Scenario: TC08
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'UpdateItem/callupdateItemTC8b.feature')
    #Request
    * json apiRequest = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 500
