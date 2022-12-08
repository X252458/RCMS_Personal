@LSMS
Feature: getEarlyRenewalPenalty API 

  @v1-0
  Scenario: TC07 Call getEarlyRenewalPenalty for Koodo subscriber with SIM ONLY
    #Operation 1
    When def getEarlyRenewalPenalty = call read(PATH_API_OPS+'GetEarlyRenewalPenalty/callGetEarlyRenewalPenaltyTC7.feature')
    #Request
    * json getEarlyRenewalPenaltyResponse = getEarlyRenewalPenalty.response
    #Status
    * def getEarlyRenewalPenaltyStatus = getEarlyRenewalPenalty.responseStatus
    #Validation
    Then match getEarlyRenewalPenaltyStatus == 201
