Feature: Get OAuth Token for RCMS API

  Scenario: Sample Test Demo
    * url 'https://apigw-st.tsl.telus.com/st/token'
		* def fieldValue = read(PATH_CONFIG + 'Auth_Token.json')
    And form field grant_type = fieldValue.grant_type
    And form field client_id = fieldValue.client_id
    And form field client_secret = fieldValue.client_secret
    And form field scope = fieldValue.scope    
    When method POST