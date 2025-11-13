package com.wisecard.api.infra.mongo.card.entity

import com.wisecard.api.infra.mongo.card.constant.CardCompany
import com.wisecard.api.infra.mongo.card.constant.CardType
import com.wisecard.api.infra.mongo.card.constant.ChannelType
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "card_benefit")
data class CardBenefitEntity(
    @Id
    val cardId: Int,
    val cardCompany: CardCompany,
    val cardName: String,
    val imgUrl: String,
    val cardType: CardType,
    val benefits: List<BenefitEntity>
)

data class BenefitEntity(
    val discounts: List<DiscountBenefitEntity> = emptyList(),
    val points: List<PointBenefitEntity> = emptyList(),
    val cashbacks: List<CashbackBenefitEntity> = emptyList(),
    val categories: List<String> = emptyList(),
    val targets: List<String> = emptyList(),
    val summary: String
)

data class DiscountBenefitEntity(
    val rate: Double? = null,
    val amount: Int? = null,
    val minimumAmount: Int? = null,
    val benefitLimit: Int? = null,
    val minimumSpending: Int? = null,
    val channel: ChannelType
)

data class PointBenefitEntity(
    val name: String,
    val amount: Int? = null,
    val rate: Double? = null,
    val minimumAmount: Int? = null,
    val benefitLimit: Int? = null,
    val minimumSpending: Int? = null,
    val channel: ChannelType
)

data class CashbackBenefitEntity(
    val rate: Double? = null,
    val amount: Int? = null,
    val minimumAmount: Int? = null,
    val benefitLimit: Int? = null,
    val minimumSpending: Int? = null,
    val channel: ChannelType
)