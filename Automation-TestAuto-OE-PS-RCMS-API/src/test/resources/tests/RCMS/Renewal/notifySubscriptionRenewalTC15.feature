Feature: TC15_Customer_having_DB_DF_BIB_Renewal_DB_with_PM_as_TRADE_IN

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc15Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC15.feature')
    #Request
    * json tc15RenewalRequest = tc15Renewal.payload
    #Status
    * def tc15RenewalStatus = tc15Renewal.responseStatus
    #Validation
    Then match tc15RenewalStatus == 200
