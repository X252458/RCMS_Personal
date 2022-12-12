@LSMS
Feature: tc07 Activate Prepaid subscriber with TIASSETCREDIT TIPROMOCREDIT

  @v1-0
  Scenario: Verify Activation Telus Subscriber with TIASSETCREDIT TIPROMOCREDIT
    #Operation 1
    When def tc07ActivatePrepaidSubTIASSETCREDIT_TIPROMOCREDIT = call read(PATH_API_OPS+'Activation/callActivationTC7.feature')
    #Request
    * json tc07ActivatePrepaidSubTIASSETCREDIT_TIPROMOCREDITRequest = tc07ActivatePrepaidSubTIASSETCREDIT_TIPROMOCREDIT.payload
    #Status
    * def tc07ActivatePrepaidSubTIASSETCREDIT_TIPROMOCREDITStatus = tc07ActivatePrepaidSubTIASSETCREDIT_TIPROMOCREDIT.responseStatus
    #Validation
    ##Then match tc07ActivatePrepaidSubTIASSETCREDIT_TIPROMOCREDITStatus == 200
