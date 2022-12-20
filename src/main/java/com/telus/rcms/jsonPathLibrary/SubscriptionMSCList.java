package com.telus.rcms.jsonPathLibrary;

public class SubscriptionMSCList {

	public static String href=null, date=null, slaId=null, violationRefValue=null,violationConsequence=null, violationComment=null, violationCommentLocale0=null, violationCommentLocale1=null;
	
	public static String relatedPartySubID=null, relatedPartyId=null,relatedPartyCharMissedMscAmtValue=null,relatedPartyCharReturnCdValue=null;
	

	
		public SubscriptionMSCList(int i){
			href="$.href";
			date="$.date";
			slaId="$.sla.id";
			relatedPartySubID="$.relatedParty[0].id";
			relatedPartyCharMissedMscAmtValue= "$.relatedParty["+i+"].characteristic[0].value";
			relatedPartyCharReturnCdValue= "$.relatedParty["+i+"].characteristic[1].value";
	
	}
	
}
