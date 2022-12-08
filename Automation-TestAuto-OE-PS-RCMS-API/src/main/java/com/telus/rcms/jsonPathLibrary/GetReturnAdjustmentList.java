package com.telus.rcms.jsonPathLibrary;

public class GetReturnAdjustmentList {

	public static String href=null, date=null, slaId=null, violationRefValue=null,violationConsequence=null, violationComment=null, violationCommentLocale0=null, violationCommentLocale1=null;
	
	public static String consequenceType=null, consequenceAdjAmtName=null, consequenceAdjAmtValue=null, consequenceBillingCode=null, consequenceBillingRevCode=null, consequenceLoyaltyType=null, consequenceAmt=null, consequenceTypeCode=null, relatedParty_Subid=null;
	

	public GetReturnAdjustmentList(){
		
		href="$.href";
		date="$.date";
		slaId="$.sla.id";
	}
	
		public GetReturnAdjustmentList(int i){
		consequenceType="$.violation.consequence["+i+"].type";
		consequenceBillingCode="$.violation.consequence["+i+"].billingCode";
		consequenceBillingRevCode="$.violation.consequence["+i+"].billingReversalCode";
		consequenceTypeCode="$.violation.consequence["+i+"].typeCode";	

	
	}
	
}
