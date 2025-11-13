package com.wisecard.api.application.promotion.dto

import com.wisecard.api.domain.promotion.model.Promotion
import com.wisecard.api.infra.mongo.card.constant.CardCompany
import java.time.Instant

data class PromotionResponse(
    val cardCompany: CardCompany,
    val description: String,
    val imgUrl: String?,
    val url: String?,
    val startDate: Instant?,
    val endDate: Instant?
)

fun Promotion.toResponseDto() = PromotionResponse(
    cardCompany = cardCompany,
    description = description,
    imgUrl = imgUrl,
    url = url,
    startDate = startDate,
    endDate = endDate
)