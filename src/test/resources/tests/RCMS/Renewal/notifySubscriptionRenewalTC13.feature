Feature: TC13_Perform_renewal_with_TAB_M_offercode_7_to_TAB_HWS_using_TRADE_IN

  Scenario: Verify Renewal 
    #Operation 1
    When def tc13Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC13.feature')
    #Request
    * json tc13RenewalRequest = tc13Renewal.payload
    #Status
    * def tc13RenewalStatus = tc13Renewal.responseStatus
    #Validation
    #Then match tc13RenewalStatus == 200
