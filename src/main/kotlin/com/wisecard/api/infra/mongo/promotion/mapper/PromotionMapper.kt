package com.wisecard.api.infra.mongo.promotion.mapper

import com.wisecard.api.domain.promotion.model.Promotion
import com.wisecard.api.infra.mongo.promotion.entity.PromotionEntity

object PromotionMapper {
    fun toEntity(promotion: Promotion): PromotionEntity = PromotionEntity(
        cardCompany = promotion.cardCompany,
        description = promotion.description,
        imgUrl = promotion.imgUrl,
        url = promotion.url,
        startDate = promotion.startDate,
        endDate = promotion.endDate
    )

    fun toDomain(entity: PromotionEntity): Promotion = Promotion(
        cardCompany = entity.cardCompany,
        description = entity.description,
        imgUrl = entity.imgUrl,
        url = entity.url,
        startDate = entity.startDate,
        endDate = entity.endDate
    )
}