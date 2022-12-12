@LSMS
Feature: TC21 Perform Activation for Koodo Subscriber with TAB+HWS

  @v1-0
  Scenario: Verify Activation Koodo Subscriber with TAB+HWS
    #Operation 1
    When def tc02ActivateKoodoTAB_HWS = call read(PATH_API_OPS+'Activation/Others/callActivationTC21.feature')
    #Request
    * json tc02ActivateKoodoTAB_HWSRequest = tc02ActivateKoodoTAB_HWS.payload
    #Status
    * def tc02ActivateKoodoTAB_HWSStatus = tc02ActivateKoodoTAB_HWS.responseStatus
    #Validation
    #Then match tc02ActivateKoodoTAB_HWSStatus == 200