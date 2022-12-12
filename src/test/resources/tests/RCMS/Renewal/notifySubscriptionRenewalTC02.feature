Feature: TC02 Telus Subscriber having DF_AF_TIASSETCREDIT_TIPROMOCREDIT Renewed to DF with payment as BILL

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc02Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC02.feature')
    #Request
    * json tc02RenewalRequest = tc02Renewal.payload
    #Status
    * def tc02RenewalStatus = tc02Renewal.responseStatus
    #Validation
    #Then match tc02RenewalStatus == 200
