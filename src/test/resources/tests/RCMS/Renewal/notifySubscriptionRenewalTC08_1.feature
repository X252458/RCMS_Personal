Feature: TC08_1_Customer_having_DB_DF_BIB_Renewal_DF_BIB_Renewal_to_DB_DF

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc08_1Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC08_1.feature')
    #Request
    * json tc08_1RenewalRequest = tc08_1Renewal.payload
    #Status
    * def tc08_1RenewalStatus = tc08_1Renewal.responseStatus
    #Validation
    #Then match tc08_1RenewalStatus == 200
