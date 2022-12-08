@LSMS
Feature: TC01 Telus Subscriber 

  @v1-0
  Scenario: GetLoyaltyAccount for Telus Subscriber 
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'GetLoyaltyAccount/callGetLoyaltyAccountTC01.feature')
    #URL
    * def apiUrl = apiDetails.URL
    #Request
    * def apiResponse = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
