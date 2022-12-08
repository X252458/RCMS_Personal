package com.telus.rcms.jsonPathLibrary;

public class ListEarnedTransaction {

	public static String id=null, quantity=null, closeBal=null, dateTime=null, descEN=null, descFR=null, 
			charTotalHistoryRecordNo=null, charTypeCd=null, charUnitOfMeasureCd=null, charReasonCode=null, charPaymentMethodCd=null,
			loyaltyPgmProductID=null;	

	public ListEarnedTransaction(int i){
		
		id="$.["+i+"].id";
		quantity="$.["+i+"].quantity";
		closeBal="$.["+i+"].closingBalance";
		dateTime="$.["+i+"].dateTime";
		
		descEN="$.["+i+"].description[0].locale";
		descFR="$.["+i+"].description[1].locale";
		
		charTotalHistoryRecordNo="$.["+i+"].characteristic[0].value";
		charTypeCd="$.["+i+"].characteristic[1].value";
		charUnitOfMeasureCd="$.["+i+"].characteristic[2].value";
		charReasonCode="$.["+i+"].characteristic[3].value";
		charPaymentMethodCd="$.["+i+"].characteristic[4].value";
		
		loyaltyPgmProductID="$.["+i+"].loyaltyProgramProduct.id";
		
		
	}
	
	
}
