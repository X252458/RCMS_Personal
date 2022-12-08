@LSMS
Feature: TC01 Telus Subscriber having ACCESSORYFINANCE 

  @v1-0
  Scenario: apiDetails for Telus Subscriber wih AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'GetResumePenalty/callGetResumePenaltyTC03.feature')
    #Request
    * def apiResponse = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 201
