package com.wisecard.api.domain.promotion.model

import com.wisecard.api.infra.mongo.card.constant.CardCompany
import java.time.Instant

data class Promotion(
    val cardCompany: CardCompany,
    val description: String,
    val imgUrl: String?,
    val url: String?,
    val startDate: Instant?,
    val endDate: Instant?
)