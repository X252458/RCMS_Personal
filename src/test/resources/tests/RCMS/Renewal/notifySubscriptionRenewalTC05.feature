Feature: TC05 Telus Subscriber having DF_AF_TIASSETCREDIT_TIPROMOCREDIT Renewed to DF with payment as BILL

  Scenario: Verify Renewal
    #Operation 1
    When def tc05Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC05.feature')
    #Request
    * json tc05RenewalRequest = tc05Renewal.payload
    #Status
    * def tc05RenewalStatus = tc05Renewal.responseStatus
    #Validation
    #Then match tc05RenewalStatus == 200
