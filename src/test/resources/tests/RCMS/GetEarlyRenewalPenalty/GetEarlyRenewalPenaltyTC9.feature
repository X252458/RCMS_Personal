@LSMS
Feature:  getEarlyRenewalPenalty API

  @v1-0
  Scenario: TC09 Call getEarlyRenewalPenalty for  Koodo Subscriber with TAB L in HPA
    #Operation 1
    When def getEarlyRenewalPenalty = call read(PATH_API_OPS+'GetEarlyRenewalPenalty/callGetEarlyRenewalPenaltyTC9.feature')
    #Request
    * json getEarlyRenewalPenaltyResponse = getEarlyRenewalPenalty.response
    #Status
    * def getEarlyRenewalPenaltyStatus = getEarlyRenewalPenalty.responseStatus
    #Validation
    #Then match getEarlyRenewalPenaltyStatus == 201
