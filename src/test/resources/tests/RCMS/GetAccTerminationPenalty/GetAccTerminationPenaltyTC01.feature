@LSMS
Feature: TC01 Call getBillingAccountTerminationPenalty for a Single subscriber with DB+DF+BIB+ACB+AF 

  @v1-0
  Scenario: GetBillingAccTerminationPenalty for Telus Subscriber wih DB+DF+BIB+ACB+AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'GetAccTerminationPenalty/callGetAccTerminationPenaltyTC01.feature')
    #Request
    * def apiResponse = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 201
