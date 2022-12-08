package com.telus.rcms.jsonPathLibrary;

public class GetEarlyRenewalPenalty {

	public static String href=null, date=null, slaId=null, violationRefValue=null,violationConsequence=null, violationComment=null, violationCommentLocale0=null, violationCommentLocale1=null;
	
	public static String consequenceType=null, consequenceAdjAmtName=null, consequenceAdjAmtValue=null, consequenceBillingCode=null, consequenceBillingRevCode=null, consequenceLoyaltyType=null, consequenceAmt=null, consequenceTypeCode=null;
	

	public GetEarlyRenewalPenalty(){
		
		href="$.href";
		date="$.date";
		slaId="$.sla.id";
		violationRefValue="$.violation.referenceValue";
		violationCommentLocale0="$.violation.comment[0].locale";
		violationCommentLocale1="$.violation.comment[1].locale";	
	}
	
		public GetEarlyRenewalPenalty(int i){
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
