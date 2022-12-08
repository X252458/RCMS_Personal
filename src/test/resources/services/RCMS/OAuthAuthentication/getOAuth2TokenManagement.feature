Feature: Get OAuth Token for RCMS API

  Scenario: Calling Management authentication token
  
    * url 'https://apigw-st.tsl.telus.com/st/token'
		* def fieldValue = read(PATH_CONFIG + 'Auth_Token.json')
    And form field grant_type = fieldValue.Management.grant_type
    And form field client_id = fieldValue.Management.client_id
    And form field client_secret = fieldValue.Management.client_secret
    And form field scope = fieldValue.Management.scope    
    When method POST