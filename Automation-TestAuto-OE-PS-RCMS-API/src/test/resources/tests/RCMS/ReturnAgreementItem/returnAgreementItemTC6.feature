Feature: ReturnAgreementItem API 

  Scenario: Calling API feature file
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'ReturnAgreementItem/callReturnAgreementItemTC6.feature')
    #Request
    * json apiRequest = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 500
