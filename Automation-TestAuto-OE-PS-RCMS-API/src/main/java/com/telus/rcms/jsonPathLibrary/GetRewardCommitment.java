package com.telus.rcms.jsonPathLibrary;

public class GetRewardCommitment {

	public static String AgreementDurationEndDateTime=null, AgreementDurationStartDateTime=null, AgreementDurationAmount=null, AgreementDurationUnits=null;
	
	public static String relatedParty_Accid=null, relatedParty_Accrole=null, relatedParty_brandid=null, relatedParty_brandidValue=null, relatedParty_AccTypeCode=null, relatedParty_AccTypeCodeValue=null, relatedParty_AccSubTypeCode=null, relatedParty_AccSubTypeCodeValue=null;    
	

	public GetRewardCommitment(int i){
	AgreementDurationEndDateTime = "$.["+i+"].agreementDuration.endDateTime";
	AgreementDurationStartDateTime = "$.["+i+"].agreementDuration.startDateTime";
	AgreementDurationAmount = "$.["+i+"].agreementDuration.quantity.amount";
	AgreementDurationUnits = "$.["+i+"].agreementDuration.quantity.units";

	relatedParty_Accid = "$.["+i+"].relatedParty[0].id";
	relatedParty_Accrole = "$.["+i+"].relatedParty[0].role";
	relatedParty_brandid = "$.["+i+"].relatedParty[0].characteristic[0].name";
	relatedParty_brandidValue = "$.["+i+"].relatedParty[0].characteristic[0].value";
	relatedParty_AccTypeCode = "$.["+i+"].relatedParty[0].characteristic[1].name";
	relatedParty_AccTypeCodeValue = "$.["+i+"].relatedParty[0].characteristic[1].value";
	relatedParty_AccSubTypeCode = "$.["+i+"].relatedParty[0].characteristic[2].name";
	relatedParty_AccSubTypeCodeValue = "$.["+i+"].relatedParty[0].characteristic[2].value";
	}
	
}
