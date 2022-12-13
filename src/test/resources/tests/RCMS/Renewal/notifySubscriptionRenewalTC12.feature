Feature: TC12_Perform_renewal_from_TAB_HWS_to_TAB_HWS_with_charge_and_partial_waive_and_TRADE_IN

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc12Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC12.feature')
    #Request
    * json tc12RenewalRequest = tc12Renewal.payload
    #Status
    * def tc12RenewalStatus = tc12Renewal.responseStatus
    #Validation
    ##Then match tc12RenewalStatus == 200
