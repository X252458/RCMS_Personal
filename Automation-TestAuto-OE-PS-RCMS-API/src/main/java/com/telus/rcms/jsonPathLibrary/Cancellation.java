package com.telus.rcms.jsonPathLibrary;

public class Cancellation {

	public static String charReasonCdValue=null, relatedParty_Oriid=null,relatedParty_TransactionidValue=null,relatedParty_ChnlOrgValue=null,relatedParty_SalesRepidValue=null,
			relatedParty_TeamMemberidValue=null,relatedParty_Accid=null,relatedParty_brandidValue=null,relatedParty_AccTypeCodeValue=null,relatedParty_AccSubTypeCodeValue;
			
	public static String paymentAmount=null,paymentMech=null,payment_waive_adjCode=null,payment_waive_reasonCode=null,payment_waive_amt=null,
			paymentRelatedPartyFinanceCommItemIdvalue=null,paymentRelatedPartyChargeType=null,paymentRelatedPartyItemType=null,
					paymentRelatedPartySubid=null,paymentRelatedPartyMarketProvinceValue=null,paymentRelatedPartyHomeProvinceValue=null,paymentRelatedPartySubscriberNumValue=null,paymentRelatedPartyComboRatePlanIndValue=null;

	public Cancellation(){
	
		 charReasonCdValue="$.characteristic[0].value";
		 
		 relatedParty_Oriid = "$.relatedParty[0].id";
		 relatedParty_TransactionidValue = "$.relatedParty[0].characteristic[0].value";
		 relatedParty_ChnlOrgValue = "$.relatedParty[0].characteristic[1].value";
		 relatedParty_SalesRepidValue = "$.relatedParty[0].characteristic[2].value";
		 relatedParty_TeamMemberidValue = "$.relatedParty[0].characteristic[3].value";

		 relatedParty_Accid = "$.relatedParty[1].id";
		 relatedParty_brandidValue = "$.relatedParty[1].characteristic[0].value";
		 relatedParty_AccTypeCodeValue = "$.relatedParty[1].characteristic[1].value";
		 relatedParty_AccSubTypeCodeValue = "$.relatedParty[1].characteristic[2].value";

	}

	public Cancellation(int i){
	paymentAmount="$.payment["+i+"].amount.value";
	paymentMech="$.payment["+i+"].paymentMechanism";
	
	payment_waive_adjCode="$.payment["+i+"].waive[0].adjustmentCode";
	payment_waive_reasonCode="$.payment["+i+"].waive[0].reasonCode";
	payment_waive_amt="$.payment["+i+"].waive[0].amount.value";
	
	paymentRelatedPartyFinanceCommItemIdvalue="$.payment["+i+"].relatedParty[0].characteristic[0].value";
	paymentRelatedPartyChargeType="$.payment["+i+"].relatedParty[0].characteristic[1].value";
	paymentRelatedPartyItemType="$.payment["+i+"].relatedParty[0].characteristic[2].value";
	
	paymentRelatedPartySubid="$.payment["+i+"].relatedParty[1].id";
	paymentRelatedPartyMarketProvinceValue="$.payment["+i+"].relatedParty[1].characteristic[0].value";
	paymentRelatedPartyHomeProvinceValue="$.payment["+i+"].relatedParty[1].characteristic[1].value";
	paymentRelatedPartySubscriberNumValue="$.payment["+i+"].relatedParty[1].characteristic[2].value";
	paymentRelatedPartyComboRatePlanIndValue="$.payment["+i+"].relatedParty[1].characteristic[3].value";

}

}
