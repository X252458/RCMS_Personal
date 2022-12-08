@Ignore
Feature: RCMS Activation API - TC01 Perform Update Device Serial Number operation on customer having DB+DF+BIB+ACB+TIASSETCREDIT+TIPROMOCREDIT+ACCESSORYFINANCE

  Background: Configuration - Set up the authentication, Headers, and params
    #Configure the xml payload
    * def auth_token = karate.properties['karate.auth_token']
    * def endDate = karate.properties['karate.endDate']
    * def startDate = karate.properties['karate.startDate']
    * def productId = karate.properties['karate.productId']
    * def serialNo = karate.properties['karate.serialNo']
    * def redeemOfferId = karate.properties['karate.redeemOfferId']
    * def subID = karate.properties['karate.subID']
    * def payload = read(PATH_API_PAYLOAD + 'UpdateDeviceSerialNo/TC02_Telus_DB_DF_BIB_RCB_UpdateDeviceSerialNo.json')
    * header Authorization = 'Bearer ' + auth_token
    * header Content-Type = 'application/json'
    * header Env = karate.properties['karate.apiEnv']
    * header X-System = 'WLS'
    * param actionName = 'DeviceSerialNumber' 

  Scenario: Firing UpdateDeviceSerialNo API
  
    #Set endpoint url
    Given url ENDPOINT_UPDATE_DEVICE_SERIAL_NO
    #Request XML passed for the operation and printing the same for verification
    And request payload
    #		When REST operation post
    When method patch
