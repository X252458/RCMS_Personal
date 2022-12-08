package com.telus.rcms.jsonPathLibrary;

public class ActivationPayloadJsonPath {

	public static String AgreementDurationEndDateTime=null, AgreementDurationStartDateTime=null, AgreementDurationAmount=null, AgreementDurationUnits=null;
	public static String relatedParty_Accid =null,   relatedParty_Accrole =null,   relatedParty_brandid =null,   relatedParty_brandidValue =null,   relatedParty_AccTypeCode =null,   relatedParty_AccTypeCodeValue =null,   relatedParty_AccSubTypeCode =null,   relatedParty_AccSubTypeCodeValue =null,relatedParty_Oriid =null,   relatedParty_Orirole =null,   relatedParty_Transactionid =null,   relatedParty_TransactionidValue =null,   relatedParty_ChnlOrgid =null,   relatedParty_ChnlOrgValue =null,   relatedParty_SalesRepid =null,   relatedParty_SalesRepidValue =null,   relatedParty_TeamMemberid =null,   relatedParty_TeamMemberidValue =null, relatedParty_Subid =null,   relatedParty_Subrole =null,   relatedParty_MarketProvince =null,   relatedParty_MarketProvinceValue =null,   relatedParty_HomeProvince =null,   relatedParty_HomeProvinceValue =null,   relatedParty_SubscriberNum =null,   relatedParty_SubscriberNumValue =null,   relatedParty_ComboRatePlanInd =null,   relatedParty_ComboRatePlanIndValue =null;

	//cust_agrmt_lifecycl table
	public static String agrmt_stat_id =null,   eff_StartDate =null,   eff_EndDate =null;  
	
	
	public ActivationPayloadJsonPath()
	{
		agreementDuration();
		relatedParty();	
	}
	
	/**
	 * agreementDuration
	 */
	public static void agreementDuration() {
		AgreementDurationEndDateTime = "$.agreementDuration.endDateTime";
		AgreementDurationStartDateTime = "$.agreementDuration.startDateTime";
		AgreementDurationAmount = "$.agreementDuration.quantity.amount";
		AgreementDurationUnits = "$.agreementDuration.quantity.units";
	}

	/**
	 * relatedParty
	 */

	public static void relatedParty() {
		 relatedParty_Accid = "$.relatedParty[0].id";
		 relatedParty_Accrole = "$.relatedParty[0].role";
		 relatedParty_brandid = "$.relatedParty[0].characteristic[0].name";
		 relatedParty_brandidValue = "$.relatedParty[0].characteristic[0].value";
		 relatedParty_AccTypeCode = "$.relatedParty[0].characteristic[1].name";
		 relatedParty_AccTypeCodeValue = "$.relatedParty[0].characteristic[1].value";
		 relatedParty_AccSubTypeCode = "$.relatedParty[0].characteristic[2].name";
		 relatedParty_AccSubTypeCodeValue = "$.relatedParty[0].characteristic[2].value";

		 relatedParty_Oriid = "$.relatedParty[1].id";
		 relatedParty_Orirole = "$.relatedParty[1].role";
		 relatedParty_Transactionid = "$.relatedParty[1].characteristic[0].name";
		 relatedParty_TransactionidValue = "$.relatedParty[1].characteristic[0].value";
		 relatedParty_ChnlOrgid = "$.relatedParty[1].characteristic[1].name";
		 relatedParty_ChnlOrgValue = "$.relatedParty[1].characteristic[1].value";
		 relatedParty_SalesRepid = "$.relatedParty[1].characteristic[2].name";
		 relatedParty_SalesRepidValue = "$.relatedParty[1].characteristic[2].value";
		 relatedParty_TeamMemberid = "$.relatedParty[1].characteristic[3].name";
		 relatedParty_TeamMemberidValue = "$.relatedParty[1].characteristic[3].value";

		 relatedParty_Subid = "$.relatedParty[2].id";
		 relatedParty_Subrole = "$.relatedParty[2].role";
		 relatedParty_MarketProvince = "$.relatedParty[2].characteristic[0].name";
		 relatedParty_MarketProvinceValue = "$.relatedParty[2].characteristic[0].value";
		 relatedParty_HomeProvince = "$.relatedParty[2].characteristic[1].name";
		 relatedParty_HomeProvinceValue = "$.relatedParty[2].characteristic[1].value";
		 relatedParty_SubscriberNum = "$.relatedParty[2].characteristic[2].name";
		 relatedParty_SubscriberNumValue = "$.relatedParty[2].characteristic[2].value";
		 relatedParty_ComboRatePlanInd = "$.relatedParty[2].characteristic[3].name";
		 relatedParty_ComboRatePlanIndValue = "$.relatedParty[2].characteristic[3].value";
		 
		 
//		 Other DB fields to Validate
		 agrmt_stat_id="1";
		 eff_StartDate=null;
		 eff_EndDate="9999-12-31";
		 
	}


}
