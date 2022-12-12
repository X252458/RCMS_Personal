Feature: tc03 Telus Subscriber having DF_AF_TIASSETCREDIT_TIPROMOCREDIT Renewed to DF with payment as BILL

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc03Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC03.feature')
    #Request
    * json tc03RenewalRequest = tc03Renewal.payload
    #Status
    * def tc03RenewalStatus = tc03Renewal.responseStatus
    #Validation
    #Then match tc03RenewalStatus == 200
