package com.telus.rcms.jsonPathLibrary;

public class GetRewardCommitmentNew {

	public static String AgreementDurationEndDateTime=null, AgreementDurationStartDateTime=null, AgreementDurationAmount=null, AgreementDurationUnits=null;
	
	public static String relatedParty_Accid=null, relatedParty_Accrole=null, relatedParty_brandid=null, relatedParty_brandidValue=null, relatedParty_AccTypeCode=null, relatedParty_AccTypeCodeValue=null, relatedParty_AccSubTypeCode=null, relatedParty_AccSubTypeCodeValue=null;    
	
	public static String agreementItem_id =null,  agreementItem_itemDurationEndDateTime =null,  agreementItem_itemDurationStartDateTime =null,  agreementItem_itemDurationAmount =null,  agreementItem_itemDurationUnits = null, agreementItem_incentiveServiceCode =null,  agreementItem_itemType =null,  agreementItem_incentiveAmount = null, agreementItem_installmentEndDateTime =null,  agreementItem_installmentStartDateTime =null,  agreementItem_installmentAmount =null,  agreementItem_installmentUnits = null, agreementItem_installmentLeftNum =null,  agreementItem_installmentLeftNumValue = null, agreementItem_installmentAppliedNum =null,  agreementItem_installmentAppliedNumValue = null, agreementItem_installmentAppliedAmt =null,  agreementItem_installmentAppliedAmtValue = null, agreementItem_termOrConditionMinRatePlan =null,  agreementItem_termOrConditionMinRatePlanValue = null, agreementItem_termOrConditionMinFeature =null,  agreementItem_termOrConditionMinFeatureValue = null, agreementItem_termOrConditionMinCombined =null,  agreementItem_termOrConditionMinCombinedValue = null, agreementItem_termOrConditionCommitmentServiceCd =null,  agreementItem_termOrConditionCommitmentServiceCdValue = null, agreementItem_termOrConditionAutoTopupCommitmentInd =null,  agreementItem_termOrConditionAutoTopupCommitmentIndValue = null, agreementItem_tax =null,  agreementItem_taxPaymentMethodCode =null,  agreementItem_taxPaymentMechanismCode =null,  agreementItem_taxPaymentChannelCode =null,  agreementItem_taxProvinceCode =null,  agreementItem_taxCategory =null,  agreementItem_taxRate =null,  agreementItem_taxAmountValue =null,  agreementItem_taxAmountUnit = null, agreementItem_product =null,  agreementItem_productSerialNumber =null,  agreementItem_productSpecificationid =null,  agreementItem_productSpecificationCategoryCode =null,  agreementItem_productSpecificationLocale1 =null,  agreementItem_productSpecificationDesc1 =null,  agreementItem_productSpecificationLocale2 =null,  agreementItem_productSpecificationDesc2 = null, agreementItem_productSpecificationTypeCode =null,  agreementItem_productPriceValue =null,  agreementItem_productPriceUnit =null,  agreementItem_productCharacteristicName =null,  agreementItem_productCharacteristicValue = null, agreementItem_promotionid =null,  agreementItem_promotionPerspectiveDate = null, agreementItem_productOfferingid =null,  agreementItem_productOfferingRedeemedOfferContextCode =null,  agreementItem_productOfferingRedeemedOfferContextCodeValue = null, agreementItem_productOfferingOfferTierCd =null,  agreementItem_productOfferingOfferTierCdValue = null, agreementItem_productOfferingOfferTierCapAmt =null,  agreementItem_productOfferingOfferTierCapAmtValue = null, agreementItem_productOfferingDataCommitmentInd =null,  agreementItem_productOfferingDataCommitmentIndValue = null, agreementItem_productOfferingContractEnforcedPlanInd =null,  agreementItem_productOfferingContractEnforcedPlanIndValue = null, agreementItem_productOfferingPerspectiveDate =null,  agreementItem_productOfferingSourceSystemId = null, agreementItem_relatedPartyId =null,  agreementItem_relatedPartyRole =null,  agreementItem_relatedPartySourceSystemId = null;
	

	public GetRewardCommitmentNew(int i){
		
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
	
	public GetRewardCommitmentNew(int i, int j){
		
	  agreementItem_id = "$.["+i+"].agreementItem["+j+"].id";
	  agreementItem_itemDurationEndDateTime = "$.["+i+"].agreementItem["+j+"].itemDuration.endDateTime";
	  agreementItem_itemDurationStartDateTime = "$.["+i+"].agreementItem["+j+"].itemDuration.startDateTime";
	  agreementItem_itemDurationAmount = "$.["+i+"].agreementItem["+j+"].itemDuration.quantity.amount";
	  agreementItem_itemDurationUnits = "$.["+i+"].agreementItem["+j+"].itemDuration.quantity.units";

	  agreementItem_incentiveServiceCode = "$.["+i+"].agreementItem["+j+"].incentiveServiceCode[0]";
	  agreementItem_itemType = "$.["+i+"].agreementItem["+j+"].itemType";
	  agreementItem_incentiveAmount = "$.["+i+"].agreementItem["+j+"].incentiveAmount";

	  agreementItem_installmentEndDateTime = "$.["+i+"].agreementItem["+j+"].installment.installmentDuration.endDateTime";
	  agreementItem_installmentStartDateTime = "$.["+i+"].agreementItem["+j+"].installment.installmentDuration.startDateTime";
	  agreementItem_installmentAmount = "$.["+i+"].agreementItem["+j+"].installment.installmentDuration.quantity.amount";
	  agreementItem_installmentUnits = "$.["+i+"].agreementItem["+j+"].installment.installmentDuration.quantity.units";

	  agreementItem_installmentLeftNum = "$.["+i+"].agreementItem["+j+"].installment.characteristic[0].name";
	  agreementItem_installmentLeftNumValue = "$.["+i+"].agreementItem["+j+"].installment.characteristic[0].value";

	  agreementItem_installmentAppliedNum = "$.["+i+"].agreementItem["+j+"].installment.characteristic[1].name";
	  agreementItem_installmentAppliedNumValue = "$.["+i+"].agreementItem["+j+"].installment.characteristic[1].value";

	  agreementItem_installmentAppliedAmt = "$.["+i+"].agreementItem["+j+"].installment.characteristic[2].name";
	  agreementItem_installmentAppliedAmtValue = "$.["+i+"].agreementItem["+j+"].installment.characteristic[2].value";

	  agreementItem_termOrConditionMinRatePlan = "$.["+i+"].agreementItem["+j+"].termOrCondition.characteristic[0].name";
	  agreementItem_termOrConditionMinRatePlanValue = "$.["+i+"].agreementItem["+j+"].termOrCondition.characteristic[0].value";

	  agreementItem_termOrConditionMinFeature = "$.["+i+"].agreementItem["+j+"].termOrCondition.characteristic[1].name";
	  agreementItem_termOrConditionMinFeatureValue = "$.["+i+"].agreementItem["+j+"].termOrCondition.characteristic[1].value";

	  agreementItem_termOrConditionMinCombined = "$.["+i+"].agreementItem["+j+"].termOrCondition.characteristic[2].name";
	  agreementItem_termOrConditionMinCombinedValue = "$.["+i+"].agreementItem["+j+"].termOrCondition.characteristic[2].value";

	  agreementItem_termOrConditionCommitmentServiceCd = "$.["+i+"].agreementItem["+j+"].termOrCondition.characteristic[3].name";
	  agreementItem_termOrConditionCommitmentServiceCdValue = "$.["+i+"].agreementItem["+j+"].termOrCondition.characteristic[3].value";

	  agreementItem_termOrConditionAutoTopupCommitmentInd = "$.["+i+"].agreementItem["+j+"].termOrCondition.characteristic[4].name";
	  agreementItem_termOrConditionAutoTopupCommitmentIndValue = "$.["+i+"].agreementItem["+j+"].termOrCondition.characteristic[4].value";

	  agreementItem_tax = "$.["+i+"].agreementItem["+j+"].tax";
	  agreementItem_taxPaymentMethodCode = "$.["+i+"].agreementItem["+j+"].tax.taxPaymentMethodCode";
	  agreementItem_taxPaymentMechanismCode = "$.["+i+"].agreementItem["+j+"].tax.taxPaymentMechanismCode";
	  agreementItem_taxPaymentChannelCode = "$.["+i+"].agreementItem["+j+"].tax.taxPaymentChannelCode";
	  agreementItem_taxProvinceCode = "$.["+i+"].agreementItem["+j+"].tax.taxProvinceCode";
	  agreementItem_taxCategory = "$.["+i+"].agreementItem["+j+"].tax.taxRate[0].taxCategory";
	  agreementItem_taxRate = "$.["+i+"].agreementItem["+j+"].tax.taxRate[0].taxRate";
	  agreementItem_taxAmountValue = "$.["+i+"].agreementItem["+j+"].tax.taxRate[0].taxAmount.value";
	  agreementItem_taxAmountUnit = "$.["+i+"].agreementItem["+j+"].tax.taxRate[0].taxAmount.unit";

	  agreementItem_product = "$.["+i+"].agreementItem["+j+"].product";
	  agreementItem_productSerialNumber = "$.["+i+"].agreementItem["+j+"].product[0].productSerialNumber";
	  agreementItem_productSpecificationid = "$.["+i+"].agreementItem["+j+"].product[0].productSpecification.id";
	  agreementItem_productSpecificationCategoryCode = "$.["+i+"].agreementItem["+j+"].product[0].productSpecification.categoryCode";
	  agreementItem_productSpecificationLocale1 = "$.["+i+"].agreementItem["+j+"].product[0].productSpecification.description[0].locale";
	  agreementItem_productSpecificationDesc1 = "$.["+i+"].agreementItem["+j+"].product[0].productSpecification.description[0].description";
	  agreementItem_productSpecificationLocale2 = "$.["+i+"].agreementItem["+j+"].product[0].productSpecification.description[1].locale";
	  agreementItem_productSpecificationDesc2 = "$.["+i+"].agreementItem["+j+"].product[0].productSpecification.description[1].description";

	  agreementItem_productSpecificationTypeCode = "$.["+i+"].agreementItem["+j+"].product[0].productSpecification.typeCode";
	  agreementItem_productPriceValue = "$.["+i+"].agreementItem["+j+"].product[0].productPrice.price.dutyFreeAmount.value";
	  agreementItem_productPriceUnit = "$.["+i+"].agreementItem["+j+"].product[0].productPrice.price.dutyFreeAmount.unit";
	  agreementItem_productCharacteristicName = "$.["+i+"].agreementItem["+j+"].product[0].productCharacteristic[0].name";
	  agreementItem_productCharacteristicValue = "$.["+i+"].agreementItem["+j+"].product[0].productCharacteristic[0].value";

	  agreementItem_promotionid = "$.["+i+"].agreementItem["+j+"].promotion[0].id";
	  agreementItem_promotionPerspectiveDate = "$.["+i+"].agreementItem["+j+"].promotion[0].perspectiveDate";

	  agreementItem_productOfferingid = "$.["+i+"].agreementItem["+j+"].productOffering.id";
	  agreementItem_productOfferingRedeemedOfferContextCode = "$.["+i+"].agreementItem["+j+"].productOffering.characteristic[0].name";
	  agreementItem_productOfferingRedeemedOfferContextCodeValue = "$.["+i+"].agreementItem["+j+"].productOffering.characteristic[0].value";

	  agreementItem_productOfferingOfferTierCd = "$.["+i+"].agreementItem["+j+"].productOffering.characteristic[1].name";
	  agreementItem_productOfferingOfferTierCdValue = "$.["+i+"].agreementItem["+j+"].productOffering.characteristic[1].value";

	  agreementItem_productOfferingOfferTierCapAmt = "$.["+i+"].agreementItem["+j+"].productOffering.characteristic[2].name";
	  agreementItem_productOfferingOfferTierCapAmtValue = "$.["+i+"].agreementItem["+j+"].productOffering.characteristic[2].value";

	  agreementItem_productOfferingDataCommitmentInd = "$.["+i+"].agreementItem["+j+"].productOffering.characteristic[3].name";
	  agreementItem_productOfferingDataCommitmentIndValue = "$.["+i+"].agreementItem["+j+"].productOffering.characteristic[3].value";

	  agreementItem_productOfferingContractEnforcedPlanInd = "$.["+i+"].agreementItem["+j+"].productOffering.characteristic[4].name";
	  agreementItem_productOfferingContractEnforcedPlanIndValue = "$.["+i+"].agreementItem["+j+"].productOffering.characteristic[4].value";

	  agreementItem_productOfferingPerspectiveDate = "$.["+i+"].agreementItem["+j+"].productOffering.perspectiveDate";
	  agreementItem_productOfferingSourceSystemId = "$.["+i+"].agreementItem["+j+"].productOffering.sourceSystemId";

	  agreementItem_relatedPartyId = "$.["+i+"].agreementItem["+j+"].relatedParty[0].id";
	  agreementItem_relatedPartyRole = "$.["+i+"].agreementItem["+j+"].relatedParty[0].role";
	  agreementItem_relatedPartySourceSystemId = "$.["+i+"].agreementItem["+j+"].relatedParty[0].characteristic[0].name";

	
	}
	
}
