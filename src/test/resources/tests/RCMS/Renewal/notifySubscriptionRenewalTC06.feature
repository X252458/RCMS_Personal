Feature: TC06_Customer_having_DB_DF_BIB_Renewal_DB_TIASSETCREDIT_TIPROMOTCREDIT_Payment_Method_TRADE_IN_PENDING

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc06Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC06.feature')
    #Request
    * json tc06RenewalRequest = tc06Renewal.payload
    #Status
    * def tc06RenewalStatus = tc06Renewal.responseStatus
    #Validation
    Then match tc06RenewalStatus == 200
