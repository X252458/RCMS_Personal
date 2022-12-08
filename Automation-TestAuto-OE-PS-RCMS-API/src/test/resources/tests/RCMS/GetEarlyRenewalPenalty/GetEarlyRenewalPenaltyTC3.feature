@LSMS
Feature: getEarlyRenewalPenalty API 

  @v1-0
  Scenario: TC03 Call getEarlyRenewalPenalty for Telus Subscriber with DB+DF+BIB+ACB+TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE
    #Operation 1
    When def getEarlyRenewalPenalty = call read(PATH_API_OPS+'GetEarlyRenewalPenalty/callGetEarlyRenewalPenaltyTC3.feature')
    #Request
    * json getEarlyRenewalPenaltyResponse = getEarlyRenewalPenalty.response
    #Status
    * def getEarlyRenewalPenaltyStatus = getEarlyRenewalPenalty.responseStatus
    #Validation
    Then match getEarlyRenewalPenaltyStatus == 201
