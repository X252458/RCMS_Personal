@LSMS
Feature: TC02 Call getChangeServiceCommitmentPenalty operation for Koodo sub with TAB+HWS_CMT Broken      

  @v1-0
  Scenario: GetChangeServiceCommitmentPenalty for Koodo Subscriber with TAB_HWS
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'GetChangeServCommitmentPenalty/callGetChangeServCommitmentPenaltyTC02.feature')
    #Request
    * def apiResponse = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 201