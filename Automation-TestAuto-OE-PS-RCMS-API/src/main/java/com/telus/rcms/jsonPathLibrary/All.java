package com.telus.rcms.jsonPathLibrary;

public class All {

	/**
	 * agreementDuration
	 */
	// TC01
	public String AgreementDurationEndDateTime = "$.agreementDuration.endDateTime";
	public String AgreementDurationStartDateTime = "$.agreementDuration.startDateTime";
	public String AgreementDurationAmount = "$.agreementDuration.quantity.amount";
	public String AgreementDurationUnits = "$.agreementDuration.quantity.units";

	/**
	 * agreementItem
	 */
	// 1
	
	public String agreementItem_id = "$.agreementItem[0].id";
	public String agreementItem_itemDurationEndDateTime = "$.agreementItem[0].itemDuration.endDateTime";
	public String agreementItem_itemDurationStartDateTime = "$.agreementItem[0].itemDuration.startDateTime";
	public String agreementItem_itemDurationAmount = "$.agreementItem[0].itemDuration.quantity.amount";
	public String agreementItem_itemDurationUnits = "$.agreementItem[0].itemDuration.quantity.units";

	public String agreementItem_incentiveServiceCode = "$.agreementItem[0].incentiveServiceCode[0]";
	public String agreementItem_itemType = "$.agreementItem[0].itemType";
	public String agreementItem_incentiveAmount = "$.agreementItem[0].incentiveAmount";

	public String agreementItem_installmentEndDateTime = "$.agreementItem[0].installment.installmentDuration.endDateTime";
	public String agreementItem_installmentStartDateTime = "$.agreementItem[0].installment.installmentDuration.startDateTime";
	public String agreementItem_installmentAmount = "$.agreementItem[0].installment.installmentDuration.quantity.amount";
	public String agreementItem_installmentUnits = "$.agreementItem[0].installment.installmentDuration.quantity.units";

	public String agreementItem_installmentLeftNum = "$.agreementItem[0].installment.characteristic[0].name";
	public String agreementItem_installmentLeftNumValue = "$.agreementItem[0].installment.characteristic[0].value";

	public String agreementItem_installmentAppliedNum = "$.agreementItem[0].installment.characteristic[1].name";
	public String agreementItem_installmentAppliedNumValue = "$.agreementItem[0].installment.characteristic[1].value";

	public String agreementItem_installmentAppliedAmt = "$.agreementItem[0].installment.characteristic[2].name";
	public String agreementItem_installmentAppliedAmtValue = "$.agreementItem[0].installment.characteristic[2].value";

	public String agreementItem_termOrConditionMinRatePlan = "$.agreementItem[0].termOrCondition.characteristic[0].name";
	public String agreementItem_termOrConditionMinRatePlanValue = "$.agreementItem[0].termOrCondition.characteristic[0].value";

	public String agreementItem_termOrConditionMinFeature = "$.agreementItem[0].termOrCondition.characteristic[1].name";
	public String agreementItem_termOrConditionMinFeatureValue = "$.agreementItem[0].termOrCondition.characteristic[1].value";

	public String agreementItem_termOrConditionMinCombined = "$.agreementItem[0].termOrCondition.characteristic[2].name";
	public String agreementItem_termOrConditionMinCombinedValue = "$.agreementItem[0].termOrCondition.characteristic[2].value";

	public String agreementItem_termOrConditionCommitmentServiceCd = "$.agreementItem[0].termOrCondition.characteristic[3].name";
	public String agreementItem_termOrConditionCommitmentServiceCdValue = "$.agreementItem[0].termOrCondition.characteristic[3].value";

	public String agreementItem_termOrConditionAutoTopupCommitmentInd = "$.agreementItem[0].termOrCondition.characteristic[4].name";
	public String agreementItem_termOrConditionAutoTopupCommitmentIndValue = "$.agreementItem[0].termOrCondition.characteristic[4].value";

	public String agreementItem_tax = "$.agreementItem[0].tax";
	public String agreementItem_taxPaymentMethodCode = "$.agreementItem[0].tax.taxPaymentMethodCode";
	public String agreementItem_taxPaymentMechanismCode = "$.agreementItem[0].tax.taxPaymentMechanismCode";
	public String agreementItem_taxPaymentChannelCode = "$.agreementItem[0].tax.taxPaymentChannelCode";
	public String agreementItem_taxProvinceCode = "$.agreementItem[0].tax.taxProvinceCode";
	public String agreementItem_taxCategory = "$.agreementItem[0].tax.taxRate[0].taxCategory";
	public String agreementItem_taxRate = "$.agreementItem[0].tax.taxRate[0].taxRate";
	public String agreementItem_taxAmountValue = "$.agreementItem[0].tax.taxRate[0].taxAmount.value";
	public String agreementItem_taxAmountUnit = "$.agreementItem[0].tax.taxRate[0].taxAmount.unit";

	public String agreementItem_product = "$.agreementItem[0].product";
	public String agreementItem_productSerialNumber = "$.agreementItem[0].product[0].productSerialNumber";
	public String agreementItem_productSpecificationid = "$.agreementItem[0].product[0].productSpecification.id";
	public String agreementItem_productSpecificationCategoryCode = "$.agreementItem[0].product[0].productSpecification.categoryCode";
	public String agreementItem_productSpecificationLocale1 = "$.agreementItem[0].product[0].productSpecification.description[0].locale";
	public String agreementItem_productSpecificationDesc1 = "$.agreementItem[0].product[0].productSpecification.description[0].description";
	public String agreementItem_productSpecificationLocale2 = "$.agreementItem[0].product[0].productSpecification.description[1].locale";
	public String agreementItem_productSpecificationDesc2 = "$.agreementItem[0].product[0].productSpecification.description[1].description";

	public String agreementItem_productSpecificationTypeCode = "$.agreementItem[0].product[0].productSpecification.typeCode";
	public String agreementItem_productPriceValue = "$.agreementItem[0].product[0].productPrice.price.dutyFreeAmount.value";
	public String agreementItem_productPriceUnit = "$.agreementItem[0].product[0].productPrice.price.dutyFreeAmount.unit";
	public String agreementItem_productCharacteristicName = "$.agreementItem[0].product[0].productCharacteristic[0].name";
	public String agreementItem_productCharacteristicValue = "$.agreementItem[0].product[0].productCharacteristic[0].value";

	public String agreementItem_promotionid = "$.agreementItem[0].promotion[0].id";
	public String agreementItem_promotionPerspectiveDate = "$.agreementItem[0].promotion[0].perspectiveDate";

	public String agreementItem_productOfferingid = "$.agreementItem[0].productOffering.id";
	public String agreementItem_productOfferingRedeemedOfferContextCode = "$.agreementItem[0].productOffering.characteristic[0].name";
	public String agreementItem_productOfferingRedeemedOfferContextCodeValue = "$.agreementItem[0].productOffering.characteristic[0].value";

	public String agreementItem_productOfferingOfferTierCd = "$.agreementItem[0].productOffering.characteristic[1].name";
	public String agreementItem_productOfferingOfferTierCdValue = "$.agreementItem[0].productOffering.characteristic[1].value";

	public String agreementItem_productOfferingOfferTierCapAmt = "$.agreementItem[0].productOffering.characteristic[2].name";
	public String agreementItem_productOfferingOfferTierCapAmtValue = "$.agreementItem[0].productOffering.characteristic[2].value";

	public String agreementItem_productOfferingDataCommitmentInd = "$.agreementItem[0].productOffering.characteristic[3].name";
	public String agreementItem_productOfferingDataCommitmentIndValue = "$.agreementItem[0].productOffering.characteristic[3].value";

	public String agreementItem_productOfferingContractEnforcedPlanInd = "$.agreementItem[0].productOffering.characteristic[4].name";
	public String agreementItem_productOfferingContractEnforcedPlanIndValue = "$.agreementItem[0].productOffering.characteristic[4].value";

	public String agreementItem_productOfferingPerspectiveDate = "$.agreementItem[0].productOffering.perspectiveDate";
	public String agreementItem_productOfferingSourceSystemId = "$.agreementItem[0].productOffering.sourceSystemId";

	public String agreementItem_relatedPartyId = "$.agreementItem[0].relatedParty[0].id";
	public String agreementItem_relatedPartyRole = "$.agreementItem[0].relatedParty[0].role";
	public String agreementItem_relatedPartySourceSystemId = "$.agreementItem[0].relatedParty[0].characteristic[0].name";

	// 2
	public String agreementItem1_id = "$.agreementItem[1].id";
	public String agreementItem1_itemDurationEndDateTime = "$.agreementItem[1].itemDuration.endDateTime";
	public String agreementItem1_itemDurationStartDateTime = "$.agreementItem[1].itemDuration.startDateTime";
	public String agreementItem1_itemDurationAmount = "$.agreementItem[1].itemDuration.quantity.amount";
	public String agreementItem1_itemDurationUnits = "$.agreementItem[1].itemDuration.quantity.units";

	public String agreementItem1_incentiveServiceCode = "$.agreementItem[1].incentiveServiceCode[0]";
	public String agreementItem1_itemType = "$.agreementItem[1].itemType";
	public String agreementItem1_incentiveAmount = "$.agreementItem[1].incentiveAmount";

	public String agreementItem1_installmentEndDateTime = "$.agreementItem[1].installment.installmentDuration.endDateTime";
	public String agreementItem1_installmentStartDateTime = "$.agreementItem[1].installment.installmentDuration.startDateTime";
	public String agreementItem1_installmentAmount = "$.agreementItem[1].installment.installmentDuration.quantity.amount";
	public String agreementItem1_installmentUnits = "$.agreementItem[1].installment.installmentDuration.quantity.units";

	public String agreementItem1_installmentLeftNum = "$.agreementItem[1].installment.characteristic[0].name";
	public String agreementItem1_installmentLeftNumValue = "$.agreementItem[1].installment.characteristic[0].value";

	public String agreementItem1_installmentAppliedNum = "$.agreementItem[1].installment.characteristic[1].name";
	public String agreementItem1_installmentAppliedNumValue = "$.agreementItem[1].installment.characteristic[1].value";

	public String agreementItem1_installmentAppliedAmt = "$.agreementItem[1].installment.characteristic[2].name";
	public String agreementItem1_installmentAppliedAmtValue = "$.agreementItem[1].installment.characteristic[2].value";

	public String agreementItem1_termOrConditionMinRatePlan = "$.agreementItem[1].termOrCondition.characteristic[0].name";
	public String agreementItem1_termOrConditionMinRatePlanValue = "$.agreementItem[1].termOrCondition.characteristic[0].value";

	public String agreementItem1_termOrConditionMinFeature = "$.agreementItem[1].termOrCondition.characteristic[1].name";
	public String agreementItem1_termOrConditionMinFeatureValue = "$.agreementItem[1].termOrCondition.characteristic[1].value";

	public String agreementItem1_termOrConditionMinCombined = "$.agreementItem[1].termOrCondition.characteristic[2].name";
	public String agreementItem1_termOrConditionMinCombinedValue = "$.agreementItem[1].termOrCondition.characteristic[2].value";

	public String agreementItem1_termOrConditionCommitmentServiceCd = "$.agreementItem[1].termOrCondition.characteristic[3].name";
	public String agreementItem1_termOrConditionCommitmentServiceCdValue = "$.agreementItem[1].termOrCondition.characteristic[3].value";

	public String agreementItem1_termOrConditionAutoTopupCommitmentInd = "$.agreementItem[1].termOrCondition.characteristic[4].name";
	public String agreementItem1_termOrConditionAutoTopupCommitmentIndValue = "$.agreementItem[1].termOrCondition.characteristic[4].value";

	public String agreementItem1_tax = "$.agreementItem[1].tax";
	public String agreementItem1_taxPaymentMethodCode = "$.agreementItem[1].tax.taxPaymentMethodCode";
	public String agreementItem1_taxPaymentMechanismCode = "$.agreementItem[1].tax.taxPaymentMechanismCode";
	public String agreementItem1_taxPaymentChannelCode = "$.agreementItem[1].tax.taxPaymentChannelCode";
	public String agreementItem1_taxProvinceCode = "$.agreementItem[1].tax.taxProvinceCode";
	public String agreementItem1_taxCategory = "$.agreementItem[1].tax.taxRate[0].taxCategory";
	public String agreementItem1_taxRate = "$.agreementItem[1].tax.taxRate[0].taxRate";
	public String agreementItem1_taxAmountValue = "$.agreementItem[1].tax.taxRate[0].taxAmount.value";
	public String agreementItem1_taxAmountUnit = "$.agreementItem[1].tax.taxRate[0].taxAmount.unit";

	public String agreementItem1_product = "$.agreementItem[1].product";
	public String agreementItem1_productSerialNumber = "$.agreementItem[1].product[0].productSerialNumber";
	public String agreementItem1_productSpecificationid = "$.agreementItem[1].product[0].productSpecification.id";
	public String agreementItem1_productSpecificationCategoryCode = "$.agreementItem[1].product[0].productSpecification.categoryCode";
	public String agreementItem1_productSpecificationLocale1 = "$.agreementItem[1].product[0].productSpecification.description[0].locale";
	public String agreementItem1_productSpecificationDesc1 = "$.agreementItem[1].product[0].productSpecification.description[0].description";
	public String agreementItem1_productSpecificationLocale2 = "$.agreementItem[1].product[0].productSpecification.description[1].locale";
	public String agreementItem1_productSpecificationDesc2 = "$.agreementItem[1].product[0].productSpecification.description[1].description";

	public String agreementItem1_productSpecificationTypeCode = "$.agreementItem[1].product[0].productSpecification.typeCode";
	public String agreementItem1_productPriceValue = "$.agreementItem[1].product[0].productPrice.price.dutyFreeAmount.value";
	public String agreementItem1_productPriceUnit = "$.agreementItem[1].product[0].productPrice.price.dutyFreeAmount.unit";
	public String agreementItem1_productCharacteristicName = "$.agreementItem[1].product[0].productCharacteristic[0].name";
	public String agreementItem1_productCharacteristicValue = "$.agreementItem[1].product[0].productCharacteristic[0].value";

	public String agreementItem1_promotionid = "$.agreementItem[1].promotion[0].id";
	public String agreementItem1_promotionPerspectiveDate = "$.agreementItem[1].promotion[0].perspectiveDate";

	public String agreementItem1_productOfferingid = "$.agreementItem[1].productOffering.id";
	public String agreementItem1_productOfferingRedeemedOfferContextCode = "$.agreementItem[1].productOffering.characteristic[0].name";
	public String agreementItem1_productOfferingRedeemedOfferContextCodeValue = "$.agreementItem[1].productOffering.characteristic[0].value";

	public String agreementItem1_productOfferingOfferTierCd = "$.agreementItem[1].productOffering.characteristic[1].name";
	public String agreementItem1_productOfferingOfferTierCdValue = "$.agreementItem[1].productOffering.characteristic[1].value";

	public String agreementItem1_productOfferingOfferTierCapAmt = "$.agreementItem[1].productOffering.characteristic[2].name";
	public String agreementItem1_productOfferingOfferTierCapAmtValue = "$.agreementItem[1].productOffering.characteristic[2].value";

	public String agreementItem1_productOfferingDataCommitmentInd = "$.agreementItem[1].productOffering.characteristic[3].name";
	public String agreementItem1_productOfferingDataCommitmentIndValue = "$.agreementItem[1].productOffering.characteristic[3].value";

	public String agreementItem1_productOfferingContractEnforcedPlanInd = "$.agreementItem[1].productOffering.characteristic[4].name";
	public String agreementItem1_productOfferingContractEnforcedPlanIndValue = "$.agreementItem[1].productOffering.characteristic[4].value";

	public String agreementItem1_productOfferingPerspectiveDate = "$.agreementItem[1].productOffering.perspectiveDate";
	public String agreementItem1_productOfferingSourceSystemId = "$.agreementItem[1].productOffering.sourceSystemId";

	// adding jsonpath for TC02

	/**
	 * relatedParty
	 */
	// TC01
	public String relatedParty_Accid = "$.relatedParty[0].id";
	public String relatedParty_Accrole = "$.relatedParty[0].role";
	public String relatedParty_brandid = "$.relatedParty[0].characteristic[0].name";
	public String relatedParty_brandidValue = "$.relatedParty[0].characteristic[0].value";
	public String relatedParty_AccTypeCode = "$.relatedParty[0].characteristic[1].name";
	public String relatedParty_AccTypeCodeValue = "$.relatedParty[0].characteristic[1].value";
	public String relatedParty_AccSubTypeCode = "$.relatedParty[0].characteristic[2].name";
	public String relatedParty_AccSubTypeCodeValue = "$.relatedParty[0].characteristic[2].value";

	public String relatedParty_Oriid = "$.relatedParty[1].id";
	public String relatedParty_Orirole = "$.relatedParty[1].role";
	public String relatedParty_Transactionid = "$.relatedParty[1].characteristic[0].name";
	public String relatedParty_TransactionidValue = "$.relatedParty[1].characteristic[0].value";
	public String relatedParty_ChnlOrgid = "$.relatedParty[1].characteristic[1].name";
	public String relatedParty_ChnlOrgValue = "$.relatedParty[1].characteristic[1].value";
	public String relatedParty_SalesRepid = "$.relatedParty[1].characteristic[2].name";
	public String relatedParty_SalesRepidValue = "$.relatedParty[1].characteristic[2].value";
	public String relatedParty_TeamMemberid = "$.relatedParty[1].characteristic[3].name";
	public String relatedParty_TeamMemberidValue = "$.relatedParty[1].characteristic[3].value";

	public String relatedParty_Subid = "$.relatedParty[2].id";
	public String relatedParty_Subrole = "$.relatedParty[2].role";
	public String relatedParty_MarketProvince = "$.relatedParty[2].characteristic[0].name";
	public String relatedParty_MarketProvinceValue = "$.relatedParty[2].characteristic[0].value";
	public String relatedParty_HomeProvince = "$.relatedParty[2].characteristic[1].name";
	public String relatedParty_HomeProvinceValue = "$.relatedParty[2].characteristic[1].value";
	public String relatedParty_SubscriberNum = "$.relatedParty[2].characteristic[2].name";
	public String relatedParty_SubscriberNumValue = "$.relatedParty[2].characteristic[2].value";
	public String relatedParty_ComboRatePlanInd = "$.relatedParty[2].characteristic[3].name";
	public String relatedParty_ComboRatePlanIndValue = "$.relatedParty[2].characteristic[3].value";

}
