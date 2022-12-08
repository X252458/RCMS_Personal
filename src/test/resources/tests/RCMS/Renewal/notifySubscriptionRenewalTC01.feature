Feature: TC01 Telus Subscriber having DF_AF_TIASSETCREDIT_TIPROMOCREDIT Renewed to DF with payment as BILL

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc01Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC01.feature')
    #Request
    * json tc01RenewalRequest = tc01Renewal.payload
    #Status
    * def tc01RenewalStatus = tc01Renewal.responseStatus
    #Validation
    Then match tc01RenewalStatus == 200
