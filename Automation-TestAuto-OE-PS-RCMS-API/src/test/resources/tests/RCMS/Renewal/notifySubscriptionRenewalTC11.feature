Feature: TC11_Perform_renewal_from_TAB_HWS_to_TAB_HWS_with_charge_and_full_waive

  Scenario: Verify Renewal 
    #Operation 1
    When def tc11Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC11.feature')
    #Request
    * json tc11RenewalRequest = tc11Renewal.payload
    #Status
    * def tc11RenewalStatus = tc11Renewal.responseStatus
    #Validation
    Then match tc11RenewalStatus == 200
