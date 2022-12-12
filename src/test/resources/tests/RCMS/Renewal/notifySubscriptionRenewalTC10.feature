Feature: tc10 Telus Subscriber having DF_AF_TIASSETCREDIT_TIPROMOCREDIT Renewed to DF with payment as BILL

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc10Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC10.feature')
    #Request
    * json tc10RenewalRequest = tc10Renewal.payload
    #Status
    * def tc10RenewalStatus = tc10Renewal.responseStatus
    #Validation
    #Then match tc10RenewalStatus == 200
