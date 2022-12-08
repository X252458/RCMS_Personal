package com.telus.rcms.jsonPathLibrary;



public class Renewal {

	public static String AgreementDurationEndDateTime=null, AgreementDurationStartDateTime=null, AgreementDurationAmount=null, AgreementDurationUnits=null;
	public static String relatedParty_Accid =null,   relatedParty_Accrole =null,   relatedParty_brandid =null,   relatedParty_brandidValue =null,   relatedParty_AccTypeCode =null,   relatedParty_AccTypeCodeValue =null,   relatedParty_AccSubTypeCode =null,   relatedParty_AccSubTypeCodeValue =null,relatedParty_Oriid =null,   relatedParty_Orirole =null,   relatedParty_Transactionid =null,   relatedParty_TransactionidValue =null,   relatedParty_ChnlOrgid =null,   relatedParty_ChnlOrgValue =null,   relatedParty_SalesRepid =null,   relatedParty_SalesRepidValue =null,   relatedParty_TeamMemberid =null,   relatedParty_TeamMemberidValue =null, relatedParty_Subid =null,   relatedParty_Subrole =null,   relatedParty_MarketProvince =null,   relatedParty_MarketProvinceValue =null,   relatedParty_HomeProvince =null,   relatedParty_HomeProvinceValue =null,   relatedParty_SubscriberNum =null,   relatedParty_SubscriberNumValue =null,   relatedParty_ComboRatePlanInd =null,   relatedParty_ComboRatePlanIndValue =null;
	
	
	public static String agreementItem_id =null,  agreementItem_itemDurationEndDateTime =null,  agreementItem_itemDurationStartDateTime =null,  agreementItem_itemDurationAmount =null,  agreementItem_itemDurationUnits = null, agreementItem_incentiveServiceCode =null,  agreementItem_itemType =null,  agreementItem_incentiveAmount = null, agreementItem_installmentEndDateTime =null,  agreementItem_installmentStartDateTime =null,  agreementItem_installmentAmount =null,  agreementItem_installmentUnits = null, agreementItem_installmentLeftNum =null,  agreementItem_installmentLeftNumValue = null, agreementItem_installmentAppliedNum =null,  agreementItem_installmentAppliedNumValue = null, agreementItem_installmentAppliedAmt =null,  agreementItem_installmentAppliedAmtValue = null, agreementItem_termOrConditionMinRatePlan =null,  agreementItem_termOrConditionMinRatePlanValue = null, agreementItem_termOrConditionMinFeature =null,  agreementItem_termOrConditionMinFeatureValue = null, agreementItem_termOrConditionMinCombined =null,  agreementItem_termOrConditionMinCombinedValue = null, agreementItem_termOrConditionCommitmentServiceCd =null,  agreementItem_termOrConditionCommitmentServiceCdValue = null, agreementItem_termOrConditionAutoTopupCommitmentInd =null,  agreementItem_termOrConditionAutoTopupCommitmentIndValue = null, agreementItem_tax =null,  agreementItem_taxPaymentMethodCode =null,  agreementItem_taxPaymentMechanismCode =null,  agreementItem_taxPaymentChannelCode =null,  agreementItem_taxProvinceCode =null,  agreementItem_taxCategory =null,  agreementItem_taxRate =null,  agreementItem_taxAmountValue =null,  agreementItem_taxAmountUnit = null, agreementItem_product =null,  agreementItem_productSerialNumber =null,  agreementItem_productSpecificationid =null,  agreementItem_productSpecificationCategoryCode =null,  agreementItem_productSpecificationLocale1 =null,  agreementItem_productSpecificationDesc1 =null,  agreementItem_productSpecificationLocale2 =null,  agreementItem_productSpecificationDesc2 = null, agreementItem_productSpecificationTypeCode =null,  agreementItem_productPriceValue =null,  agreementItem_productPriceUnit =null,  agreementItem_productCharacteristicName =null,  agreementItem_productCharacteristicValue = null, agreementItem_promotionid =null,  agreementItem_promotionPerspectiveDate = null, agreementItem_productOfferingid =null,  agreementItem_productOfferingRedeemedOfferContextCode =null,  agreementItem_productOfferingRedeemedOfferContextCodeValue = null, agreementItem_productOfferingOfferTierCd =null,  agreementItem_productOfferingOfferTierCdValue = null, agreementItem_productOfferingOfferTierCapAmt =null,  agreementItem_productOfferingOfferTierCapAmtValue = null, agreementItem_productOfferingDataCommitmentInd =null,  agreementItem_productOfferingDataCommitmentIndValue = null, agreementItem_productOfferingContractEnforcedPlanInd =null,  agreementItem_productOfferingContractEnforcedPlanIndValue = null, agreementItem_productOfferingPerspectiveDate =null,  agreementItem_productOfferingSourceSystemId = null, agreementItem_relatedPartyId =null,  agreementItem_relatedPartyRole =null,  agreementItem_relatedPartySourceSystemId = null;

	
	public static String payment = null, paymentAmt= null, paymentUnit= null, payment_paymentMachanism= null, payment_relatedParty= null, payment_relatedPartyId= null, 
	 payment_relatedPartyRole= null, payment_FinanceCommitmentItemId= null, payment_FinanceCommitmentItemValue= null, payment_ChargeType= null, 
	 payment_ChargeTypeValue= null, payment_ItemType= null, payment_ItemTypeValue= null, payment_waive_adjCode=null, payment_waive_reasonCode=null, payment_waive_amt=null;
	
	public Renewal(){
		
		AgreementDurationEndDateTime = "$.agreementCreate.agreementDuration.endDateTime";
		AgreementDurationStartDateTime = "$.agreementCreate.agreementDuration.startDateTime";
		AgreementDurationAmount = "$.agreementCreate.agreementDuration.quantity.amount";
		AgreementDurationUnits = "$.agreementCreate.agreementDuration.quantity.units";

		
		 relatedParty_Accid = "$.agreementCreate.relatedParty[0].id";
		 relatedParty_Accrole = "$.agreementCreate.relatedParty[0].role";
		 relatedParty_brandid = "$.agreementCreate.relatedParty[0].characteristic[0].name";
		 relatedParty_brandidValue = "$.agreementCreate.relatedParty[0].characteristic[0].value";
		 relatedParty_AccTypeCode = "$.agreementCreate.relatedParty[0].characteristic[1].name";
		 relatedParty_AccTypeCodeValue = "$.agreementCreate.relatedParty[0].characteristic[1].value";
		 relatedParty_AccSubTypeCode = "$.agreementCreate.relatedParty[0].characteristic[2].name";
		 relatedParty_AccSubTypeCodeValue = "$.agreementCreate.relatedParty[0].characteristic[2].value";

		 relatedParty_Oriid = "$.agreementCreate.relatedParty[1].id";
		 relatedParty_Orirole = "$.agreementCreate.relatedParty[1].role";
		 relatedParty_Transactionid = "$.agreementCreate.relatedParty[1].characteristic[0].name";
		 relatedParty_TransactionidValue = "$.agreementCreate.relatedParty[1].characteristic[0].value";
		 relatedParty_ChnlOrgid = "$.agreementCreate.relatedParty[1].characteristic[1].name";
		 relatedParty_ChnlOrgValue = "$.agreementCreate.relatedParty[1].characteristic[1].value";
		 relatedParty_SalesRepid = "$.agreementCreate.relatedParty[1].characteristic[2].name";
		 relatedParty_SalesRepidValue = "$.agreementCreate.relatedParty[1].characteristic[2].value";
		 relatedParty_TeamMemberid = "$.agreementCreate.relatedParty[1].characteristic[3].name";
		 relatedParty_TeamMemberidValue = "$.agreementCreate.relatedParty[1].characteristic[3].value";

		 relatedParty_Subid = "$.agreementCreate.relatedParty[2].id";
		 relatedParty_Subrole = "$.agreementCreate.relatedParty[2].role";
		 relatedParty_MarketProvince = "$.agreementCreate.relatedParty[2].characteristic[0].name";
		 relatedParty_MarketProvinceValue = "$.agreementCreate.relatedParty[2].characteristic[0].value";
		 relatedParty_HomeProvince = "$.agreementCreate.relatedParty[2].characteristic[1].name";
		 relatedParty_HomeProvinceValue = "$.agreementCreate.relatedParty[2].characteristic[1].value";
		 relatedParty_SubscriberNum = "$.agreementCreate.relatedParty[2].characteristic[2].name";
		 relatedParty_SubscriberNumValue = "$.agreementCreate.relatedParty[2].characteristic[2].value";
		 relatedParty_ComboRatePlanInd = "$.agreementCreate.relatedParty[2].characteristic[3].name";
		 relatedParty_ComboRatePlanIndValue = "$.agreementCreate.relatedParty[2].characteristic[3].value";
		 
		 
	}
	
		
		public Renewal(int i){
	  agreementItem_id = "$.agreementCreate.agreementItem["+i+"].id";
	  agreementItem_itemDurationEndDateTime = "$.agreementCreate.agreementItem["+i+"].itemDuration.endDateTime";
	  agreementItem_itemDurationStartDateTime = "$.agreementCreate.agreementItem["+i+"].itemDuration.startDateTime";
	  agreementItem_itemDurationAmount = "$.agreementCreate.agreementItem["+i+"].itemDuration.quantity.amount";
	  agreementItem_itemDurationUnits = "$.agreementCreate.agreementItem["+i+"].itemDuration.quantity.units";

	  agreementItem_incentiveServiceCode = "$.agreementCreate.agreementItem["+i+"].incentiveServiceCode[0]";
	  agreementItem_itemType = "$.agreementCreate.agreementItem["+i+"].itemType";
	  agreementItem_incentiveAmount = "$.agreementCreate.agreementItem["+i+"].incentiveAmount";

	  agreementItem_installmentEndDateTime = "$.agreementCreate.agreementItem["+i+"].installment.installmentDuration.endDateTime";
	  agreementItem_installmentStartDateTime = "$.agreementCreate.agreementItem["+i+"].installment.installmentDuration.startDateTime";
	  agreementItem_installmentAmount = "$.agreementCreate.agreementItem["+i+"].installment.installmentDuration.quantity.amount";
	  agreementItem_installmentUnits = "$.agreementCreate.agreementItem["+i+"].installment.installmentDuration.quantity.units";

	  agreementItem_installmentLeftNum = "$.agreementCreate.agreementItem["+i+"].installment.characteristic[0].name";
	  agreementItem_installmentLeftNumValue = "$.agreementCreate.agreementItem["+i+"].installment.characteristic[0].value";

	  agreementItem_installmentAppliedNum = "$.agreementCreate.agreementItem["+i+"].installment.characteristic[1].name";
	  agreementItem_installmentAppliedNumValue = "$.agreementCreate.agreementItem["+i+"].installment.characteristic[1].value";

	  agreementItem_installmentAppliedAmt = "$.agreementCreate.agreementItem["+i+"].installment.characteristic[2].name";
	  agreementItem_installmentAppliedAmtValue = "$.agreementCreate.agreementItem["+i+"].installment.characteristic[2].value";

	  agreementItem_termOrConditionMinRatePlan = "$.agreementCreate.agreementItem["+i+"].termOrCondition.characteristic[0].name";
	  agreementItem_termOrConditionMinRatePlanValue = "$.agreementCreate.agreementItem["+i+"].termOrCondition.characteristic[0].value";

	  agreementItem_termOrConditionMinFeature = "$.agreementCreate.agreementItem["+i+"].termOrCondition.characteristic[1].name";
	  agreementItem_termOrConditionMinFeatureValue = "$.agreementCreate.agreementItem["+i+"].termOrCondition.characteristic[1].value";

	  agreementItem_termOrConditionMinCombined = "$.agreementCreate.agreementItem["+i+"].termOrCondition.characteristic[2].name";
	  agreementItem_termOrConditionMinCombinedValue = "$.agreementCreate.agreementItem["+i+"].termOrCondition.characteristic[2].value";

	  agreementItem_termOrConditionCommitmentServiceCd = "$.agreementCreate.agreementItem["+i+"].termOrCondition.characteristic[3].name";
	  agreementItem_termOrConditionCommitmentServiceCdValue = "$.agreementCreate.agreementItem["+i+"].termOrCondition.characteristic[3].value";

	  agreementItem_termOrConditionAutoTopupCommitmentInd = "$.agreementCreate.agreementItem["+i+"].termOrCondition.characteristic[4].name";
	  agreementItem_termOrConditionAutoTopupCommitmentIndValue = "$.agreementCreate.agreementItem["+i+"].termOrCondition.characteristic[4].value";

	  agreementItem_tax = "$.agreementCreate.agreementItem["+i+"].tax";
	  agreementItem_taxPaymentMethodCode = "$.agreementCreate.agreementItem["+i+"].tax.taxPaymentMethodCode";
	  agreementItem_taxPaymentMechanismCode = "$.agreementCreate.agreementItem["+i+"].tax.taxPaymentMechanismCode";
	  agreementItem_taxPaymentChannelCode = "$.agreementCreate.agreementItem["+i+"].tax.taxPaymentChannelCode";
	  agreementItem_taxProvinceCode = "$.agreementCreate.agreementItem["+i+"].tax.taxProvinceCode";
	  agreementItem_taxCategory = "$.agreementCreate.agreementItem["+i+"].tax.taxRate[0].taxCategory";
	  agreementItem_taxRate = "$.agreementCreate.agreementItem["+i+"].tax.taxRate[0].taxRate";
	  agreementItem_taxAmountValue = "$.agreementCreate.agreementItem["+i+"].tax.taxRate[0].taxAmount.value";
	  agreementItem_taxAmountUnit = "$.agreementCreate.agreementItem["+i+"].tax.taxRate[0].taxAmount.unit";

	  agreementItem_product = "$.agreementCreate.agreementItem["+i+"].product";
	  agreementItem_productSerialNumber = "$.agreementCreate.agreementItem["+i+"].product[0].productSerialNumber";
	  agreementItem_productSpecificationid = "$.agreementCreate.agreementItem["+i+"].product[0].productSpecification.id";
	  agreementItem_productSpecificationCategoryCode = "$.agreementCreate.agreementItem["+i+"].product[0].productSpecification.categoryCode";
	  agreementItem_productSpecificationLocale1 = "$.agreementCreate.agreementItem["+i+"].product[0].productSpecification.description[0].locale";
	  agreementItem_productSpecificationDesc1 = "$.agreementCreate.agreementItem["+i+"].product[0].productSpecification.description[0].description";
	  agreementItem_productSpecificationLocale2 = "$.agreementCreate.agreementItem["+i+"].product[0].productSpecification.description[1].locale";
	  agreementItem_productSpecificationDesc2 = "$.agreementCreate.agreementItem["+i+"].product[0].productSpecification.description[1].description";

	  agreementItem_productSpecificationTypeCode = "$.agreementCreate.agreementItem["+i+"].product[0].productSpecification.typeCode";
	  agreementItem_productPriceValue = "$.agreementCreate.agreementItem["+i+"].product[0].productPrice.price.dutyFreeAmount.value";
	  agreementItem_productPriceUnit = "$.agreementCreate.agreementItem["+i+"].product[0].productPrice.price.dutyFreeAmount.unit";
	  agreementItem_productCharacteristicName = "$.agreementCreate.agreementItem["+i+"].product[0].productCharacteristic[0].name";
	  agreementItem_productCharacteristicValue = "$.agreementCreate.agreementItem["+i+"].product[0].productCharacteristic[0].value";

	  agreementItem_promotionid = "$.agreementCreate.agreementItem["+i+"].promotion[0].id";
	  agreementItem_promotionPerspectiveDate = "$.agreementCreate.agreementItem["+i+"].promotion[0].perspectiveDate";

	  agreementItem_productOfferingid = "$.agreementCreate.agreementItem["+i+"].productOffering.id";
	  agreementItem_productOfferingRedeemedOfferContextCode = "$.agreementCreate.agreementItem["+i+"].productOffering.characteristic[0].name";
	  agreementItem_productOfferingRedeemedOfferContextCodeValue = "$.agreementCreate.agreementItem["+i+"].productOffering.characteristic[0].value";

	  agreementItem_productOfferingOfferTierCd = "$.agreementCreate.agreementItem["+i+"].productOffering.characteristic[1].name";
	  agreementItem_productOfferingOfferTierCdValue = "$.agreementCreate.agreementItem["+i+"].productOffering.characteristic[1].value";

	  agreementItem_productOfferingOfferTierCapAmt = "$.agreementCreate.agreementItem["+i+"].productOffering.characteristic[2].name";
	  agreementItem_productOfferingOfferTierCapAmtValue = "$.agreementCreate.agreementItem["+i+"].productOffering.characteristic[2].value";

	  agreementItem_productOfferingDataCommitmentInd = "$.agreementCreate.agreementItem["+i+"].productOffering.characteristic[3].name";
	  agreementItem_productOfferingDataCommitmentIndValue = "$.agreementCreate.agreementItem["+i+"].productOffering.characteristic[3].value";

	  agreementItem_productOfferingContractEnforcedPlanInd = "$.agreementCreate.agreementItem["+i+"].productOffering.characteristic[4].name";
	  agreementItem_productOfferingContractEnforcedPlanIndValue = "$.agreementCreate.agreementItem["+i+"].productOffering.characteristic[4].value";

	  agreementItem_productOfferingPerspectiveDate = "$.agreementCreate.agreementItem["+i+"].productOffering.perspectiveDate";
	  agreementItem_productOfferingSourceSystemId = "$.agreementCreate.agreementItem["+i+"].productOffering.sourceSystemId";

	  agreementItem_relatedPartyId = "$.agreementCreate.agreementItem["+i+"].relatedParty[0].id";
	  agreementItem_relatedPartyRole = "$.agreementCreate.agreementItem["+i+"].relatedParty[0].role";
	  agreementItem_relatedPartySourceSystemId = "$.agreementCreate.agreementItem["+i+"].relatedParty[0].characteristic[0].name";
		
		}
		
		public Renewal(String obj, int i){
		payment="$.payment";
		paymentAmt="$.payment["+i+"].amount.value";
		paymentUnit="$.payment["+i+"].amount.unit";
		payment_paymentMachanism="$.payment["+i+"].paymentMechanism";
		payment_relatedParty="$.payment["+i+"].relatedParty[0]";
		payment_relatedPartyId="$.payment["+i+"].relatedParty[0].id";
		payment_relatedPartyRole="$.payment["+i+"].relatedParty[0].role";
		
		payment_FinanceCommitmentItemId="$.payment["+i+"].relatedParty[0].characteristic[0].name";
		payment_FinanceCommitmentItemValue="$.payment["+i+"].relatedParty[0].characteristic[0].value";
		
		payment_ChargeType="$.payment["+i+"].relatedParty[0].characteristic[1].name";
		payment_ChargeTypeValue="$.payment["+i+"].relatedParty[0].characteristic[1].value";
		
		payment_ItemType="$.payment["+i+"].relatedParty[0].characteristic[2].name";
		payment_ItemTypeValue="$.payment["+i+"].relatedParty[0].characteristic[2].value";
		
		payment_waive_adjCode="$.payment["+i+"].waive[0].adjustmentCode";
		payment_waive_reasonCode="$.payment["+i+"].waive[0].reasonCode";
		payment_waive_amt="$.payment["+i+"].waive[0].amount.value";
		}
	  	


}
