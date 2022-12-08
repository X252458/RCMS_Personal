package com.telus.rcms.jsonPathLibrary;

public class GetLoyaltyAccount {

	public static String id=null,loyaltyPgmProductName=null, status=null, charMonthlyDrawdownAmt=null,charFinanceCommItemId=null, charInstallmentLeftNum=null,charPaidAmt=null,charCatlogueItemCatCd=null,charCatlogueItemCatTypeCd=null,
			loyaltyBalance=null;

	public GetLoyaltyAccount(int i){
		
		id="$.["+i+"].id";
		loyaltyPgmProductName="$.["+i+"].loyaltyProgramProduct.name";
		status="$.["+i+"].status";
		charMonthlyDrawdownAmt="$.["+i+"].characteristic[0].value";
		charFinanceCommItemId="$.["+i+"].characteristic[1].value";
		charInstallmentLeftNum="$.["+i+"].characteristic[2].value";
		charPaidAmt="$.["+i+"].characteristic[3].value";
		charCatlogueItemCatCd="$.["+i+"].characteristic[4].value";
		charCatlogueItemCatTypeCd="$.["+i+"].characteristic[5].value";
		
		loyaltyBalance="$.["+i+"].loyaltyBalance.quantity.balance";
				
	}
	
	
}
