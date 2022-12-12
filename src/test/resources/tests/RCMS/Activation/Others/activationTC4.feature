@RCMSactivation
Feature: TC04 Activate Telus Subscriber with TIASSETCREDIT_TIPROMOCREDIT_ACCESSORYFINANCE

  Scenario: Verify Activation Telus Subscriber with TIASSETCREDIT_TIPROMOCREDIT_ACCESSORYFINANCE
    #Operation 1
    When def tc04ActivateTelusSubwithTIA_TIP_AF = call read(PATH_API_OPS+'Activation/Others/callActivationTC4.feature')
    #Request
    * json tc04ActivateTelusSubwithTIA_TIP_AFRequest = tc04ActivateTelusSubwithTIA_TIP_AF.payload
    #Status
    * def tc04ActivateTelusSubwithTIA_TIP_AFStatus = tc04ActivateTelusSubwithTIA_TIP_AF.responseStatus
    #Validation
    #Then match tc04ActivateTelusSubwithTIA_TIP_AFStatus == 200
