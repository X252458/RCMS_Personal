Feature: TC03 Telus Subscriber having DB+DF+BIB renewed to DB using Payment Method BIB_TELUS_PENDING_ LOG Event_CHARGE_COMPLETE when device received is rejected due to its condition or device not received before due date.


  Scenario: Varify Telus Subscriber renewed to DB using Payment Method BIB_TELUS_PENDING LOG Event with reason code CHARGE_COMPLETE
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'LogEvent/callLogEventTC3.feature')
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 201