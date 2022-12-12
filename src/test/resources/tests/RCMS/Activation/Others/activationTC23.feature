Feature: TC07_TELUS_sub_having_DB_DF_BIB_ACB_Renewal_DB_BIB_RCB

  Scenario: Verify Renewal with AF
    #Operation 1
    When def tc07Renewal = call read(PATH_API_OPS+'Activation/Others/callActivationTC23.feature')
    #Request
    * json tc07RenewalRequest = tc07Renewal.payload
    #Status
    * def tc07RenewalStatus = tc07Renewal.responseStatus
    #Validation
    #Then match tc07RenewalStatus == 200