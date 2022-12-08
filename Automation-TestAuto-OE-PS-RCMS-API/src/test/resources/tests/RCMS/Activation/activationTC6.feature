@LSMS
Feature: TC06 Activate TELUS subscriber with no reward account(SIM ONLY)

  @v1-0
  Scenario: Verify Activation TELUS subscriber with no reward account(SIM ONLY)
    #Operation 1
    When def tc06ActivateTeluswithNoRewardSimOnly = call read(PATH_API_OPS+'Activation/callActivationTC6.feature')
    #Request
    * json tc06ActivateTeluswithNoRewardSimOnlyRequest = tc06ActivateTeluswithNoRewardSimOnly.payload
    #Status
    * def tc06ActivateTeluswithNoRewardSimOnlyStatus = tc06ActivateTeluswithNoRewardSimOnly.responseStatus
    #Validation
    Then match tc06ActivateTeluswithNoRewardSimOnlyStatus == 200
