@LSMS
Feature: getEarlyRenewalPenalty API 

  @v1-0
  Scenario: TC05 Call getEarlyRenewalPenalty for  TELUS subscriber with no reward account(SIM ONLY)+ACCESSORYFINANCE
    #Operation 1
    When def getEarlyRenewalPenalty = call read(PATH_API_OPS+'GetEarlyRenewalPenalty/callGetEarlyRenewalPenaltyTC5.feature')
    #Request
    * json getEarlyRenewalPenaltyResponse = getEarlyRenewalPenalty.response
    #Status
    * def getEarlyRenewalPenaltyStatus = getEarlyRenewalPenalty.responseStatus
    #Validation
    #Then match getEarlyRenewalPenaltyStatus == 201
