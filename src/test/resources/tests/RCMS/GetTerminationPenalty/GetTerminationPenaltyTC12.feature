@LSMS
Feature: TC01 Telus Subscriber having ACCESSORYFINANCE 

  @v1-0
  Scenario: GetTerminationPenalty for Telus Subscriber wih AF
    #Operation 1
    When def getTerminationPenalty = call read(PATH_API_OPS+'GetTerminationPenalty/callGetTerminationPenaltyTC12.feature')
    #Request
    * def getTerminationPenaltyResponse = getTerminationPenalty.response
    #Status
    * def getTerminationPenaltyStatus = getTerminationPenalty.responseStatus
    #Validation
    Then match getTerminationPenaltyStatus == 201
