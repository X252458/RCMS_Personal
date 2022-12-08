@RCMSactivation
Feature: TC01 Activate Telus Subscriber with DF_AF_TIASSETCREDIT_TIPROMOCREDIT

  Scenario: Verify Activation Telus Subscriber with DF_AF_TIASSETCREDIT_TIPROMOCREDIT
    #Operation 1
    When def tc01ActivateTelusSubWithDF_AF_TIASSETCREDIT_TIPROMOCREDIT = call read(PATH_API_OPS+'Activation/Others/callActivationTC1.feature')
    #Request
    * json tc01ActivateTelusSubWithDF_AF_TIASSETCREDIT_TIPROMOCREDITRequest = tc01ActivateTelusSubWithDF_AF_TIASSETCREDIT_TIPROMOCREDIT.payload
    #Status
    * def tc01ActivateTelusSubWithDF_AF_TIASSETCREDIT_TIPROMOCREDITStatus = tc01ActivateTelusSubWithDF_AF_TIASSETCREDIT_TIPROMOCREDIT.responseStatus
    #Validation
    Then match tc01ActivateTelusSubWithDF_AF_TIASSETCREDIT_TIPROMOCREDITStatus == 200
