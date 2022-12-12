Feature: TC02 Telus Subscriber having DB+DF+BIB renewed to DB  using Payment Method TRADE_IN_PENDING_ LOG Event_RETURN_COMPLETE when device is received in good condition


  Scenario:  Varify Telus Subscriber renewed to DB using Payment Method BIB_TRADE_PENDING LOG Event with reason code RETURN_COMPLETE
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'LogEvent/callLogEventTC2.feature')
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 201