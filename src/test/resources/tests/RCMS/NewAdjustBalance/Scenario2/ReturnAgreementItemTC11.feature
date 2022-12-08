Feature: TC11 Perfome Adjustbalance to Update AF_Adjustment reason CANCELLATION_PAYMENT and  Returnagreementitem

  Scenario: Verify Adjust Balance with reasonCode CANCELLATION_PAYMENT and Return Agreemnet Item after full payment
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'NewAdjustBalance/Scenario2/callReturnAgreementItemTC11.feature')
    #Response
    * json apiDetailsResponse = apiDetails.response
    #Request
    * json apiDetailsRequest = apiDetails.payload
    #Status
    * def apiDetailsStatus = apiDetails.responseStatus
    #Validation
    Then match apiDetailsStatus == 500
   