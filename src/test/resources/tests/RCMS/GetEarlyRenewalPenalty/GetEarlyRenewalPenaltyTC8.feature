@LSMS
Feature: getEarlyRenewalPenalty API 

  @v1-0
  Scenario: TC08 Call getEarlyRenewalPenalty for  Koodo Subscriber with TAB S in HPA
    #Operation 1
    When def getEarlyRenewalPenalty = call read(PATH_API_OPS+'GetEarlyRenewalPenalty/callGetEarlyRenewalPenaltyTC8.feature')
    #Request
    * json getEarlyRenewalPenaltyResponse = getEarlyRenewalPenalty.response
    #Status
    * def getEarlyRenewalPenaltyStatus = getEarlyRenewalPenalty.responseStatus
    #Validation
    #Then match getEarlyRenewalPenaltyStatus == 201
