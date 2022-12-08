Feature: TC14_Perform_renewal_for_a_subscriber_with_TAB_L_offercode_6_to_SIM_Only

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc14Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC14.feature')
    #Request
    * json tc14RenewalRequest = tc14Renewal.payload
    #Status
    * def tc14RenewalStatus = tc14Renewal.responseStatus
    #Validation
    Then match tc14RenewalStatus == 200
