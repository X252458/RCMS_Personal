Feature: TC04 Telus Subscriber having DB_DF_BIB_RCB Renewed to DB

  Scenario: Verify Renewal with DB
    #Operation 1
    When def tc04RenewalDB = call read(PATH_API_OPS+'Renewal/getRewardCommitment/callRenewalTC4.feature')
    #Request
    * json tc04RenewalDBRequest = tc04RenewalDB.payload
    #Status
    * def tc04RenewalDBStatus = tc04RenewalDB.responseStatus
    #Validation
    #Then match tc04RenewalDBStatus == 200