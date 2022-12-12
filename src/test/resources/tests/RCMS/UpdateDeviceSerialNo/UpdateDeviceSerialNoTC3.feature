Feature: Calling UpdateSerialNo API

  Scenario: Verify update on installment date
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'UpdateDeviceSerialNo/callUpdateDeviceSerialNoTC3.feature')
    #Request
    * def apiResponse = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 200
