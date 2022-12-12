@LSMS
Feature: TC01 Telus Subscriber having ACCESSORYFINANCE 

  @v1-0
  Scenario: TransactionHistory for Telus Subscriber wih AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'ListEarnedTransaction/callListEarnedTransactionTC01.feature')
    #URL
    * def apiUrl = apiDetails.URL
    #Request
    * def apiResponse = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    #Then match apiStatus == 200
