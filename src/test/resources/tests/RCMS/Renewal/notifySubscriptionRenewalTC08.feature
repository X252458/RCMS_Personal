Feature: TC08_Customer_having_DB_DF_BIB_Renewal_DF_BIB_Renewal_to_DB_DF

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc08Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC08.feature')
    #Request
    * json tc08RenewalRequest = tc08Renewal.payload
    #Status
    * def tc08RenewalStatus = tc08Renewal.responseStatus
    #Validation
    #Then match tc08RenewalStatus == 200
