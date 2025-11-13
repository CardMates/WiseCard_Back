package com.wisecard.api.application.promotion

import com.wisecard.api.application.promotion.dto.PromotionResponse
import com.wisecard.api.application.promotion.dto.toResponseDto
import com.wisecard.api.domain.promotion.service.PromotionDomainService
import com.wisecard.api.infra.mongo.card.constant.CardCompany
import org.springframework.stereotype.Service

@Service
class PromotionService(
    private val promotionDomainService: PromotionDomainService,
) {
    fun getPromotions(cardCompany: CardCompany?): List<PromotionResponse> {
        val promotions = promotionDomainService.getAllPromotions()

        val filtered = promotions.filter { promotion ->
            (cardCompany == null || promotion.cardCompany == cardCompany)
        }
        return filtered.map { it.toResponseDto() }
    }
}