package com.telus.rcms.jsonPathLibrary;

public class UpdateItem {

	public static String itemUpdate_id = null, itemUpdate_itemDurationEndDateTime = null,
			itemUpdate_itemDurationStartDateTime = null, itemUpdate_itemDurationAmount = null,
			itemUpdate_itemDurationUnits = null, itemUpdate_incentiveServiceCode = null, itemUpdate_itemType = null,
			itemUpdate_incentiveAmount = null, itemUpdate_installmentEndDateTime = null,
			itemUpdate_installmentStartDateTime = null, itemUpdate_installmentAmount = null,
			itemUpdate_installmentUnits = null, itemUpdate_installmentLeftNum = null,
			itemUpdate_installmentLeftNumValue = null, itemUpdate_installmentAppliedNum = null,
			itemUpdate_installmentAppliedNumValue = null, itemUpdate_installmentAppliedAmt = null,
			itemUpdate_installmentAppliedAmtValue = null, itemUpdate_termOrConditionMinRatePlan = null,
			itemUpdate_termOrConditionMinRatePlanValue = null, itemUpdate_termOrConditionMinFeature = null,
			itemUpdate_termOrConditionMinFeatureValue = null, itemUpdate_termOrConditionMinCombined = null,
			itemUpdate_termOrConditionMinCombinedValue = null, itemUpdate_termOrConditionCommitmentServiceCd = null,
			itemUpdate_termOrConditionCommitmentServiceCdValue = null,
			itemUpdate_termOrConditionAutoTopupCommitmentInd = null,
			itemUpdate_termOrConditionAutoTopupCommitmentIndValue = null, itemUpdate_tax = null,
			itemUpdate_taxPaymentMethodCode = null, itemUpdate_taxPaymentMechanismCode = null,
			itemUpdate_taxPaymentChannelCode = null, itemUpdate_taxProvinceCode = null, itemUpdate_taxCategory = null,
			itemUpdate_taxRate = null, itemUpdate_taxAmountValue = null, itemUpdate_taxAmountUnit = null,
			itemUpdate_product = null, itemUpdate_productSerialNumber = null, itemUpdate_productSpecificationid = null,
			itemUpdate_productSpecificationCategoryCode = null, itemUpdate_productSpecificationLocale1 = null,
			itemUpdate_productSpecificationDesc1 = null, itemUpdate_productSpecificationLocale2 = null,
			itemUpdate_productSpecificationDesc2 = null, itemUpdate_productSpecificationTypeCode = null,
			itemUpdate_productPriceValue = null, itemUpdate_productPriceUnit = null,
			itemUpdate_productCharacteristicName = null, itemUpdate_productCharacteristicValue = null,
			itemUpdate_promotionid = null, itemUpdate_promotionPerspectiveDate = null,
			itemUpdate_productOfferingid = null, itemUpdate_productOfferingRedeemedOfferContextCode = null,
			itemUpdate_productOfferingRedeemedOfferContextCodeValue = null,
			itemUpdate_productOfferingOfferTierCd = null, itemUpdate_productOfferingOfferTierCdValue = null,
			itemUpdate_productOfferingOfferTierCapAmt = null, itemUpdate_productOfferingOfferTierCapAmtValue = null,
			itemUpdate_productOfferingDataCommitmentInd = null, itemUpdate_productOfferingDataCommitmentIndValue = null,
			itemUpdate_productOfferingContractEnforcedPlanInd = null,
			itemUpdate_productOfferingContractEnforcedPlanIndValue = null,
			itemUpdate_productOfferingPerspectiveDate = null, itemUpdate_productOfferingSourceSystemId = null,
			itemUpdate_relatedPartyId = null, itemUpdate_relatedPartyRole = null,
			itemUpdate_relatedPartySourceSystemId = null;

	public static String relatedParty_Accid = null, relatedParty_Accrole = null, relatedParty_brandid = null,
			relatedParty_brandidValue = null, relatedParty_AccTypeCode = null, relatedParty_AccTypeCodeValue = null,
			relatedParty_AccSubTypeCode = null, relatedParty_AccSubTypeCodeValue = null, relatedParty_Oriid = null,
			relatedParty_Orirole = null, relatedParty_Transactionid = null, relatedParty_TransactionidValue = null,
			relatedParty_ChnlOrgid = null, relatedParty_ChnlOrgValue = null, relatedParty_SalesRepid = null,
			relatedParty_SalesRepidValue = null, relatedParty_TeamMemberid = null,
			relatedParty_TeamMemberidValue = null, relatedParty_Subid = null, relatedParty_Subrole = null,
			relatedParty_MarketProvince = null, relatedParty_MarketProvinceValue = null,
			relatedParty_HomeProvince = null, relatedParty_HomeProvinceValue = null, relatedParty_SubscriberNum = null,
			relatedParty_SubscriberNumValue = null, relatedParty_ComboRatePlanInd = null,
			relatedParty_ComboRatePlanIndValue = null;

	public UpdateItem(int i) {

		itemUpdate_id = "$.itemUpdate[" + i + "].id";
		itemUpdate_itemDurationEndDateTime = "$.itemUpdate[" + i + "].itemDuration.endDateTime";
		itemUpdate_itemDurationStartDateTime = "$.itemUpdate[" + i + "].itemDuration.startDateTime";
		itemUpdate_itemDurationAmount = "$.itemUpdate[" + i + "].itemDuration.quantity.amount";
		itemUpdate_itemDurationUnits = "$.itemUpdate[" + i + "].itemDuration.quantity.units";

		itemUpdate_itemType = "$.itemUpdate[" + i + "].itemType";
		itemUpdate_incentiveServiceCode = "$.itemUpdate[" + i + "].incentiveServiceCode[0]";
		itemUpdate_incentiveAmount = "$.itemUpdate[" + i + "].incentiveAmount";

		itemUpdate_installmentEndDateTime = "$.itemUpdate[" + i + "].installment.installmentDuration.endDateTime";
		itemUpdate_installmentStartDateTime = "$.itemUpdate[" + i + "].installment.installmentDuration.startDateTime";
		itemUpdate_installmentAmount = "$.itemUpdate[" + i + "].installment.installmentDuration.quantity.amount";
		itemUpdate_installmentUnits = "$.itemUpdate[" + i + "].installment.installmentDuration.quantity.units";

		itemUpdate_installmentLeftNum = "$.itemUpdate[" + i + "].installment.characteristic[0].name";
		itemUpdate_installmentLeftNumValue = "$.itemUpdate[" + i + "].installment.characteristic[0].value";

		itemUpdate_installmentAppliedNum = "$.itemUpdate[" + i + "].installment.characteristic[1].name";
		itemUpdate_installmentAppliedNumValue = "$.itemUpdate[" + i + "].installment.characteristic[1].value";

		itemUpdate_installmentAppliedAmt = "$.itemUpdate[" + i + "].installment.characteristic[2].name";
		itemUpdate_installmentAppliedAmtValue = "$.itemUpdate[" + i + "].installment.characteristic[2].value";

		itemUpdate_termOrConditionMinRatePlan = "$.itemUpdate[" + i + "].termOrCondition.characteristic[0].name";
		itemUpdate_termOrConditionMinRatePlanValue = "$.itemUpdate[" + i + "].termOrCondition.characteristic[0].value";

		itemUpdate_termOrConditionMinFeature = "$.itemUpdate[" + i + "].termOrCondition.characteristic[1].name";
		itemUpdate_termOrConditionMinFeatureValue = "$.itemUpdate[" + i + "].termOrCondition.characteristic[1].value";

		itemUpdate_termOrConditionMinCombined = "$.itemUpdate[" + i + "].termOrCondition.characteristic[2].name";
		itemUpdate_termOrConditionMinCombinedValue = "$.itemUpdate[" + i + "].termOrCondition.characteristic[2].value";

		itemUpdate_termOrConditionCommitmentServiceCd = "$.itemUpdate[" + i
				+ "].termOrCondition.characteristic[3].name";
		itemUpdate_termOrConditionCommitmentServiceCdValue = "$.itemUpdate[" + i
				+ "].termOrCondition.characteristic[3].value";

		itemUpdate_termOrConditionAutoTopupCommitmentInd = "$.itemUpdate[" + i
				+ "].termOrCondition.characteristic[4].name";
		itemUpdate_termOrConditionAutoTopupCommitmentIndValue = "$.itemUpdate[" + i
				+ "].termOrCondition.characteristic[4].value";

		itemUpdate_tax = "$.itemUpdate[" + i + "].tax";
		itemUpdate_taxPaymentMethodCode = "$.itemUpdate[" + i + "].tax.taxPaymentMethodCode";
		itemUpdate_taxPaymentMechanismCode = "$.itemUpdate[" + i + "].tax.taxPaymentMechanismCode";
		itemUpdate_taxPaymentChannelCode = "$.itemUpdate[" + i + "].tax.taxPaymentChannelCode";
		itemUpdate_taxProvinceCode = "$.itemUpdate[" + i + "].tax.taxProvinceCode";
		itemUpdate_taxCategory = "$.itemUpdate[" + i + "].tax.taxRate[0].taxCategory";
		itemUpdate_taxRate = "$.itemUpdate[" + i + "].tax.taxRate[0].taxRate";
		itemUpdate_taxAmountValue = "$.itemUpdate[" + i + "].tax.taxRate[0].taxAmount.value";
		itemUpdate_taxAmountUnit = "$.itemUpdate[" + i + "].tax.taxRate[0].taxAmount.unit";

		itemUpdate_product = "$.itemUpdate[" + i + "].product";
		itemUpdate_productSerialNumber = "$.itemUpdate[" + i + "].product[0].productSerialNumber";
		itemUpdate_productSpecificationid = "$.itemUpdate[" + i + "].product[0].productSpecification.id";
		itemUpdate_productSpecificationCategoryCode = "$.itemUpdate[" + i
				+ "].product[0].productSpecification.categoryCode";
		itemUpdate_productSpecificationLocale1 = "$.itemUpdate[" + i
				+ "].product[0].productSpecification.description[0].locale";
		itemUpdate_productSpecificationDesc1 = "$.itemUpdate[" + i
				+ "].product[0].productSpecification.description[0].description";
		itemUpdate_productSpecificationLocale2 = "$.itemUpdate[" + i
				+ "].product[0].productSpecification.description[1].locale";
		itemUpdate_productSpecificationDesc2 = "$.itemUpdate[" + i
				+ "].product[0].productSpecification.description[1].description";

		itemUpdate_productSpecificationTypeCode = "$.itemUpdate[" + i + "].product[0].productSpecification.typeCode";
		itemUpdate_productPriceValue = "$.itemUpdate[" + i + "].product[0].productPrice.price.dutyFreeAmount.value";
		itemUpdate_productPriceUnit = "$.itemUpdate[" + i + "].product[0].productPrice.price.dutyFreeAmount.unit";
		itemUpdate_productCharacteristicName = "$.itemUpdate[" + i + "].product[0].productCharacteristic[0].name";
		itemUpdate_productCharacteristicValue = "$.itemUpdate[" + i + "].product[0].productCharacteristic[0].value";

		itemUpdate_promotionid = "$.itemUpdate[" + i + "].promotion[0].id";
		itemUpdate_promotionPerspectiveDate = "$.itemUpdate[" + i + "].promotion[0].perspectiveDate";

		itemUpdate_productOfferingid = "$.itemUpdate[" + i + "].productOffering.id";
		itemUpdate_productOfferingRedeemedOfferContextCode = "$.itemUpdate[" + i
				+ "].productOffering.characteristic[0].name";
		itemUpdate_productOfferingRedeemedOfferContextCodeValue = "$.itemUpdate[" + i
				+ "].productOffering.characteristic[0].value";

		itemUpdate_productOfferingOfferTierCd = "$.itemUpdate[" + i + "].productOffering.characteristic[1].name";
		itemUpdate_productOfferingOfferTierCdValue = "$.itemUpdate[" + i + "].productOffering.characteristic[1].value";

		itemUpdate_productOfferingOfferTierCapAmt = "$.itemUpdate[" + i + "].productOffering.characteristic[2].name";
		itemUpdate_productOfferingOfferTierCapAmtValue = "$.itemUpdate[" + i
				+ "].productOffering.characteristic[2].value";

		itemUpdate_productOfferingDataCommitmentInd = "$.itemUpdate[" + i + "].productOffering.characteristic[3].name";
		itemUpdate_productOfferingDataCommitmentIndValue = "$.itemUpdate[" + i
				+ "].productOffering.characteristic[3].value";

		itemUpdate_productOfferingContractEnforcedPlanInd = "$.itemUpdate[" + i
				+ "].productOffering.characteristic[4].name";
		itemUpdate_productOfferingContractEnforcedPlanIndValue = "$.itemUpdate[" + i
				+ "].productOffering.characteristic[4].value";

		itemUpdate_productOfferingPerspectiveDate = "$.itemUpdate[" + i + "].productOffering.perspectiveDate";
		itemUpdate_productOfferingSourceSystemId = "$.itemUpdate[" + i + "].productOffering.sourceSystemId";

		itemUpdate_relatedPartyId = "$.itemUpdate[" + i + "].relatedParty[0].id";
		itemUpdate_relatedPartyRole = "$.itemUpdate[" + i + "].relatedParty[0].role";
		itemUpdate_relatedPartySourceSystemId = "$.itemUpdate[" + i + "].relatedParty[0].characteristic[0].name";

		relatedParty_Accid = "$.relatedParty[0].id";
		relatedParty_Accrole = "$.relatedParty[0].role";
		relatedParty_brandid = "$.relatedParty[0].characteristic[0].name";
		relatedParty_brandidValue = "$.relatedParty[0].characteristic[0].value";
		relatedParty_AccTypeCode = "$.relatedParty[0].characteristic[1].name";
		relatedParty_AccTypeCodeValue = "$.relatedParty[0].characteristic[1].value";
		relatedParty_AccSubTypeCode = "$.relatedParty[0].characteristic[2].name";
		relatedParty_AccSubTypeCodeValue = "$.relatedParty[0].characteristic[2].value";

		relatedParty_Oriid = "$.relatedParty[1].id";
		relatedParty_Orirole = "$.relatedParty[1].role";
		relatedParty_Transactionid = "$.relatedParty[1].characteristic[0].name";
		relatedParty_TransactionidValue = "$.relatedParty[1].characteristic[0].value";
		relatedParty_ChnlOrgid = "$.relatedParty[1].characteristic[1].name";
		relatedParty_ChnlOrgValue = "$.relatedParty[1].characteristic[1].value";
		relatedParty_SalesRepid = "$.relatedParty[1].characteristic[2].name";
		relatedParty_SalesRepidValue = "$.relatedParty[1].characteristic[2].value";
		relatedParty_TeamMemberid = "$.relatedParty[1].characteristic[3].name";
		relatedParty_TeamMemberidValue = "$.relatedParty[1].characteristic[3].value";

		relatedParty_Subid = "$.relatedParty[2].id";
		relatedParty_Subrole = "$.relatedParty[2].role";
		relatedParty_MarketProvince = "$.relatedParty[2].characteristic[0].name";
		relatedParty_MarketProvinceValue = "$.relatedParty[2].characteristic[0].value";
		relatedParty_HomeProvince = "$.relatedParty[2].characteristic[1].name";
		relatedParty_HomeProvinceValue = "$.relatedParty[2].characteristic[1].value";
		relatedParty_SubscriberNum = "$.relatedParty[2].characteristic[2].name";
		relatedParty_SubscriberNumValue = "$.relatedParty[2].characteristic[2].value";
		relatedParty_ComboRatePlanInd = "$.relatedParty[2].characteristic[3].name";
		relatedParty_ComboRatePlanIndValue = "$.relatedParty[2].characteristic[3].value";
	}
}
