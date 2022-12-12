@LSMS
Feature: TC01 Call getChangeServiceCommitmentPenalty operation for Koodo sub with TAB+HWS_MSC Broken Already provided the Payload during Activation automation      

  @v1-0
  Scenario: GetChangeServiceCommitmentPenalty for Koodo Subscriber with TAB_HWS
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'GetChangeServCommitmentPenalty/callGetChangeServCommitmentPenaltyTC01.feature')
    #Request
    * def apiResponse = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 201