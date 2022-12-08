@LSMS
Feature: TC04 Call getChangeServiceCommitmentPenalty operation on a renewed customer having DB + RCB+AF [MSC Broken]       

  @v1-0
  Scenario: GetChangeServiceCommitmentPenalty for Koodo Subscriber with TAB_HWS
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'GetChangeServCommitmentPenalty/callGetChangeServCommitmentPenaltyTC04.feature')
    #Request
    * def apiResponse = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 201