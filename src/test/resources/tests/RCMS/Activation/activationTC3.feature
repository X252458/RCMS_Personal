Feature: tc03 Activate DB+DF+RCB+AF

  @v1-0
  Scenario: Verify Activation Koodo Subscriber with SIM only
    #Operation 1
    When def tc03ActivateKoodoWithSimOnly = call read(PATH_API_OPS+'Activation/callActivationTC3.feature')
    #Request
    * json tc03ActivateKoodoWithSimOnlyRequest = tc03ActivateKoodoWithSimOnly.payload
    #Status
    * def tc03ActivateKoodoWithSimOnlyStatus = tc03ActivateKoodoWithSimOnly.responseStatus
    #Validation
    Then match tc03ActivateKoodoWithSimOnlyStatus == 200
