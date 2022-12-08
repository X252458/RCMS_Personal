package com.telus.rcms.jsonPathLibrary;



public class UpdateDeviceSerialNo {

	public static String brandId=null, contractEndDate=null, contractStartDate=null, productId=null, serialNumber=null, redeemedOfferId=null, relatedParty_Subid=null;
	
	
	public UpdateDeviceSerialNo(){
		
		brandId = "$.characteristic[0].value";
		contractEndDate = "$.characteristic[1].value";
		contractStartDate = "$.characteristic[2].value";
		productId = "$.characteristic[3].value";
		serialNumber = "$.characteristic[4].value";
		redeemedOfferId = "$.characteristic[5].value";
		relatedParty_Subid= "$.relatedParty[0].id";
		}
	  	


}
