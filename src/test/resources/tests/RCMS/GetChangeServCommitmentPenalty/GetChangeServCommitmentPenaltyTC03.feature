@LSMS
Feature: TC03  Call getChangeServiceCommitmentPenalty operation for renewed customer having DB + DF + BIB + RCB+AF [CMT Broken]      

  @v1-0
  Scenario: GetChangeServiceCommitmentPenalty for Telus Subscriber with  DB + DF + BIB + RCB+AF [CMT Broken]
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'GetChangeServCommitmentPenalty/callGetChangeServCommitmentPenaltyTC03.feature')
    #Request
    * def apiResponse = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 201