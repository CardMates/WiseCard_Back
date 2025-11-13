package com.wisecard.api.domain.card.model

import com.wisecard.api.infra.mongo.card.constant.CardCompany
import com.wisecard.api.infra.mongo.card.constant.CardType
import com.wisecard.api.infra.mongo.card.constant.ChannelType

data class CardBenefit(
    val cardId: Int,
    val cardCompany: CardCompany,
    val cardName: String,
    val imgUrl: String,
    val cardType: CardType,
    val benefits: List<Benefit>
)

data class Benefit(
    val discounts: List<DiscountBenefit> = emptyList(),
    val points: List<PointBenefit> = emptyList(),
    val cashbacks: List<CashbackBenefit> = emptyList(),
    val categories: List<String> = emptyList(),
    val targets: List<String> = emptyList(),
    val summary: String
)

data class DiscountBenefit(
    val rate: Double?,
    val amount: Int?,
    val minimumAmount: Int?,
    val benefitLimit: Int?,
    val minimumSpending: Int?,
    val channel: ChannelType
)

data class PointBenefit(
    val name: String,
    val amount: Int?,
    val rate: Double?,
    val minimumAmount: Int?,
    val benefitLimit: Int?,
    val minimumSpending: Int?,
    val channel: ChannelType
)

data class CashbackBenefit(
    val rate: Double?,
    val amount: Int?,
    val minimumAmount: Int?,
    val benefitLimit: Int?,
    val minimumSpending: Int?,
    val channel: ChannelType
)