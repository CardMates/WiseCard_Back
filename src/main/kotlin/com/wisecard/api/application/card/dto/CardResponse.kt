package com.wisecard.api.application.card.dto

import com.wisecard.api.domain.card.model.*

data class CardResponse(
    val cardId: Int,
    val cardCompany: String,
    val cardName: String,
    val imgUrl: String,
    val cardType: String,
    val benefits: List<BenefitResponseDto>
)

data class BenefitResponseDto(
    val summary: String,
    val discounts: List<DiscountBenefitResponseDto>,
    val points: List<PointBenefitResponseDto>,
    val cashbacks: List<CashbackBenefitResponseDto>,
    val categories: List<String>,
    val targets: List<String>
)

data class DiscountBenefitResponseDto(
    val rate: Double?,
    val amount: Int?,
    val minimumAmount: Int?,
    val benefitLimit: Int?,
    val minimumSpending: Int?,
    val channel: String
)

data class PointBenefitResponseDto(
    val name: String,
    val amount: Int?,
    val rate: Double?,
    val minimumAmount: Int?,
    val benefitLimit: Int?,
    val minimumSpending: Int?,
    val channel: String
)

data class CashbackBenefitResponseDto(
    val rate: Double?,
    val amount: Int?,
    val minimumAmount: Int?,
    val benefitLimit: Int?,
    val minimumSpending: Int?,
    val channel: String
)

fun CardBenefit.toResponseDto() = CardResponse(
    cardId = cardId,
    cardCompany = cardCompany.name,
    cardName = cardName,
    imgUrl = imgUrl,
    cardType = cardType.name,
    benefits = benefits.map { it.toResponseDto() }
)

fun Benefit.toResponseDto() = BenefitResponseDto(
    summary = summary,
    discounts = discounts.map { it.toResponseDto() },
    points = points.map { it.toResponseDto() },
    cashbacks = cashbacks.map { it.toResponseDto() },
    categories = categories,
    targets = targets
)

private fun DiscountBenefit.toResponseDto() = DiscountBenefitResponseDto(
    rate = rate,
    amount = amount,
    minimumAmount = minimumAmount,
    benefitLimit = benefitLimit,
    minimumSpending = minimumSpending,
    channel = channel.name
)

private fun PointBenefit.toResponseDto() = PointBenefitResponseDto(
    name = name,
    amount = amount,
    rate = rate,
    minimumAmount = minimumAmount,
    benefitLimit = benefitLimit,
    minimumSpending = minimumSpending,
    channel = channel.name
)

private fun CashbackBenefit.toResponseDto() = CashbackBenefitResponseDto(
    rate = rate,
    amount = amount,
    minimumAmount = minimumAmount,
    benefitLimit = benefitLimit,
    minimumSpending = minimumSpending,
    channel = channel.name
)