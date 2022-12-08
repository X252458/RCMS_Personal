Feature: TC01 Telus Subscriber having DF Renewed to DB,RCB,AF

  Scenario: Verify Renewal with DB,RCB, AF
    #Operation 1
    When def tc01Renewal = call read(PATH_API_OPS+'Renewal/Cancel/callRenewalTC1.feature')
    #Request
    * json tc01RenewalRequest = tc01Renewal.payload
    #Status
    * def tc01RenewalStatus = tc01Renewal.responseStatus
    #Validation
    Then match tc01RenewalStatus == 200
