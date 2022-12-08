Feature: TC09 EAR Perform Renewal for a customer havingTAB to HWS and perform exchange to TAB+HWS

  Scenario: TC09 EAR Perform Renewal for a customer havingTAB to HWS and perform exchange to TAB+HWS
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Exchange/callExchangeTC9.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200
