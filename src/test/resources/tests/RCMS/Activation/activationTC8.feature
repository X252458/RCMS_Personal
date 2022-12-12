@LSMS
Feature: TC08 Activate Telus Subscriber with ACB

  @v1-0
  Scenario: Verify Activation Telus Subscriber with ACB
    #Operation 1
    When def tc08ActivateTelusSubACB = call read(PATH_API_OPS+'Activation/callActivationTC8.feature')
    #Request
    * json tc08ActivateTelusSubACBRequest = tc08ActivateTelusSubACB.payload
    #Status
    * def tc08ActivateTelusSubACBStatus = tc08ActivateTelusSubACB.responseStatus
    #Validation
    #Then match tc08ActivateTelusSubACBStatus == 200
