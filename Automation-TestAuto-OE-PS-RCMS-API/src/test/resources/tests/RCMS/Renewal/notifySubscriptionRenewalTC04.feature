Feature: TC04 Telus Subscriber having TC04_Customer_having_DB_DF_BIB_Renewal_DB_Payment_Method_BIB_TELUS_PENDING

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc04Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC04.feature')
    #Request
    * json tc04RenewalRequest = tc04Renewal.payload
    #Status
    * def tc04RenewalStatus = tc04Renewal.responseStatus
    #Validation
    Then match tc04RenewalStatus == 200
