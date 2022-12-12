@LSMS
Feature: TC07 Telus Subscriber having all and update

  @v1-0
  Scenario: Verify Activation Telus Subscriber wih All
    #Operation 1
    When def getRewardComm = call read(PATH_API_OPS+'GetRewardCommitment/callGetRewardCommTC7.feature')
    #Request
    * json getRewardCommResponse = getRewardComm.response
    #Status
    * def getRewardCommStatus = getRewardComm.responseStatus
    #Validation
    #Then match getRewardCommStatus == 200
