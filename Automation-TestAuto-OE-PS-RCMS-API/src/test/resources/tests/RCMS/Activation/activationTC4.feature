@LSMS
Feature: TC04 Activate Prepaid subscriber with PRESOC PRECREDIT

  @v1-0
  Scenario: Verify Activation Telus Subscriber with ACB
    #Operation 1
    When def tc04ActivatePrepaidSubPRESOC_PRECREDIT = call read(PATH_API_OPS+'Activation/callActivationTC4.feature')
    #Request
    * json tc04ActivatePrepaidSubPRESOC_PRECREDITRequest = tc04ActivatePrepaidSubPRESOC_PRECREDIT.payload
    #Status
    * def tc04ActivatePrepaidSubPRESOC_PRECREDITStatus = tc04ActivatePrepaidSubPRESOC_PRECREDIT.responseStatus
    #Validation
    Then match tc04ActivatePrepaidSubPRESOC_PRECREDITStatus == 200
