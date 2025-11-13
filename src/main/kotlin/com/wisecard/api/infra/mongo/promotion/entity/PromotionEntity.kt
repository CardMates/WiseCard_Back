package com.wisecard.api.infra.mongo.promotion.entity

import com.wisecard.api.infra.mongo.card.constant.CardCompany
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "promotion")
data class PromotionEntity(
    @Id
    val id: String? = null,
    val cardCompany: CardCompany,
    val description: String,
    val imgUrl: String? = null,
    val url: String? = null,
    val startDate: Instant? = null,
    val endDate: Instant? = null,
)