@LSMS
Feature: TC03 Call getMigrationPenalty for a subscriber who wants to Migrate from Postpaid to Prepaid - From DB + DF + BIB + ACB+TIASSETCREDIT+TIPROMOCREDIT to PRESOC

  @v1-0
  Scenario: GetMigrationPenalty for Telus Subscriber wih AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'GetMigrationPenalty/callGetMigrationPenaltyTC05.feature')
    #Request
    * def apiResponse = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 201
