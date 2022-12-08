@LSMS
Feature: TC09 Call getRewardCommitment operation for Telus Subscriber with DB+DF+BIB+RCB


  @v1-0
  Scenario: Verify Activation Telus Subscriber wih DB+DF+BIB+RCB
    #Operation 1
    When def getRewardComm = call read(PATH_API_OPS+'GetRewardCommitment/callGetRewardCommTC9.feature')
    #Request
    * json getRewardCommResponse = getRewardComm.response
    #Status
    * def getRewardCommStatus = getRewardComm.responseStatus
    #Validation
    Then match getRewardCommStatus == 200
