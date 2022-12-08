@RCMSactivation
Feature: TC01 Activate Telus Subscriber with DB+DF+BIB+ACB+TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE

  Scenario: Verify Activation Telus Subscriber with All
    #Operation 1
    When def tc01ActivateTelusSubWithAll = call read(PATH_API_OPS+'Activation/callActivationTC1.feature')
    #Request
    * json tc01ActivateTelusSubWithAllRequest = tc01ActivateTelusSubWithAll.payload
    #Status
    * def tc01ActivateTelusSubWithAllStatus = tc01ActivateTelusSubWithAll.responseStatus
    #Validation
    Then match tc01ActivateTelusSubWithAllStatus == 200
