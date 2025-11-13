package com.wisecard.api.api.promotion

import com.wisecard.api.application.promotion.PromotionService
import com.wisecard.api.application.promotion.dto.PromotionResponse
import com.wisecard.api.infra.mongo.card.constant.CardCompany
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PromotionController(
    private val promotionService: PromotionService
) {
    @GetMapping(PromotionApi.V1.BASE_URI)
    fun getPromotions(
        @RequestParam(required = false) cardCompany: CardCompany?,
    ):List<PromotionResponse> {
        return promotionService.getPromotions(cardCompany)
    }
}