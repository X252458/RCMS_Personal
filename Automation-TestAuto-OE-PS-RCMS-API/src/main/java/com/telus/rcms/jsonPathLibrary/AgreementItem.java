package com.telus.rcms.jsonPathLibrary;

public class AgreementItem {
	
	public static String agreementItem_id =null,  agreementItem_itemDurationEndDateTime =null,  agreementItem_itemDurationStartDateTime =null,  agreementItem_itemDurationAmount =null,  agreementItem_itemDurationUnits = null, agreementItem_incentiveServiceCode =null,  agreementItem_itemType =null,  agreementItem_incentiveAmount = null, agreementItem_installmentEndDateTime =null,  agreementItem_installmentStartDateTime =null,  agreementItem_installmentAmount =null,  agreementItem_installmentUnits = null, agreementItem_installmentLeftNum =null,  agreementItem_installmentLeftNumValue = null, agreementItem_installmentAppliedNum =null,  agreementItem_installmentAppliedNumValue = null, agreementItem_installmentAppliedAmt =null,  agreementItem_installmentAppliedAmtValue = null, agreementItem_termOrConditionMinRatePlan =null,  agreementItem_termOrConditionMinRatePlanValue = null, agreementItem_termOrConditionMinFeature =null,  agreementItem_termOrConditionMinFeatureValue = null, agreementItem_termOrConditionMinCombined =null,  agreementItem_termOrConditionMinCombinedValue = null, agreementItem_termOrConditionCommitmentServiceCd =null,  agreementItem_termOrConditionCommitmentServiceCdValue = null, agreementItem_termOrConditionAutoTopupCommitmentInd =null,  agreementItem_termOrConditionAutoTopupCommitmentIndValue = null, agreementItem_tax =null,  agreementItem_taxPaymentMethodCode =null,  agreementItem_taxPaymentMechanismCode =null,  agreementItem_taxPaymentChannelCode =null,  agreementItem_taxProvinceCode =null,  agreementItem_taxCategory =null,  agreementItem_taxRate =null,  agreementItem_taxAmountValue =null,  agreementItem_taxAmountUnit = null, agreementItem_product =null,  agreementItem_productSerialNumber =null,  agreementItem_productSpecificationid =null,  agreementItem_productSpecificationCategoryCode =null,  agreementItem_productSpecificationLocale1 =null,  agreementItem_productSpecificationDesc1 =null,  agreementItem_productSpecificationLocale2 =null,  agreementItem_productSpecificationDesc2 = null, agreementItem_productSpecificationTypeCode =null,  agreementItem_productPriceValue =null,  agreementItem_productPriceUnit =null,  agreementItem_productCharacteristicName =null,  agreementItem_productCharacteristicValue = null, agreementItem_promotionid =null,  agreementItem_promotionPerspectiveDate = null, agreementItem_productOfferingid =null,  agreementItem_productOfferingRedeemedOfferContextCode =null,  agreementItem_productOfferingRedeemedOfferContextCodeValue = null, agreementItem_productOfferingOfferTierCd =null,  agreementItem_productOfferingOfferTierCdValue = null, agreementItem_productOfferingOfferTierCapAmt =null,  agreementItem_productOfferingOfferTierCapAmtValue = null, agreementItem_productOfferingDataCommitmentInd =null,  agreementItem_productOfferingDataCommitmentIndValue = null, agreementItem_productOfferingContractEnforcedPlanInd =null,  agreementItem_productOfferingContractEnforcedPlanIndValue = null, agreementItem_productOfferingPerspectiveDate =null,  agreementItem_productOfferingSourceSystemId = null, agreementItem_relatedPartyId =null,  agreementItem_relatedPartyRole =null,  agreementItem_relatedPartySourceSystemId = null;
	
	public AgreementItem(int i){
		  agreementItem_id = "$.agreementItem["+i+"].id";
		  agreementItem_itemDurationEndDateTime = "$.agreementItem["+i+"].itemDuration.endDateTime";
		  agreementItem_itemDurationStartDateTime = "$.agreementItem["+i+"].itemDuration.startDateTime";
		  agreementItem_itemDurationAmount = "$.agreementItem["+i+"].itemDuration.quantity.amount";
		  agreementItem_itemDurationUnits = "$.agreementItem["+i+"].itemDuration.quantity.units";

		  agreementItem_incentiveServiceCode = "$.agreementItem["+i+"].incentiveServiceCode[0]";
		  agreementItem_itemType = "$.agreementItem["+i+"].itemType";
		  agreementItem_incentiveAmount = "$.agreementItem["+i+"].incentiveAmount";

		  agreementItem_installmentEndDateTime = "$.agreementItem["+i+"].installment.installmentDuration.endDateTime";
		  agreementItem_installmentStartDateTime = "$.agreementItem["+i+"].installment.installmentDuration.startDateTime";
		  agreementItem_installmentAmount = "$.agreementItem["+i+"].installment.installmentDuration.quantity.amount";
		  agreementItem_installmentUnits = "$.agreementItem["+i+"].installment.installmentDuration.quantity.units";

		  agreementItem_installmentLeftNum = "$.agreementItem["+i+"].installment.characteristic[0].name";
		  agreementItem_installmentLeftNumValue = "$.agreementItem["+i+"].installment.characteristic[0].value";

		  agreementItem_installmentAppliedNum = "$.agreementItem["+i+"].installment.characteristic[1].name";
		  agreementItem_installmentAppliedNumValue = "$.agreementItem["+i+"].installment.characteristic[1].value";

		  agreementItem_installmentAppliedAmt = "$.agreementItem["+i+"].installment.characteristic[2].name";
		  agreementItem_installmentAppliedAmtValue = "$.agreementItem["+i+"].installment.characteristic[2].value";

		  agreementItem_termOrConditionMinRatePlan = "$.agreementItem["+i+"].termOrCondition.characteristic[0].name";
		  agreementItem_termOrConditionMinRatePlanValue = "$.agreementItem["+i+"].termOrCondition.characteristic[0].value";

		  agreementItem_termOrConditionMinFeature = "$.agreementItem["+i+"].termOrCondition.characteristic[1].name";
		  agreementItem_termOrConditionMinFeatureValue = "$.agreementItem["+i+"].termOrCondition.characteristic[1].value";

		  agreementItem_termOrConditionMinCombined = "$.agreementItem["+i+"].termOrCondition.characteristic[2].name";
		  agreementItem_termOrConditionMinCombinedValue = "$.agreementItem["+i+"].termOrCondition.characteristic[2].value";

		  agreementItem_termOrConditionCommitmentServiceCd = "$.agreementItem["+i+"].termOrCondition.characteristic[3].name";
		  agreementItem_termOrConditionCommitmentServiceCdValue = "$.agreementItem["+i+"].termOrCondition.characteristic[3].value";

		  agreementItem_termOrConditionAutoTopupCommitmentInd = "$.agreementItem["+i+"].termOrCondition.characteristic[4].name";
		  agreementItem_termOrConditionAutoTopupCommitmentIndValue = "$.agreementItem["+i+"].termOrCondition.characteristic[4].value";

		  agreementItem_tax = "$.agreementItem["+i+"].tax";
		  agreementItem_taxPaymentMethodCode = "$.agreementItem["+i+"].tax.taxPaymentMethodCode";
		  agreementItem_taxPaymentMechanismCode = "$.agreementItem["+i+"].tax.taxPaymentMechanismCode";
		  agreementItem_taxPaymentChannelCode = "$.agreementItem["+i+"].tax.taxPaymentChannelCode";
		  agreementItem_taxProvinceCode = "$.agreementItem["+i+"].tax.taxProvinceCode";
		  agreementItem_taxCategory = "$.agreementItem["+i+"].tax.taxRate[0].taxCategory";
		  agreementItem_taxRate = "$.agreementItem["+i+"].tax.taxRate[0].taxRate";
		  agreementItem_taxAmountValue = "$.agreementItem["+i+"].tax.taxRate[0].taxAmount.value";
		  agreementItem_taxAmountUnit = "$.agreementItem["+i+"].tax.taxRate[0].taxAmount.unit";

		  agreementItem_product = "$.agreementItem["+i+"].product";
		  agreementItem_productSerialNumber = "$.agreementItem["+i+"].product[0].productSerialNumber";
		  agreementItem_productSpecificationid = "$.agreementItem["+i+"].product[0].productSpecification.id";
		  agreementItem_productSpecificationCategoryCode = "$.agreementItem["+i+"].product[0].productSpecification.categoryCode";
		  agreementItem_productSpecificationLocale1 = "$.agreementItem["+i+"].product[0].productSpecification.description[0].locale";
		  agreementItem_productSpecificationDesc1 = "$.agreementItem["+i+"].product[0].productSpecification.description[0].description";
		  agreementItem_productSpecificationLocale2 = "$.agreementItem["+i+"].product[0].productSpecification.description[1].locale";
		  agreementItem_productSpecificationDesc2 = "$.agreementItem["+i+"].product[0].productSpecification.description[1].description";

		  agreementItem_productSpecificationTypeCode = "$.agreementItem["+i+"].product[0].productSpecification.typeCode";
		  agreementItem_productPriceValue = "$.agreementItem["+i+"].product[0].productPrice.price.dutyFreeAmount.value";
		  agreementItem_productPriceUnit = "$.agreementItem["+i+"].product[0].productPrice.price.dutyFreeAmount.unit";
		  agreementItem_productCharacteristicName = "$.agreementItem["+i+"].product[0].productCharacteristic[0].name";
		  agreementItem_productCharacteristicValue = "$.agreementItem["+i+"].product[0].productCharacteristic[0].value";

		  agreementItem_promotionid = "$.agreementItem["+i+"].promotion[0].id";
		  agreementItem_promotionPerspectiveDate = "$.agreementItem["+i+"].promotion[0].perspectiveDate";

		  agreementItem_productOfferingid = "$.agreementItem["+i+"].productOffering.id";
		  agreementItem_productOfferingRedeemedOfferContextCode = "$.agreementItem["+i+"].productOffering.characteristic[0].name";
		  agreementItem_productOfferingRedeemedOfferContextCodeValue = "$.agreementItem["+i+"].productOffering.characteristic[0].value";

		  agreementItem_productOfferingOfferTierCd = "$.agreementItem["+i+"].productOffering.characteristic[1].name";
		  agreementItem_productOfferingOfferTierCdValue = "$.agreementItem["+i+"].productOffering.characteristic[1].value";

		  agreementItem_productOfferingOfferTierCapAmt = "$.agreementItem["+i+"].productOffering.characteristic[2].name";
		  agreementItem_productOfferingOfferTierCapAmtValue = "$.agreementItem["+i+"].productOffering.characteristic[2].value";

		  agreementItem_productOfferingDataCommitmentInd = "$.agreementItem["+i+"].productOffering.characteristic[3].name";
		  agreementItem_productOfferingDataCommitmentIndValue = "$.agreementItem["+i+"].productOffering.characteristic[3].value";

		  agreementItem_productOfferingContractEnforcedPlanInd = "$.agreementItem["+i+"].productOffering.characteristic[4].name";
		  agreementItem_productOfferingContractEnforcedPlanIndValue = "$.agreementItem["+i+"].productOffering.characteristic[4].value";

		  agreementItem_productOfferingPerspectiveDate = "$.agreementItem["+i+"].productOffering.perspectiveDate";
		  agreementItem_productOfferingSourceSystemId = "$.agreementItem["+i+"].productOffering.sourceSystemId";

		  agreementItem_relatedPartyId = "$.agreementItem["+i+"].relatedParty[0].id";
		  agreementItem_relatedPartyRole = "$.agreementItem["+i+"].relatedParty[0].role";
		  agreementItem_relatedPartySourceSystemId = "$.agreementItem["+i+"].relatedParty[0].characteristic[0].name";
	}
}
