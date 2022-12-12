Feature: TC01 Telus Subscriber having DB+DF+BIB renewed to DB using Payment Method BIB_TELUS_PENDING_ LOG Event_RETURN_COMPLETE when device is received in good condition


  Scenario: Varify Telus Subscriber renewed to DB using Payment Method BIB_TELUS_PENDING LOG Event with reason code RETURN_COMPLETE
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'LogEvent/callLogEventTC1.feature')
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 201