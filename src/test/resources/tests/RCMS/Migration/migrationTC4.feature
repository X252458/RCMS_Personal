Feature: Migration API 

  Scenario: Calling API feature file
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Migration/callMigrationTC4.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
