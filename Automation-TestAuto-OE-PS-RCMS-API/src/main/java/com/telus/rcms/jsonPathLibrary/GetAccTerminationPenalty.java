package com.telus.rcms.jsonPathLibrary;

public class GetAccTerminationPenalty {

	public static String href=null, date=null, slaId=null, violationConsequence=null, violationComment=null;
	
	public static String relatedParty_Subid=null,relatedParty_SubscriberNum=null;
	
	public static String consequenceType=null, consequenceAdjAmtName=null, consequenceAdjAmtValue=null, consequenceBillingCode=null, consequenceBillingRevCode=null, consequenceLoyaltyType=null, consequenceAmt=null, consequenceTypeCode=null;
	
	

	public GetAccTerminationPenalty(){
		
		href="$.href";
		date="$.date";
		slaId="$.sla.id";
		
	}
	
	public GetAccTerminationPenalty(String sub,int i){
		relatedParty_Subid="$.relatedParty["+i+"].id";
		relatedParty_SubscriberNum="$.relatedParty["+i+"].characteristic[0].value";
		
	}
	
		public GetAccTerminationPenalty(int i){
		consequenceType="$.violation.consequence["+i+"].type";
		consequenceAdjAmtName="$.violation.consequence["+i+"].characteristic[0].name";
		consequenceAdjAmtValue="$.violation.consequence["+i+"].characteristic[0].value";
		consequenceBillingCode="$.violation.consequence["+i+"].billingCode";
		consequenceBillingRevCode="$.violation.consequence["+i+"].billingReversalCode";
		consequenceLoyaltyType="$.violation.consequence["+i+"].loyaltyType";
		consequenceAmt="$.violation.consequence["+i+"].amount";
		consequenceTypeCode="$.violation.consequence["+i+"].typeCode";	
	
	}
	
}
