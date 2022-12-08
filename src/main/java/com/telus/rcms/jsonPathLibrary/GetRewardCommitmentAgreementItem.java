package com.telus.rcms.jsonPathLibrary;

public class GetRewardCommitmentAgreementItem {
	
	public static String agreementItem_id =null,  agreementItem_itemDurationEndDateTime =null,  agreementItem_itemDurationStartDateTime =null,  agreementItem_itemDurationAmount =null,  agreementItem_itemDurationUnits = null, agreementItem_incentiveServiceCode =null,  agreementItem_itemType =null,  agreementItem_incentiveAmount = null, agreementItem_installmentEndDateTime =null,  agreementItem_installmentStartDateTime =null,  agreementItem_installmentAmount =null,  agreementItem_installmentUnits = null, agreementItem_installmentLeftNum =null,  agreementItem_installmentLeftNumValue = null, agreementItem_installmentAppliedNum =null,  agreementItem_installmentAppliedNumValue = null, agreementItem_installmentAppliedAmt =null,  agreementItem_installmentAppliedAmtValue = null, agreementItem_termOrConditionMinRatePlan =null,  agreementItem_termOrConditionMinRatePlanValue = null, agreementItem_termOrConditionMinFeature =null,  agreementItem_termOrConditionMinFeatureValue = null, agreementItem_termOrConditionMinCombined =null,  agreementItem_termOrConditionMinCombinedValue = null, agreementItem_termOrConditionCommitmentServiceCd =null,  agreementItem_termOrConditionCommitmentServiceCdValue = null, agreementItem_termOrConditionAutoTopupCommitmentInd =null,  agreementItem_termOrConditionAutoTopupCommitmentIndValue = null, agreementItem_tax =null,  agreementItem_taxPaymentMethodCode =null,  agreementItem_taxPaymentMechanismCode =null,  agreementItem_taxPaymentChannelCode =null,  agreementItem_taxProvinceCode =null,  agreementItem_taxCategory =null,  agreementItem_taxRate =null,  agreementItem_taxAmountValue =null,  agreementItem_taxAmountUnit = null, agreementItem_product =null,  agreementItem_productSerialNumber =null,  agreementItem_productSpecificationid =null,  agreementItem_productSpecificationCategoryCode =null,  agreementItem_productSpecificationLocale1 =null,  agreementItem_productSpecificationDesc1 =null,  agreementItem_productSpecificationLocale2 =null,  agreementItem_productSpecificationDesc2 = null, agreementItem_productSpecificationTypeCode =null,  agreementItem_productPriceValue =null,  agreementItem_productPriceUnit =null,  agreementItem_productCharacteristicName =null,  agreementItem_productCharacteristicValue = null, agreementItem_promotionid =null,  agreementItem_promotionPerspectiveDate = null, agreementItem_productOfferingid =null,  agreementItem_productOfferingRedeemedOfferContextCode =null,  agreementItem_productOfferingRedeemedOfferContextCodeValue = null, agreementItem_productOfferingOfferTierCd =null,  agreementItem_productOfferingOfferTierCdValue = null, agreementItem_productOfferingOfferTierCapAmt =null,  agreementItem_productOfferingOfferTierCapAmtValue = null, agreementItem_productOfferingDataCommitmentInd =null,  agreementItem_productOfferingDataCommitmentIndValue = null, agreementItem_productOfferingContractEnforcedPlanInd =null,  agreementItem_productOfferingContractEnforcedPlanIndValue = null, agreementItem_productOfferingPerspectiveDate =null,  agreementItem_productOfferingSourceSystemId = null, agreementItem_relatedPartyId =null,  agreementItem_relatedPartyRole =null,  agreementItem_relatedPartySourceSystemId = null;
	
	public GetRewardCommitmentAgreementItem(int j, int i){
		
		  agreementItem_id = "$.["+j+"].agreementItem["+i+"].id";
		  agreementItem_itemDurationEndDateTime = "$.["+j+"].agreementItem["+i+"].itemDuration.endDateTime";
		  agreementItem_itemDurationStartDateTime = "$.["+j+"].agreementItem["+i+"].itemDuration.startDateTime";
		  agreementItem_itemDurationAmount = "$.["+j+"].agreementItem["+i+"].itemDuration.quantity.amount";
		  agreementItem_itemDurationUnits = "$.["+j+"].agreementItem["+i+"].itemDuration.quantity.units";

		  agreementItem_incentiveServiceCode = "$.["+j+"].agreementItem["+i+"].incentiveServiceCode[0]";
		  agreementItem_itemType = "$.["+j+"].agreementItem["+i+"].itemType";
		  agreementItem_incentiveAmount = "$.["+j+"].agreementItem["+i+"].incentiveAmount";

		  agreementItem_installmentEndDateTime = "$.["+j+"].agreementItem["+i+"].installment.installmentDuration.endDateTime";
		  agreementItem_installmentStartDateTime = "$.["+j+"].agreementItem["+i+"].installment.installmentDuration.startDateTime";
		  agreementItem_installmentAmount = "$.["+j+"].agreementItem["+i+"].installment.installmentDuration.quantity.amount";
		  agreementItem_installmentUnits = "$.["+j+"].agreementItem["+i+"].installment.installmentDuration.quantity.units";

		  agreementItem_installmentLeftNum = "$.["+j+"].agreementItem["+i+"].installment.characteristic[0].name";
		  agreementItem_installmentLeftNumValue = "$.["+j+"].agreementItem["+i+"].installment.characteristic[0].value";

		  agreementItem_installmentAppliedNum = "$.["+j+"].agreementItem["+i+"].installment.characteristic[1].name";
		  agreementItem_installmentAppliedNumValue = "$.["+j+"].agreementItem["+i+"].installment.characteristic[1].value";

		  agreementItem_installmentAppliedAmt = "$.["+j+"].agreementItem["+i+"].installment.characteristic[2].name";
		  agreementItem_installmentAppliedAmtValue = "$.["+j+"].agreementItem["+i+"].installment.characteristic[2].value";

		  agreementItem_termOrConditionMinRatePlan = "$.["+j+"].agreementItem["+i+"].termOrCondition.characteristic[0].name";
		  agreementItem_termOrConditionMinRatePlanValue = "$.["+j+"].agreementItem["+i+"].termOrCondition.characteristic[0].value";

		  agreementItem_termOrConditionMinFeature = "$.["+j+"].agreementItem["+i+"].termOrCondition.characteristic[1].name";
		  agreementItem_termOrConditionMinFeatureValue = "$.["+j+"].agreementItem["+i+"].termOrCondition.characteristic[1].value";

		  agreementItem_termOrConditionMinCombined = "$.["+j+"].agreementItem["+i+"].termOrCondition.characteristic[2].name";
		  agreementItem_termOrConditionMinCombinedValue = "$.["+j+"].agreementItem["+i+"].termOrCondition.characteristic[2].value";

		  agreementItem_termOrConditionCommitmentServiceCd = "$.["+j+"].agreementItem["+i+"].termOrCondition.characteristic[3].name";
		  agreementItem_termOrConditionCommitmentServiceCdValue = "$.["+j+"].agreementItem["+i+"].termOrCondition.characteristic[3].value";

		  agreementItem_termOrConditionAutoTopupCommitmentInd = "$.["+j+"].agreementItem["+i+"].termOrCondition.characteristic[4].name";
		  agreementItem_termOrConditionAutoTopupCommitmentIndValue = "$.["+j+"].agreementItem["+i+"].termOrCondition.characteristic[4].value";

		  agreementItem_tax = "$.["+j+"].agreementItem["+i+"].tax";
		  agreementItem_taxPaymentMethodCode = "$.["+j+"].agreementItem["+i+"].tax.taxPaymentMethodCode";
		  agreementItem_taxPaymentMechanismCode = "$.["+j+"].agreementItem["+i+"].tax.taxPaymentMechanismCode";
		  agreementItem_taxPaymentChannelCode = "$.["+j+"].agreementItem["+i+"].tax.taxPaymentChannelCode";
		  agreementItem_taxProvinceCode = "$.["+j+"].agreementItem["+i+"].tax.taxProvinceCode";
		  agreementItem_taxCategory = "$.["+j+"].agreementItem["+i+"].tax.taxRate[0].taxCategory";
		  agreementItem_taxRate = "$.["+j+"].agreementItem["+i+"].tax.taxRate[0].taxRate";
		  agreementItem_taxAmountValue = "$.["+j+"].agreementItem["+i+"].tax.taxRate[0].taxAmount.value";
		  agreementItem_taxAmountUnit = "$.["+j+"].agreementItem["+i+"].tax.taxRate[0].taxAmount.unit";

		  agreementItem_product = "$.["+j+"].agreementItem["+i+"].product";
		  agreementItem_productSerialNumber = "$.["+j+"].agreementItem["+i+"].product[0].productSerialNumber";
		  agreementItem_productSpecificationid = "$.["+j+"].agreementItem["+i+"].product[0].productSpecification.id";
		  agreementItem_productSpecificationCategoryCode = "$.["+j+"].agreementItem["+i+"].product[0].productSpecification.categoryCode";
		  agreementItem_productSpecificationLocale1 = "$.["+j+"].agreementItem["+i+"].product[0].productSpecification.description[0].locale";
		  agreementItem_productSpecificationDesc1 = "$.["+j+"].agreementItem["+i+"].product[0].productSpecification.description[0].description";
		  agreementItem_productSpecificationLocale2 = "$.["+j+"].agreementItem["+i+"].product[0].productSpecification.description[1].locale";
		  agreementItem_productSpecificationDesc2 = "$.["+j+"].agreementItem["+i+"].product[0].productSpecification.description[1].description";

		  agreementItem_productSpecificationTypeCode = "$.["+j+"].agreementItem["+i+"].product[0].productSpecification.typeCode";
		  agreementItem_productPriceValue = "$.["+j+"].agreementItem["+i+"].product[0].productPrice.price.dutyFreeAmount.value";
		  agreementItem_productPriceUnit = "$.["+j+"].agreementItem["+i+"].product[0].productPrice.price.dutyFreeAmount.unit";
		  agreementItem_productCharacteristicName = "$.["+j+"].agreementItem["+i+"].product[0].productCharacteristic[0].name";
		  agreementItem_productCharacteristicValue = "$.["+j+"].agreementItem["+i+"].product[0].productCharacteristic[0].value";

		  agreementItem_promotionid = "$.["+j+"].agreementItem["+i+"].promotion[0].id";
		  agreementItem_promotionPerspectiveDate = "$.["+j+"].agreementItem["+i+"].promotion[0].perspectiveDate";

		  agreementItem_productOfferingid = "$.["+j+"].agreementItem["+i+"].productOffering.id";
		  agreementItem_productOfferingRedeemedOfferContextCode = "$.["+j+"].agreementItem["+i+"].productOffering.characteristic[0].name";
		  agreementItem_productOfferingRedeemedOfferContextCodeValue = "$.["+j+"].agreementItem["+i+"].productOffering.characteristic[0].value";

		  agreementItem_productOfferingOfferTierCd = "$.["+j+"].agreementItem["+i+"].productOffering.characteristic[1].name";
		  agreementItem_productOfferingOfferTierCdValue = "$.["+j+"].agreementItem["+i+"].productOffering.characteristic[1].value";

		  agreementItem_productOfferingOfferTierCapAmt = "$.["+j+"].agreementItem["+i+"].productOffering.characteristic[2].name";
		  agreementItem_productOfferingOfferTierCapAmtValue = "$.["+j+"].agreementItem["+i+"].productOffering.characteristic[2].value";

		  agreementItem_productOfferingDataCommitmentInd = "$.["+j+"].agreementItem["+i+"].productOffering.characteristic[3].name";
		  agreementItem_productOfferingDataCommitmentIndValue = "$.["+j+"].agreementItem["+i+"].productOffering.characteristic[3].value";

		  agreementItem_productOfferingContractEnforcedPlanInd = "$.["+j+"].agreementItem["+i+"].productOffering.characteristic[4].name";
		  agreementItem_productOfferingContractEnforcedPlanIndValue = "$.["+j+"].agreementItem["+i+"].productOffering.characteristic[4].value";

		  agreementItem_productOfferingPerspectiveDate = "$.["+j+"].agreementItem["+i+"].productOffering.perspectiveDate";
		  agreementItem_productOfferingSourceSystemId = "$.["+j+"].agreementItem["+i+"].productOffering.sourceSystemId";

		  agreementItem_relatedPartyId = "$.["+j+"].agreementItem["+i+"].relatedParty[0].id";
		  agreementItem_relatedPartyRole = "$.["+j+"].agreementItem["+i+"].relatedParty[0].role";
		  agreementItem_relatedPartySourceSystemId = "$.["+j+"].agreementItem["+i+"].relatedParty[0].characteristic[0].name";
	}
}
