package com.wisecard.api.infra.mongo.card.mapper

import com.wisecard.api.domain.card.model.*
import com.wisecard.api.infra.mongo.card.entity.*

object CardBenefitMapper {
    fun toEntity(domain: CardBenefit): CardBenefitEntity =
        CardBenefitEntity(
            cardId = domain.cardId,
            cardCompany = domain.cardCompany,
            cardName = domain.cardName,
            imgUrl = domain.imgUrl,
            cardType = domain.cardType,
            benefits = domain.benefits.map {
                BenefitEntity(
                    discounts = it.discounts.map { d ->
                        DiscountBenefitEntity(
                            rate = d.rate,
                            amount = d.amount,
                            minimumAmount = d.minimumAmount,
                            benefitLimit = d.benefitLimit,
                            minimumSpending = d.minimumSpending,
                            channel = d.channel
                        )
                    },
                    points = it.points.map { p ->
                        PointBenefitEntity(
                            name = p.name,
                            amount = p.amount,
                            rate = p.rate,
                            minimumAmount = p.minimumAmount,
                            benefitLimit = p.benefitLimit,
                            minimumSpending = p.minimumSpending,
                            channel = p.channel
                        )
                    },
                    cashbacks = it.cashbacks.map { c ->
                        CashbackBenefitEntity(
                            rate = c.rate,
                            amount = c.amount,
                            minimumAmount = c.minimumAmount,
                            benefitLimit = c.benefitLimit,
                            minimumSpending = c.minimumSpending,
                            channel = c.channel
                        )
                    },
                    categories = it.categories,
                    targets = it.targets,
                    summary = it.summary
                )
            }
        )

    fun toDomain(entity: CardBenefitEntity): CardBenefit =
        CardBenefit(
            cardId = entity.cardId,
            cardCompany = entity.cardCompany,
            cardName = entity.cardName,
            imgUrl = entity.imgUrl,
            cardType = entity.cardType,
            benefits = entity.benefits.map {
                Benefit(
                    discounts = it.discounts.map { d ->
                        DiscountBenefit(
                            rate = d.rate,
                            amount = d.amount,
                            minimumAmount = d.minimumAmount,
                            benefitLimit = d.benefitLimit,
                            minimumSpending = d.minimumSpending,
                            channel = d.channel
                        )
                    },
                    points = it.points.map { p ->
                        PointBenefit(
                            name = p.name,
                            amount = p.amount,
                            rate = p.rate,
                            minimumAmount = p.minimumAmount,
                            benefitLimit = p.benefitLimit,
                            minimumSpending = p.minimumSpending,
                            channel = p.channel
                        )
                    },
                    cashbacks = it.cashbacks.map { c ->
                        CashbackBenefit(
                            rate = c.rate,
                            amount = c.amount,
                            minimumAmount = c.minimumAmount,
                            benefitLimit = c.benefitLimit,
                            minimumSpending = c.minimumSpending,
                            channel = c.channel
                        )
                    },
                    categories = it.categories,
                    targets = it.targets,
                    summary = it.summary
                )
            }
        )
}