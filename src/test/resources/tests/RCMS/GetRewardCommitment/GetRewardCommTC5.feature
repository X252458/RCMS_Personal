@LSMS
Feature: TC04 Telus Subscriber having TIAssetCredit and TIPormoCredit, and update

  @v1-0
  Scenario: Verify Activation Telus Subscriber wih All
    #Operation 1
    When def getRewardComm = call read(PATH_API_OPS+'GetRewardCommitment/callGetRewardCommTC5.feature')
    #Request
    * json getRewardCommResponse = getRewardComm.response
    #Status
    * def getRewardCommStatus = getRewardComm.responseStatus
    #Validation
    Then match getRewardCommStatus == 200
