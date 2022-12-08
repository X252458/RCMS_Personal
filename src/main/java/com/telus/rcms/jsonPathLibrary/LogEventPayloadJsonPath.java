package com.telus.rcms.jsonPathLibrary;

public class LogEventPayloadJsonPath {

	public static String  eventID=null,    eventTime=null,    eventType=null, memberId=null;
	public static String  rewardTypeCode=null,    reasonCode=null,    deviceDueDate=null; 
	public static String subID=null, oriID=null, oriChannelID=null, orisalesRepID=null;
	
	public LogEventPayloadJsonPath(){
	 
		eventID="$.eventId";
		eventTime="$.eventTime";
		eventType="$.eventType";
		memberId="$.memberId";
		rewardTypeCode="$.characteristic[0].value";
		reasonCode="$.characteristic[1].value";
		deviceDueDate="$.characteristic[2].value";
		
		subID="$.relatedParty[0].id";
		oriID="$.relatedParty[1].id";
		oriChannelID="$.relatedParty[1].characteristic[0].value";
		orisalesRepID="$.relatedParty[1].characteristic[1].value";
	}
}
