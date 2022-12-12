Feature: TC09_Perform_Renewal_from_TAB_HWS_to_TAB_HWS

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc09Renewal = call read(PATH_API_OPS+'Renewal/callnotifySubscriptionRenewalTC09.feature')
    #Request
    * json tc09RenewalRequest = tc09Renewal.payload
    #Status
    * def tc09RenewalStatus = tc09Renewal.responseStatus
    #Validation
    #Then match tc09RenewalStatus == 200
