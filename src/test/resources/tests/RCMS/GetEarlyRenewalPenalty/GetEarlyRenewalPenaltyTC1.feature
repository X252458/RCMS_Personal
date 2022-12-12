@LSMS
Feature: TC01 Telus Subscriber having ACCESSORYFINANCE 

  @v1-0
  Scenario: GetEarlyRenewalPenalty for Telus Subscriber wih AF
    #Operation 1
    When def getEarlyRenewalPenalty = call read(PATH_API_OPS+'GetEarlyRenewalPenalty/callGetEarlyRenewalPenaltyTC1.feature')
    #Request
    * def getEarlyRenewalPenaltyResponse = getEarlyRenewalPenalty.response
    #Status
    * def getEarlyRenewalPenaltyStatus = getEarlyRenewalPenalty.responseStatus
    #Validation
    #Then match getEarlyRenewalPenaltyStatus == 201
