@LSMS
Feature: GetEarlyRenewalPenalty API

  @v1-0
  Scenario: TC02 Call getEarlyRenewalPenalty for Telus  subscriber with TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE
    #Operation 1
    When def getEarlyRenewalPenalty = call read(PATH_API_OPS+'GetEarlyRenewalPenalty/callGetEarlyRenewalPenaltyTC2.feature')
    #Request
    * json getEarlyRenewalPenaltyResponse = getEarlyRenewalPenalty.response
    #Status
    * def getEarlyRenewalPenaltyStatus = getEarlyRenewalPenalty.responseStatus
    #Validation
    Then match getEarlyRenewalPenaltyStatus == 201
