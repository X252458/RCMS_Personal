@LSMS
Feature:  getEarlyRenewalPenalty API 

  @v1-0
  Scenario: TC04 Call getEarlyRenewalPenalty for Telus Subscriber with DB + DF + RCB+ACCESSORYFINANCE
    #Operation 1
    When def getEarlyRenewalPenalty = call read(PATH_API_OPS+'GetEarlyRenewalPenalty/callGetEarlyRenewalPenaltyTC4.feature')
    #Request
    * json getEarlyRenewalPenaltyResponse = getEarlyRenewalPenalty.response
    #Status
    * def getEarlyRenewalPenaltyStatus = getEarlyRenewalPenalty.responseStatus
    #Validation
    Then match getEarlyRenewalPenaltyStatus == 201
