Feature: Get OAuth Token for RCMS API

  Scenario: Calling Violation authentication token
  
    * url 'https://apigw-st.tsl.telus.com/st/token'
		* def fieldValue = read(PATH_CONFIG + 'Auth_Token.json')
    And form field grant_type = fieldValue.Violation.grant_type
    And form field client_id = fieldValue.Violation.client_id
    And form field client_secret = fieldValue.Violation.client_secret
    And form field scope = fieldValue.Violation.scope    
    When method POST