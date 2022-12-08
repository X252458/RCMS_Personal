package com.telus.rcms.jsonPathLibrary;

public class ExchangeAgreementItem {
	
			public static String AgreementItem_ID=null, AgreementDurationEndDateTime=null, AgreementDurationStartDateTime=null, AgreementDurationAmount=null, 
					AgreementDurationUnits=null,AgreementItemType=null,AgreementDurationIncentiveAmount=null,AgreementDurationIncentiveServiceCode=null,
			        AgreementDurationInstallmentEndDateTime=null,AgreementDurationInstallmentStartDateTime=null,AgreementDurationInstallmentAmount=null,
			        AgreementDurationInstallmentUnits=null,AgreementDurationCharacteristicName1=null,AgreementDurationCharacteristicValue1=null,
			        AgreementDurationCharacteristicName2=null,AgreementDurationCharacteristicValue2=null,AgreementDurationCharacteristicName3=null,
			        AgreementDurationCharacteristicValue3=null;
	        
	        public static String AgreementDurationTermOrConditionCharacteristicName1=null, AgreementDurationTermOrConditionCharacteristicValue1=null,
			        AgreementDurationTermOrConditionCharacteristicName2=null,AgreementDurationTermOrConditionCharacteristicValue2=null,
			        AgreementDurationTermOrConditionCharacteristicName3=null,AgreementDurationTermOrConditionCharacteristicValue3=null;
	        
	        public static String AgreementDurationTaxMethodCode=null,AgreementDurationTaxMechanismCode=null,AgreementDurationTaxChannelCode=null,
	        		AgreementDurationTaxProvinceCode=null,AgreementDurationTaxCategory=null,AgreementDurationTaxRate=null,
	        		AgreementDurationTaxAmountValue=null,AgreementDurationTaxRateUnit=null;
	
	        public static String AgreementDurationProductSerialNumber=null, AgreementDurationProductSpecificationID=null,AgreementDurationProductSpecificationCategoryCode=null,
					AgreementDurationProductSpecificationTypeCode=null,AgreementDurationProductDutyFreeAmountValue=null,AgreementDurationProductDutyFreeAmountUnit=null,
					AgreementDurationProductCharacteristicName=null,AgreementDurationProductCharacteristicValue=null;
	        
	        public static String AgreementDurationPromotionID=null, AgreementDurationPromotionPerspectiveDate=null;
	
			public static String  AgreementDurationRelatedPartyID_1=null, AgreementDurationRelatedPartyRole_1=null,
					AgreementDurationRelatedPartyCharacteristicName_1=null, AgreementDurationRelatedPartyCharacteristicValue_1=null,
			
			AgreementDurationRelatedPartyID_2=null, AgreementDurationRelatedPartyRole_2=null,
			AgreementDurationRelatedPartyCharacteristicName_2=null, AgreementDurationRelatedPartyCharacteristicValue_2=null;
			
			public static String AgreementDurationProductOfferingID=null, AgreementDurationProductOfferingCharacteristicName_0=null, AgreementDurationProductOfferingCharacteristicValue_0=null, 
					AgreementDurationProductOfferingCharacteristicName_1=null, AgreementDurationProductOfferingCharacteristicValue_1=null, 
					AgreementDurationProductOfferingCharacteristicName_2=null, AgreementDurationProductOfferingCharacteristicValue_2=null,
					AgreementDurationProductOfferingPerspectiveDate=null,AgreementDurationProductOfferingSourceSystemId=null;
	
			public static String RelatedPartyID_1=null, RelatedPartyRole_1=null, RelatedParty_1_CharacteristicName_0=null,
					RelatedParty_1_CharacteristicValue_0=null, RelatedParty_1_CharacteristicName_1=null,RelatedParty_1_CharacteristicValue_1=null,
					RelatedParty_1_CharacteristicName_2=null, RelatedParty_1_CharacteristicValue_2=null,
			
			RelatedPartyID_2=null,
			RelatedPartyRole_2=null,
			RelatedParty_2_CharacteristicName_0=null,
			RelatedParty_2_CharacteristicValue_0=null,
			RelatedParty_2_CharacteristicName_1=null,
			RelatedParty_2_CharacteristicValue_1=null,
			RelatedParty_2_CharacteristicName_2=null,
			RelatedParty_2_CharacteristicValue_2=null,
			
			RelatedPartyID_3=null,
			RelatedPartyRole_3=null,
			RelatedParty_3_CharacteristicName_0=null,
			RelatedParty_3_CharacteristicValue_0=null,
			RelatedParty_3_CharacteristicName_1=null,
			RelatedParty_3_CharacteristicValue_1=null,
			RelatedParty_3_CharacteristicName_2=null,
			RelatedParty_3_CharacteristicValue_2=null,
	        RelatedParty_3_CharacteristicName_3=null,
	        RelatedParty_3_CharacteristicValue_3=null;
			
	
	
	public ExchangeAgreementItem(int i) {
		AgreementItem_ID = "$.agreementItem["+i+"].id";
		AgreementDurationEndDateTime = "$.agreementItem["+i+"].itemDuration.endDateTime";
		AgreementDurationStartDateTime = "$.agreementItem["+i+"].itemDuration.startDateTime";
		AgreementDurationAmount = "$.agreementItem["+i+"].itemDuration.quantity.amount";
		AgreementDurationUnits = "$.agreementItem["+i+"].itemDuration.quantity.units";
		AgreementItemType="$.agreementItem["+i+"].itemType";
		AgreementDurationIncentiveAmount="$.agreementItem["+i+"].incentiveAmount";
		AgreementDurationIncentiveServiceCode="$.agreementItem["+i+"].incentiveServiceCode";
		
		//Installment
		AgreementDurationInstallmentEndDateTime="$.agreementItem["+i+"].installment.installmentDuration.endDateTime";
		AgreementDurationInstallmentStartDateTime="$.agreementItem["+i+"].installment.installmentDuration.startDateTime";
		AgreementDurationInstallmentAmount="$.agreementItem["+i+"].installment.installmentDuration.quantity.amount";
		AgreementDurationInstallmentUnits="$.agreementItem["+i+"].installment.installmentDuration.quantity.units";
		AgreementDurationCharacteristicName1="$.agreementItem["+i+"].installment.characteristic[0].name";
		AgreementDurationCharacteristicValue1="$.agreementItem["+i+"].installment.characteristic[0].value";
		AgreementDurationCharacteristicName2="$.agreementItem["+i+"].installment.characteristic[1].name";
		AgreementDurationCharacteristicValue2="$.agreementItem["+i+"].installment.characteristic[1].value";
		AgreementDurationCharacteristicName3="$.agreementItem["+i+"].installment.characteristic[2].name";
		AgreementDurationCharacteristicValue3="$.agreementItem["+i+"].installment.characteristic[2].value";
		
		//Term and Condition
		AgreementDurationTermOrConditionCharacteristicName1="$.agreementItem["+i+"].termOrCondition.characteristic[0].name";
		AgreementDurationTermOrConditionCharacteristicValue1="$.agreementItem["+i+"].termOrCondition.characteristic[0].value";
		AgreementDurationTermOrConditionCharacteristicName2="$.agreementItem["+i+"].termOrCondition.characteristic[1].name";
		AgreementDurationTermOrConditionCharacteristicValue2="$.agreementItem["+i+"].termOrCondition.characteristic[1].value";
		AgreementDurationTermOrConditionCharacteristicName3="$.agreementItem["+i+"].termOrCondition.characteristic[2].name";
		AgreementDurationTermOrConditionCharacteristicValue3="$.agreementItem["+i+"].termOrCondition.characteristic[2].value";
		
		//Tax
		AgreementDurationTaxMethodCode="$.agreementItem["+i+"].tax.taxPaymentMethodCode";
		AgreementDurationTaxMechanismCode="$.agreementItem["+i+"].tax.taxPaymentMechanismCode";
		AgreementDurationTaxChannelCode="$.agreementItem["+i+"].tax.taxPaymentChannelCode";
		AgreementDurationTaxProvinceCode="$.agreementItem["+i+"].tax.taxProvinceCode";
		AgreementDurationTaxCategory="$.agreementItem["+i+"].tax.taxRate[0].taxCategory";
		AgreementDurationTaxRate="$.agreementItem["+i+"].tax.taxRate[0].taxRate";
		AgreementDurationTaxAmountValue="$.agreementItem["+i+"].tax.taxRate[0].taxAmount.value";
		AgreementDurationTaxRateUnit="$.agreementItem["+i+"].tax.taxRate[0].taxAmount.unit";
		
		//Product
		AgreementDurationProductSerialNumber="$.agreementItem["+i+"].product[0].productSerialNumber";
		AgreementDurationProductSpecificationID="$.agreementItem["+i+"].product[0].productSpecification.id";
		AgreementDurationProductSpecificationCategoryCode="$.agreementItem["+i+"].product[0].productSpecification.categoryCode";
		AgreementDurationProductSpecificationTypeCode="$.agreementItem["+i+"].product[0].productSpecification.typeCode";
		AgreementDurationProductDutyFreeAmountValue="$.agreementItem["+i+"].product[0].productPrice.price.dutyFreeAmount.value";
		AgreementDurationProductDutyFreeAmountUnit="$.agreementItem["+i+"].product[0].productPrice.price.dutyFreeAmount.unit";
		AgreementDurationProductCharacteristicName="$.agreementItem["+i+"].product[0].productCharacteristic[0].name";
		AgreementDurationProductCharacteristicValue="$.agreementItem["+i+"].product[0].productCharacteristic[0].value";
		
		//Promotion
		AgreementDurationPromotionID="$.agreementItem["+i+"].promotion[0].id";
		AgreementDurationPromotionPerspectiveDate="$.agreementItem["+i+"].promotion[0].perspectiveDate";
		
		//Related party
		AgreementDurationRelatedPartyID_1="$.agreementItem["+i+"].relatedParty[0].id";
		AgreementDurationRelatedPartyRole_1="$.agreementItem["+i+"].relatedParty[0].role";
		AgreementDurationRelatedPartyCharacteristicName_1="$.agreementItem["+i+"].relatedParty[0].characteristic[0].name";
		AgreementDurationRelatedPartyCharacteristicValue_1="$.agreementItem["+i+"].relatedParty[0].characteristic[0].value";
		AgreementDurationRelatedPartyID_2="$.agreementItem["+i+"].relatedParty[1].id";
		AgreementDurationRelatedPartyRole_2="$.agreementItem["+i+"].relatedParty[1].role";
		AgreementDurationRelatedPartyCharacteristicName_2="$.agreementItem["+i+"].relatedParty[1].characteristic[0].name";
		AgreementDurationRelatedPartyCharacteristicValue_2="$.agreementItem["+i+"].relatedParty[1].characteristic[0].value";
		
		//Product Offering
		AgreementDurationProductOfferingID="$.agreementItem["+i+"].productOffering.id";
		AgreementDurationProductOfferingCharacteristicName_0="$.agreementItem["+i+"].productOffering.characteristic[0].name";
		AgreementDurationProductOfferingCharacteristicValue_0="$.agreementItem["+i+"].productOffering.characteristic[0].value";
		AgreementDurationProductOfferingCharacteristicName_1="$.agreementItem["+i+"].productOffering.characteristic[1].name";
		AgreementDurationProductOfferingCharacteristicValue_1="$.agreementItem["+i+"].productOffering.characteristic[1].value";
		AgreementDurationProductOfferingCharacteristicName_2="$.agreementItem["+i+"].productOffering.characteristic[2].name";
		AgreementDurationProductOfferingCharacteristicValue_2="$.agreementItem["+i+"].productOffering.characteristic[2].value";
		AgreementDurationProductOfferingPerspectiveDate = "$.agreementItem["+i+"].productOffering.perspectiveDate";
		AgreementDurationProductOfferingSourceSystemId = "$.agreementItem["+i+"].productOffering.sourceSystemId";
	}
		//Related Party
		public ExchangeAgreementItem() {
		RelatedPartyID_1="$.relatedParty[0].id";
		RelatedPartyRole_1="$.relatedParty[0].role";
		RelatedParty_1_CharacteristicName_0="$.relatedParty[0].characteristic[0].name";
		RelatedParty_1_CharacteristicValue_0="$.relatedParty[0].characteristic[0].value";
		RelatedParty_1_CharacteristicName_1="$.relatedParty[0].characteristic[1].name";
		RelatedParty_1_CharacteristicValue_1="$.relatedParty[0].characteristic[1].value";
		RelatedParty_1_CharacteristicName_2="$.relatedParty[0].characteristic[2].name";
		RelatedParty_1_CharacteristicValue_2="$.relatedParty[0].characteristic[2].value";
		
		RelatedPartyID_2="$.relatedParty[1].id";
		RelatedPartyRole_2="$.relatedParty[1].role";
		RelatedParty_2_CharacteristicName_0="$.relatedParty[1].characteristic[0].name";
		RelatedParty_2_CharacteristicValue_0="$.relatedParty[1].characteristic[0].value";
		RelatedParty_2_CharacteristicName_1="$.relatedParty[1].characteristic[1].name";
		RelatedParty_2_CharacteristicValue_1="$.relatedParty[1].characteristic[1].value";
		RelatedParty_2_CharacteristicName_2="$.relatedParty[1].characteristic[2].name";
		RelatedParty_2_CharacteristicValue_2="$.relatedParty[1].characteristic[2].value";
		
		RelatedPartyID_3="$.relatedParty[2].id";
		RelatedPartyRole_3="$.relatedParty[2].role";
		RelatedParty_3_CharacteristicName_0="$.relatedParty[2].characteristic[0].name";
		RelatedParty_3_CharacteristicValue_0="$.relatedParty[2].characteristic[0].value";
		RelatedParty_3_CharacteristicName_1="$.relatedParty[2].characteristic[1].name";
		RelatedParty_3_CharacteristicValue_1="$.relatedParty[2].characteristic[1].value";
		RelatedParty_3_CharacteristicName_2="$.relatedParty[2].characteristic[2].name";
		RelatedParty_3_CharacteristicValue_2="$.relatedParty[2].characteristic[2].value";
		RelatedParty_3_CharacteristicName_3="$.relatedParty[2].characteristic[3].name";
		RelatedParty_3_CharacteristicValue_3="$.relatedParty[2].characteristic[3].value";
		
	}

}
