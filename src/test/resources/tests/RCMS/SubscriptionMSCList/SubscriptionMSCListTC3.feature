Feature: Calling validateSubMscList API

  Scenario: Verify SubMscList
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'SubscriptionMSCList/callSubscriptionMSCListTC3.feature')
    #Request
    * def apiRequest = apiDetails.payload
    * def apiResponse = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 200
