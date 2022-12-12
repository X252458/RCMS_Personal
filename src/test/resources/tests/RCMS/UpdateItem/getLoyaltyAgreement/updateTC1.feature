Feature: Updating TIAssetCredit and TIPromoCredit installment date

  Scenario: Verify update on installment date
    #Operation 1
    When def tc01UpdateTelusSubWithTICreditInstal = call read(PATH_API_OPS+'UpdateItem/getLoyaltyAgreement/callUpdateTC1.feature')
    #Request
    * json tc01UpdateTelusSubWithTICreditInstalRequest = tc01UpdateTelusSubWithTICreditInstal.payload
    #Status
    * def tc01UpdateTelusSubWithTICreditInstalStatus = tc01UpdateTelusSubWithTICreditInstal.responseStatus
    #Validation
    #Then match tc01UpdateTelusSubWithTICreditInstalStatus == 200