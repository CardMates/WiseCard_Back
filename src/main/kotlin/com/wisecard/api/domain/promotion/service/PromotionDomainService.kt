package com.wisecard.api.domain.promotion.service


import com.wisecard.api.domain.promotion.model.Promotion
import com.wisecard.api.domain.promotion.repository.PromotionRepository
import org.springframework.stereotype.Service

@Service
class PromotionDomainService(
    private val promotionRepository: PromotionRepository
) {
    fun savePromotions(promotions: List<Promotion>) {
        promotionRepository.saveAll(promotions)
    }

    fun getAllPromotions(): List<Promotion> {
        return promotionRepository.findAll()
    }

    fun deleteAllPromotions() {
        promotionRepository.deleteAll()
    }
}