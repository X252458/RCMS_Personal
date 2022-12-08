Feature: Get OAuth Token for RCMS API

  Scenario: Calling RewardService authentication token
  
    * url 'https://apigw-st.tsl.telus.com/st/token'
		* def fieldValue = read(PATH_CONFIG + 'Auth_Token.json')
    And form field grant_type = fieldValue.RewardService.grant_type
    And form field client_id = fieldValue.RewardService.client_id
    And form field client_secret = fieldValue.RewardService.client_secret
    And form field scope = fieldValue.RewardService.scope    
    When method POST