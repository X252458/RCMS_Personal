Feature: TC01 Telus Subscriber having DB_AF_ACB_TIASSETCREDIT_TIPROMOCREDIT Renewed to DF with payment as BILL

  Scenario: Verify Renewal with AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'Renewal/getRewardAccInfo/callRenewalTC1.feature')
    #Request
    * json apiRequest = apiDetails.payload
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 200
