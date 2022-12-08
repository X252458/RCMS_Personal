Feature: TC05 Telus Subscriber having DB+DF+BIB renewed to DB using Payment Method BIB_TELUS_PENDING_ LOG Event_UPDATE_DUEDATE when changing original device due date


  Scenario: Varify Telus Subscriber renewed to DB using Payment Method BIB_TELUS_PENDING LOG Event with reason code UPDATE_DUEDATE
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'LogEvent/callLogEventTC5.feature')
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 201