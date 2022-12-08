Feature: TC02 Activate Telus Subscriber with DF_BIB_ACB_DB_TIA_TIP

  Scenario: Verify Activation Telus Subscriber with DF_BIB_ACB_DB_TIA_TIP
    #Operation 1
    When def tc01ActivateTelusSubWithDF_BIB_ACB_DB_TIA_TIP = call read(PATH_API_OPS+'Activation/Others/callActivationTC2.feature')
    #Request
    * json tc01ActivateTelusSubWithDF_BIB_ACB_DB_TIA_TIPRequest = tc01ActivateTelusSubWithDF_BIB_ACB_DB_TIA_TIP.payload
    #Status
    * def tc01ActivateTelusSubWithDF_BIB_ACB_DB_TIA_TIPStatus = tc01ActivateTelusSubWithDF_BIB_ACB_DB_TIA_TIP.responseStatus
    #Validation
    Then match tc01ActivateTelusSubWithDF_BIB_ACB_DB_TIA_TIPStatus == 200
