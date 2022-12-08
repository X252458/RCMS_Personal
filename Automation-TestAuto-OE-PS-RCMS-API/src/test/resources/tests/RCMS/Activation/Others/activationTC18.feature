@RCMSactivation
Feature: TC12 Activate Telus Subscriber with DB+ACB+TIASSETCREDIT+TIPROMOCREDIT+AF+DF

  Scenario: Verify Activation Telus Subscriber with DB+ACB+TIA+TIP+AF+DF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Activation/Others/callActivationTC18.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 200