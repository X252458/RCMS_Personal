Feature: TC08 Telus Subscriber having DB+DF+BIB renewed to DB using Payment Method BIB_TELUS_PENDING_ LOG Event_update due date more than once.


  Scenario: Varify Telus Subscriber renewed to DB using Payment Method BIB_TRADE_PENDING LOG Event with reason code UPDATE_DUEDATE Twice
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'LogEvent/callLogEventTC8.feature')
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 201