Feature: TC07 Telus Subscriber having DB+DF+BIB renewed to DB using Payment Method BIB_TELUS_PENDING_ LOG Event_when charge complete/return complete is done ,we cannot update the due date.


  Scenario: Varify Telus Subscriber renewed to DB using Payment Method BIB_TRADE_PENDING LOG Event with reason code UPDATE_DUEDATE after return and Charge complete is done
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'LogEvent/callLogEventTC7.feature')
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    #Then match apiDetailsStatus == 500