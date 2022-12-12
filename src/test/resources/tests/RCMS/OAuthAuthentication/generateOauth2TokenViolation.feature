Feature: Generation of OAuth Token for RCMS API


  Scenario: Verify Violation Credentials(OAuth2 Token) for RCMS
    #Operation 1
    When def accessTokenFeatureCall = call read(PATH_API_OPS+'OAuthAuthentication/getOAuth2TokenViolation.feature')
    #Response
   * json accessTokenResponse = accessTokenFeatureCall.response
    #Status
    * def accessTokenStatus = accessTokenFeatureCall.responseStatus
     #token
    * def access_token = accessTokenResponse.access_token
    #Validation
    #Then match accessTokenStatus == 200
