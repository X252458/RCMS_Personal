@LSMS
Feature: TC05 Activate Telus subscriber with ACCESSORYFINANCE

  @v1-0
  Scenario: Verify Activation Telus Subscriber
    #Operation 1
    When def tc05ActivateTelusSubwithAF = call read(PATH_API_OPS+'Activation/callActivationTC5.feature')
    #Request
    * json tc05ActivateTelusSubwithAFRequest = tc05ActivateTelusSubwithAF.payload
    #Status
    * def tc05ActivateTelusSubwithAFStatus = tc05ActivateTelusSubwithAF.responseStatus
    #Validation
    ##Then match tc05ActivateTelusSubwithAFStatus == 200
