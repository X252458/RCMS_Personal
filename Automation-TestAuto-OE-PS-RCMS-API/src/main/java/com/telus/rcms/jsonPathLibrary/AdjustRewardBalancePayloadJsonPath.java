package com.telus.rcms.jsonPathLibrary;

public class AdjustRewardBalancePayloadJsonPath {
	
	public static String quantityUnit= null, quantityBalance= null,
			relatedParty_subID=null, relatedParty_subRole=null, relatedParty_orgID=null, relatedParty_orgRole=null,
			relatedParty_channelName=null, relatedParty_channelValue=null,
					relatedParty_salesRepName=null, relatedParty_salesRepValue=null,
							relatedParty_teamMemName=null, relatedParty_teamMemValue=null,
			reasonCode=null, paymentMethodCd=null;
	
	public static String  cancelReasonCode=null, cancelAmount=null, payMech=null, financeCommitID=null,chargeType=null,  itemType=null, subMarketProvince=null,
			subHomeProvince=null, subID=null, subComboPlan=null, channelOrgID=null, channelSalesRepID=null, channelTeamMemID=null,
			accountID=null, accBrandID=null, accTypCode=null, accSubTypCode=null;
	
	public static String SC_subID=null, SC_subNo=null,PriviousStatusCode=null,CurrentStatusCode=null,
			SC_accID=null,SC_OrgID=null,SC_ChnlOrgID=null,SC_SalesRepID=null,SC_TeamMemID=null;
	
	public AdjustRewardBalancePayloadJsonPath() {
		quantityUnit="$.quantity.unit"; 
		quantityBalance="$.quantity.balance";
		relatedParty_subID="$.relatedParty[0].id"; 
		relatedParty_subRole="$.relatedParty[0].role"; 
		relatedParty_orgID="$.relatedParty[1].id"; 
		relatedParty_orgRole="$.relatedParty[1].role";
		relatedParty_channelName="$.relatedParty[1].characteristic[0].name";
		relatedParty_channelValue="$.relatedParty[1].characteristic[0].value";
		relatedParty_salesRepName="$.relatedParty[1].characteristic[1].name";
		relatedParty_salesRepValue="$.relatedParty[1].characteristic[1].value";
		relatedParty_teamMemName="$.relatedParty[1].characteristic[2].name";
		relatedParty_teamMemValue="$.relatedParty[1].characteristic[2].value";
		reasonCode="$.reasonCode"; 
		paymentMethodCd="$.paymentMethodCd";
		
		
		//Status Change API
		SC_subID= "$.relatedParty[0].id";
		SC_subNo= "$.relatedParty[0].characteristic[0].value";
		PriviousStatusCode="$.relatedParty[0].characteristic[1].value";
		CurrentStatusCode="$.relatedParty[0].characteristic[2].value";
		SC_accID="$.relatedParty[1].id";
		SC_OrgID="$.relatedParty[2].id";
		SC_ChnlOrgID="$.relatedParty[2].characteristic[1].value";
		SC_SalesRepID="$.relatedParty[2].characteristic[2].value";
		SC_TeamMemID="$.relatedParty[2].characteristic[3].value";
	}
	//chargeType itemType subMarketProvince subHomeProvince subID subComboPlan channelOrgID channelSalesRepID channelTeamMemID accountID accBrandID accTypCode accSubTypCode
		/**
		 * Adding path for NewAdjustBalance Testcase- Scenario 2:- TC08
		 */
		public AdjustRewardBalancePayloadJsonPath(int i) {
		cancelReasonCode="$.characteristic[0].value";
		cancelAmount="$.payment["+i+"].amount.value";
		payMech="$.payment["+i+"].paymentMechanism";
		financeCommitID="$.payment["+i+"].relatedParty[0].characteristic[0].value";
		chargeType="$.payment["+i+"].relatedParty[0].characteristic[1].value";
		itemType="$.payment["+i+"].relatedParty[0].characteristic[2].value";
		subMarketProvince="$.payment["+i+"].relatedParty[1].characteristic[0].value";
		subHomeProvince="$.payment["+i+"].relatedParty[1].characteristic[1].value";
		subID="$.payment["+i+"].relatedParty[1].id";
		subComboPlan="$.payment["+i+"].relatedParty[1].characteristic[3].value";
		channelOrgID="$.relatedParty[0].characteristic[1].value";
		channelSalesRepID="$.relatedParty[0].characteristic[2].value";
		channelTeamMemID="$.relatedParty[0].characteristic[3].value";
		accountID="$.relatedParty[1].id";
		accBrandID="$.relatedParty[1].characteristic[0].value";
		accTypCode="$.relatedParty[1].characteristic[1].value";
		accSubTypCode="$.relatedParty[1].characteristic[2].value";

	}		
}
