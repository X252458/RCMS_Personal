@LSMS
Feature: TC01 Telus Subscriber having ACCESSORYFINANCE 

  @v1-0
  Scenario: GetReturnPenalty for Telus Subscriber wih AF
    #Operation 1
    When def getReturnPenalty = call read(PATH_API_OPS+'GetReturnPenalty/callGetReturnPenaltyTC14.feature')
    #Request
    * def getReturnPenaltyResponse = getReturnPenalty.response
    #Status
    * def getReturnPenaltyStatus = getReturnPenalty.responseStatus
    #Validation
    #Then match getReturnPenaltyStatus == 201
