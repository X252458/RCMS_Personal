@LSMS
Feature: TC02 Call getMigrationPenalty for a subscriber who wants to Migrate from Postpaid to Prepaid - From DB + DF + BIB + ACB + RCB to PRECREDIT

  @v1-0
  Scenario: GetMigrationPenalty for Telus Subscriber wih AF
    #Operation 1
    When def apiDetails = call read(PATH_API_OPS+'GetMigrationPenalty/callGetMigrationPenaltyTC02.feature')
    #Request
    * def apiResponse = apiDetails.response
    #Status
    * def apiStatus = apiDetails.responseStatus
    #Validation
    Then match apiStatus == 201
